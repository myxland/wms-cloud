package com.zlsrj.wms.saas.strategy.tenant.remove.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.service.ITenantMeterReadSituationService;
import com.zlsrj.wms.saas.strategy.tenant.remove.TenantRemoveStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantMeterReadSituationRemoveStrategy implements TenantRemoveStrategy {
	@Autowired
	private ITenantMeterReadSituationService tenantMeterReadSituationService;

	@Override
	public boolean removeData(TenantInfo tenantInfo) {
		boolean success = false;
		
		success = tenantMeterReadSituationService.removeBatchByTenantInfo(tenantInfo);
		
		return success;
	}

}
