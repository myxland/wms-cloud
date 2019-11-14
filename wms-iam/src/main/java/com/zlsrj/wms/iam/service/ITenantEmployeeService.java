package com.zlsrj.wms.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantEmployeeService extends IService<TenantEmployee> {
	boolean initByTenant(TenantInfo tenantInfo);

	TenantEmployee getByEmpName(String empName);
}
