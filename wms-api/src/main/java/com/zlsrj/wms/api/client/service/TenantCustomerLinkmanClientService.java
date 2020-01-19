package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCustomerLinkmanQueryParam;
import com.zlsrj.wms.api.entity.TenantCustomerLinkman;
import com.zlsrj.wms.api.vo.TenantCustomerLinkmanVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantCustomerLinkman")
public interface TenantCustomerLinkmanClientService {
	@RequestMapping(value = "/tenant-customer-linkmans/{id}", method = RequestMethod.GET)
	public TenantCustomerLinkmanVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-customer-linkmans", method = RequestMethod.GET)
	public Page<TenantCustomerLinkmanVo> page(@RequestBody TenantCustomerLinkmanQueryParam tenantCustomerLinkmanQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-customer-linkmans", method = RequestMethod.POST)
	public TenantCustomerLinkmanVo save(@RequestBody TenantCustomerLinkman tenantCustomerLinkman);

	@RequestMapping(value = "/tenant-customer-linkmans/{id}", method = RequestMethod.PUT)
	public TenantCustomerLinkmanVo updateById(@PathVariable("id") Long id, @RequestBody TenantCustomerLinkman tenantCustomerLinkman);

	@RequestMapping(value = "/tenant-customer-linkmans/{id}", method = RequestMethod.PATCH)
	public TenantCustomerLinkmanVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantCustomerLinkman tenantCustomerLinkman);

	@RequestMapping(value = "/tenant-customer-linkmans/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

