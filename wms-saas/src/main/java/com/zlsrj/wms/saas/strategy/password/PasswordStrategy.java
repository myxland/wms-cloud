package com.zlsrj.wms.saas.strategy.password;

import com.zlsrj.wms.api.entity.TenantEmployee;

public interface PasswordStrategy {
	String getPassword(TenantEmployee tenantEmployee);
}
