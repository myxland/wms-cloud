package com.zlsrj.wms.saas.strategy.tenant;

import com.zlsrj.wms.api.entity.TenantInfo;

public interface TenantInsertStrategy {
	boolean initData(TenantInfo tenantInfo);
}
