package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.dto.TenantEmployeeUpdateParam;
import com.zlsrj.wms.api.entity.TenantEmployee;

public interface ITenantEmployeeService extends IService<TenantEmployee> {
	String save(TenantEmployeeAddParam tenantEmployeeAddParam);
	boolean updateById(String id,TenantEmployeeUpdateParam tenantEmployeeUpdateParam);
	boolean update(String id,TenantEmployeeUpdateParam tenantEmployeeUpdateParam);
}