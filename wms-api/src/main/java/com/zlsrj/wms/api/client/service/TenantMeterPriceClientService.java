package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterPriceAddParam;
import com.zlsrj.wms.api.dto.TenantMeterPriceQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterPriceUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterPriceVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeterPrice")
public interface TenantMeterPriceClientService {
	@RequestMapping(value = "/tenant-meter-prices/{id}", method = RequestMethod.GET)
	public TenantMeterPriceVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meter-prices/list", method = RequestMethod.GET)
	public List<TenantMeterPriceVo> list(@RequestBody TenantMeterPriceQueryParam tenantMeterPriceQueryParam);

	@RequestMapping(value = "/tenant-meter-prices/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantMeterPriceQueryParam tenantMeterPriceQueryParam);

	@RequestMapping(value = "/tenant-meter-prices", method = RequestMethod.GET)
	public Page<TenantMeterPriceVo> page(@RequestBody TenantMeterPriceQueryParam tenantMeterPriceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-meter-prices/aggregation", method = RequestMethod.GET)
	public TenantMeterPriceVo aggregation(@RequestBody TenantMeterPriceQueryParam tenantMeterPriceQueryParam);

	@RequestMapping(value = "/tenant-meter-prices", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterPriceAddParam tenantMeterPriceAddParam);

	@RequestMapping(value = "/tenant-meter-prices/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterPriceUpdateParam tenantMeterPriceUpdateParam);

	@RequestMapping(value = "/tenant-meter-prices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}