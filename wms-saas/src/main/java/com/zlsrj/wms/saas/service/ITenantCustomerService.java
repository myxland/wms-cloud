package com.zlsrj.wms.saas.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantCustomerAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomer;

public interface ITenantCustomerService extends IService<TenantCustomer> {
	TenantCustomer getAggregation(Wrapper<TenantCustomer> wrapper);
	
	String save(TenantCustomerAddParam tenantCustomerAddParam);

	boolean updateById(TenantCustomerUpdateParam tenantCustomerUpdateParam);

	List<TenantCustomer> getMaxCustomerCode();
}