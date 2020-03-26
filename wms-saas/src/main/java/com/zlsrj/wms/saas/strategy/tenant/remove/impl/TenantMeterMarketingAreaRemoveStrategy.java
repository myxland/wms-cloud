package com.zlsrj.wms.saas.strategy.tenant.remove.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.service.ITenantMeterMarketingAreaService;
import com.zlsrj.wms.saas.strategy.tenant.remove.TenantRemoveStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantMeterMarketingAreaRemoveStrategy implements TenantRemoveStrategy {
	@Autowired
	private ITenantMeterMarketingAreaService tenantMeterMarketingAreaService;

	@Override
	public boolean removeData(TenantInfo tenantInfo) {
		boolean success = false;
		
		success = tenantMeterMarketingAreaService.removeBatchByTenantInfo(tenantInfo);
		
		return success;
	}

}
