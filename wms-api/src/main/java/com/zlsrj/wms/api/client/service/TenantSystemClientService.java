package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantSystemQueryParam;
import com.zlsrj.wms.api.entity.TenantSystem;
import com.zlsrj.wms.api.vo.TenantSystemVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-MBG", contextId = "TenantSystem")
public interface TenantSystemClientService {
	@RequestMapping(value = "/tenant-systems/{id}", method = RequestMethod.GET)
	public TenantSystemVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-systems", method = RequestMethod.GET)
	public Page<TenantSystemVo> page(@RequestBody TenantSystemQueryParam tenantSystemQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-systems", method = RequestMethod.POST)
	public TenantSystemVo save(@RequestBody TenantSystem tenantSystem);

	@RequestMapping(value = "/tenant-systems/{id}", method = RequestMethod.PUT)
	public TenantSystemVo updateById(@PathVariable("id") String id, @RequestBody TenantSystem tenantSystem);

	@RequestMapping(value = "/tenant-systems/{id}", method = RequestMethod.PATCH)
	public TenantSystemVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantSystem tenantSystem);

	@RequestMapping(value = "/tenant-systems/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

