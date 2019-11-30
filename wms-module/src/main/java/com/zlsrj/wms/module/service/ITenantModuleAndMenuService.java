package com.zlsrj.wms.module.service;

import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantModuleAndMenuService{
	/*
	 *  创建模块 创建菜单
	 */
	boolean initByTenant(TenantInfo tenantInfo);
}
