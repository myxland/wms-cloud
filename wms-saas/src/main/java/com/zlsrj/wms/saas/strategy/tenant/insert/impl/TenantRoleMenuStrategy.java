package com.zlsrj.wms.saas.strategy.tenant.insert.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.saas.service.ITenantRoleMenuService;
import com.zlsrj.wms.saas.strategy.tenant.insert.TenantInsertStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantRoleMenuStrategy implements TenantInsertStrategy {
	@Autowired
	private ITenantRoleMenuService tenantRoleMenuService;

	@Override
	public boolean initData(TenantInfo tenantInfo) {
		boolean success = false;
		
		success = tenantRoleMenuService.saveBatchByTenantInfo(tenantInfo);
		
		return success;
	}

}
