package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterReadLogCurrentQueryParam;
import com.zlsrj.wms.api.entity.TenantMeterReadLogCurrent;
import com.zlsrj.wms.api.vo.TenantMeterReadLogCurrentVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeterReadLogCurrent")
public interface TenantMeterReadLogCurrentClientService {
	@RequestMapping(value = "/tenant-meter-read-log-currents/{id}", method = RequestMethod.GET)
	public TenantMeterReadLogCurrentVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meter-read-log-currents", method = RequestMethod.GET)
	public Page<TenantMeterReadLogCurrentVo> page(@RequestBody TenantMeterReadLogCurrentQueryParam tenantMeterReadLogCurrentQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-meter-read-log-currents", method = RequestMethod.POST)
	public TenantMeterReadLogCurrentVo save(@RequestBody TenantMeterReadLogCurrent tenantMeterReadLogCurrent);

	@RequestMapping(value = "/tenant-meter-read-log-currents/{id}", method = RequestMethod.PUT)
	public TenantMeterReadLogCurrentVo updateById(@PathVariable("id") String id, @RequestBody TenantMeterReadLogCurrent tenantMeterReadLogCurrent);

	@RequestMapping(value = "/tenant-meter-read-log-currents/{id}", method = RequestMethod.PATCH)
	public TenantMeterReadLogCurrentVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterReadLogCurrent tenantMeterReadLogCurrent);

	@RequestMapping(value = "/tenant-meter-read-log-currents/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

