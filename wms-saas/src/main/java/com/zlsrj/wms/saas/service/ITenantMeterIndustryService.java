package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterIndustryAddParam;
import com.zlsrj.wms.api.dto.TenantMeterIndustryUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterIndustry;

public interface ITenantMeterIndustryService extends IService<TenantMeterIndustry> {
	String save(TenantMeterIndustryAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantMeterIndustryUpdateParam tenantCustomerTypeUpdateParam);

}