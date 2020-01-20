package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantReceivableQueryParam;
import com.zlsrj.wms.api.entity.TenantReceivable;
import com.zlsrj.wms.api.vo.TenantReceivableVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.ApiOperation;

@FeignClient(value = "WMS-SAAS", contextId = "TenantReceivable")
public interface TenantReceivableClientService {
	@RequestMapping(value = "/tenant-receivables/{id}", method = RequestMethod.GET)
	public TenantReceivableVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-receivables", method = RequestMethod.GET)
	public Page<TenantReceivableVo> page(@RequestBody TenantReceivableQueryParam tenantReceivableQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-receivables/aggregation", method = RequestMethod.GET)
	public TenantReceivableVo aggregation(@RequestBody TenantReceivableQueryParam tenantReceivableQueryParam);

	@RequestMapping(value = "/tenant-receivables", method = RequestMethod.POST)
	public TenantReceivableVo save(@RequestBody TenantReceivable tenantReceivable);

	@RequestMapping(value = "/tenant-receivables/{id}", method = RequestMethod.PUT)
	public TenantReceivableVo updateById(@PathVariable("id") Long id, @RequestBody TenantReceivable tenantReceivable);

	@RequestMapping(value = "/tenant-receivables/{id}", method = RequestMethod.PATCH)
	public TenantReceivableVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantReceivable tenantReceivable);

	@RequestMapping(value = "/tenant-receivables/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

