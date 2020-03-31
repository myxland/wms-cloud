package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCustomerAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerUpdateParam;
import com.zlsrj.wms.api.vo.TenantCustomerVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantCustomer")
public interface TenantCustomerClientService {
	@RequestMapping(value = "/tenant-customers/{id}", method = RequestMethod.GET)
	public TenantCustomerVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-customers/list", method = RequestMethod.GET)
	public List<TenantCustomerVo> list(@RequestBody TenantCustomerQueryParam tenantCustomerQueryParam);

	@RequestMapping(value = "/tenant-customers/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantCustomerQueryParam tenantCustomerQueryParam);

	@RequestMapping(value = "/tenant-customers", method = RequestMethod.GET)
	public Page<TenantCustomerVo> page(@RequestBody TenantCustomerQueryParam tenantCustomerQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-customers/aggregation", method = RequestMethod.GET)
	public TenantCustomerVo aggregation(@RequestBody TenantCustomerQueryParam tenantCustomerQueryParam);

	@RequestMapping(value = "/tenant-customers", method = RequestMethod.POST)
	public String save(@RequestBody TenantCustomerAddParam tenantCustomerAddParam);

	@RequestMapping(value = "/tenant-customers/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantCustomerUpdateParam tenantCustomerUpdateParam);

	@RequestMapping(value = "/tenant-customers/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}