package com.zlsrj.wms.mbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.mbg.entity.TenantSystem;

public interface ITenantSystemService extends IService<TenantSystem> {
	boolean initByTenant(Long tenantId, Integer tenantType);
}
