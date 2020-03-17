package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterTypeAddParam;
import com.zlsrj.wms.api.dto.TenantMeterTypeQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterTypeUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterType;
import com.zlsrj.wms.api.vo.TenantMeterTypeVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeterType")
public interface TenantMeterTypeClientService {
	@RequestMapping(value = "/tenant-meter-types/{id}", method = RequestMethod.GET)
	public TenantMeterTypeVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meter-types/list", method = RequestMethod.GET)
	public List<TenantMeterTypeVo> list(@RequestBody TenantMeterTypeQueryParam tenantMeterTypeQueryParam);
	
	@RequestMapping(value = "/tenant-meter-types", method = RequestMethod.GET)
	public Page<TenantMeterTypeVo> page(@RequestBody TenantMeterTypeQueryParam tenantMeterTypeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-meter-types", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterTypeAddParam tenantMeterTypeAddParam);

	@RequestMapping(value = "/tenant-meter-types/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterTypeUpdateParam tenantMeterTypeUpdateParam);

	@RequestMapping(value = "/tenant-meter-types/{id}", method = RequestMethod.PATCH)
	public TenantMeterTypeVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterType tenantMeterType);

	@RequestMapping(value = "/tenant-meter-types/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

