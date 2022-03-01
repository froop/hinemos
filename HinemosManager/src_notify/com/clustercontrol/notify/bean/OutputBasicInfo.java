/*
 * Copyright (c) 2018 NTT DATA INTELLILINK Corporation. All rights reserved.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */

package com.clustercontrol.notify.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.clustercontrol.jobmanagement.bean.RunInstructionInfo;
import com.clustercontrol.monitor.bean.EventHinemosPropertyConstant;
import com.clustercontrol.notify.util.NotifyUtil;

/**
 * 通知の基本情報を保持するクラス<BR>
 *
 * @version 3.0.0
 * @since 3.0.0
 */
public class OutputBasicInfo implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = 8483134102030125386L;

	/**
	 * 重要度。
	 * @see com.clustercontrol.bean.PriorityConstant
	 */
	private int priority;

	/**
	 * 出力日時
	 */
	private Long generationDate;

	/**
	 * プラグインID
	 */
	private String pluginId;

	/**
	 * 監視項目ID
	 */
	private String monitorId;

	/**
	 * 抑制用のサブキー（任意の文字列）
	 */
	private String subKey = "";

	/**
	 * ファシリティID
	 */
	private String facilityId;

	/**
	 * スコープ
	 */
	private String scopeText;

	/**
	 * アプリケーション
	 */
	private String application;

	/**
	 * メッセージ
	 */
	private String message;

	/**
	 * オリジナルメッセージ
	 */
	private String messageOrg;

	/**
	 * 多重化ID
	 */
	private String multiId;

	/**
	 * 承認依頼文
	 */
	private String jobApprovalText;

	/**
	 * 承認依頼メール本文
	 */
	private String jobApprovalMail;

	/**
	 * 監視ジョブ指示情報
	 */
    private RunInstructionInfo runInstructionInfo;

	/**
	 * 標準出力
	 */
    private List<String> jobMessage = new ArrayList<>();
	
	/**
	 * 標準出力ノード
	 */
    private List<String> jobFacilityId = new ArrayList<>();
	
	/**
	 * 通知グループID
	 */
	private String notifyGroupId;
	
	/**
	 * ジョブユニットID
	 */
	private String m_jobunitId = "";

	/**
	 * ジョブID
	 */
	private String m_jobId = "";

	/**
	 * 判定による重要度変化のタイプ
	 */
	private Integer priorityChangeJudgmentType;

	/**
	 * 取得失敗による重要度変化のタイプ
	 */
	private Integer priorityChangeFailureType;
	
	/**
	 * ユーザ項目01
	 */
	private String userItem01;
	
	/**
	 * ユーザ項目02
	 */
	private String userItem02;
	
	/**
	 * ユーザ項目03
	 */
	private String userItem03;
	
	/**
	 * ユーザ項目04
	 */
	private String userItem04;
	
	/**
	 * ユーザ項目05
	 */
	private String userItem05;
	
	/**
	 * ユーザ項目06
	 */
	private String userItem06;
	
	/**
	 * ユーザ項目07
	 */
	private String userItem07;
	
	/**
	 * ユーザ項目08
	 */
	private String userItem08;
	
	/**
	 * ユーザ項目09
	 */
	private String userItem09;
	
	/**
	 * ユーザ項目10
	 */
	private String userItem10;
	
	/**
	 * ユーザ項目11
	 */
	private String userItem11;
	
	/**
	 * ユーザ項目12
	 */
	private String userItem12;
	
	/**
	 * ユーザ項目13
	 */
	private String userItem13;
	
	/**
	 * ユーザ項目14
	 */
	private String userItem14;
	
	/**
	 * ユーザ項目15
	 */
	private String userItem15;
	
	/**
	 * ユーザ項目16
	 */
	private String userItem16;
	
	/**
	 * ユーザ項目17
	 */
	private String userItem17;
	
	/**
	 * ユーザ項目18
	 */
	private String userItem18;
	
	/**
	 * ユーザ項目19
	 */
	private String userItem19;
	
	/**
	 * ユーザ項目20
	 */
	private String userItem20;
	
	/**
	 * ユーザ項目21
	 */
	private String userItem21;
	
	/**
	 * ユーザ項目22
	 */
	private String userItem22;
	
	/**
	 * ユーザ項目23
	 */
	private String userItem23;
	
	/**
	 * ユーザ項目24
	 */
	private String userItem24;
	
	/**
	 * ユーザ項目25
	 */
	private String userItem25;
	
	/**
	 * ユーザ項目26
	 */
	private String userItem26;
	
	/**
	 * ユーザ項目27
	 */
	private String userItem27;
	
	/**
	 * ユーザ項目28
	 */
	private String userItem28;
	
	/**
	 * ユーザ項目29
	 */
	private String userItem29;
	
	/**
	 * ユーザ項目30
	 */
	private String userItem30;
	
	/**
	 * ユーザ項目31
	 */
	private String userItem31;
	
	/**
	 * ユーザ項目32
	 */
	private String userItem32;
	
	/**
	 * ユーザ項目33
	 */
	private String userItem33;
	
	/**
	 * ユーザ項目34
	 */
	private String userItem34;
	
	/**
	 * ユーザ項目35
	 */
	private String userItem35;
	
	/**
	 * ユーザ項目36
	 */
	private String userItem36;
	
	/**
	 * ユーザ項目37
	 */
	private String userItem37;
	
	/**
	 * ユーザ項目38
	 */
	private String userItem38;
	
	/**
	 * ユーザ項目39
	 */
	private String userItem39;
	
	/**
	 * ユーザ項目40
	 */
	private String userItem40;

	/**
	 * 通知を一意に見分けるID(UUID)
	 */
	private String notifyUUID = UUID.randomUUID().toString();

	/**
	 * ジョブ連携メッセージID
	 */
	private String joblinkMessageId;

	public OutputBasicInfo() {}

	/**
	 * アプリケーションを返します。
	 * 
	 * @return アプリケーション
	 */
	public String getApplication() {
		return application;
	}
	/**
	 * アプリケーションを設定します。
	 * 
	 * @param application アプリケーション
	 */
	public void setApplication(String application) {
		this.application = application;
	}
	/**
	 * ファシリティIDを返します。
	 * 
	 * @return ファシリティID
	 */
	public String getFacilityId() {
		return facilityId;
	}
	/**
	 * ファシリティIDを設定します。
	 * 
	 * @param id ファシリティID
	 */
	public void setFacilityId(String id) {
		facilityId = id;
	}
	/**
	 * 出力日時を返します。
	 * 
	 * @return 出力日時
	 */
	public Long getGenerationDate() {
		return generationDate;
	}
	/**
	 * 出力日時を設定します。
	 * 
	 * @param date 出力日時
	 */
	public void setGenerationDate(Long date) {
		generationDate = date;
	}
	/**
	 * メッセージを返します。
	 * 
	 * @return メッセージ
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * メッセージを設定します。
	 * 
	 * @param message メッセージ
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * オリジナルメッセージを返します。
	 * 
	 * @return オリジナルメッセージ
	 */
	public String getMessageOrg() {
		return messageOrg;
	}
	/**
	 * オリジナルメッセージを設定します。
	 * 
	 * @param org オリジナルメッセージ
	 */
	public void setMessageOrg(String org) {
		messageOrg = org;
	}
	/**
	 * 監視項目IDを返します。
	 * 
	 * @return 監視項目ID
	 */
	public String getMonitorId() {
		return monitorId;
	}
	/**
	 * 監視項目IDを設定します。
	 * 
	 * @param id 監視項目ID
	 */
	public void setMonitorId(String id) {
		monitorId = id;
	}
	/**
	 * 抑制用のサブキー（任意の文字列）を返します。
	 * 
	 * @return 抑制用のサブキー（任意の文字列）
	 */
	public String getSubKey() {
		return subKey;
	}
	/**
	 * 抑制用のサブキー（任意の文字列）を設定します。
	 * 
	 * @param subkey 抑制用のサブキー（任意の文字列）
	 */
	public void setSubKey(String subkey) {
		subKey = subkey;
	}
	/**
	 * プラグインIDを返します。
	 * 
	 * @return プラグインID
	 */
	public String getPluginId() {
		return pluginId;
	}
	/**
	 * プラグインIDを設定します。
	 * 
	 * @param id プラグインID
	 */
	public void setPluginId(String id) {
		pluginId = id;
	}
	/**
	 * 重要度を返します。
	 * 
	 * @return 重要度
	 * @see com.clustercontrol.bean.PriorityConstant
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * 重要度を設定します。
	 * 
	 * @param priority 重要度
	 * @see com.clustercontrol.bean.PriorityConstant
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * スコープを返します。
	 * 
	 * @return スコープ
	 */
	public String getScopeText() {
		return scopeText;
	}
	/**
	 * スコープを設定します。
	 * 
	 * @param text スコープ
	 */
	public void setScopeText(String text) {
		scopeText = text;
	}
	/**
	 * 多重化IDを返します。
	 * 
	 * @return 多重化ID
	 */
	public String getMultiId() {
		return multiId;
	}
	/**
	 * 多重化IDを設定します。
	 * クラスタ構成の場合、設定します。
	 * 
	 * @param id 多重化ID
	 */
	public void setMultiId(String id) {
		multiId = id;
	}
	
	/**
	 * 承認ジョブにおける承認依頼文を返します。
	 * 
	 * @return 承認依頼文
	 */
	public String getJobApprovalText() {
		return jobApprovalText;
	}
	/**
	 * 承認ジョブにおける承認依頼文を設定します。
	 * 
	 * @param txt 承認依頼文
	 */
	public void setJobApprovalText(String txt) {
		jobApprovalText = txt;
	}
	/**
	 * 承認ジョブにおける承認依頼メール本文を返します。
	 * 
	 * @return 承認依頼メール本文
	 */
	public String getJobApprovalMail() {
		return jobApprovalMail;
	}
	/**
	 * 承認ジョブにおける承認依頼メール本文を設定します。
	 * 
	 * @param txt 承認依頼メール本文
	 */
	public void setJobApprovalMail(String mail) {
		jobApprovalMail = mail;
	}

	/**
	 * 監視ジョブ指示情報を返します。
	 * 
	 * @return 監視ジョブ指示情報
	 */
	public RunInstructionInfo getRunInstructionInfo() {
        return runInstructionInfo;
	}
	/**
	 * 監視ジョブ指示情報を設定します。
	 * 
	 * @param runInstructionInfo 監視ジョブ指示情報
	 */
	public void setRunInstructionInfo(RunInstructionInfo runInstructionInfo) {
        this.runInstructionInfo = runInstructionInfo;
	}

	/**
	 *  標準出力を返します。
	 * 
	 * @return  標準出力
	 */
	public List<String> getJobMessage() {
        return jobMessage;
	}
	/**
	 *  標準出力を設定します。
	 * クラスタ構成の場合、設定します。
	 * 
	 * @param message  標準出力
	 */
	public void setJobMessage(List<String> message) {
        jobMessage = message;
	}

	
	/**
	 *  標準出力ノードを返します。
	 * 
	 * @return  標準出力ノード
	 */
	public List<String> getJobFacilityId() {
        return jobFacilityId;
	}
	/**
	 *  標準出力ノードを設定します。
	 * クラスタ構成の場合、設定します。
	 * 
	 * @param node  標準出力ノード
	 */
	public void setJobFacilityId(List<String> node) {
        jobFacilityId = node;
	}

	
	/**
	 *  通知グループIDを返します。
	 * 
	 * @return  通知グループID
	 */
	public String getNotifyGroupId() {
        return notifyGroupId;
	}
	/**
	 *  通知グループIDを設定します。
	 * 
	 * @param notifyGroupId  通知グループID
	 */
	public void setNotifyGroupId(String notifyGroupId) {
        this.notifyGroupId = notifyGroupId;
	}

	
	/**
	 *  ジョブユニットIDを返します。
	 * 
	 * @return  ジョブユニットID
	 */
	public String getJobunitId() {
		return m_jobunitId;
	}
	/**
	 *  ジョブユニットIDを設定します。
	 * 
	 * @param jobunitId  ジョブユニットID
	 */
	public void setJobunitId(String jobunitId) {
		m_jobunitId = jobunitId;
	}

	
	/**
	 *  ジョブIDを返します。
	 * 
	 * @return  ジョブID
	 */
	public String getJobId() {
		return m_jobId;
	}
	/**
	 *  ジョブIDを設定します。
	 * 
	 * @param jobId  ジョブID
	 */
	public void setJobId(String jobId) {
		m_jobId = jobId;
	}
	
	
	/**
	 * 判定による重要度変化のタイプを返します。
	 * 
	 * @return  判定による重要度変化のタイプ
	 */
	public Integer getPriorityChangeJudgmentType() {
		return priorityChangeJudgmentType;
	}
	/**
	 *  判定による重要度変化のタイプを設定します。
	 * 
	 * @param priorityChangeJudgmentType  判定による重要度変化のタイプ
	 */
	public void setPriorityChangeJudgmentType(Integer priorityChangeJudgmentType) {
		this.priorityChangeJudgmentType = priorityChangeJudgmentType;
	}

	/**
	 *  取得失敗による重要度変化のタイプを返します。
	 * 
	 * @return  取得失敗による重要度変化のタイプ
	 */
	public Integer getPriorityChangeFailureType() {
		return priorityChangeFailureType;
	}
	/**
	 *  取得失敗による重要度変化のタイプを設定します。
	 * 
	 * @param priorityChangeFailureType  取得失敗による重要度変化のタイプ
	 */
	public void setPriorityChangeFailureType(Integer priorityChangeFailureType) {
		this.priorityChangeFailureType = priorityChangeFailureType;
	}

	/**
	 * ユーザ項目01を返します。
	 * 
	 * @return ユーザ項目01
	 */
	public String getUserItem01() {
		return userItem01;
	}

	/**
	 * ユーザ項目01を設定します。
	 * 
	 * @param userItem01 ユーザ項目01
	 */
	public void setUserItem01(String userItem01) {
        this.userItem01 = userItem01;
	}

	
	/**
	 * ユーザ項目02を返します。
	 * 
	 * @return ユーザ項目02
	 */
	public String getUserItem02() {
		return userItem02;
	}

	/**
	 * ユーザ項目02を設定します。
	 * 
	 * @param userItem02 ユーザ項目02
	 */
	public void setUserItem02(String userItem02) {
        this.userItem02 = userItem02;
	}

	
	/**
	 * ユーザ項目03を返します。
	 * 
	 * @return ユーザ項目03
	 */
	public String getUserItem03() {
		return userItem03;
	}

	/**
	 * ユーザ項目03を設定します。
	 * 
	 * @param userItem03 ユーザ項目03
	 */
	public void setUserItem03(String userItem03) {
        this.userItem03 = userItem03;
	}

	
	/**
	 * ユーザ項目04を返します。
	 * 
	 * @return ユーザ項目04
	 */
	public String getUserItem04() {
		return userItem04;
	}

	/**
	 * ユーザ項目04を設定します。
	 * 
	 * @param userItem04 ユーザ項目04
	 */
	public void setUserItem04(String userItem04) {
        this.userItem04 = userItem04;
	}

	
	/**
	 * ユーザ項目05を返します。
	 * 
	 * @return ユーザ項目05
	 */
	public String getUserItem05() {
		return userItem05;
	}

	/**
	 * ユーザ項目05を設定します。
	 * 
	 * @param userItem05 ユーザ項目05
	 */
	public void setUserItem05(String userItem05) {
        this.userItem05 = userItem05;
	}

	
	/**
	 * ユーザ項目06を返します。
	 * 
	 * @return ユーザ項目06
	 */
	public String getUserItem06() {
		return userItem06;
	}

	/**
	 * ユーザ項目06を設定します。
	 * 
	 * @param userItem06 ユーザ項目06
	 */
	public void setUserItem06(String userItem06) {
        this.userItem06 = userItem06;
	}

	
	/**
	 * ユーザ項目07を返します。
	 * 
	 * @return ユーザ項目07
	 */
	public String getUserItem07() {
		return userItem07;
	}

	/**
	 * ユーザ項目07を設定します。
	 * 
	 * @param userItem07 ユーザ項目07
	 */
	public void setUserItem07(String userItem07) {
        this.userItem07 = userItem07;
	}

	
	/**
	 * ユーザ項目08を返します。
	 * 
	 * @return ユーザ項目08
	 */
	public String getUserItem08() {
		return userItem08;
	}

	/**
	 * ユーザ項目08を設定します。
	 * 
	 * @param userItem08 ユーザ項目08
	 */
	public void setUserItem08(String userItem08) {
        this.userItem08 = userItem08;
	}

	
	/**
	 * ユーザ項目09を返します。
	 * 
	 * @return ユーザ項目09
	 */
	public String getUserItem09() {
		return userItem09;
	}

	/**
	 * ユーザ項目09を設定します。
	 * 
	 * @param userItem09 ユーザ項目09
	 */
	public void setUserItem09(String userItem09) {
        this.userItem09 = userItem09;
	}

	
	/**
	 * ユーザ項目10を返します。
	 * 
	 * @return ユーザ項目10
	 */
	public String getUserItem10() {
		return userItem10;
	}

	/**
	 * ユーザ項目10を設定します。
	 * 
	 * @param userItem10 ユーザ項目10
	 */
	public void setUserItem10(String userItem10) {
        this.userItem10 = userItem10;
	}

	
	/**
	 * ユーザ項目11を返します。
	 * 
	 * @return ユーザ項目11
	 */
	public String getUserItem11() {
		return userItem11;
	}

	/**
	 * ユーザ項目11を設定します。
	 * 
	 * @param userItem11 ユーザ項目11
	 */
	public void setUserItem11(String userItem11) {
        this.userItem11 = userItem11;
	}

	
	/**
	 * ユーザ項目12を返します。
	 * 
	 * @return ユーザ項目12
	 */
	public String getUserItem12() {
		return userItem12;
	}

	/**
	 * ユーザ項目12を設定します。
	 * 
	 * @param userItem12 ユーザ項目12
	 */
	public void setUserItem12(String userItem12) {
        this.userItem12 = userItem12;
	}

	
	/**
	 * ユーザ項目13を返します。
	 * 
	 * @return ユーザ項目13
	 */
	public String getUserItem13() {
		return userItem13;
	}

	/**
	 * ユーザ項目13を設定します。
	 * 
	 * @param userItem13 ユーザ項目13
	 */
	public void setUserItem13(String userItem13) {
        this.userItem13 = userItem13;
	}

	
	/**
	 * ユーザ項目14を返します。
	 * 
	 * @return ユーザ項目14
	 */
	public String getUserItem14() {
		return userItem14;
	}

	/**
	 * ユーザ項目14を設定します。
	 * 
	 * @param userItem14 ユーザ項目14
	 */
	public void setUserItem14(String userItem14) {
        this.userItem14 = userItem14;
	}

	
	/**
	 * ユーザ項目15を返します。
	 * 
	 * @return ユーザ項目15
	 */
	public String getUserItem15() {
		return userItem15;
	}

	/**
	 * ユーザ項目15を設定します。
	 * 
	 * @param userItem15 ユーザ項目15
	 */
	public void setUserItem15(String userItem15) {
        this.userItem15 = userItem15;
	}

	
	/**
	 * ユーザ項目16を返します。
	 * 
	 * @return ユーザ項目16
	 */
	public String getUserItem16() {
		return userItem16;
	}

	/**
	 * ユーザ項目16を設定します。
	 * 
	 * @param userItem16 ユーザ項目16
	 */
	public void setUserItem16(String userItem16) {
        this.userItem16 = userItem16;
	}

	
	/**
	 * ユーザ項目17を返します。
	 * 
	 * @return ユーザ項目17
	 */
	public String getUserItem17() {
		return userItem17;
	}

	/**
	 * ユーザ項目17を設定します。
	 * 
	 * @param userItem17 ユーザ項目17
	 */
	public void setUserItem17(String userItem17) {
        this.userItem17 = userItem17;
	}

	
	/**
	 * ユーザ項目18を返します。
	 * 
	 * @return ユーザ項目18
	 */
	public String getUserItem18() {
		return userItem18;
	}

	/**
	 * ユーザ項目18を設定します。
	 * 
	 * @param userItem18 ユーザ項目18
	 */
	public void setUserItem18(String userItem18) {
        this.userItem18 = userItem18;
	}

	
	/**
	 * ユーザ項目19を返します。
	 * 
	 * @return ユーザ項目19
	 */
	public String getUserItem19() {
		return userItem19;
	}

	/**
	 * ユーザ項目19を設定します。
	 * 
	 * @param userItem19 ユーザ項目19
	 */
	public void setUserItem19(String userItem19) {
        this.userItem19 = userItem19;
	}

	
	/**
	 * ユーザ項目20を返します。
	 * 
	 * @return ユーザ項目20
	 */
	public String getUserItem20() {
		return userItem20;
	}

	/**
	 * ユーザ項目20を設定します。
	 * 
	 * @param userItem20 ユーザ項目20
	 */
	public void setUserItem20(String userItem20) {
        this.userItem20 = userItem20;
	}

	
	/**
	 * ユーザ項目21を返します。
	 * 
	 * @return ユーザ項目21
	 */
	public String getUserItem21() {
		return userItem21;
	}

	/**
	 * ユーザ項目21を設定します。
	 * 
	 * @param userItem21 ユーザ項目21
	 */
	public void setUserItem21(String userItem21) {
        this.userItem21 = userItem21;
	}

	
	/**
	 * ユーザ項目22を返します。
	 * 
	 * @return ユーザ項目22
	 */
	public String getUserItem22() {
		return userItem22;
	}

	/**
	 * ユーザ項目22を設定します。
	 * 
	 * @param userItem22 ユーザ項目22
	 */
	public void setUserItem22(String userItem22) {
        this.userItem22 = userItem22;
	}

	
	/**
	 * ユーザ項目23を返します。
	 * 
	 * @return ユーザ項目23
	 */
	public String getUserItem23() {
		return userItem23;
	}

	/**
	 * ユーザ項目23を設定します。
	 * 
	 * @param userItem23 ユーザ項目23
	 */
	public void setUserItem23(String userItem23) {
        this.userItem23 = userItem23;
	}

	
	/**
	 * ユーザ項目24を返します。
	 * 
	 * @return ユーザ項目24
	 */
	public String getUserItem24() {
		return userItem24;
	}

	/**
	 * ユーザ項目24を設定します。
	 * 
	 * @param userItem24 ユーザ項目24
	 */
	public void setUserItem24(String userItem24) {
        this.userItem24 = userItem24;
	}

	
	/**
	 * ユーザ項目25を返します。
	 * 
	 * @return ユーザ項目25
	 */
	public String getUserItem25() {
		return userItem25;
	}

	/**
	 * ユーザ項目25を設定します。
	 * 
	 * @param userItem25 ユーザ項目25
	 */
	public void setUserItem25(String userItem25) {
        this.userItem25 = userItem25;
	}

	
	/**
	 * ユーザ項目26を返します。
	 * 
	 * @return ユーザ項目26
	 */
	public String getUserItem26() {
		return userItem26;
	}

	/**
	 * ユーザ項目26を設定します。
	 * 
	 * @param userItem26 ユーザ項目26
	 */
	public void setUserItem26(String userItem26) {
        this.userItem26 = userItem26;
	}

	
	/**
	 * ユーザ項目27を返します。
	 * 
	 * @return ユーザ項目27
	 */
	public String getUserItem27() {
		return userItem27;
	}

	/**
	 * ユーザ項目27を設定します。
	 * 
	 * @param userItem27 ユーザ項目27
	 */
	public void setUserItem27(String userItem27) {
        this.userItem27 = userItem27;
	}

	
	/**
	 * ユーザ項目28を返します。
	 * 
	 * @return ユーザ項目28
	 */
	public String getUserItem28() {
		return userItem28;
	}

	/**
	 * ユーザ項目28を設定します。
	 * 
	 * @param userItem28 ユーザ項目28
	 */
	public void setUserItem28(String userItem28) {
        this.userItem28 = userItem28;
	}

	
	/**
	 * ユーザ項目29を返します。
	 * 
	 * @return ユーザ項目29
	 */
	public String getUserItem29() {
		return userItem29;
	}

	/**
	 * ユーザ項目29を設定します。
	 * 
	 * @param userItem29 ユーザ項目29
	 */
	public void setUserItem29(String userItem29) {
        this.userItem29 = userItem29;
	}

	
	/**
	 * ユーザ項目30を返します。
	 * 
	 * @return ユーザ項目30
	 */
	public String getUserItem30() {
		return userItem30;
	}

	/**
	 * ユーザ項目30を設定します。
	 * 
	 * @param userItem30 ユーザ項目30
	 */
	public void setUserItem30(String userItem30) {
        this.userItem30 = userItem30;
	}

	
	/**
	 * ユーザ項目31を返します。
	 * 
	 * @return ユーザ項目31
	 */
	public String getUserItem31() {
		return userItem31;
	}

	/**
	 * ユーザ項目31を設定します。
	 * 
	 * @param userItem31 ユーザ項目31
	 */
	public void setUserItem31(String userItem31) {
        this.userItem31 = userItem31;
	}

	
	/**
	 * ユーザ項目32を返します。
	 * 
	 * @return ユーザ項目32
	 */
	public String getUserItem32() {
		return userItem32;
	}

	/**
	 * ユーザ項目32を設定します。
	 * 
	 * @param userItem32 ユーザ項目32
	 */
	public void setUserItem32(String userItem32) {
        this.userItem32 = userItem32;
	}

	
	/**
	 * ユーザ項目33を返します。
	 * 
	 * @return ユーザ項目33
	 */
	public String getUserItem33() {
		return userItem33;
	}

	/**
	 * ユーザ項目33を設定します。
	 * 
	 * @param userItem33 ユーザ項目33
	 */
	public void setUserItem33(String userItem33) {
        this.userItem33 = userItem33;
	}

	
	/**
	 * ユーザ項目34を返します。
	 * 
	 * @return ユーザ項目34
	 */
	public String getUserItem34() {
		return userItem34;
	}

	/**
	 * ユーザ項目34を設定します。
	 * 
	 * @param userItem34 ユーザ項目34
	 */
	public void setUserItem34(String userItem34) {
        this.userItem34 = userItem34;
	}

	
	/**
	 * ユーザ項目35を返します。
	 * 
	 * @return ユーザ項目35
	 */
	public String getUserItem35() {
		return userItem35;
	}

	/**
	 * ユーザ項目35を設定します。
	 * 
	 * @param userItem35 ユーザ項目35
	 */
	public void setUserItem35(String userItem35) {
        this.userItem35 = userItem35;
	}

	
	/**
	 * ユーザ項目36を返します。
	 * 
	 * @return ユーザ項目36
	 */
	public String getUserItem36() {
		return userItem36;
	}

	/**
	 * ユーザ項目36を設定します。
	 * 
	 * @param userItem36 ユーザ項目36
	 */
	public void setUserItem36(String userItem36) {
        this.userItem36 = userItem36;
	}

	
	/**
	 * ユーザ項目37を返します。
	 * 
	 * @return ユーザ項目37
	 */
	public String getUserItem37() {
		return userItem37;
	}

	/**
	 * ユーザ項目37を設定します。
	 * 
	 * @param userItem37 ユーザ項目37
	 */
	public void setUserItem37(String userItem37) {
        this.userItem37 = userItem37;
	}

	
	/**
	 * ユーザ項目38を返します。
	 * 
	 * @return ユーザ項目38
	 */
	public String getUserItem38() {
		return userItem38;
	}

	/**
	 * ユーザ項目38を設定します。
	 * 
	 * @param userItem38 ユーザ項目38
	 */
	public void setUserItem38(String userItem38) {
        this.userItem38 = userItem38;
	}

	
	/**
	 * ユーザ項目39を返します。
	 * 
	 * @return ユーザ項目39
	 */
	public String getUserItem39() {
		return userItem39;
	}

	/**
	 * ユーザ項目39を設定します。
	 * 
	 * @param userItem39 ユーザ項目39
	 */
	public void setUserItem39(String userItem39) {
        this.userItem39 = userItem39;
	}

	
	/**
	 * ユーザ項目40を返します。
	 * 
	 * @return ユーザ項目40
	 */
	public String getUserItem40() {
		return userItem40;
	}

	/**
	 * ユーザ項目40を設定します。
	 * 
	 * @param userItem40 ユーザ項目40
	 */
	public void setUserItem40(String userItem40) {
        this.userItem40 = userItem40;
	}

	/**
	 * 通知元を識別するUUIDを返します。
	 * 
	 * @return notifyUUID 通知元の識別UUID
	 */
	public String getNotifyUUID() {
		return notifyUUID;
	}
	
	/**
	 * ジョブ連携メッセージIDを返します。
	 * 
	 * @return ジョブ連携メッセージID
	 */
	public String getJoblinkMessageId() {
		return joblinkMessageId;
	}

	/**
	 * ジョブ連携メッセージIDを設定します。
	 * 
	 * @param joblinkMessageId ジョブ連携メッセージID
	 */
	public void setJoblinkMessageId(String joblinkMessageId) {
        this.joblinkMessageId = joblinkMessageId;
	}

	@Override
	public String toString(){
		String str = "OutputBasicInfo : "
				+ "FacilityId = " + facilityId 
				+ ", PluginId = " + pluginId 
				+ ", MonitorId = " + monitorId
				+ ", NotifyGroupId = " + notifyGroupId;
		return str;
	}
	
	@Override
	public OutputBasicInfo clone(){
		OutputBasicInfo clonedInfo = null;
		try {
			clonedInfo = (OutputBasicInfo) super.clone();
			clonedInfo.setApplication(application);
			clonedInfo.setFacilityId(facilityId);
			clonedInfo.setGenerationDate(generationDate);
			clonedInfo.setMessage(message);
			clonedInfo.setMessageOrg(messageOrg);
			clonedInfo.setMonitorId(monitorId);
			clonedInfo.setMultiId(multiId);
			clonedInfo.setPluginId(pluginId);
			clonedInfo.setPriority(priority);
			clonedInfo.setScopeText(scopeText);
			clonedInfo.setSubKey(subKey);
			clonedInfo.setNotifyGroupId(notifyGroupId);
			clonedInfo.notifyUUID = notifyUUID;
			clonedInfo.setPriorityChangeJudgmentType(priorityChangeJudgmentType);
			clonedInfo.setPriorityChangeFailureType(priorityChangeFailureType);				clonedInfo.setJoblinkMessageId(joblinkMessageId);
			for (int i = 1; i <= EventHinemosPropertyConstant.USER_ITEM_SIZE; i++) {
				NotifyUtil.setUserItemValue(clonedInfo, i, NotifyUtil.getUserItemValue(this, i));
			}
		} catch (CloneNotSupportedException e) {
			// do nothing
		}
		return clonedInfo;
	}
}
