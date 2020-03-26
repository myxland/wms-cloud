package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterIndustryAddParam;
import com.zlsrj.wms.api.dto.TenantMeterIndustryUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterIndustry;

public interface ITenantMeterIndustryService extends IService<TenantMeterIndustry> {
	
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
	
	boolean removeBatchByTenantInfo(TenantInfo tenantInfo);
	
	String save(TenantMeterIndustryAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantMeterIndustryUpdateParam tenantCustomerTypeUpdateParam);

}