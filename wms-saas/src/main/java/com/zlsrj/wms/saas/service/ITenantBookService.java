package com.zlsrj.wms.saas.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantBookAddParam;
import com.zlsrj.wms.api.dto.TenantBookUpdateParam;
import com.zlsrj.wms.api.entity.TenantBook;

public interface ITenantBookService extends IService<TenantBook> {
	TenantBook getAggregation(Wrapper<TenantBook> wrapper);
	
	String save(TenantBookAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantBookUpdateParam tenantCustomerTypeUpdateParam);

	List<TenantBook> getMaxBookCode();
}