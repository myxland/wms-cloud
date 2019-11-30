package com.zlsrj.wms.module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantModuleService extends IService<TenantModule> {
	/**
	 * 根据新建租户信息创建默认用户类型
	 * @param tenantInfo
	 * @return
	 */
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
}
