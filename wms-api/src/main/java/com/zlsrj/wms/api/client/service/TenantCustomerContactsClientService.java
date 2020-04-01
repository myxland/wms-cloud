package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCustomerContactsAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerContactsQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerContactsUpdateParam;
import com.zlsrj.wms.api.vo.TenantCustomerContactsVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantCustomerContacts")
public interface TenantCustomerContactsClientService {
	@RequestMapping(value = "/tenant-customer-contactss/{id}", method = RequestMethod.GET)
	public TenantCustomerContactsVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-customer-contactss/list", method = RequestMethod.GET)
	public List<TenantCustomerContactsVo> list(@RequestBody TenantCustomerContactsQueryParam tenantCustomerContactsQueryParam);

	@RequestMapping(value = "/tenant-customer-contactss/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantCustomerContactsQueryParam tenantCustomerContactsQueryParam);

	@RequestMapping(value = "/tenant-customer-contactss", method = RequestMethod.GET)
	public Page<TenantCustomerContactsVo> page(@RequestBody TenantCustomerContactsQueryParam tenantCustomerContactsQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-customer-contactss", method = RequestMethod.POST)
	public String save(@RequestBody TenantCustomerContactsAddParam tenantCustomerContactsAddParam);

	@RequestMapping(value = "/tenant-customer-contactss/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantCustomerContactsUpdateParam tenantCustomerContactsUpdateParam);

	@RequestMapping(value = "/tenant-customer-contactss/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}