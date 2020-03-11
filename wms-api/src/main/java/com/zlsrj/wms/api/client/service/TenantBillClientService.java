package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantBillQueryParam;
import com.zlsrj.wms.api.entity.TenantBill;
import com.zlsrj.wms.api.vo.TenantBillVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-TENANT", contextId = "TenantBill")
public interface TenantBillClientService {
	@RequestMapping(value = "/tenant-bills/{id}", method = RequestMethod.GET)
	public TenantBillVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-bills/tenant-id/{tenant-id}", method = RequestMethod.GET)
	public TenantBillVo getByTenantId(@PathVariable("tenant-id") String tenantId);

	@RequestMapping(value = "/tenant-bills", method = RequestMethod.GET)
	public Page<TenantBillVo> page(@RequestBody TenantBillQueryParam tenantBillQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-bills", method = RequestMethod.POST)
	public TenantBillVo save(@RequestBody TenantBill tenantBill);

	@RequestMapping(value = "/tenant-bills/{id}", method = RequestMethod.PUT)
	public TenantBillVo updateById(@PathVariable("id") String id, @RequestBody TenantBill tenantBill);

	@RequestMapping(value = "/tenant-bills/{id}", method = RequestMethod.PATCH)
	public TenantBillVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantBill tenantBill);

	@RequestMapping(value = "/tenant-bills/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

