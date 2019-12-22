package com.zlsrj.wms.employee.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantRoleMenu;
import com.zlsrj.wms.employee.mapper.TenantRoleMenuMapper;
import com.zlsrj.wms.employee.service.IIdService;
import com.zlsrj.wms.employee.service.ITenantRoleMenuService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantRoleMenuServiceImpl extends ServiceImpl<TenantRoleMenuMapper, TenantRoleMenu> implements ITenantRoleMenuService {
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
