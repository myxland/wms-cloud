package com.zlsrj.wms.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantRoleMenu;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.ModuleInfo;

public interface ITenantRoleMenuService extends IService<TenantRoleMenu> {
	/**
	 * 根据新建租户信息创建默认用户类型
	 * @param tenantInfo
	 * @return
	 */
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
	/**
	 * 根据新建模块信息创建默认用户类型
	 * @param moduleInfo
	 * @return
	 */
	boolean saveBatchByModuleInfo(ModuleInfo moduleInfo);
}
