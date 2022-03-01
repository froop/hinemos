/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */
package com.clustercontrol.rest.endpoint.jobmanagement.dto;

import com.clustercontrol.fault.InvalidSetting;
import com.clustercontrol.rest.dto.RequestDto;

public class JobCommandParamRequest implements RequestDto {
	/** 名前 **/
	private String paramId;
	/** 値（デフォルト値） */
	private String value;
	/** 標準出力フラグ */
	private Boolean jobStandardOutputFlg = false;

	public JobCommandParamRequest() {
	}
	
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getJobStandardOutputFlg() {
		return jobStandardOutputFlg;
	}
	public void setJobStandardOutputFlg(Boolean jobStandardOutputFlg) {
		this.jobStandardOutputFlg = jobStandardOutputFlg;
	}

	@Override
	public void correlationCheck() throws InvalidSetting {
	}
}
