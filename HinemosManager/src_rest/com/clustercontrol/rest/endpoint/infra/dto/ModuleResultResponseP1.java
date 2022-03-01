/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */
package com.clustercontrol.rest.endpoint.infra.dto;

import java.util.ArrayList;
import java.util.List;

import com.clustercontrol.rest.endpoint.infra.dto.enumtype.ModuleTypeEnum;
import com.clustercontrol.rest.annotation.beanconverter.RestBeanConvertEnum;

public class ModuleResultResponseP1 {

	@RestBeanConvertEnum
	private ModuleTypeEnum moduleType;
	private String sessionId;
	private String moduleId;
	private Boolean hasNext;
	private List<ModuleNodeResultResponseP1> nodeResultList = new ArrayList<>();

	public ModuleResultResponseP1() {
	}

	public ModuleTypeEnum getModuleType() {
		return moduleType;
	}

	public void setModuleType(ModuleTypeEnum moduleType) {
		this.moduleType = moduleType;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}

	public List<ModuleNodeResultResponseP1> getNodeResultList() {
		return nodeResultList;
	}

	public void setNodeResultList(List<ModuleNodeResultResponseP1> nodeResultList) {
		this.nodeResultList = nodeResultList;
	}

	@Override
	public String toString() {
		return "ModuleResultResponseP1 [moduleType=" + moduleType + ", sessionId=" + sessionId + ", moduleId="
				+ moduleId + ", hasNext=" + hasNext + ", nodeResultList=" + nodeResultList + "]";
	}

}
