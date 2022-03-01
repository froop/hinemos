/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */

package com.clustercontrol.rest.endpoint.agent.dto;

import com.clustercontrol.repository.model.NodeDeviceInfo;
import com.clustercontrol.repository.model.NodeNetworkInterfaceInfo;
import com.clustercontrol.rest.annotation.beanconverter.RestBeanConvertAssertion;
import com.clustercontrol.rest.annotation.beanconverter.RestBeanConvertIdClassSet;

@RestBeanConvertAssertion(from = NodeNetworkInterfaceInfo.class)
@RestBeanConvertIdClassSet(infoClass = NodeDeviceInfo.class, idName = "id")
public class AgtNodeNetworkInterfaceInfoResponse {

	// ---- from NodeDeviceInfoPK
	// private String facilityId;
	private Integer deviceIndex;
	private String deviceType;
	private String deviceName;

	// ---- from NodeDeviceInfo
	//private NodeDeviceInfoPK id;
	private String deviceDisplayName;
	private Long deviceSize;
	private String deviceSizeUnit;
	private String deviceDescription;

	// ---- from NodeNetworkInterfaceInfo
	private String nicIpAddress;
	private String nicMacAddress;
	private Long regDate;
	private String regUser;
	private Long updateDate;
	private String updateUser;
	private Boolean searchTarget;

	public AgtNodeNetworkInterfaceInfoResponse() {
	}

	// ---- accessors

	public Integer getDeviceIndex() {
		return deviceIndex;
	}

	public void setDeviceIndex(Integer deviceIndex) {
		this.deviceIndex = deviceIndex;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceDisplayName() {
		return deviceDisplayName;
	}

	public void setDeviceDisplayName(String deviceDisplayName) {
		this.deviceDisplayName = deviceDisplayName;
	}

	public Long getDeviceSize() {
		return deviceSize;
	}

	public void setDeviceSize(Long deviceSize) {
		this.deviceSize = deviceSize;
	}

	public String getDeviceSizeUnit() {
		return deviceSizeUnit;
	}

	public void setDeviceSizeUnit(String deviceSizeUnit) {
		this.deviceSizeUnit = deviceSizeUnit;
	}

	public String getDeviceDescription() {
		return deviceDescription;
	}

	public void setDeviceDescription(String deviceDescription) {
		this.deviceDescription = deviceDescription;
	}

	public String getNicIpAddress() {
		return nicIpAddress;
	}

	public void setNicIpAddress(String nicIpAddress) {
		this.nicIpAddress = nicIpAddress;
	}

	public String getNicMacAddress() {
		return nicMacAddress;
	}

	public void setNicMacAddress(String nicMacAddress) {
		this.nicMacAddress = nicMacAddress;
	}

	public Long getRegDate() {
		return regDate;
	}

	public void setRegDate(Long regDate) {
		this.regDate = regDate;
	}

	public String getRegUser() {
		return regUser;
	}

	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	public Long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Boolean getSearchTarget() {
		return searchTarget;
	}

	public void setSearchTarget(Boolean searchTarget) {
		this.searchTarget = searchTarget;
	}

}
