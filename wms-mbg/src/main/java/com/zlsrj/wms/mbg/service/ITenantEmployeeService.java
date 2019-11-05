package com.zlsrj.wms.mbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.mbg.entity.TenantEmployee;
import com.zlsrj.wms.mbg.entity.TenantInfo;

public interface ITenantEmployeeService extends IService<TenantEmployee> {
	boolean initByTenant(TenantInfo tenantInfo);
}
