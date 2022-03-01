/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */

package com.clustercontrol.rpa.view.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

import com.clustercontrol.rpa.action.GetRpaScenarioOperationResultCreateSettingListTableDefine;
import com.clustercontrol.rpa.composite.RpaScenarioOperationResultCreateSettingListComposite;
import com.clustercontrol.rpa.util.RpaRestClientWrapper;
import com.clustercontrol.rpa.view.RpaScenarioOperationResultCreateSettingView;
import com.clustercontrol.util.Messages;

/**
 * RPAシナリオ実績[シナリオ実績作成設定]ビューの削除アクションクラス<BR>
 */
public class RpaScenarioCreateSettingDeleteAction extends AbstractHandler implements IElementUpdater {

	// ログ
	private static Log log = LogFactory.getLog( RpaScenarioCreateSettingDeleteAction.class );

	/** アクションID */
	public static final String ID = RpaScenarioCreateSettingDeleteAction.class.getName();

	/** ビュー */
	private IWorkbenchPart viewPart;

	/**
	 * Dispose
	 */
	@Override
	public void dispose() {
		this.viewPart = null;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// 選択アイテムの取得
		this.viewPart = HandlerUtil.getActivePart(event);
		RpaScenarioOperationResultCreateSettingView view = null;
		try {
			view = (RpaScenarioOperationResultCreateSettingView) this.viewPart.getAdapter(RpaScenarioOperationResultCreateSettingView.class);
		} catch (Exception e) { 
			log.info("execute " + e.getMessage()); 
			return null; 
		}

		if (view == null) {
			log.info("execute: view is null"); 
			return null;
		}

		RpaScenarioOperationResultCreateSettingListComposite composite = (RpaScenarioOperationResultCreateSettingListComposite) view.getListComposite();
		StructuredSelection selection = (StructuredSelection) composite.getTableViewer().getSelection();

		List<?> list = (List<?>) selection.toList();
		List<String[]> argsList = new ArrayList<String[]>();
		if(list != null && list.size() > 0){
			for (Object obj : list) {
				List<?> objList = (List<?>)obj;
				String[] args = new String[2];
				args[0] = (String) objList.get(GetRpaScenarioOperationResultCreateSettingListTableDefine.MANAGER_NAME);
				args[1] = (String) objList.get(GetRpaScenarioOperationResultCreateSettingListTableDefine.SETTING_ID);
				argsList.add(args);
			}
		}

		// 選択アイテムがある場合に、削除処理を呼び出す
		if(argsList.isEmpty() ) {
			return null;
		}
		// 削除を実行してよいかの確認ダイアログの表示
		String msg = null;
		String[] msgArgs = new String[1];
		if(argsList.isEmpty() == false) {
			if (argsList.size() == 1) {
				msgArgs[0] = argsList.get(0)[1];
				msg = "message.rpa.scenario.create.setting.delete.confirm";
			} else {
				msgArgs[0] = Integer.toString(argsList.size());
				msg = "message.rpa.scenario.create.settings.delete.confirm";
			}
		}

		if (!MessageDialog.openConfirm(
				null,
				Messages.getString("confirmed"),
				Messages.getString(msg, msgArgs))) {

			// OKが押されない場合は終了
			return null;
		}

		Map<String, List<String>> deleteMap = null;

		for(String[] args : argsList) {
			String managerName = args[0];
			String scenarioId = args[1];
			if(deleteMap == null) {
				deleteMap = new ConcurrentHashMap<String, List<String>>();
			}
			if(deleteMap.get(managerName) == null) {
				deleteMap.put(managerName, new ArrayList<String>());
			}
			deleteMap.get(managerName).add(scenarioId);
		}
		// findbugs対応 文字列の連結方式をStringBuilderを利用する方法に変更
		StringBuilder errMessage = new StringBuilder("");
		StringBuilder successManager = new StringBuilder("");
		int successCount = 0;
		for(Map.Entry<String, List<String>> map : deleteMap.entrySet()) {
			try {
				RpaRestClientWrapper wrapper = RpaRestClientWrapper.getWrapper(map.getKey());
				wrapper.deleteRpaScenarioOperationResultCreateSetting(String.join(",", map.getValue()));
				successCount = successCount + map.getValue().size();
				if (!(successManager.length()==0)) {
					successManager.append(", ");
				}
				successManager.append(map.getKey());
			} catch(Exception e) {
				if (!(errMessage.length() == 0)) {
					errMessage.append("\n");
				}
				errMessage.append(String.format("%s (%s)", e.getMessage(), map.getKey()));
			}
		}
		if (!(errMessage.length() == 0)) {
			MessageDialog.openError(null, Messages.getString("failed"), errMessage.toString());
		}
		if (!(successManager.length()==0)) {
			String message = null;
			if (successCount == 1) {
				message = Messages.getString("message.rpa.scenario.create.setting.delete.finish", new String[]{msgArgs[0], successManager.toString()});
			} else {
				message = Messages.getString("message.rpa.scenario.create.settings.delete.finish", new String[]{Integer.toString(successCount), successManager.toString()});
			}
			MessageDialog.openInformation(null, Messages.getString("successful"), message);
			view.update();
		}
		return null;
	}

	@Override
	public void updateElement(UIElement element, @SuppressWarnings("rawtypes") Map parameters) {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		// page may not start at state restoring
		if( null != window ){
			IWorkbenchPage page = window.getActivePage();
			if( null != page ){
				IWorkbenchPart part = page.getActivePart();

				boolean editEnable = false;
				if(part instanceof RpaScenarioOperationResultCreateSettingView){
					// Enable button when 1 item is selected
					RpaScenarioOperationResultCreateSettingView view = (RpaScenarioOperationResultCreateSettingView)part;

					if(view.getSelectedNum() > 0) {
						editEnable = true;
					}
				}
				this.setBaseEnabled(editEnable);
			} else {
				this.setBaseEnabled(false);
			}
		}
	}

}
