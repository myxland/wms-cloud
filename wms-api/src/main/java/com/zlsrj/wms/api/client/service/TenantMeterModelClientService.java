package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterModelAddParam;
import com.zlsrj.wms.api.dto.TenantMeterModelQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterModelUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterModel;
import com.zlsrj.wms.api.vo.TenantMeterModelVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeterModel")
public interface TenantMeterModelClientService {
	@RequestMapping(value = "/tenant-meter-models/{id}", method = RequestMethod.GET)
	public TenantMeterModelVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meter-models/list", method = RequestMethod.GET)
	public List<TenantMeterModelVo> list(@RequestBody TenantMeterModelQueryParam tenantMeterModelQueryParam);
	
	@RequestMapping(value = "/tenant-meter-models", method = RequestMethod.GET)
	public Page<TenantMeterModelVo> page(@RequestBody TenantMeterModelQueryParam tenantMeterModelQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-meter-models", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterModelAddParam tenantMeterModelAddParam);

	@RequestMapping(value = "/tenant-meter-models/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterModelUpdateParam tenantMeterModelUpdateParam);

	@RequestMapping(value = "/tenant-meter-models/{id}", method = RequestMethod.PATCH)
	public TenantMeterModelVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterModel tenantMeterModel);

	@RequestMapping(value = "/tenant-meter-models/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

