package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCustomerTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantCustomerType;
import com.zlsrj.wms.api.vo.TenantCustomerTypeVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantCustomerType")
public interface TenantCustomerTypeClientService {
	@RequestMapping(value = "/tenant-customer-types/{id}", method = RequestMethod.GET)
	public TenantCustomerTypeVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-customer-types", method = RequestMethod.GET)
	public Page<TenantCustomerTypeVo> page(@RequestBody TenantCustomerTypeQueryParam tenantCustomerTypeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-customer-types", method = RequestMethod.POST)
	public TenantCustomerTypeVo save(@RequestBody TenantCustomerType tenantCustomerType);

	@RequestMapping(value = "/tenant-customer-types/{id}", method = RequestMethod.PUT)
	public TenantCustomerTypeVo updateById(@PathVariable("id") String id, @RequestBody TenantCustomerType tenantCustomerType);

	@RequestMapping(value = "/tenant-customer-types/{id}", method = RequestMethod.PATCH)
	public TenantCustomerTypeVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantCustomerType tenantCustomerType);

	@RequestMapping(value = "/tenant-customer-types/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

