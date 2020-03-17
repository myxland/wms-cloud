package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterMarketingArea;
import com.zlsrj.wms.api.vo.TenantMeterMarketingAreaVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeterMarketingArea")
public interface TenantMeterMarketingAreaClientService {
	@RequestMapping(value = "/tenant-meter-marketing-areas/{id}", method = RequestMethod.GET)
	public TenantMeterMarketingAreaVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meter-marketing-areas/list", method = RequestMethod.GET)
	public List<TenantMeterMarketingAreaVo> list(@RequestBody TenantMeterMarketingAreaQueryParam tenantMeterMarketingAreaQueryParam);
	
	@RequestMapping(value = "/tenant-meter-marketing-areas", method = RequestMethod.GET)
	public Page<TenantMeterMarketingAreaVo> page(@RequestBody TenantMeterMarketingAreaQueryParam tenantMeterMarketingAreaQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-meter-marketing-areas", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterMarketingAreaAddParam tenantMeterMarketingAreaAddParam);

	@RequestMapping(value = "/tenant-meter-marketing-areas/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterMarketingAreaUpdateParam tenantMeterMarketingAreaUpdateParam);

	@RequestMapping(value = "/tenant-meter-marketing-areas/{id}", method = RequestMethod.PATCH)
	public TenantMeterMarketingAreaVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterMarketingArea tenantMeterMarketingArea);

	@RequestMapping(value = "/tenant-meter-marketing-areas/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

