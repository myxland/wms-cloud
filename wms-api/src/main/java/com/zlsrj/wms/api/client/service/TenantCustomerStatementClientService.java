package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCustomerStatementAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerStatementQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerStatementUpdateParam;
import com.zlsrj.wms.api.vo.TenantCustomerStatementVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantCustomerStatement")
public interface TenantCustomerStatementClientService {
	@RequestMapping(value = "/tenant-customer-statements/{id}", method = RequestMethod.GET)
	public TenantCustomerStatementVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-customer-statements/list", method = RequestMethod.GET)
	public List<TenantCustomerStatementVo> list(@RequestBody TenantCustomerStatementQueryParam tenantCustomerStatementQueryParam);

	@RequestMapping(value = "/tenant-customer-statements/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantCustomerStatementQueryParam tenantCustomerStatementQueryParam);

	@RequestMapping(value = "/tenant-customer-statements", method = RequestMethod.GET)
	public Page<TenantCustomerStatementVo> page(@RequestBody TenantCustomerStatementQueryParam tenantCustomerStatementQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-customer-statements", method = RequestMethod.POST)
	public String save(@RequestBody TenantCustomerStatementAddParam tenantCustomerStatementAddParam);

	@RequestMapping(value = "/tenant-customer-statements/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantCustomerStatementUpdateParam tenantCustomerStatementUpdateParam);

	@RequestMapping(value = "/tenant-customer-statements/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}