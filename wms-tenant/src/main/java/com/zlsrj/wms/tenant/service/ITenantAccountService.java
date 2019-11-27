package com.zlsrj.wms.tenant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantAccount;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantAccountService extends IService<TenantAccount> {
	/**
	 * 根据新建租户信息创建默认租户账户
	 * @param tenantInfo
	 * @return
	 */
	boolean saveByTenantInfo(TenantInfo tenantInfo);
}
