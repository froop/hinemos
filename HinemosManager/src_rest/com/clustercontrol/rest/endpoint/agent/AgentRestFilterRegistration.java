/*
 * Copyright (c) 2022 NTT DATA INTELLILINK Corporation.
 *
 * Hinemos (http://www.hinemos.info/)
 *
 * See the LICENSE file for licensing information.
 */

package com.clustercontrol.rest.endpoint.agent;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.clustercontrol.rest.filter.AgentSendSettingAcquisitionFilter;
import com.clustercontrol.rest.filter.BasicAuthenticationFilter;

public class AgentRestFilterRegistration implements DynamicFeature {
	private static final Log log = LogFactory.getLog(AgentRestFilterRegistration.class);

	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		if (log.isDebugEnabled()) {
			log.debug("AgentRestFilterRegistration : resourceMethod=" + resourceInfo.getResourceClass().getName() + "#" + resourceInfo.getResourceMethod().getName());
		}
		if (AgentRestEndpoints.class.isAssignableFrom(resourceInfo.getResourceClass())) {
			if( !(resourceInfo.getResourceMethod().getName().equals("healthCheck")) ){
				//Basic認証を設定
				context.register(BasicAuthenticationFilter.class);
				// 送信取得
				context.register(AgentSendSettingAcquisitionFilter.class);
			}
		}
	}

}
