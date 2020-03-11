package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCaliberQueryParam;
import com.zlsrj.wms.api.entity.TenantCaliber;
import com.zlsrj.wms.api.vo.TenantCaliberVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantCaliber")
public interface TenantCaliberClientService {
	@RequestMapping(value = "/tenant-calibers/{id}", method = RequestMethod.GET)
	public TenantCaliberVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-calibers", method = RequestMethod.GET)
	public Page<TenantCaliberVo> page(@RequestBody TenantCaliberQueryParam tenantCaliberQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-calibers", method = RequestMethod.POST)
	public TenantCaliberVo save(@RequestBody TenantCaliber tenantCaliber);

	@RequestMapping(value = "/tenant-calibers/{id}", method = RequestMethod.PUT)
	public TenantCaliberVo updateById(@PathVariable("id") String id, @RequestBody TenantCaliber tenantCaliber);

	@RequestMapping(value = "/tenant-calibers/{id}", method = RequestMethod.PATCH)
	public TenantCaliberVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantCaliber tenantCaliber);

	@RequestMapping(value = "/tenant-calibers/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

