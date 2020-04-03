package com.zlsrj.wms.saas.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantBookAddParam;
import com.zlsrj.wms.api.dto.TenantBookBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantBookUpdateParam;
import com.zlsrj.wms.api.entity.TenantBook;

public interface ITenantBookService extends IService<TenantBook> {
	TenantBook getAggregation(Wrapper<TenantBook> wrapper);
	
	String save(TenantBookAddParam TenantBookAddParam);

	boolean updateById(TenantBookUpdateParam tenantBookUpdateParam);
	
	boolean updateByIds(TenantBookBatchUpdateParam tenantBookBatchUpdateParam);

	List<TenantBook> getMaxBookCode();
	
	List<TenantBook> getReaderEmployeeList(QueryWrapper<TenantBook> queryWrapperTenantBook);
}