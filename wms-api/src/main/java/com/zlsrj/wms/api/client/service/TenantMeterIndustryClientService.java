package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterIndustryAddParam;
import com.zlsrj.wms.api.dto.TenantMeterIndustryQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterIndustryUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterIndustry;
import com.zlsrj.wms.api.vo.TenantMeterIndustryVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeterIndustry")
public interface TenantMeterIndustryClientService {
	@RequestMapping(value = "/tenant-meter-industrys/{id}", method = RequestMethod.GET)
	public TenantMeterIndustryVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meter-industrys/list", method = RequestMethod.GET)
	public List<TenantMeterIndustryVo> list(@RequestBody TenantMeterIndustryQueryParam tenantMeterIndustryQueryParam);
	
	@RequestMapping(value = "/tenant-meter-industrys", method = RequestMethod.GET)
	public Page<TenantMeterIndustryVo> page(@RequestBody TenantMeterIndustryQueryParam tenantMeterIndustryQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-meter-industrys", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterIndustryAddParam tenantMeterIndustryAddParam);

	@RequestMapping(value = "/tenant-meter-industrys/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterIndustryUpdateParam tenantMeterIndustryUpdateParam);

	@RequestMapping(value = "/tenant-meter-industrys/{id}", method = RequestMethod.PATCH)
	public TenantMeterIndustryVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterIndustry tenantMeterIndustry);

	@RequestMapping(value = "/tenant-meter-industrys/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

