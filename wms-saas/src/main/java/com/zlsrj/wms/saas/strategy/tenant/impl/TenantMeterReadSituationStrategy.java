package com.zlsrj.wms.saas.strategy.tenant.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.service.ITenantMeterReadSituationService;
import com.zlsrj.wms.saas.strategy.tenant.TenantInsertStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantMeterReadSituationStrategy implements TenantInsertStrategy {
	@Autowired
	private ITenantMeterReadSituationService tenantMeterReadSituationService;

	@Override
	public boolean initData(TenantInfo tenantInfo) {
		boolean success = false;
		
		success = tenantMeterReadSituationService.saveBatchByTenantInfo(tenantInfo);
		
		return success;
	}

}
