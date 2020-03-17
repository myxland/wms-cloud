package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterSupplyArea;
import com.zlsrj.wms.api.vo.TenantMeterSupplyAreaVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeterSupplyArea")
public interface TenantMeterSupplyAreaClientService {
	@RequestMapping(value = "/tenant-meter-supply-areas/{id}", method = RequestMethod.GET)
	public TenantMeterSupplyAreaVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meter-supply-areas/list", method = RequestMethod.GET)
	public List<TenantMeterSupplyAreaVo> list(@RequestBody TenantMeterSupplyAreaQueryParam tenantMeterSupplyAreaQueryParam);
	
	@RequestMapping(value = "/tenant-meter-supply-areas", method = RequestMethod.GET)
	public Page<TenantMeterSupplyAreaVo> page(@RequestBody TenantMeterSupplyAreaQueryParam tenantMeterSupplyAreaQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-meter-supply-areas", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterSupplyAreaAddParam tenantMeterSupplyAreaAddParam);

	@RequestMapping(value = "/tenant-meter-supply-areas/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterSupplyAreaUpdateParam tenantMeterSupplyAreaUpdateParam);

	@RequestMapping(value = "/tenant-meter-supply-areas/{id}", method = RequestMethod.PATCH)
	public TenantMeterSupplyAreaVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterSupplyArea tenantMeterSupplyArea);

	@RequestMapping(value = "/tenant-meter-supply-areas/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

