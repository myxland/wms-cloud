package com.zlsrj.wms.saas.strategy.tenant.remove.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.service.ITenantChargeAgencyService;
import com.zlsrj.wms.saas.strategy.tenant.remove.TenantRemoveStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantChargeAgencyRemoveStrategy implements TenantRemoveStrategy {
	@Autowired
	private ITenantChargeAgencyService tenantChargeAgencyService;

	@Override
	public boolean removeData(TenantInfo tenantInfo) {
		boolean success = false;
		
		success = tenantChargeAgencyService.removeBatchByTenantInfo(tenantInfo);
		
		return success;
	}

}
