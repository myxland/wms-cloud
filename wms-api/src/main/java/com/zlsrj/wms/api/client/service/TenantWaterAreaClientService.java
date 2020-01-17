package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantWaterAreaQueryParam;
import com.zlsrj.wms.api.entity.TenantWaterArea;
import com.zlsrj.wms.api.vo.TenantWaterAreaVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantWaterArea")
public interface TenantWaterAreaClientService {
	@RequestMapping(value = "/tenant-water-areas/{id}", method = RequestMethod.GET)
	public TenantWaterAreaVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-water-areas", method = RequestMethod.GET)
	public Page<TenantWaterAreaVo> page(@RequestBody TenantWaterAreaQueryParam tenantWaterAreaQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-water-areas", method = RequestMethod.POST)
	public TenantWaterAreaVo save(@RequestBody TenantWaterArea tenantWaterArea);

	@RequestMapping(value = "/tenant-water-areas/{id}", method = RequestMethod.PUT)
	public TenantWaterAreaVo updateById(@PathVariable("id") Long id, @RequestBody TenantWaterArea tenantWaterArea);

	@RequestMapping(value = "/tenant-water-areas/{id}", method = RequestMethod.PATCH)
	public TenantWaterAreaVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantWaterArea tenantWaterArea);

	@RequestMapping(value = "/tenant-water-areas/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

