package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantPriceDetailAddParam;
import com.zlsrj.wms.api.dto.TenantPriceDetailQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceDetailUpdateParam;
import com.zlsrj.wms.api.entity.TenantPriceDetail;
import com.zlsrj.wms.api.vo.TenantPriceDetailVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantPriceDetail")
public interface TenantPriceDetailClientService {
	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.GET)
	public TenantPriceDetailVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-price-details/list", method = RequestMethod.GET)
	public List<TenantPriceDetailVo> list(@RequestBody TenantPriceDetailQueryParam tenantPriceDetailQueryParam);
	
	@RequestMapping(value = "/tenant-price-details", method = RequestMethod.GET)
	public Page<TenantPriceDetailVo> page(@RequestBody TenantPriceDetailQueryParam tenantPriceDetailQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-price-details/aggregation", method = RequestMethod.GET)
	public TenantPriceDetailVo aggregation(@RequestBody TenantPriceDetailQueryParam tenantPriceDetailQueryParam);

	@RequestMapping(value = "/tenant-price-details", method = RequestMethod.POST)
	public String save(@RequestBody TenantPriceDetailAddParam tenantPriceDetailAddParam);

	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantPriceDetailUpdateParam tenantPriceDetailUpdateParam);

	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.PATCH)
	public TenantPriceDetailVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPriceDetail tenantPriceDetail);

	@RequestMapping(value = "/tenant-price-details/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

