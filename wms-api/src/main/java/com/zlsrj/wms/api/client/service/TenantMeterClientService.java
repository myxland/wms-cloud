package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterQueryParam;
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.api.vo.TenantMeterVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeter")
public interface TenantMeterClientService {
	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.GET)
	public TenantMeterVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-meters", method = RequestMethod.GET)
	public Page<TenantMeterVo> page(@RequestBody TenantMeterQueryParam tenantMeterQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-meters", method = RequestMethod.POST)
	public TenantMeterVo save(@RequestBody TenantMeter tenantMeter);

	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.PUT)
	public TenantMeterVo updateById(@PathVariable("id") Long id, @RequestBody TenantMeter tenantMeter);

	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.PATCH)
	public TenantMeterVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantMeter tenantMeter);

	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

