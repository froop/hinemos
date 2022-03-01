/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */
package com.clustercontrol.rest.endpoint.jobmanagement.dto;

import java.util.ArrayList;

import com.clustercontrol.rest.annotation.beanconverter.RestBeanConvertDatetime;
import com.clustercontrol.rest.annotation.beanconverter.RestBeanConvertEnum;
import com.clustercontrol.rest.endpoint.jobmanagement.dto.enumtype.ScheduleTypeEnum;
import com.clustercontrol.rest.endpoint.jobmanagement.dto.enumtype.SessionPremakeEveryXHourEnum;
import com.clustercontrol.rest.endpoint.jobmanagement.dto.enumtype.SessionPremakeScheduleTypeEnum;

public class JobScheduleResponse {

	/** 実行契機ID */
	private String id;

	/** 実行契機名 */
	private String name;

	/** ジョブID */
	private String jobId;

	/** ジョブ名 */
	private String jobName;

	/** ジョブユニットID */
	private String jobunitId;

	/** オーナーロールID */
	private String ownerRoleId;

	/** 新規作成ユーザ */
	private String createUser;

	/** 作成日時 */
	@RestBeanConvertDatetime
	private String createTime;

	/** 最新更新ユーザ */
	private String updateUser;

	/** 最新更新日時 */
	@RestBeanConvertDatetime
	private String updateTime;

	/** 有効/無効 */
	private Boolean valid = false;

	/** カレンダID */
	private String calendarId;

	/** ランタイムジョブ変数情報 */
	private ArrayList<JobRuntimeParamResponse> jobRuntimeParamList;

	/** スケジュール種別 */
	@RestBeanConvertEnum
	private ScheduleTypeEnum scheduleType;

	private Integer week;

	private Integer hour;

	private Integer minute;

	private Integer fromXminutes;

	private Integer everyXminutes;

	/** ジョブセッション事前生成 */
	private Boolean sessionPremakeFlg;

	@RestBeanConvertEnum
	private SessionPremakeScheduleTypeEnum sessionPremakeScheduleType;

	private Integer sessionPremakeWeek;

	private Integer sessionPremakeHour;

	private Integer sessionPremakeMinute;

	@RestBeanConvertEnum
	private SessionPremakeEveryXHourEnum sessionPremakeEveryXHour;

	@RestBeanConvertDatetime
	private String sessionPremakeDate;

	@RestBeanConvertDatetime
	private String sessionPremakeToDate;

	private Boolean sessionPremakeInternalFlg;

	public JobScheduleResponse() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getJobunitId() {
		return jobunitId;
	}

	public void setJobunitId(String jobunitId) {
		this.jobunitId = jobunitId;
	}

	public String getOwnerRoleId() {
		return ownerRoleId;
	}

	public void setOwnerRoleId(String ownerRoleId) {
		this.ownerRoleId = ownerRoleId;
	}

	public ArrayList<JobRuntimeParamResponse> getJobRuntimeParamList() {
		return jobRuntimeParamList;
	}

	public void setJobRuntimeParamList(ArrayList<JobRuntimeParamResponse> jobRuntimeParamList) {
		this.jobRuntimeParamList = jobRuntimeParamList;
	}

	public ScheduleTypeEnum getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(ScheduleTypeEnum scheduleType) {
		this.scheduleType = scheduleType;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Integer getFromXminutes() {
		return fromXminutes;
	}

	public void setFromXminutes(Integer fromXminutes) {
		this.fromXminutes = fromXminutes;
	}

	public Integer getEveryXminutes() {
		return everyXminutes;
	}

	public void setEveryXminutes(Integer everyXminutes) {
		this.everyXminutes = everyXminutes;
	}

	public Boolean getSessionPremakeFlg() {
		return sessionPremakeFlg;
	}

	public void setSessionPremakeFlg(Boolean sessionPremakeFlg) {
		this.sessionPremakeFlg = sessionPremakeFlg;
	}

	public SessionPremakeScheduleTypeEnum getSessionPremakeScheduleType() {
		return sessionPremakeScheduleType;
	}

	public void setSessionPremakeScheduleType(SessionPremakeScheduleTypeEnum sessionPremakeScheduleType) {
		this.sessionPremakeScheduleType = sessionPremakeScheduleType;
	}

	public Integer getSessionPremakeWeek() {
		return sessionPremakeWeek;
	}

	public void setSessionPremakeWeek(Integer sessionPremakeWeek) {
		this.sessionPremakeWeek = sessionPremakeWeek;
	}

	public Integer getSessionPremakeHour() {
		return sessionPremakeHour;
	}

	public void setSessionPremakeHour(Integer sessionPremakeHour) {
		this.sessionPremakeHour = sessionPremakeHour;
	}

	public Integer getSessionPremakeMinute() {
		return sessionPremakeMinute;
	}

	public void setSessionPremakeMinute(Integer sessionPremakeMinute) {
		this.sessionPremakeMinute = sessionPremakeMinute;
	}

	public SessionPremakeEveryXHourEnum getSessionPremakeEveryXHour() {
		return sessionPremakeEveryXHour;
	}

	public void setSessionPremakeEveryXHour(SessionPremakeEveryXHourEnum sessionPremakeEveryXHour) {
		this.sessionPremakeEveryXHour = sessionPremakeEveryXHour;
	}

	public String getSessionPremakeDate() {
		return sessionPremakeDate;
	}

	public void setSessionPremakeDate(String sessionPremakeDate) {
		this.sessionPremakeDate = sessionPremakeDate;
	}

	public String getSessionPremakeToDate() {
		return sessionPremakeToDate;
	}

	public void setSessionPremakeToDate(String sessionPremakeToDate) {
		this.sessionPremakeToDate = sessionPremakeToDate;
	}

	public Boolean getSessionPremakeInternalFlg() {
		return sessionPremakeInternalFlg;
	}

	public void setSessionPremakeInternalFlg(Boolean sessionPremakeInternalFlg) {
		this.sessionPremakeInternalFlg = sessionPremakeInternalFlg;
	}
}
