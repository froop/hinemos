/*
 * 
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */
package com.clustercontrol.rest.endpoint.monitorsetting.dto;

import com.clustercontrol.fault.InvalidSetting;
import com.clustercontrol.rest.dto.RequestDto;

public class HttpCheckInfoRequest implements RequestDto {
	private String requestUrl;
	private Integer timeout;
	public HttpCheckInfoRequest() {
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	@Override
	public String toString() {
		return "HttpCheckInfo [requestUrl=" + requestUrl + ", timeout=" + timeout + "]";
	}

	@Override
	public void correlationCheck() throws InvalidSetting {
	}

}