package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantConfigWxQueryParam;
import com.zlsrj.wms.api.entity.TenantConfigWx;
import com.zlsrj.wms.api.vo.TenantConfigWxVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantConfigWx")
public interface TenantConfigWxClientService {
	@RequestMapping(value = "/tenant-config-wxs/{id}", method = RequestMethod.GET)
	public TenantConfigWxVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-config-wxs", method = RequestMethod.GET)
	public Page<TenantConfigWxVo> page(@RequestBody TenantConfigWxQueryParam tenantConfigWxQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-config-wxs/aggregation", method = RequestMethod.GET)
	public TenantConfigWxVo aggregation(@RequestBody TenantConfigWxQueryParam tenantConfigWxQueryParam);

	@RequestMapping(value = "/tenant-config-wxs", method = RequestMethod.POST)
	public TenantConfigWxVo save(@RequestBody TenantConfigWx tenantConfigWx);

	@RequestMapping(value = "/tenant-config-wxs/{id}", method = RequestMethod.PUT)
	public TenantConfigWxVo updateById(@PathVariable("id") Long id, @RequestBody TenantConfigWx tenantConfigWx);

	@RequestMapping(value = "/tenant-config-wxs/{id}", method = RequestMethod.PATCH)
	public TenantConfigWxVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantConfigWx tenantConfigWx);

	@RequestMapping(value = "/tenant-config-wxs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

