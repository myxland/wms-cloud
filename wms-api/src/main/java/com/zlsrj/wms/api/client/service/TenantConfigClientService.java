package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantConfigQueryParam;
import com.zlsrj.wms.api.entity.TenantConfig;
import com.zlsrj.wms.api.vo.TenantConfigVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-TENANT", contextId = "TenantConfig")
public interface TenantConfigClientService {
	@RequestMapping(value = "/tenant-configs/{id}", method = RequestMethod.GET)
	public TenantConfigVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-configs/tenant-id/{tenant-id}", method = RequestMethod.GET)
	public TenantConfigVo getByTenantId(@PathVariable("tenant-id") Long tenantId);

	@RequestMapping(value = "/tenant-configs", method = RequestMethod.GET)
	public Page<TenantConfigVo> page(@RequestBody TenantConfigQueryParam tenantConfigQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-configs", method = RequestMethod.POST)
	public TenantConfigVo save(@RequestBody TenantConfig tenantConfig);

	@RequestMapping(value = "/tenant-configs/{id}", method = RequestMethod.PUT)
	public TenantConfigVo updateById(@PathVariable("id") Long id, @RequestBody TenantConfig tenantConfig);

	@RequestMapping(value = "/tenant-configs/{id}", method = RequestMethod.PATCH)
	public TenantConfigVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantConfig tenantConfig);

	@RequestMapping(value = "/tenant-configs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

