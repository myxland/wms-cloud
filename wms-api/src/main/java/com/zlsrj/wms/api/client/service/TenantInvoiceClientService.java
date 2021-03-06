package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantInvoiceQueryParam;
import com.zlsrj.wms.api.entity.TenantInvoice;
import com.zlsrj.wms.api.vo.TenantInvoiceVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-TENANT", contextId = "TenantInvoice")
public interface TenantInvoiceClientService {
	@RequestMapping(value = "/tenant-invoices/{id}", method = RequestMethod.GET)
	public TenantInvoiceVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-invoices/tenant-id/{tenant-id}", method = RequestMethod.GET)
	public TenantInvoiceVo getByTenantId(@PathVariable("tenant-id") Long tenantId);

	@RequestMapping(value = "/tenant-invoices", method = RequestMethod.GET)
	public Page<TenantInvoiceVo> page(@RequestBody TenantInvoiceQueryParam tenantInvoiceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-invoices", method = RequestMethod.POST)
	public TenantInvoiceVo save(@RequestBody TenantInvoice tenantInvoice);

	@RequestMapping(value = "/tenant-invoices/{id}", method = RequestMethod.PUT)
	public TenantInvoiceVo updateById(@PathVariable("id") Long id, @RequestBody TenantInvoice tenantInvoice);

	@RequestMapping(value = "/tenant-invoices/{id}", method = RequestMethod.PATCH)
	public TenantInvoiceVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantInvoice tenantInvoice);

	@RequestMapping(value = "/tenant-invoices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

