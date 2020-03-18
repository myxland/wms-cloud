package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantPriceStepAddParam;
import com.zlsrj.wms.api.dto.TenantPriceStepQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceStepUpdateParam;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.api.vo.TenantPriceStepVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantPriceStep")
public interface TenantPriceStepClientService {
	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.GET)
	public TenantPriceStepVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-price-steps/list", method = RequestMethod.GET)
	public List<TenantPriceStepVo> list(@RequestBody TenantPriceStepQueryParam tenantPriceStepQueryParam);
	
	@RequestMapping(value = "/tenant-price-steps", method = RequestMethod.GET)
	public Page<TenantPriceStepVo> page(@RequestBody TenantPriceStepQueryParam tenantPriceStepQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-price-steps/aggregation", method = RequestMethod.GET)
	public TenantPriceStepVo aggregation(@RequestBody TenantPriceStepQueryParam tenantPriceStepQueryParam);

	@RequestMapping(value = "/tenant-price-steps", method = RequestMethod.POST)
	public String save(@RequestBody TenantPriceStepAddParam tenantPriceStepAddParam);

	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantPriceStepUpdateParam tenantPriceStepUpdateParam);

	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.PATCH)
	public TenantPriceStepVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPriceStep tenantPriceStep);

	@RequestMapping(value = "/tenant-price-steps/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

