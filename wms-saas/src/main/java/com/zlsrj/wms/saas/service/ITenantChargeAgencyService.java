package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantChargeAgencyAddParam;
import com.zlsrj.wms.api.dto.TenantChargeAgencyUpdateParam;
import com.zlsrj.wms.api.entity.TenantChargeAgency;

public interface ITenantChargeAgencyService extends IService<TenantChargeAgency> {
	String save(TenantChargeAgencyAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantChargeAgencyUpdateParam tenantCustomerTypeUpdateParam);

}