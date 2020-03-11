package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantReceivableDetailQueryParam;
import com.zlsrj.wms.api.entity.TenantReceivableDetail;
import com.zlsrj.wms.api.vo.TenantReceivableDetailVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantReceivableDetail")
public interface TenantReceivableDetailClientService {
	@RequestMapping(value = "/tenant-receivable-details/{id}", method = RequestMethod.GET)
	public TenantReceivableDetailVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-receivable-details", method = RequestMethod.GET)
	public Page<TenantReceivableDetailVo> page(@RequestBody TenantReceivableDetailQueryParam tenantReceivableDetailQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-receivable-details", method = RequestMethod.POST)
	public TenantReceivableDetailVo save(@RequestBody TenantReceivableDetail tenantReceivableDetail);

	@RequestMapping(value = "/tenant-receivable-details/{id}", method = RequestMethod.PUT)
	public TenantReceivableDetailVo updateById(@PathVariable("id") String id, @RequestBody TenantReceivableDetail tenantReceivableDetail);

	@RequestMapping(value = "/tenant-receivable-details/{id}", method = RequestMethod.PATCH)
	public TenantReceivableDetailVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantReceivableDetail tenantReceivableDetail);

	@RequestMapping(value = "/tenant-receivable-details/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

