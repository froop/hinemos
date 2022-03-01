/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */
package com.clustercontrol.rest.endpoint.common.dto;

import com.clustercontrol.notify.restaccess.model.RestAccessSendHttpHeader;
import com.clustercontrol.rest.annotation.beanconverter.RestBeanConvertIdClassSet;

@RestBeanConvertIdClassSet(infoClass = RestAccessSendHttpHeader.class, idName = "id")
public class RestAccessSendHttpHeaderResponse {
	private Long headerOrderNo;
	private String key;
	private String value;

	public RestAccessSendHttpHeaderResponse() {
	}

	public Long getHeaderOrderNo() {
		return headerOrderNo;
	}
	public void setHeaderOrderNo(Long headerOrderNo) {
		this.headerOrderNo = headerOrderNo;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


}
