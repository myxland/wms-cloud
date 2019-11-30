package com.zlsrj.wms.module.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.module.service.ITenantModuleAndMenuService;
import com.zlsrj.wms.module.service.ITenantModuleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantModuleAndMenuServiceImpl implements ITenantModuleAndMenuService {

	@Resource
	private ITenantModuleService tenantModuleService;

	@Override
	public boolean initByTenant(TenantInfo tenantInfo) {
		boolean success = tenantModuleService.saveBatchByTenantInfo(tenantInfo);
		return success;
	}

}
