package com.zlsrj.wms.saas.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterAddParam;
import com.zlsrj.wms.api.dto.TenantMeterBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantMeterUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeter;

public interface ITenantMeterService extends IService<TenantMeter> {
	TenantMeter getAggregation(Wrapper<TenantMeter> wrapper);
	
	String save(TenantMeterAddParam tenantMeterAddParam);

	boolean updateById(TenantMeterUpdateParam tenantMeterUpdateParam);
	
	boolean updateByIds(TenantMeterBatchUpdateParam tenantMeterBatchUpdateParam);

	List<TenantMeter> getMaxMeterCode();
}