package com.zlsrj.wms.employee.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantRoleModule;
import com.zlsrj.wms.employee.mapper.TenantRoleModuleMapper;
import com.zlsrj.wms.employee.service.IIdService;
import com.zlsrj.wms.employee.service.ITenantRoleModuleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantRoleModuleServiceImpl extends ServiceImpl<TenantRoleModuleMapper, TenantRoleModule> implements ITenantRoleModuleService {
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
