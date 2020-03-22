package com.zlsrj.wms.saas.strategy.tenant.impl;

import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.strategy.tenant.TenantInsertStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantEmployeeStrategy implements TenantInsertStrategy {

	@Override
	public boolean initData(TenantInfo tenantInfo) {
		boolean success = false;
		
		return success;
	}

}
