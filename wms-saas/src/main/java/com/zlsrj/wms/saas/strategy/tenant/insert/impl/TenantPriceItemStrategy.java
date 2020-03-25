package com.zlsrj.wms.saas.strategy.tenant.insert.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.service.ITenantPriceItemService;
import com.zlsrj.wms.saas.strategy.tenant.insert.TenantInsertStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantPriceItemStrategy implements TenantInsertStrategy {
	@Autowired
	private ITenantPriceItemService tenantPriceItemService;

	@Override
	public boolean initData(TenantInfo tenantInfo) {
		boolean success = false;
		
		success = tenantPriceItemService.saveBatchByTenantInfo(tenantInfo);
		
		return success;
	}

}
