package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantPriceAddParam;
import com.zlsrj.wms.api.dto.TenantPriceUpdateParam;
import com.zlsrj.wms.api.entity.TenantPrice;

public interface ITenantPriceService extends IService<TenantPrice> {
	TenantPrice getAggregation(Wrapper<TenantPrice> wrapper);
	
	String save(TenantPriceAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantPriceUpdateParam tenantCustomerTypeUpdateParam);

}