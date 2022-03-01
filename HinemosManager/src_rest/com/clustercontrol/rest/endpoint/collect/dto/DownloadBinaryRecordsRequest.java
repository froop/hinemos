/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */
package com.clustercontrol.rest.endpoint.collect.dto;

import java.util.List;

import com.clustercontrol.fault.InvalidSetting;
import com.clustercontrol.rest.dto.RequestDto;

public class DownloadBinaryRecordsRequest implements RequestDto {

	public DownloadBinaryRecordsRequest() {
	}

	private List<QueryCollectBinaryDataRequest> queryCollectBinaryDataRequest;

	private String filename;

	private List<DownloadBinaryRecordsKeyRequest> records;

	public List<QueryCollectBinaryDataRequest> getQueryCollectBinaryDataRequest() {
		return this.queryCollectBinaryDataRequest;
	}

	public void setQueryCollectBinaryDataRequest(List<QueryCollectBinaryDataRequest> queryCollectBinaryDataRequest) {
		this.queryCollectBinaryDataRequest = queryCollectBinaryDataRequest;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<DownloadBinaryRecordsKeyRequest> getRecords() {
		return records;
	}

	public void setRecords(List<DownloadBinaryRecordsKeyRequest> records) {
		this.records = records;
	}

	@Override
	public void correlationCheck() throws InvalidSetting {
	}
}
