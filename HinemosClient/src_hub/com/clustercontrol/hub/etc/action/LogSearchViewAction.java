/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */

package com.clustercontrol.hub.etc.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.clustercontrol.composite.FacilityTreeComposite;
import com.clustercontrol.hub.view.LogScopeTreeView;
import com.clustercontrol.hub.view.LogSearchView;
import com.clustercontrol.repository.util.FacilityTreeItemResponse;
import com.clustercontrol.repository.util.ScopePropertyUtil;
import com.clustercontrol.util.Messages;

/**
 * 収集蓄積検索[マネージャ名＜スコープ名＞]ビューを表示するクライアント側アクションクラス<BR>
 * @since 7.0.0
 */
public class LogSearchViewAction extends AbstractHandler {

	// ログ
	private static Log m_log = LogFactory.getLog( LogSearchViewAction.class );
	
	/** ビュー */
	private IWorkbenchWindow window;
	/** ビュー */
	private IWorkbenchPart viewPart;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		m_log.debug("LogSearchViewAction new view");

		window = HandlerUtil.getActiveWorkbenchWindow(event);
		// In case this action has been disposed
		if( null == window || !isEnabled() ){
			return null;
		}

		// 選択アイテムの取得
		this.viewPart = HandlerUtil.getActivePart(event);
		
		LogScopeTreeView view = (LogScopeTreeView) viewPart.getAdapter(LogScopeTreeView.class);

		FacilityTreeComposite tree = view.getScopeTreeComposite();

		FacilityTreeItemResponse selectItem = tree.getSelectItem();
		
		// 選択アイテム取得(ツリー自体でも行っているが、念のため)
		if (selectItem != null) {
			// ビューの作成
			IWorkbenchPage page = window.getActivePage();
			FacilityTreeItemResponse managerTreeItem = ScopePropertyUtil.getManager(selectItem);
			// スコープツリーのTOPをダブルクリックした場合はなにもせずにreturn
			if (managerTreeItem == null) {
				return null;
			}
			// マネージャをダブルクリックした場合はなにもせずにreturn
			String manager = managerTreeItem.getData().getFacilityId();
			if (manager.equals(selectItem.getData().getFacilityId())) {
				return null;
			}
			try {
				LogSearchView searchView = LogSearchView.createSearchView(
						page, manager, selectItem.getData().getFacilityId());
				m_log.debug("LogSearchViewAction(), open " + searchView.getTitle());

				searchView.setFocus();
				searchView.update();
			} catch (PartInitException e) {
				m_log.warn("LogSearchViewAction(), " + e.getMessage(), e);
			} catch(Exception e) {
				m_log.warn("LogSearchViewAction is Failed " + e.getMessage());
				MessageDialog.openWarning(
						null,
						Messages.getString("word.warn"),
						Messages.getString("message.search.failed") + "\n" + e.toString());
			}
		}
		
		return null;
	}
}