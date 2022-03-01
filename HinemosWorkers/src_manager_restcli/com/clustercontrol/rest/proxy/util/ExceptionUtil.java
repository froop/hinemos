/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */

package com.clustercontrol.rest.proxy.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openapitools.client.model.ExceptionBody;

import com.clustercontrol.fault.HinemosException;
import com.clustercontrol.fault.HinemosUnknown;
import com.clustercontrol.fault.RestConnectFailed;
import com.clustercontrol.rest.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExceptionUtil {
	private static Log m_log = LogFactory.getLog(ExceptionUtil.class);

	// ApiExceptionの内容をチェックし、マネージャ由来のExceptionがセットされていれば変換する
	public static Exception conversionApiException(ApiException apiException) {
		m_log.debug("conversionApiException : start");
		if (apiException.getResponseBody() == null) {
			// マネージャからのレスポンスによるExceptionでない(=getResponseBodyが空)なら処理の対象外
			if (m_log.isDebugEnabled()) {
				if (apiException.getCause() != null) {
					m_log.debug("conversionApiException :getResponseBody() = null " + "Cause : "
							+ apiException.getCause().getClass().getName() + "Cause  Mesaage : "
							+ apiException.getCause().getMessage());
				} else {
					m_log.debug("conversionApiException :getResponseBody() = null ");
				}
			}
			return new RestConnectFailed(apiException.getMessage(), apiException.getCause());
		}
		if (m_log.isDebugEnabled()) {
			m_log.debug("conversionApiException : Status code: " + apiException.getCode() + "Response headers: "
					+ apiException.getResponseHeaders() + "getResponseBody(): " + apiException.getResponseBody());
		}

		if (apiException.getCode() == 407) {
			// StatusCode 407なら httppプロキシの認証エラーなので対応するExcepitonを生成
			String excepitonMessage = "407 Proxy Authentication Required";
			m_log.warn("conversionApiException :" + excepitonMessage + " Status code: " + apiException.getCode()
			+ " Response headers: " + apiException.getResponseHeaders() + " getResponseBody(): "
			+ apiException.getResponseBody());
			return new HinemosUnknown(excepitonMessage);
		}

		ObjectMapper mapper = new ObjectMapper();
		ExceptionBody eb = null;
		HinemosException throwTarget = null;
		try {
			eb = mapper.readValue(apiException.getResponseBody(), ExceptionBody.class);
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Class<? extends HinemosException> clazz = (Class) Class.forName(eb.getException());
			throwTarget = clazz.getConstructor(String.class).newInstance(eb.getMessage());
		} catch (Exception e) {
			// 想定外エラー 取得したレスポンスが不正なフォーマットなら発生する。通常はありえない。
			m_log.warn("conversionApiException :An error occurred during data object conversion.  getResponseBody() ="
					+ apiException.getResponseBody());
			return new HinemosUnknown(e);
		}
		return throwTarget;
	}

}
