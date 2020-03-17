package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterBrandAddParam;
import com.zlsrj.wms.api.dto.TenantMeterBrandUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterBrand;

public interface ITenantMeterBrandService extends IService<TenantMeterBrand> {
	String save(TenantMeterBrandAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantMeterBrandUpdateParam tenantCustomerTypeUpdateParam);

}