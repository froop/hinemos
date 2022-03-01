/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */
package com.clustercontrol.rest.endpoint.notify.dto;

public class LogEscalateNotifyInfoResponse extends AbstractNotifyInfoResponse {
	private LogEscalateNotifyDetailInfoResponse notifyLogEscalateInfo;

	public LogEscalateNotifyInfoResponse() {
	}

	public LogEscalateNotifyDetailInfoResponse getNotifyLogEscalateInfo() {
		return notifyLogEscalateInfo;
	}

	public void setNotifyLogEscalateInfo(LogEscalateNotifyDetailInfoResponse notifyLogEscalateInfo) {
		this.notifyLogEscalateInfo = notifyLogEscalateInfo;
	}
}
