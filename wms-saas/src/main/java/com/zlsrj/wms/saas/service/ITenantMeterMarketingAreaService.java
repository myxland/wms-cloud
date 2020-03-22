package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterMarketingArea;

public interface ITenantMeterMarketingAreaService extends IService<TenantMeterMarketingArea> {
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
	
	String save(TenantMeterMarketingAreaAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantMeterMarketingAreaUpdateParam tenantCustomerTypeUpdateParam);

}