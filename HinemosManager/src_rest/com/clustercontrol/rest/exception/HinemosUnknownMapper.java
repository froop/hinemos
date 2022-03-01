/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */
package com.clustercontrol.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.clustercontrol.fault.HinemosUnknown;

@Provider
public class HinemosUnknownMapper implements ExceptionMapper<HinemosUnknown> {

	@Override
	public Response toResponse(HinemosUnknown exception) {
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new ExceptionBody(Status.INTERNAL_SERVER_ERROR.getStatusCode(), exception)).build();
	}
}
