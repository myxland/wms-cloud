package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantSmsQueryParam;
import com.zlsrj.wms.api.entity.TenantSms;
import com.zlsrj.wms.api.vo.TenantSmsVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-TENANT", contextId = "TenantSms")
public interface TenantSmsClientService {
	@RequestMapping(value = "/tenant-smss/{id}", method = RequestMethod.GET)
	public TenantSmsVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-smss/tenant-id/{tenant-id}", method = RequestMethod.GET)
	public TenantSmsVo getByTenantId(@PathVariable("tenant-id") Long tenantId);

	@RequestMapping(value = "/tenant-smss", method = RequestMethod.GET)
	public Page<TenantSmsVo> page(@RequestBody TenantSmsQueryParam tenantSmsQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-smss", method = RequestMethod.POST)
	public TenantSmsVo save(@RequestBody TenantSms tenantSms);

	@RequestMapping(value = "/tenant-smss/{id}", method = RequestMethod.PUT)
	public TenantSmsVo updateById(@PathVariable("id") Long id, @RequestBody TenantSms tenantSms);

	@RequestMapping(value = "/tenant-smss/{id}", method = RequestMethod.PATCH)
	public TenantSmsVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantSms tenantSms);

	@RequestMapping(value = "/tenant-smss/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

