package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterCaliberAddParam;
import com.zlsrj.wms.api.dto.TenantMeterCaliberUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterCaliber;

public interface ITenantMeterCaliberService extends IService<TenantMeterCaliber> {
	
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
	
	boolean removeBatchByTenantInfo(TenantInfo tenantInfo);
	
	String save(TenantMeterCaliberAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantMeterCaliberUpdateParam tenantCustomerTypeUpdateParam);

}