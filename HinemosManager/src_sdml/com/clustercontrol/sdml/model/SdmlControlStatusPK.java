/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */
package com.clustercontrol.sdml.model;

import java.io.Serializable;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * The primary key class for the cc_sdml_control_status database table.
 * 
 */
@Embeddable
public class SdmlControlStatusPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private String applicationId;
	private String facilityId;

	public SdmlControlStatusPK() {
	}

	public SdmlControlStatusPK(String applicationId, String facilityId) {
		this.setApplicationId(applicationId);
		this.setFacilityId(facilityId);
	}

	@Column(name="application_id")
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@Column(name="facility_id")
	public String getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SdmlControlStatusPK)) {
			return false;
		}
		SdmlControlStatusPK castOther = (SdmlControlStatusPK)other;
		return
				this.applicationId.equals(castOther.applicationId)
				&& this.facilityId.equals(castOther.facilityId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationId.hashCode();
		hash = hash * prime + this.facilityId.hashCode();
		return hash;
	}

	@Override
	public String toString() {
		String[] names = {
				"applicationId",
				"facilityId"
		};
		String[] values = {
				this.applicationId,
				this.facilityId
		};
		return Arrays.toString(names) + " = " + Arrays.toString(values);
	}
}
