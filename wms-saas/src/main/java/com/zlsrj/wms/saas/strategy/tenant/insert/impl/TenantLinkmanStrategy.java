package com.zlsrj.wms.saas.strategy.tenant.insert.impl;

import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.strategy.tenant.insert.TenantInsertStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantLinkmanStrategy implements TenantInsertStrategy {

	private final static String SMS_TEMPLATE="尊敬的 %s：您已经成功开通XXX云平台，登录账号：手机号，登录密码： %s，请您即时登录www.zlsrj.com ，开通您所需要的相关服务，如有任何疑问，请拨打客服专线：XXX。";
	
	@Override
	public boolean initData(TenantInfo tenantInfo) {
//		发送短信至新租户联系人手机
//		尊敬的 XXX：
//
//		您已经成功开通XXX云平台，登录账号：手机号，登录密码：XXX，请您即时登录www.zlsrj.com ，开通您所需要的相关服务，如有任何疑问，请拨打客服专线：XXX。
		String tenantLinkmanMobile = tenantInfo.getTenantLinkmanMobile();
		String tenantLinkman = tenantInfo.getTenantLinkman();
		
		log.info(String.format(SMS_TEMPLATE, tenantLinkman,tenantLinkmanMobile.substring(tenantLinkmanMobile.length()-6)));
		
		return true;
	}

}
