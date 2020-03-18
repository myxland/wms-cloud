package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantPriceAddParam;
import com.zlsrj.wms.api.dto.TenantPriceQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceUpdateParam;
import com.zlsrj.wms.api.entity.TenantPrice;
import com.zlsrj.wms.api.vo.TenantPriceVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantPrice")
public interface TenantPriceClientService {
	@RequestMapping(value = "/tenant-prices/{id}", method = RequestMethod.GET)
	public TenantPriceVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-prices/list", method = RequestMethod.GET)
	public List<TenantPriceVo> list(@RequestBody TenantPriceQueryParam tenantPriceQueryParam);
	
	@RequestMapping(value = "/tenant-prices", method = RequestMethod.GET)
	public Page<TenantPriceVo> page(@RequestBody TenantPriceQueryParam tenantPriceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-prices/aggregation", method = RequestMethod.GET)
	public TenantPriceVo aggregation(@RequestBody TenantPriceQueryParam tenantPriceQueryParam);

	@RequestMapping(value = "/tenant-prices", method = RequestMethod.POST)
	public String save(@RequestBody TenantPriceAddParam tenantPriceAddParam);

	@RequestMapping(value = "/tenant-prices/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantPriceUpdateParam tenantPriceUpdateParam);

	@RequestMapping(value = "/tenant-prices/{id}", method = RequestMethod.PATCH)
	public TenantPriceVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPrice tenantPrice);

	@RequestMapping(value = "/tenant-prices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

