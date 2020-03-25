package com.zlsrj.wms.saas.strategy.tenant.insert.impl;

import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.strategy.tenant.insert.TenantInsertStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantOperatorStrategy implements TenantInsertStrategy {

	private final static String SMS_TEMPLATE="%s：您有一个新租户开通，租户名称：%s，登录账号：%s，登录密码：%s，请即时与客户联系。";
	
	@Override
	public boolean initData(TenantInfo tenantInfo) {
//		发送短信至运营人员手机
//		XXX：
//
//		您有一个新租户开通，租户名称：XXX，登录账号：手机号，登录密码：XXX，请即时与客户联系。
		String operatorMobile = "";
		String operatorName = "";
		
		String tenantName = tenantInfo.getTenantName();
		String tenantLinkmanMobile = tenantInfo.getTenantLinkmanMobile();
//		String tenantLinkman = tenantInfo.getTenantLinkman();
		
		log.info(operatorMobile);
		log.info(String.format(SMS_TEMPLATE, operatorName,tenantName,tenantLinkmanMobile,tenantLinkmanMobile.substring(tenantLinkmanMobile.length()-6)));
		
		return true;
	}

}
