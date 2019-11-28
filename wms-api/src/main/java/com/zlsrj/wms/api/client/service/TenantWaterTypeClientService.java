package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantWaterTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantWaterType;
import com.zlsrj.wms.api.vo.TenantWaterTypeVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-TENANT", contextId = "TenantWaterType")
public interface TenantWaterTypeClientService {
	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.GET)
	public TenantWaterTypeVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-water-types", method = RequestMethod.GET)
	public Page<TenantWaterTypeVo> page(@RequestBody TenantWaterTypeQueryParam tenantWaterTypeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-water-types", method = RequestMethod.POST)
	public TenantWaterTypeVo save(@RequestBody TenantWaterType tenantWaterType);

	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.PUT)
	public TenantWaterTypeVo updateById(@PathVariable("id") Long id, @RequestBody TenantWaterType tenantWaterType);

	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.PATCH)
	public TenantWaterTypeVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantWaterType tenantWaterType);

	@RequestMapping(value = "/tenant-water-types/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

