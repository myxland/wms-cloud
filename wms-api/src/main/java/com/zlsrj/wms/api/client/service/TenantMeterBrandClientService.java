package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterBrandAddParam;
import com.zlsrj.wms.api.dto.TenantMeterBrandQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterBrandUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterBrand;
import com.zlsrj.wms.api.vo.TenantMeterBrandVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeterBrand")
public interface TenantMeterBrandClientService {
	@RequestMapping(value = "/tenant-meter-brands/{id}", method = RequestMethod.GET)
	public TenantMeterBrandVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meter-brands/list", method = RequestMethod.GET)
	public List<TenantMeterBrandVo> list(@RequestBody TenantMeterBrandQueryParam tenantMeterBrandQueryParam);
	
	@RequestMapping(value = "/tenant-meter-brands", method = RequestMethod.GET)
	public Page<TenantMeterBrandVo> page(@RequestBody TenantMeterBrandQueryParam tenantMeterBrandQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-meter-brands", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterBrandAddParam tenantMeterBrandAddParam);

	@RequestMapping(value = "/tenant-meter-brands/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterBrandUpdateParam tenantMeterBrandUpdateParam);

	@RequestMapping(value = "/tenant-meter-brands/{id}", method = RequestMethod.PATCH)
	public TenantMeterBrandVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterBrand tenantMeterBrand);

	@RequestMapping(value = "/tenant-meter-brands/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

