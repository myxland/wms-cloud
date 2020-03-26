package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterModelAddParam;
import com.zlsrj.wms.api.dto.TenantMeterModelUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterModel;

public interface ITenantMeterModelService extends IService<TenantMeterModel> {
	
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
	
	boolean removeBatchByTenantInfo(TenantInfo tenantInfo);
	
	String save(TenantMeterModelAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantMeterModelUpdateParam tenantCustomerTypeUpdateParam);

}