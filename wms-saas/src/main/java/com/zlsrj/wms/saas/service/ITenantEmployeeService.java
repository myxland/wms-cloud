package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.entity.TenantEmployee;

public interface ITenantEmployeeService extends IService<TenantEmployee> {
	String save(TenantEmployeeAddParam tenantEmployeeAddParam);
}