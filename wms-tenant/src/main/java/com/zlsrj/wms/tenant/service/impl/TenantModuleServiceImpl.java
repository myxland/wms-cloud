package com.zlsrj.wms.tenant.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.tenant.mapper.TenantModuleMapper;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantModuleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantModuleServiceImpl extends ServiceImpl<TenantModuleMapper, TenantModule> implements ITenantModuleService {
	@Resource
	private IIdService idService;

	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		return false;
	}

	@Override
	public boolean saveBatchByModuleInfo(ModuleInfo moduleInfo) {
		return false;
	}

}
