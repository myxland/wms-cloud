package com.zlsrj.wms.mbg.service;

import com.zlsrj.wms.mbg.entity.TenantInfo;

public interface ITenantBaseService {
	boolean initByTenant(TenantInfo tenantInfo);
}
