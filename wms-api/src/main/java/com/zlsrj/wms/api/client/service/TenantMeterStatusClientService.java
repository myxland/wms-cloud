package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantMeterStatusQueryParam;
import com.zlsrj.wms.api.entity.TenantMeterStatus;
import com.zlsrj.wms.api.vo.TenantMeterStatusVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantMeterStatus")
public interface TenantMeterStatusClientService {
	@RequestMapping(value = "/tenant-meter-statuss/{id}", method = RequestMethod.GET)
	public TenantMeterStatusVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-meter-statuss", method = RequestMethod.GET)
	public Page<TenantMeterStatusVo> page(@RequestBody TenantMeterStatusQueryParam tenantMeterStatusQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-meter-statuss", method = RequestMethod.POST)
	public TenantMeterStatusVo save(@RequestBody TenantMeterStatus tenantMeterStatus);

	@RequestMapping(value = "/tenant-meter-statuss/{id}", method = RequestMethod.PUT)
	public TenantMeterStatusVo updateById(@PathVariable("id") String id, @RequestBody TenantMeterStatus tenantMeterStatus);

	@RequestMapping(value = "/tenant-meter-statuss/{id}", method = RequestMethod.PATCH)
	public TenantMeterStatusVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterStatus tenantMeterStatus);

	@RequestMapping(value = "/tenant-meter-statuss/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

