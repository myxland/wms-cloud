package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantPriceStepAddParam;
import com.zlsrj.wms.api.dto.TenantPriceStepUpdateParam;
import com.zlsrj.wms.api.entity.TenantPriceStep;

public interface ITenantPriceStepService extends IService<TenantPriceStep> {
	TenantPriceStep getAggregation(Wrapper<TenantPriceStep> wrapper);
	
	String save(TenantPriceStepAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantPriceStepUpdateParam tenantCustomerTypeUpdateParam);
	
}