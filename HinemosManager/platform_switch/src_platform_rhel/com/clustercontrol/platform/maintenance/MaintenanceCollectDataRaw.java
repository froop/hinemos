/*
 * Copyright (c) 2018 NTT DATA INTELLILINK Corporation. All rights reserved.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */

package com.clustercontrol.platform.maintenance;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.clustercontrol.accesscontrol.bean.RoleIdConstant;
import com.clustercontrol.commons.util.HinemosPropertyCommon;
import com.clustercontrol.commons.util.JpaTransactionManager;
import com.clustercontrol.maintenance.factory.MaintenanceObject;
import com.clustercontrol.maintenance.util.QueryUtil;
import com.clustercontrol.monitor.run.model.MonitorInfo;
import com.clustercontrol.monitor.run.util.MonitorCollectDataCache;
import com.clustercontrol.monitor.session.MonitorSettingControllerBean;
import com.clustercontrol.util.HinemosTime;

/**
 * 性能実績の削除処理
 *
 * @version 5.1.0
 * @since 5.1.0
 *
 */
public class MaintenanceCollectDataRaw extends MaintenanceObject {

	private static Log m_log = LogFactory.getLog( MaintenanceCollectDataRaw.class );

	private static final Object _deleteLock = new Object();

	/**
	 * 削除処理
	 */
	@Override
	protected int _delete(Long boundary, boolean status, String ownerRoleId){
		
		m_log.info("deleteCollectData() : start");
		
		long start = HinemosTime.currentTimeMillis();
		int ret = 0;
		JpaTransactionManager jtm = null;
		
		try{
			//AdminRoleの場合は監視IDを条件にせず、全て削除
			if(RoleIdConstant.isAdministratorRole(ownerRoleId)){
				jtm = new JpaTransactionManager();
				jtm.begin();
				synchronized (_deleteLock) {
					ret = delete(boundary, status);
					jtm.commit();
				}
			}else{
				ArrayList<MonitorInfo> monitorList = new MonitorSettingControllerBean().getMonitorList();
				
				ArrayList<String> monitorIdList = new ArrayList<>();
				
				for (MonitorInfo monitorInfo : monitorList) {
					if (RoleIdConstant.isAdministratorRole(ownerRoleId) 
							|| monitorInfo.getOwnerRoleId().equals(ownerRoleId)) {
						monitorIdList.add(monitorInfo.getMonitorId());
					}
				}
				
				if (monitorIdList.isEmpty()) {
					return ret;
				}
				jtm = new JpaTransactionManager();
				jtm.begin();
				
				for(int i = 0; i < monitorIdList.size(); i++){
					synchronized (_deleteLock) {
						ret += delete(boundary, status, monitorIdList.get(i));
						jtm.commit();
					}
				}
			}

			// 収集データキャッシュ上の不要なデータを削除する。
			MonitorCollectDataCache.removeUnnecessaryData();

		} catch(Exception e){
			String countMessage = "delete count : " + ret + " records" + "\n";
			m_log.warn("deleteCollectData() : " + countMessage
					+ e.getClass().getSimpleName() + ", " + e.getMessage(), e);
			ret = -1;
			if (jtm != null)
				jtm.rollback();
		} finally {
			if (jtm != null)
				jtm.close();
		}
		m_log.info("deleteCollectData() result : " + ret +
				 ", time : " + (HinemosTime.currentTimeMillis() - start));
		return ret;
	}
	
	protected int delete(Long boundary, boolean status, String monitorId) {
		m_log.debug("_delete() start : status = " + status);
		
		//SQL文の実行
		// for HA (縮退判定時間を延ばすため)、シングルには影響なし(0)：タイムアウト値設定
		int ret  = QueryUtil.deleteCollectDataByDateTimeAndMonitorId(
				boundary, HinemosPropertyCommon.maintenance_query_timeout.getIntegerValue(), monitorId);
		
		//終了
		m_log.debug("_delete() count : " + ret);
		return ret;
	}
	
	
	protected int delete(Long boundary, boolean status) {
		m_log.debug("_delete() start : status = " + status);
		
		//SQL文の実行
		// for HA (縮退判定時間を延ばすため)、シングルには影響なし(0)：タイムアウト値設定
		int ret  = QueryUtil.deleteCollectDataByDateTime(
				boundary, HinemosPropertyCommon.maintenance_query_timeout.getIntegerValue());
		
		//終了
		m_log.debug("_delete() count : " + ret);
		return ret;
	}
}
