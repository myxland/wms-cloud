package com.zlsrj.wms.mbg.service;

import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantRbacService {
	boolean initByTenant(TenantInfo tenantInfo);
}
