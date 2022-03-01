/*
 * Copyright (c) 2018 NTT DATA INTELLILINK Corporation. All rights reserved.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */
package com.clustercontrol.xcloud.common;

import com.clustercontrol.xcloud.util.CloudMessage;

public enum CloudMessageConstant {
	EXECUTED_AUTO_SEARCH("XCLOUD_CORE_MSG_EXECUTED_AUTO_SEARCH"),
	EXECUTED_AUTO_SEARCH_FAILED("XCLOUD_CORE_MSG_EXECUTED_AUTO_SEARCH_FAILED"),
	CLOUD_STATUS("XCLOUD_CORE_CLOUD_STATUS"),
	
	UNEXPECTED_ERROR("XCLOUD_CORE_MSG_UNEXPECTED_ERROR"),
	VALIDATION_NOTNULL("XCLOUD_CORE_MSG_VALIDATION_NOTNULL"),
	VALIDATION_LISTELEMENT("XCLOUD_CORE_MSG_VALIDATION_LISTELEMENT"),
	VALIDATION_FILTERCONDITION("XCLOUD_CORE_MSG_VALIDATION_FILTERCONDITION"),
	VALIDATION_FILTERLISTCONDITION("XCLOUD_CORE_MSG_VALIDATION_FILTERLISTCONDITION"),
	VALIDATION_PATTERN("XCLOUD_CORE_MSG_VALIDATION_PATTERN"),
	
	CLOUDSERVICE_AVAILABLE("XCLOUD_CORE_CLOUDSERVICE_AVAILABLE"),
	CLOUDSERVICE_UNAVAILABLE("XCLOUD_CORE_CLOUDSERVICE_UNAVAILABLE"),
	CLOUDSERVICE_UNKNOWN("XCLOUD_CORE_CLOUDSERVICE_UNKNOWN"),
	CLOUDSERVICE_EXCEPTION("XCLOUD_CORE_CLOUDSERVICE_EXCEPTION"),
	
	AUTODETECTION("XCLOUD_CORE_MSG_AUTO_DETECTION"),
	AUTOUPDATE_ERROR("XCLOUD_CORE_MSG_AUTOUPDATE_ERROR"),
	AUTOUPDATE_ERROR_DETAIL1("XCLOUD_CORE_MSG_AUTOUPDATE_ERROR_DETAIL1"),
	AUTOUPDATE_ERROR_DETAIL2("XCLOUD_CORE_MSG_AUTOUPDATE_ERROR_DETAIL2"),
	
	CLOUDSERVICE_CONDITION_MONITOR("XCLOUD_CORE_CLOUDSERVICE_CONDITION_MONITOR"),
	CLOUDSERVICE_BILLING_MONITOR("XCLOUD_CORE_CLOUDSERVICE_BILLING_MONITOR"),
	CLOUDSERVICE_BILLING_DETAIL_MONITOR("XCLOUD_CORE_CLOUDSERVICE_BILLING_DETAIL_MONITOR"),
	CLOUDSERVICE_LOG_MONITOR("XCLOUD_CORE_CLOUDSERVICE_LOG_MONITOR"),
	
	MONITOR_PLATFORM_SERVICE_BILLING_NOT_GET_BILLING("XCLOUD_CORE_MSG_MONITOR_PLATFORM_SERVICE_BILLING_NOT_GET_BILLING"),
	
	CLOUDSERVICE_CLOUDID("XCLOUD_CORE_CS_CLOUDID"),
	CLOUDSERVICE_TARGETID("XCLOUD_CORE_CS_TARGETID"),
	CLOUDSERVICE_MESSAGE("XCLOUD_CORE_CS_MESSAGE"),
	CLOUDSERVICE_LEFT_BRACE("XCLOUD_CORE_LBRACE"),
	CLOUDSERVICE_RIGHT_BRACE("XCLOUD_CORE_RBRACE"),
	
	CLOUDSERVICE_MESSAGE_FORMAT("XCLOUD_CORE_CLOUDSERVICE_MESSAGE_FORMAT"),
	CLOUDSERVICE_MESSAGE_FORMAT2("XCLOUD_CORE_CLOUDSERVICE_MESSAGE_FORMAT2"),
	CLOUDSERVICE_ORG_MESSAGE_FORMAT("XCLOUD_CORE_CLOUDSERVICE_ORG_MESSAGE_FORMAT"),
	CLOUDSERVICE_ORG_MESSAGE_FORMAT2("XCLOUD_CORE_CLOUDSERVICE_ORG_MESSAGE_FORMAT2"),
	CLOUDSERVICE_ORG_MESSAGE_FORMAT3("XCLOUD_CORE_CLOUDSERVICE_ORG_MESSAGE_FORMAT3"),
	
	BILLINGALARM_NOTIFY_SUM("XCLOUD_CORE_MSG_BILLINGALARM_NOTIFY_SUM"),
	BILLINGALARM_NOTIFY_ORG_SUM("XCLOUD_CORE_MSG_BILLINGALARM_NOTIFY_ORG_SUM"),
	BILLINGALARM_NOTIFY_DELTA("XCLOUD_CORE_MSG_BILLINGALARM_NOTIFY_DELTA"),
	BILLINGALARM_NOTIFY_ORG_DELTA("XCLOUD_CORE_MSG_BILLINGALARM_NOTIFY_ORG_DELTA"),
	BILLINGALARM_NOTIFY_UNKNOWN("XCLOUD_CORE_MSG_BILLINGALARM_NOTIFY_UNKNOWN"),
	BILLINGALARM_NOTIFY_ORG_UNKNOWN("XCLOUD_CORE_MSG_BILLINGALARM_NOTIFY_ORG_UNKNOWN"),
	
	BILLINGALARM_NOTIFY_UNKNOWN_FACILITY_TYPE("XCLOUD_CORE_MSG_BILLINGALARM_NOTIFY_UNKNOWN_FACILITY_TYPE"),
	BILLINGALARM_NOTIFY_UNKNOWN_KIND_TYPE("XCLOUD_CORE_MSG_BILLINGALARM_NOTIFY_UNKNOWN_KIND_TYPE"),
	
	VALIDATION_SCOPE_NOT_FOUND("XCLOUD_CORE_MSG_VALIDATION_SCOPE_NOT_FOUND"),
	MONITOR_PLATFORM_SERVICE_BILLING_INVALID_TARGET("XCLOUD_CORE_MSG_MONITOR_PLATFORM_SERVICE_BILLING_INVALID_TARGET"),
	MONITOR_UNKNOWN("XCLOUD_CORE_MONITOR_UNKNOWN"),
	
	PUBLIC_CLOUDROOT_SCOPE_NAME("XCLOUD_CORE_PUBLIC_CLOUDROOT_SCOPE_NAME"),
	PRIVATE_CLOUDROOT_SCOPE_NAME("XCLOUD_CORE_PRIVATE_CLOUDROOT_SCOPE_NAME"),
	ALL_NODE_SCOPE_NAME("XCLOUD_CORE_ALL_NODE_SCOPE_NAME"),
	
	MONITOR_PLATFORM_BILLING_SERVICE_NOTIFY_UNKNOWN("XCLOUD_CORE_MSG_MONITOR_PLATFORM_SERVICE_BILLING_NOTIFY_UNKNOWN"),
	MONITOR_PLATFORM_BILLING_SERVICE_NOTIFY_ORG_UNKNOWN("XCLOUD_CORE_MSG_MONITOR_PLATFORM_SERVICE_BILLING_NOTIFY_ORG_UNKNOWN"),

	FAILURE_UNEXPECTED("XCLOUD_CORE_MSG_FAILURE_UNEXPECTED"),
	;
	
	private String messageId; 
	
	CloudMessageConstant(String messageId) {
		this.messageId = messageId;
	}
	
	public String getMessage(String... args) {
		return CloudMessage.getMessage(messageId, args);
	}
	
	public static String escape (String s) {
		return CloudMessage.escape(s);
	}
}
