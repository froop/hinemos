/*
 * Copyright (c) 2018 NTT DATA INTELLILINK Corporation. All rights reserved.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */

package com.clustercontrol.http.dialog;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.openapitools.client.model.AddHttpStringMonitorRequest;
import org.openapitools.client.model.HttpCheckInfoResponse;
import org.openapitools.client.model.ModifyHttpStringMonitorRequest;
import org.openapitools.client.model.MonitorInfoResponse;
import org.openapitools.client.model.MonitorStringValueInfoRequest;

import com.clustercontrol.bean.RequiredFieldColorConstant;
import com.clustercontrol.composite.TextWithParameterComposite;
import com.clustercontrol.fault.InvalidRole;
import com.clustercontrol.fault.MonitorDuplicate;
import com.clustercontrol.monitor.run.dialog.CommonMonitorStringDialog;
import com.clustercontrol.monitor.util.MonitorsettingRestClientWrapper;
import com.clustercontrol.notify.bean.PriChangeFailSelectTypeConstant;
import com.clustercontrol.notify.bean.PriChangeJudgeSelectTypeConstant;
import com.clustercontrol.util.HinemosMessage;
import com.clustercontrol.util.Messages;
import com.clustercontrol.util.RestClientBeanUtil;
import com.clustercontrol.util.WidgetTestUtil;

/**
 * HTTP監視（文字列）作成・変更ダイアログクラス<BR>
 *
 * @version 4.0.0
 * @since 4.0.0
 */
public class HttpStringCreateDialog extends CommonMonitorStringDialog {

	// ----- instance フィールド ----- //
	/** タイムアウト用テキストボックス */
	private Text m_textTimeout = null;

	/** URL */
	private TextWithParameterComposite m_textRequestUrl = null;

	// ----- コンストラクタ ----- //

	/**
	 * 作成用ダイアログのインスタンスを返します。
	 *
	 * @param parent
	 *            親のシェルオブジェクト
	 */
	public HttpStringCreateDialog(Shell parent) {
		super(parent, null);
		this.priorityChangeJudgeSelect = PriChangeJudgeSelectTypeConstant.TYPE_PATTERN;
		this.priorityChangeFailSelect = PriChangeFailSelectTypeConstant.TYPE_GET;
	}

	/**
	 * 変更用ダイアログのインスタンスを返します。
	 *
	 * @param parent 親のシェルオブジェクト
	 * @param managerName マネージャ名
	 * @param monitorId 変更する監視項目ID
	 * @param updateFlg 更新するか否か（true:変更、false:新規登録）
	 */
	public HttpStringCreateDialog(Shell parent, String managerName, String monitorId, boolean updateFlg) {
		super(parent, managerName);

		this.managerName = managerName;
		this.monitorId = monitorId;
		this.updateFlg = updateFlg;
		this.priorityChangeJudgeSelect = PriChangeJudgeSelectTypeConstant.TYPE_PATTERN;
		this.priorityChangeFailSelect = PriChangeFailSelectTypeConstant.TYPE_GET;
	}

	// ----- instance メソッド ----- //

	/**
	 * ダイアログエリアを生成します。
	 *
	 * @param parent 親のインスタンス
	 */
	@Override
	protected void customizeDialog(Composite parent) {

		super.customizeDialog(parent);

		// タイトル
		shell.setText(Messages.getString("dialog.http.create.modify"));

		// 変数として利用されるラベル
		Label label = null;
		// 変数として利用されるグリッドデータ
		GridData gridData = null;

		/*
		 * チェック設定グループ（条件グループの子グループ）
		 */
		Group groupCheckRule = new Group(groupRule, SWT.NONE);
		WidgetTestUtil.setTestId(this, "checkrule", groupCheckRule);
		GridLayout layout = new GridLayout(1, true);
		layout.marginWidth = HALF_MARGIN;
		layout.marginHeight = HALF_MARGIN;
		layout.numColumns = BASIC_UNIT;
		groupCheckRule.setLayout(layout);
		gridData = new GridData();
		gridData.horizontalSpan = BASIC_UNIT;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		groupCheckRule.setLayoutData(gridData);
		groupCheckRule.setText(Messages.getString("check.rule"));

		/*
		 * URL
		 */
		// ラベル
		label = new Label(groupCheckRule, SWT.NONE);
		WidgetTestUtil.setTestId(this, "requesturl", label);
		WidgetTestUtil.setTestId(this, "url", label);
		gridData = new GridData();
		gridData.horizontalSpan = WIDTH_TITLE_LONG;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		label.setLayoutData(gridData);
		label.setText(Messages.getString("request.url") + " : ");
		// テキスト
		this.m_textRequestUrl = new TextWithParameterComposite(groupCheckRule, SWT.BORDER | SWT.LEFT | SWT.SINGLE);
		WidgetTestUtil.setTestId(this, "requesturl", m_textRequestUrl);
		gridData = new GridData();
		gridData.horizontalSpan = LONG_UNIT;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		this.m_textRequestUrl.setLayoutData(gridData);
		this.m_textRequestUrl.setText("http://");
		String tooltipText = Messages.getString("request.url.tooltip") + Messages.getString("replace.parameter.node");
		this.m_textRequestUrl.setToolTipText(tooltipText);
		this.m_textRequestUrl.setColor(new Color(parent.getDisplay(), new RGB(0, 0, 255)));
		this.m_textRequestUrl.addModifyListener(new ModifyListener(){
			@Override
			public void modifyText(ModifyEvent arg0) {
				update();
			}
		});

		/*
		 * タイムアウト
		 */
		// ラベル
		label = new Label(groupCheckRule, SWT.NONE);
		WidgetTestUtil.setTestId(this, "timeout", label);
		gridData = new GridData();
		gridData.horizontalSpan = WIDTH_TITLE_LONG;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		label.setLayoutData(gridData);
		label.setText(Messages.getString("time.out") + " : ");

		// テキスト
		this.m_textTimeout = new Text(groupCheckRule, SWT.BORDER);
		WidgetTestUtil.setTestId(this, "timeout", m_textTimeout);
		gridData = new GridData();
		gridData.horizontalSpan = WIDTH_VALUE_LONG;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		this.m_textTimeout.setLayoutData(gridData);
		this.m_textTimeout.addModifyListener(new ModifyListener(){
			@Override
			public void modifyText(ModifyEvent arg0) {
				update();
			}
		});

		// ラベル（単位）
		label = new Label(groupCheckRule, SWT.NONE);
		WidgetTestUtil.setTestId(this, "millisec", label);
		gridData = new GridData();
		gridData.horizontalSpan = WIDTH_VALUE_LONG;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		label.setLayoutData(gridData);
		label.setText(Messages.getString("milli.sec"));

		// 空白
		label = new Label(groupCheckRule, SWT.NONE);
		WidgetTestUtil.setTestId(this, "blankmillisec", label);
		gridData = new GridData();
		gridData.horizontalSpan = BASIC_UNIT - WIDTH_TEXT_LONG;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		label.setLayoutData(gridData);

		// ダイアログを調整
		this.adjustDialog();

		// 初期表示
		MonitorInfoResponse info = null;
		if(this.monitorId == null){
			// 作成の場合
			info = new MonitorInfoResponse();
			this.setInfoInitialValue(info);
		} else {
			// 変更の場合、情報取得
			try {
				MonitorsettingRestClientWrapper wrapper = MonitorsettingRestClientWrapper.getWrapper(getManagerName());
				info = wrapper.getMonitor(this.monitorId);
			} catch (InvalidRole e) {
				// アクセス権なしの場合、エラーダイアログを表示する
				MessageDialog.openInformation(
						null,
						Messages.getString("message"),
						Messages.getString("message.accesscontrol.16"));
				throw new InternalError(e.getMessage());
			} catch (Exception e) {
				// 上記以外の例外
				MessageDialog.openInformation(
						null,
						Messages.getString("message"),
						Messages.getString("message.hinemos.failure.unexpected") + ", " + HinemosMessage.replace(e.getMessage()));
				throw new InternalError(e.getMessage());
			}
		}
		this.setInputData(info);

	}

	/**
	 * 更新処理
	 *
	 */
	@Override
	public void update(){
		super.update();

		// 必須項目を明示
		if(this.m_textTimeout.getEnabled() && "".equals(this.m_textTimeout.getText())){
			this.m_textTimeout.setBackground(RequiredFieldColorConstant.COLOR_REQUIRED);
		}else{
			this.m_textTimeout.setBackground(RequiredFieldColorConstant.COLOR_UNREQUIRED);
		}
		if("http://".equals(this.m_textRequestUrl.getText()) || !(this.m_textRequestUrl.getText().startsWith("http://") || this.m_textRequestUrl.getText().startsWith("https://"))){
			this.m_textRequestUrl.setBackground(RequiredFieldColorConstant.COLOR_REQUIRED);
		}else{
			this.m_textRequestUrl.setBackground(RequiredFieldColorConstant.COLOR_UNREQUIRED);
		}
	}

	/**
	 * 各項目に入力値を設定します。
	 *
	 * @param monitor 設定値として用いる監視情報
	 */
	@Override
	protected void setInputData(MonitorInfoResponse monitor) {

		super.setInputData(monitor);

		this.inputData = monitor;

		// 監視条件 HTTP監視情報
		HttpCheckInfoResponse httpInfo = monitor.getHttpCheckInfo();
		if(httpInfo == null){
			httpInfo = new HttpCheckInfoResponse();
			httpInfo.setUrlReplace(false);
			httpInfo.setTimeout(TIMEOUT_SEC);
			httpInfo.setProxySet(false);
			httpInfo.setProxyPort(0);
		}
		if(httpInfo != null){
			if (httpInfo.getRequestUrl() != null) {
				this.m_textRequestUrl.setText(httpInfo.getRequestUrl());
			}
			this.m_textTimeout.setText(Integer.toString(httpInfo.getTimeout()));
		}
		// 必須項目を明示
		this.update();
		m_stringValueInfo.setInputData(monitor);
	}

	/**
	 * 入力値を用いて通知情報を生成します。
	 *
	 * @return 入力値を保持した通知情報
	 */
	@Override
	protected MonitorInfoResponse createInputData() {
		super.createInputData();
		if(validateResult != null){
			return null;
		}

		// 監視条件 HTTP監視情報
		HttpCheckInfoResponse httpInfo = new HttpCheckInfoResponse();

		if (this.m_textRequestUrl.getText() != null
				&& !"".equals((this.m_textRequestUrl.getText()).trim())) {
			//テキストボックスから文字列を取得
			httpInfo.setRequestUrl(this.m_textRequestUrl.getText());
		}

		// 数値チェック（入力値が数値かどうか）
		if (!"".equals((this.m_textTimeout.getText()).trim())) {

			try{
				httpInfo.setTimeout(Integer.valueOf(this.m_textTimeout.getText().trim()));
			}
			catch(NumberFormatException e){
				this.setValidateResult(Messages.getString("message.hinemos.1"),Messages.getString("message.monitor.42"));
				return null;
			}
		}
		monitorInfo.setHttpCheckInfo(httpInfo);

		// 結果判定の定義
		validateResult = m_stringValueInfo.createInputData(monitorInfo);
		if(validateResult != null){
			return null;
		}

		// 通知関連情報とアプリケーションの設定
		validateResult = m_notifyInfo.createInputData(monitorInfo);
		if (validateResult != null) {
			if(validateResult.getID() == null){	// 通知ID警告用出力
				if(!displayQuestion(validateResult)){
					validateResult = null;
					return null;
				}
			}
			else{	// アプリケーション未入力チェック
				return null;
			}
		}

		return monitorInfo;
	}

	/**
	 * 入力値をマネージャに登録します。
	 *
	 * @return true：正常、false：異常
	 *
	 * @see com.clustercontrol.dialog.CommonDialog#action()
	 */
	@Override
	protected boolean action() {
		boolean result = false;

		if(this.inputData != null){
			String[] args = { this.inputData.getMonitorId(), getManagerName() };
			MonitorsettingRestClientWrapper wrapper = MonitorsettingRestClientWrapper.getWrapper(getManagerName());
			if(!this.updateFlg){
				// 作成の場合
				try {
					AddHttpStringMonitorRequest info = new AddHttpStringMonitorRequest();
					RestClientBeanUtil.convertBean(this.inputData, info);
					info.setRunInterval(AddHttpStringMonitorRequest.RunIntervalEnum.fromValue(this.inputData.getRunInterval().getValue()));
					if (info.getStringValueInfo() != null
							&& this.inputData.getStringValueInfo() != null) {
						for (int i = 0; i < info.getStringValueInfo().size(); i++) {
							info.getStringValueInfo().get(i).setPriority(MonitorStringValueInfoRequest.PriorityEnum.fromValue(
									this.inputData.getStringValueInfo().get(i).getPriority().getValue()));
						}
					}
					wrapper.addHttpStringMonitor(info);
					MessageDialog.openInformation(
							null,
							Messages.getString("successful"),
							Messages.getString("message.monitor.33", args));
					result = true;
				} catch (MonitorDuplicate e) {
					// 監視項目IDが重複している場合、エラーダイアログを表示する
					MessageDialog.openInformation(
							null,
							Messages.getString("message"),
							Messages.getString("message.monitor.53", args));

				} catch (Exception e) {
					String errMessage = "";
					if (e instanceof InvalidRole) {
						// アクセス権なしの場合、エラーダイアログを表示する
						MessageDialog.openInformation(
								null,
								Messages.getString("message"),
								Messages.getString("message.accesscontrol.16"));
					} else {
						errMessage = ", " + HinemosMessage.replace(e.getMessage());
					}
					MessageDialog.openError(
							null,
							Messages.getString("failed"),
							Messages.getString("message.monitor.34", args) + errMessage);
				}
			} else{
				// 変更の場合
				try {
					ModifyHttpStringMonitorRequest info = new ModifyHttpStringMonitorRequest();
					RestClientBeanUtil.convertBean(this.inputData, info);
					info.setRunInterval(ModifyHttpStringMonitorRequest.RunIntervalEnum.fromValue(this.inputData.getRunInterval().getValue()));
					if (info.getStringValueInfo() != null
							&& this.inputData.getStringValueInfo() != null) {
						for (int i = 0; i < info.getStringValueInfo().size(); i++) {
							info.getStringValueInfo().get(i).setPriority(MonitorStringValueInfoRequest.PriorityEnum.fromValue(
									this.inputData.getStringValueInfo().get(i).getPriority().getValue()));
						}
					}
					wrapper.modifyHttpStringMonitor(this.inputData.getMonitorId(), info);
					MessageDialog.openInformation(
							null,
							Messages.getString("successful"),
							Messages.getString("message.monitor.35", args));
					result = true;
				} catch (Exception e) {
					String errMessage = "";
					if (e instanceof InvalidRole) {
						// アクセス権なしの場合、エラーダイアログを表示する
						MessageDialog.openInformation(
								null,
								Messages.getString("message"),
								Messages.getString("message.accesscontrol.16"));
					} else {
						errMessage = ", " + HinemosMessage.replace(e.getMessage());
					}
					MessageDialog.openError(
							null,
							Messages.getString("failed"),
							Messages.getString("message.monitor.36", args) + errMessage);
				}
			}
		}

		return result;
	}

	/**
	 * MonitorInfoに初期値を設定します
	 *
	 * @see com.clustercontrol.dialog.CommonMonitorDialog#setInfoInitialValue()
	 */
	@Override
	protected void setInfoInitialValue(MonitorInfoResponse monitor) {

		super.setInfoInitialValue(monitor);

		HttpCheckInfoResponse httpCheckInfo = new HttpCheckInfoResponse();
		// URL置換
		httpCheckInfo.setUrlReplace(false);
		// タイムアウト（ミリ秒）
		httpCheckInfo.setTimeout(TIMEOUT_SEC);
		// プロキシ設定
		httpCheckInfo.setProxySet(false);
		// プロキシ ポート */
		httpCheckInfo.setProxyPort(0);
		monitor.setHttpCheckInfo(httpCheckInfo);
	}
}
