package com.zlsrj.wms.saas.strategy.tenant.remove;

import com.zlsrj.wms.api.entity.TenantInfo;

public interface TenantRemoveStrategy {
	boolean removeData(TenantInfo tenantInfo);
}
