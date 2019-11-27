package com.zlsrj.wms.tenant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantConfig;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantConfigService extends IService<TenantConfig> {
	/**
	 * 根据新建租户信息创建默认租户基础配置
	 * @param tenantInfo
	 * @return
	 */
	boolean saveByTenantInfo(TenantInfo tenantInfo);
}
