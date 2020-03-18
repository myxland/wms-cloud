package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantPriceItemAddParam;
import com.zlsrj.wms.api.dto.TenantPriceItemUpdateParam;
import com.zlsrj.wms.api.entity.TenantPriceItem;

public interface ITenantPriceItemService extends IService<TenantPriceItem> {
	TenantPriceItem getAggregation(Wrapper<TenantPriceItem> wrapper);
	
	String save(TenantPriceItemAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantPriceItemUpdateParam tenantCustomerTypeUpdateParam);

}