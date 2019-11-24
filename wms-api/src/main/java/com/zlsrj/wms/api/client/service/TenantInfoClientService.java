package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantInfoQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-TENANT", contextId = "TenantInfo")
public interface TenantInfoClientService {
	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.GET)
	public TenantInfoVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-infos", method = RequestMethod.GET)
	public Page<TenantInfoVo> page(@RequestBody TenantInfoQueryParam tenantInfoQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-infos", method = RequestMethod.POST)
	public TenantInfoVo save(@RequestBody TenantInfo tenantInfo);

	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.PUT)
	public TenantInfoVo updateById(@PathVariable("id") Long id, @RequestBody TenantInfo tenantInfo);

	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.PATCH)
	public TenantInfoVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantInfo tenantInfo);

	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

