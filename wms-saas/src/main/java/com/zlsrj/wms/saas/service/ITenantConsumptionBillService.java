package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantConsumptionBillAddParam;
import com.zlsrj.wms.api.dto.TenantConsumptionBillUpdateParam;
import com.zlsrj.wms.api.entity.TenantConsumptionBill;

public interface ITenantConsumptionBillService extends IService<TenantConsumptionBill> {
	TenantConsumptionBill getAggregation(Wrapper<TenantConsumptionBill> wrapper);
	
	String save(TenantConsumptionBillAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantConsumptionBillUpdateParam tenantCustomerTypeUpdateParam);

}