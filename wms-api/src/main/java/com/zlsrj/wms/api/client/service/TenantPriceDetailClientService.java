package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantPriceDetailQueryParam;
import com.zlsrj.wms.api.entity.TenantPriceDetail;
import com.zlsrj.wms.api.vo.TenantPriceDetailVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-MBG", contextId = "TenantPriceDetail")
public interface TenantPriceDetailClientService {
	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.GET)
	public TenantPriceDetailVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-price-details", method = RequestMethod.GET)
	public Page<TenantPriceDetailVo> page(@RequestBody TenantPriceDetailQueryParam tenantPriceDetailQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-price-details", method = RequestMethod.POST)
	public TenantPriceDetailVo save(@RequestBody TenantPriceDetail tenantPriceDetail);

	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.PUT)
	public TenantPriceDetailVo updateById(@PathVariable("id") String id, @RequestBody TenantPriceDetail tenantPriceDetail);

	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.PATCH)
	public TenantPriceDetailVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPriceDetail tenantPriceDetail);

	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

