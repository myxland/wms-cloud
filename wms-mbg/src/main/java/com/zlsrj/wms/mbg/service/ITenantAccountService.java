package com.zlsrj.wms.mbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantAccount;

public interface ITenantAccountService extends IService<TenantAccount> {
	boolean initByTenant(Long tenantId);
}
