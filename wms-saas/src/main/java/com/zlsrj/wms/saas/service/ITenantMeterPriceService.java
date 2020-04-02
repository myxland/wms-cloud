package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterPriceAddParam;
import com.zlsrj.wms.api.dto.TenantMeterPriceUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterPrice;

public interface ITenantMeterPriceService extends IService<TenantMeterPrice> {
	TenantMeterPrice getAggregation(Wrapper<TenantMeterPrice> wrapper);
	
	String save(TenantMeterPriceAddParam tenantMeterPriceAddParam);

	boolean updateById(TenantMeterPriceUpdateParam tenantMeterPriceUpdateParam);

}