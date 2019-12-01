package com.zlsrj.wms.tenant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.api.entity.TenantModuleBatch;

public interface ITenantModuleService extends IService<TenantModule> {
	/**
	 * 根据新建租户信息创建默认用户类型
	 * 
	 * @param tenantInfo
	 * @return
	 */
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);

	/**
	 * 根据新建模块信息创建默认用户类型
	 * 
	 * @param moduleInfo
	 * @return
	 */
	boolean saveBatchByModuleInfo(ModuleInfo moduleInfo);

	/**
	 * 批量新增租户与模块关系(根据租户ID)
	 */
	boolean saveBatchTenantModuleByTenantId(Long tenantId, TenantModuleBatch tenantModuleBatch);

	/**
	 * 批量新增租户与模块关系(根据模块ID)
	 */
	boolean saveBatchTenantModuleByModuleId(Long moduleId, TenantModuleBatch tenantModuleBatch);
}
