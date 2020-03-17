package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterTypeAddParam;
import com.zlsrj.wms.api.dto.TenantMeterTypeUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterType;

public interface ITenantMeterTypeService extends IService<TenantMeterType> {
	String save(TenantMeterTypeAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantMeterTypeUpdateParam tenantCustomerTypeUpdateParam);

}