/*
 * Copyright (c) 2018 NTT DATA INTELLILINK Corporation. All rights reserved.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */

package com.clustercontrol.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.clustercontrol.fault.HinemosUnknown;

/**
 * General Command Executor Class
 */
public class CommandExecutor {

	private static Log log = LogFactory.getLog(CommandExecutor.class);
	
	// コマンド実行時に子プロセスに引き渡す環境変数（key・value）
	private final Map<String, String> envMap = new HashMap<String, String>();

	// thread for command execution
	private final ExecutorService _commandExecutor;

	private final String[] _command;
	private final String _commandLine; //ログ出力用コマンド文字列

	private final Charset _charset;
	public static final Charset _defaultCharset = Charset.forName("UTF-8");

	private final long _timeout;
	private static final long _defaultTimeout = 30000;
	public static final long _disableTimeout = -1;

	private final int _bufferSize;
	public static final int _defaultBufferSize = 8120;

	public static final Object _runtimeExecLock = new Object();
	
	private final boolean _destroy;
	
	private final boolean _forceSigterm;

	private Process process = null;

	public CommandExecutor(String[] command) throws HinemosUnknown {
		this(command, _defaultCharset);
	}

	public CommandExecutor(String[] command, Charset charset) throws HinemosUnknown {
		this(command, charset, _defaultTimeout);
	}

	public CommandExecutor(String[] command, long timeout) throws HinemosUnknown {
		this(command, _defaultCharset, timeout);
	}

	public CommandExecutor(String[] command, Charset charset, long timeout) throws HinemosUnknown {
		this(command, charset, timeout, _defaultBufferSize, true);
	}

	public CommandExecutor(String[] command, Charset charset, long timeout, boolean destroy) throws HinemosUnknown {
		this(command, charset, timeout, _defaultBufferSize, destroy);
	}

	public CommandExecutor(String[] command, Charset charset, long timeout, int bufferSize) throws HinemosUnknown {
		this(command, charset, timeout, bufferSize, true);
	}

	public CommandExecutor(String[] command, Charset charset, long timeout, int bufferSize, boolean destroy)
			throws HinemosUnknown {
		this(command, charset, timeout, bufferSize, destroy, false);
	}

	public CommandExecutor(String[] command, Charset charset, long timeout, int bufferSize, boolean destroy,
			boolean forceSigterm) throws HinemosUnknown {
		this(new CommandExecutorParams()
				.setCommand(command)
				.setCharset(charset)
				.setTimeout(timeout)
				.setBufferSize(bufferSize)
				.setDestroy(destroy)
				.setForceSigterm(forceSigterm));
	}
	
	public CommandExecutor(CommandExecutorParams params) throws HinemosUnknown {
		this._command = params.getCommand();
		this._charset = params.getCharset();
		this._timeout = params.getTimeout();
		this._bufferSize = params.getBufferSize();
		this._destroy = params.getDestroy();
		this._forceSigterm = params.getForceSigterm();
		
		log.debug("initializing " + this);

		if (_command == null) {
			throw new NullPointerException("command is not defined : " + this);
		}

		StringBuilder commandStr = new StringBuilder();
		for (String arg : _command) {
			commandStr.append(' ');
			commandStr.append(arg);
		}
		this._commandLine = commandStr.substring(1); // 先頭の空白を取り除いて格納する

		if (_charset == null) {
			throw new NullPointerException("charset is not defined : " + this);
		}

		_commandExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
			private volatile int _count = 0;

			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "CommandExecutor-" + _count++);
			}
		});
	}
	
	@Override
	public String toString() {
		return this.getClass().getCanonicalName() + " [command = " + Arrays.toString(_command)
				+ ", charset = " + _charset
				+ ", timeout = " + _timeout
				+ ", bufferSize = " + _bufferSize
				+ ", forceSigterm = " + String.valueOf(_forceSigterm)
				+ "]";
	}

	public Process getProcess() {
		return this.process;
	}
	
	public void addEnvironment(String key, String value) {
		envMap.put(key, value);
	}
	
	public Process execute() throws HinemosUnknown {
		// workaround for JVM(Windows) Bug
		// Runtime#exec is not thread safe on Windows.
	
		try {
			synchronized (_runtimeExecLock) {
				ProcessBuilder pb = new ProcessBuilder(_command);
				// 子プロセスには環境変数"_JAVA_OPTIONS"は渡さない
				pb.environment().remove("_JAVA_OPTIONS");
				for (Map.Entry<String, String> entry : envMap.entrySet()) {
					pb.environment().put(entry.getKey(), entry.getValue());
				}
				process = pb.start();
			}
		} catch (Exception e) {
			log.warn("command executor failure. (command = " + _commandLine + ") " + e.getMessage(), e);
			throw new HinemosUnknown(e.getMessage());
		}
		return process;
	}

	public CommandResult getResult() {
		CommandExecuteTask task = new CommandExecuteTask(process, _charset, _timeout, _bufferSize, _destroy, _forceSigterm);
		Future<CommandResult> future = _commandExecutor.submit(task);
		log.debug("executing command. (command = " + _commandLine + ", timeout = " + _timeout + " [msec])");

		// receive result
		CommandResult ret = null;
		try {
			if (_timeout == _disableTimeout) {
				ret = future.get();
			} else {
				ret = future.get(_timeout, TimeUnit.MILLISECONDS);
			}

			log.debug("exit code : " + (ret != null ? ret.exitCode : null));
			log.debug("stdout : " + (ret != null ? ret.stdout : null));
			log.debug("stderr : " + (ret != null ? ret.stderr : null));
			log.debug("buffer discarded : " + (ret != null ? ret.bufferDiscarded : null));
		} catch (TimeoutException e) {
			log.info("command execution failure. (command = " + _commandLine + ") " + e.getMessage());
		} catch (Exception e) {
			log.warn("command execution failure. (command = " + _commandLine + ") " + e.getMessage(), e);
		} finally {
			// release thread pool
			log.debug("releasing command threads.");
			_commandExecutor.shutdownNow();
		}

		return ret;
	}

	/**
	 * Command Result Class
	 */
	public static class CommandResult {
		public final Integer exitCode;			// null when command timeout
		public final String stdout;				// null when command timeout
		public final String stderr;				// null when command timeout
		public final boolean bufferDiscarded;	// true when buffer is discarded

		public CommandResult(int exitCode, String stdout, String stderr, boolean bufferDiscarded) {
			this.exitCode = exitCode;
			this.stdout = stdout;
			this.stderr = stderr;
			this.bufferDiscarded = bufferDiscarded;
		}

		@Override
		public String toString() {
			return "CommandResult ["
					+ "exitCode=" + exitCode
					+ ", stdout=" + stdout
					+ ", stderr=" + stderr
					+ ", bufferDiscarded=" + bufferDiscarded
					+ "]";
		}
	}

	/**
	 * CommandExecutorのパラメータオブジェクトです。
	 */
	public static class CommandExecutorParams {
		private String[] command;
		private Charset charset = _defaultCharset;
		private long timeout = _defaultTimeout;
		private int bufferSize = _defaultBufferSize;
		private boolean destroy = true;
		private boolean forceSigterm = false;

		public CommandExecutorParams() {
		}

		/**
		 * このオブジェクトに設定されているcommandを返します。
		 */
		public String[] getCommand() {
			return command;
		}

		/**
		 * commandを設定します。
		 */
		public CommandExecutorParams setCommand(String[] command) {
			this.command = command;
			return this;
		}

		/**
		 * このオブジェクトに設定されているcharsetを返します。
		 */
		public Charset getCharset() {
			return charset;
		}

		/**
		 * charsetを設定します。
		 */
		public CommandExecutorParams setCharset(Charset charset) {
			this.charset = charset;
			return this;
		}

		/**
		 * このオブジェクトに設定されているtimeoutを返します。
		 */
		public long getTimeout() {
			return timeout;
		}

		/**
		 * timeoutを設定します。
		 */
		public CommandExecutorParams setTimeout(long timeout) {
			this.timeout = timeout;
			return this;
		}

		/**
		 * このオブジェクトに設定されているbufferSizeを返します。
		 */
		public int getBufferSize() {
			return bufferSize;
		}

		/**
		 * bufferSizeを設定します。
		 */
		public CommandExecutorParams setBufferSize(int bufferSize) {
			this.bufferSize = bufferSize;
			return this;
		}

		/**
		 * このオブジェクトに設定されているdestroyを返します。
		 */
		public boolean getDestroy() {
			return destroy;
		}

		/**
		 * destroyを設定します。
		 */
		public CommandExecutorParams setDestroy(boolean destroy) {
			this.destroy = destroy;
			return this;
		}

		/**
		 * このオブジェクトに設定されているforceSigtermを返します。
		 */
		public boolean getForceSigterm() {
			return forceSigterm;
		}

		/**
		 * forceSigtermを設定します。
		 */
		public CommandExecutorParams setForceSigterm(boolean forceSigterm) {
			this.forceSigterm = forceSigterm;
			return this;
		}

	}

	/**
	 * Command Execute Task
	 */
	public class CommandExecuteTask implements Callable<CommandResult> {

		// command timeout (receive timeout)
		private final long timeout;

		// maximun size of received string
		public final int bufferSize;

		// buffer discarded or not
		public boolean bufferDiscarded = false;

		// charset of receive string
		public final Charset charset;

		public final Process process;
		
		// true if destroy child process
		public final boolean destroy;

		// true if linux destroy child process force sigterm
		public final boolean forceSigterm;

		// thread for receive stdout and stderr
		private final ExecutorService _receiverService;

		public CommandExecuteTask(Process process, Charset charset, long timeout, int bufferSize) {
			this(process, charset, timeout, bufferSize, true);
		}

		public CommandExecuteTask(Process process, Charset charset, long timeout, int bufferSize, boolean destroy) {
			this(process, charset, timeout, bufferSize, destroy, false);
		}

		public CommandExecuteTask(Process process, Charset charset, long timeout, int bufferSize, boolean destroy,
				boolean forceSigterm) {
			this.charset = charset;
			this.timeout = timeout;
			this.bufferSize = bufferSize;
			this.process = process;
			this.destroy = destroy;
			this.forceSigterm = forceSigterm;

			log.debug("initializing " + this);

			_receiverService = Executors.newFixedThreadPool(2, new ThreadFactory() {
				private volatile int _count = 0;

				@Override
				public Thread newThread(Runnable r) {
					return new Thread(r, "CommendExecutor-" + _count++);
				}
			});
		}

		@Override
		public String toString() {
			return this.getClass().getCanonicalName() + " [command = " + Arrays.toString(_command)
					+ ", charset = " + charset
					+ ", timeout = " + timeout
					+ ", bufferSize = " + bufferSize
					+ ", forceSigterm = " + String.valueOf(forceSigterm)
					+ "]";
		}

		/**
		 * execute command
		 */
		@Override
		public CommandResult call() {
			Process process = this.process;

			Future<String> stdoutTask = null;
			Future<String> stderrTask = null;

			Integer exitCode = null;
			String stdout = null;
			String stderr = null;

			try {
				log.debug("starting child process : " + this);

				StreamReader stdoutReader = new StreamReader(process.getInputStream(), "CommandStdoutReader", _bufferSize);
				StreamReader stderrReader = new StreamReader(process.getErrorStream(), "CommandStderrReader", _bufferSize);
				stdoutTask = _receiverService.submit(stdoutReader);
				stderrTask = _receiverService.submit(stderrReader);

				log.debug("waiting child process : " + _commandLine);
				exitCode = process.waitFor();
				log.debug("child process exited : " + _commandLine);

				if (timeout == _disableTimeout) {
					stdout = stdoutTask.get();
					stderr = stderrTask.get();
				} else {
					stdout = stdoutTask.get(timeout, TimeUnit.MILLISECONDS);
					stderr = stderrTask.get(timeout, TimeUnit.MILLISECONDS);
				}
			} catch (InterruptedException e) {
				log.info("command executor failure. (command = " + _commandLine + ") "+ e.getMessage());
				stdout = "";
				stderr = "Internal Error : " + e.getMessage();
				exitCode = -1;
			} catch (Exception e) {
				log.warn("command executor failure. (command = " + _commandLine + ") " + e.getMessage(), e);
				stdout = "";
				stderr = "Internal Error : " + e.getMessage();
				exitCode = -1;
			} finally {
				log.debug("canceling stdout and stderr reader.");
				if(stdoutTask != null){
					stdoutTask.cancel(true);
				}
				if(stderrTask != null){
					stderrTask.cancel(true);
				}
				if (process != null && process.getOutputStream() != null) {
					try {
						process.getOutputStream().close();
					} catch (IOException e) {
					}
				}
				if (process != null && destroy) {
					log.debug("destroying child process.");
					// 以下と同様の実装が「HinemosWorkers/src_agent/com/clustercontrol/agent/job/DeleteProcessThread.java」に存在します。修正する際には併せて修正してください。
					// sudoの仕様変更対応に伴い、Hinemosエージェント起動ユーザとは異なるユーザで実行したコマンドは
					// process.destroy()でプロセス停止されないため、sigtermシグナルを送信することで強制停止を行う
					if (forceSigterm && process.getClass().getName().equals("java.lang.UNIXProcess")) {
						try {
							// sudoプロセスID取得
							Field f = process.getClass().getDeclaredField("pid");
							f.setAccessible(true);
							int pid = (int) f.get(process);
							log.debug("run() : PID = " + pid);

							// sudoの子プロセスID取得
							Process getPidProcess = Runtime.getRuntime()
									.exec("ps --ppid " + pid + " -o pid --no-heading");
							log.debug("call() : getPidProcess.waitFor() : " + getPidProcess.waitFor());
							InputStream is = getPidProcess.getInputStream();
							BufferedReader br = new BufferedReader(new InputStreamReader(is));

							boolean getChildPid = false;
							String data = "";
							try {
								while ((data = br.readLine()) != null) {
									if (!data.equals("")) {
										// 起動元プロセス（sudo）でなく、その子プロセス（起動したいコマンド）にシグナル送信
										String command = "kill -15 " + data;
										log.info("call() : command : " + command);
										Process sigtermProcess = Runtime.getRuntime().exec(command);
										log.debug("call() : sigtermProcess.waitFor() : " + sigtermProcess.waitFor());
										// 正常終了（戻り値が0）をチェック
										if (sigtermProcess.exitValue() == 0) {
											getChildPid = true;
										}
									}
								}
								if (!getChildPid) {
									log.debug("get child pid failure.");
									// 子プロセスを取得できなかったので起動元を停止させる
									String command = "kill -15 " + Integer.toString(pid);
									log.info("call() : command : " + command);
									Process sigtermProcess = Runtime.getRuntime().exec(command);
									log.debug("call() : sigtermProcess.waitFor() : " + sigtermProcess.waitFor());
									// 正常終了（戻り値が0）をチェック
									if (sigtermProcess.exitValue() != 0) {
										log.debug(
												"call() : sigtermProcess.exitValue() : " + sigtermProcess.exitValue());
										process.destroy();
									}
								}
							} finally {
								is.close();
								br.close();
							}
						} catch (RuntimeException e) {
							log.warn("shutdown process : " + e.getMessage());
						} catch (Exception e) {
							log.warn("shutdown process : " + e.getMessage(), e);
						}
					} else {
						process.destroy();
					}
				}
				// release thread pool
				log.debug("releasing receiver threads.");
				_receiverService.shutdownNow();
			}

			return new CommandResult(exitCode, stdout, stderr, bufferDiscarded);
		}

		/**
		 * STDOUT and STDERR Reader Class<br/>
		 */
		public class StreamReader implements Callable<String> {

			// stream of stdout or stderr
			private final InputStream is;

			private final String threadName;

			private int tmpBufSize = 1024;
			
			public StreamReader(InputStream is, String threadName, int bufferSize) {
				this.is = is;
				this.threadName = threadName;

				log.debug("initializing " + this);
			}

			@Override
			public String toString() {
				return this.getClass().getCanonicalName() + " [threadName = " + threadName
						+ ", stream = " + is
						+ "]";
			}

			@Override
			public String call() {
				Thread.currentThread().setName(threadName);

				ArrayList<byte[]> outputList = new ArrayList<byte[]>();
				String outputStr = null;

				int size = 0;
				int total = 0;

				try {
					// read stream
					// until EOF (because some programs will be effected when stream is closed on running state)
					while (size != -1) {
						// receive buffer from stream
						byte[] output = new byte[tmpBufSize];
						int offset = 0;
						
						//tmpBufSizeバイトの配列につめていく
						while ((size = is.read(output, offset, tmpBufSize - offset)) != -1) {
							log.debug("coping to output as stout/stderr. (offset = " + offset + ", size = "  + size + "[byte])");
							
							// offset position
							offset += size;
							
							if (offset >= tmpBufSize) {
								// バイト列にすべてつめたらループを抜ける
								break;
							}

						}

						if (total < bufferSize) {
							// 最大読み込みバイト数に達していなかったらバッファリストに追加する
							// 最大読み込みバイト数に達している場合、バッファリストには追加しないが、コマンドからは継続して出力内容を受け取る必要があるため処理は中断しない
							outputList.add(output); 
						}
					
						// 全読み込みサイズを加算
						if (Integer.MAX_VALUE - offset < total) {
							total = Integer.MAX_VALUE;
						} else {
							total += offset;
						}
					}
					log.debug("reached end of stream.");
				} catch (IOException e) {
					log.warn("reading stream failure... " + e.getMessage(), e);
				} catch (Exception e) {
					log.warn("unexpected failure... " + e.getMessage(), e);
				} finally {
					if (is != null) {
						try {
							log.debug("closing a stream.");
							is.close();
						} catch (IOException e) {
							log.warn("closing stream failure... " + e.getMessage(), e);
						}
					}
				}

				// byte to string
				if (total == 0) {
					outputStr = "";
				} else {
					// tmpBufferSizeごとの配列のリストをひとつのバイト配列に結合する
					int offset = 0;
					byte[] output = new byte[(bufferSize < total ? bufferSize
							: total)];
					for (byte[] buf : outputList) {
						int copySize = (buf.length < (output.length - offset) ? buf.length : output.length - offset);
						System.arraycopy(buf, 0, output, offset, copySize);
						offset += copySize;
					}

					// 結合したバイト配列を文字列に変換（結合しないと配列をまたがるマルチバイト文字が文字化けする）
					outputStr = new String(output, 0,
							(bufferSize < total ? bufferSize : total), charset);
				}
				if (total > bufferSize) {
					bufferDiscarded = true;
					log.warn("discarding command's output. (buffer = " + bufferSize + "[byte] < total = " + total + "[byte])");
				}

				return outputStr;
			}
		}
	}
}
