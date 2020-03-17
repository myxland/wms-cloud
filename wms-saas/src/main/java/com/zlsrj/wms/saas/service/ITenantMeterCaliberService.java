package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterCaliberAddParam;
import com.zlsrj.wms.api.dto.TenantMeterCaliberUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterCaliber;

public interface ITenantMeterCaliberService extends IService<TenantMeterCaliber> {
	String save(TenantMeterCaliberAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantMeterCaliberUpdateParam tenantCustomerTypeUpdateParam);

}