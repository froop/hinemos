/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */
package com.clustercontrol.rest.endpoint.utility.controller;

import java.util.List;

import com.clustercontrol.filtersetting.bean.FilterOwner;
import com.clustercontrol.filtersetting.bean.FilterSettingInfo;
import com.clustercontrol.filtersetting.session.FilterSettingControllerBean;
import com.clustercontrol.rest.endpoint.filtersetting.dto.AddJobHistoryFilterSettingRequest;
import com.clustercontrol.rest.endpoint.filtersetting.dto.ModifyJobHistoryFilterSettingRequest;
import com.clustercontrol.rest.endpoint.filtersetting.dto.enumtype.FilterCategoryEnum;
import com.clustercontrol.rest.endpoint.utility.dto.ImportJobHistoryFilterSettingRecordRequest;
import com.clustercontrol.rest.endpoint.utility.dto.RecordRegistrationResponse;
import com.clustercontrol.rest.endpoint.utility.dto.enumtype.ImportResultEnum;
import com.clustercontrol.rest.util.RestBeanUtil;
import com.clustercontrol.rest.util.RestCommonValitater;

public class ImportFilterSettingJobHistoryController extends AbstractImportController<ImportJobHistoryFilterSettingRecordRequest,RecordRegistrationResponse>{

	public ImportFilterSettingJobHistoryController(boolean isRollbackIfAbnormal, List<ImportJobHistoryFilterSettingRecordRequest> importList) {
		super(isRollbackIfAbnormal, importList);
	}
	
	public RecordRegistrationResponse proccssRecord( ImportJobHistoryFilterSettingRecordRequest importRec ) throws Exception {
		
		RecordRegistrationResponse dtoRecRes = new RecordRegistrationResponse();
		FilterSettingInfo infoReq = new FilterSettingInfo();
		
		// ControllerBean呼び出し
		if(importRec.getIsNewRecord()){
			AddJobHistoryFilterSettingRequest requestDto = new AddJobHistoryFilterSettingRequest();
			RestBeanUtil.convertBeanSimple(importRec.getImportData(), requestDto);
			
			// DTOからINFOへ変換
			RestCommonValitater.checkRequestDto(requestDto);
			requestDto.correlationCheck();
			requestDto.validate(importRec.getImportData().getCommon());
			RestBeanUtil.convertBean(requestDto, infoReq);
			infoReq.setCommon(importRec.getImportData().getCommon());
			
			// 共通フィルタorユーザフィルタで分岐
			setCommonProperty(importRec, infoReq);
			
			// 新規登録
			new FilterSettingControllerBean().addFilterSetting(infoReq);
		}else{
			ModifyJobHistoryFilterSettingRequest requestDto = new ModifyJobHistoryFilterSettingRequest();
			RestBeanUtil.convertBeanSimple(importRec.getImportData(), requestDto);
			
			// DTOからINFOへ変換
			RestCommonValitater.checkRequestDto(requestDto);
			requestDto.correlationCheck();
			requestDto.validate(importRec.getImportData().getCommon());
			RestBeanUtil.convertBean(requestDto, infoReq);
			infoReq.setCommon(importRec.getImportData().getCommon());
			infoReq.setFilterId(importRec.getImportData().getFilterId());
			
			// 共通フィルタorユーザフィルタで分岐
			setCommonProperty(importRec, infoReq);
			
			// 変更
			new FilterSettingControllerBean().modifyFilterSetting(infoReq);
		}
		
		// オブジェクト権限登録の為、複合キーの情報を返す
		StringBuilder buff = new StringBuilder();
		if(infoReq.getOwnerUserId() == null){
			buff.append(FilterOwner.ofCommon());
		} else {
			buff.append(infoReq.getOwnerUserId());
		}
		buff.append(",");
		buff.append(infoReq.getFilterId());
		dtoRecRes.setImportKeyValue(buff.toString());
		dtoRecRes.setResult(ImportResultEnum.NORMAL);
		return dtoRecRes;
	}
	
	private void setCommonProperty(ImportJobHistoryFilterSettingRecordRequest importRec, 
			FilterSettingInfo infoReq){
		// 共通フィルタorユーザフィルタで分岐
		if(importRec.getImportData().getCommon()){
			infoReq.setFilterCategory(FilterCategoryEnum.JOB_HISTORY);
			infoReq.setOwnerUserId(null);
		} else {
			infoReq.setFilterCategory(FilterCategoryEnum.JOB_HISTORY);
			infoReq.setOwnerUserId(importRec.getImportData().getOwnerUserId());
		}
	}
	
	@Override
	protected RecordRegistrationResponse getRecordResponseInstance(){
		return  new RecordRegistrationResponse();
	}
}
