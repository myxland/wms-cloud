package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterModelAddParam;
import com.zlsrj.wms.api.dto.TenantMeterModelUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterModel;

public interface ITenantMeterModelService extends IService<TenantMeterModel> {
	String save(TenantMeterModelAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantMeterModelUpdateParam tenantCustomerTypeUpdateParam);

}