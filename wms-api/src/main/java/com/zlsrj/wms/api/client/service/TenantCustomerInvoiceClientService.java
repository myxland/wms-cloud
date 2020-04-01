package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceUpdateParam;
import com.zlsrj.wms.api.vo.TenantCustomerInvoiceVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantCustomerInvoice")
public interface TenantCustomerInvoiceClientService {
	@RequestMapping(value = "/tenant-customer-invoices/{id}", method = RequestMethod.GET)
	public TenantCustomerInvoiceVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-customer-invoices/list", method = RequestMethod.GET)
	public List<TenantCustomerInvoiceVo> list(@RequestBody TenantCustomerInvoiceQueryParam tenantCustomerInvoiceQueryParam);

	@RequestMapping(value = "/tenant-customer-invoices/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantCustomerInvoiceQueryParam tenantCustomerInvoiceQueryParam);

	@RequestMapping(value = "/tenant-customer-invoices", method = RequestMethod.GET)
	public Page<TenantCustomerInvoiceVo> page(@RequestBody TenantCustomerInvoiceQueryParam tenantCustomerInvoiceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-customer-invoices", method = RequestMethod.POST)
	public String save(@RequestBody TenantCustomerInvoiceAddParam tenantCustomerInvoiceAddParam);

	@RequestMapping(value = "/tenant-customer-invoices/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantCustomerInvoiceUpdateParam tenantCustomerInvoiceUpdateParam);

	@RequestMapping(value = "/tenant-customer-invoices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}