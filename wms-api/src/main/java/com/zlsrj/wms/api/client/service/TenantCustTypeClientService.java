package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCustTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantCustType;
import com.zlsrj.wms.api.vo.TenantCustTypeVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-MBG", contextId = "TenantCustType")
public interface TenantCustTypeClientService {
	@RequestMapping(value = "/tenant-cust-types/{id}", method = RequestMethod.GET)
	public TenantCustTypeVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-cust-types", method = RequestMethod.GET)
	public Page<TenantCustTypeVo> page(@RequestBody TenantCustTypeQueryParam tenantCustTypeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-cust-types", method = RequestMethod.POST)
	public TenantCustTypeVo save(@RequestBody TenantCustType tenantCustType);

	@RequestMapping(value = "/tenant-cust-types/{id}", method = RequestMethod.PUT)
	public TenantCustTypeVo updateById(@PathVariable("id") Long id, @RequestBody TenantCustType tenantCustType);

	@RequestMapping(value = "/tenant-cust-types/{id}", method = RequestMethod.PATCH)
	public TenantCustTypeVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantCustType tenantCustType);

	@RequestMapping(value = "/tenant-cust-types/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

