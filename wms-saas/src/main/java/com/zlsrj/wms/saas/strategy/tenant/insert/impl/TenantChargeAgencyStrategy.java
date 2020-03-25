package com.zlsrj.wms.saas.strategy.tenant.insert.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.service.ITenantChargeAgencyService;
import com.zlsrj.wms.saas.strategy.tenant.insert.TenantInsertStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantChargeAgencyStrategy implements TenantInsertStrategy {
	@Autowired
	private ITenantChargeAgencyService tenantChargeAgencyService;

	@Override
	public boolean initData(TenantInfo tenantInfo) {
		boolean success = false;
		
		success = tenantChargeAgencyService.saveBatchByTenantInfo(tenantInfo);
		
		return success;
	}

}
