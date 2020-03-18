package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantPriceDetailAddParam;
import com.zlsrj.wms.api.dto.TenantPriceDetailUpdateParam;
import com.zlsrj.wms.api.entity.TenantPriceDetail;

public interface ITenantPriceDetailService extends IService<TenantPriceDetail> {
	TenantPriceDetail getAggregation(Wrapper<TenantPriceDetail> wrapper);
	
	String save(TenantPriceDetailAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantPriceDetailUpdateParam tenantCustomerTypeUpdateParam);

}