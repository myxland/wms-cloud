package com.zlsrj.wms.mbg.service;

import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantBaseService {
	boolean initByTenant(TenantInfo tenantInfo);
}
