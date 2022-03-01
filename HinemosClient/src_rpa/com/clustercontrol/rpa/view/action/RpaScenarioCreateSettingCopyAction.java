/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */

package com.clustercontrol.rpa.view.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

import com.clustercontrol.bean.PropertyDefineConstant;
import com.clustercontrol.rpa.action.GetRpaScenarioOperationResultCreateSettingListTableDefine;
import com.clustercontrol.rpa.composite.RpaScenarioOperationResultCreateSettingListComposite;
import com.clustercontrol.rpa.dialog.RpaScenarioOperationResultCreateSettingDialog;
import com.clustercontrol.rpa.view.RpaScenarioOperationResultCreateSettingView;

/**
 * RPA設定[シナリオ実績作成設定]ビューのコピーアクションクラス<BR>
 */
public class RpaScenarioCreateSettingCopyAction extends AbstractHandler implements IElementUpdater{
	
	private IWorkbenchWindow window;
	private IWorkbenchPart viewPart;

	/** ログ */
	private static Log log = LogFactory.getLog(RpaScenarioCreateSettingCopyAction.class);
	
	/** アクションID */
	public static final String ID = RpaScenarioCreateSettingCopyAction.class.getName();
	
	/**
	 * Handler execution
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		this.window = HandlerUtil.getActiveWorkbenchWindow(event);
		// In case this action has been disposed
		if( null == this.window || !isEnabled() ){
			return null;
		}

		this.viewPart = HandlerUtil.getActivePart(event);
		
		RpaScenarioOperationResultCreateSettingView view = (RpaScenarioOperationResultCreateSettingView) this.viewPart
				.getAdapter(RpaScenarioOperationResultCreateSettingView.class);
		if (view == null) {
			log.info("execute: view is null"); 
			return null;
		}
		RpaScenarioOperationResultCreateSettingListComposite composite = (RpaScenarioOperationResultCreateSettingListComposite) view.getListComposite();
		StructuredSelection selection = (StructuredSelection) composite.getTableViewer().getSelection();
		
		List<?> list = (List<?>) selection.getFirstElement();
		String managerName = null;
		String settingId = null;
		
		if(list != null && list.size() > 0) {
			managerName = (String) list.get(GetRpaScenarioOperationResultCreateSettingListTableDefine.MANAGER_NAME);
			settingId = (String) list.get(GetRpaScenarioOperationResultCreateSettingListTableDefine.SETTING_ID);
		}
		
		if (managerName != null && settingId != null) {
			// ダイアログを生成
			RpaScenarioOperationResultCreateSettingDialog dialog = new RpaScenarioOperationResultCreateSettingDialog(
					this.viewPart.getSite().getShell(), managerName, settingId, 
					PropertyDefineConstant.MODE_COPY);
			
			// ダイアログにて変更が選択された場合、入力内容をもって更新を行う。
			if (dialog.open() == IDialogConstants.OK_ID) {
				view.update();
			}
		}

		return null;
	}
	
	/**
	 * Dispose
	 */
	@Override
	public void dispose(){
		this.viewPart = null;
		this.window = null;
	}
	
	/**
	 * Update handler status
	 */
	@Override
	public void updateElement(UIElement element, @SuppressWarnings("rawtypes") Map parameters) {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		// page may not start at state restoring
		if( null != window ){
			IWorkbenchPage page = window.getActivePage();
			if( null != page ){
				IWorkbenchPart part = page.getActivePart();

				if( part instanceof RpaScenarioOperationResultCreateSettingView ){
					// Enable button when 1 item is selected
					this.setBaseEnabled( 1 == ((RpaScenarioOperationResultCreateSettingView) part).getSelectedNum() );
				}else{
					this.setBaseEnabled( false );
				}
			} else {
				this.setBaseEnabled(false);
			}
		}
	}
}
