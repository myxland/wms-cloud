package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantPriceTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.vo.TenantPriceTypeVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-MBG", contextId = "TenantPriceType")
public interface TenantPriceTypeClientService {
	@RequestMapping(value = "/tenant-price-types/{id}", method = RequestMethod.GET)
	public TenantPriceTypeVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-price-types", method = RequestMethod.GET)
	public Page<TenantPriceTypeVo> page(@RequestBody TenantPriceTypeQueryParam tenantPriceTypeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-price-types", method = RequestMethod.POST)
	public TenantPriceTypeVo save(@RequestBody TenantPriceType tenantPriceType);

	@RequestMapping(value = "/tenant-price-types/{id}", method = RequestMethod.PUT)
	public TenantPriceTypeVo updateById(@PathVariable("id") Long id, @RequestBody TenantPriceType tenantPriceType);

	@RequestMapping(value = "/tenant-price-types/{id}", method = RequestMethod.PATCH)
	public TenantPriceTypeVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantPriceType tenantPriceType);

	@RequestMapping(value = "/tenant-price-types/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

