package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantAccountQueryParam;
import com.zlsrj.wms.api.entity.TenantAccount;
import com.zlsrj.wms.api.vo.TenantAccountVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-TENANT", contextId = "TenantAccount")
public interface TenantAccountClientService {
	@RequestMapping(value = "/tenant-accounts/{id}", method = RequestMethod.GET)
	public TenantAccountVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-accounts", method = RequestMethod.GET)
	public Page<TenantAccountVo> page(@RequestBody TenantAccountQueryParam tenantAccountQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-accounts", method = RequestMethod.POST)
	public TenantAccountVo save(@RequestBody TenantAccount tenantAccount);

	@RequestMapping(value = "/tenant-accounts/{id}", method = RequestMethod.PUT)
	public TenantAccountVo updateById(@PathVariable("id") Long id, @RequestBody TenantAccount tenantAccount);

	@RequestMapping(value = "/tenant-accounts/{id}", method = RequestMethod.PATCH)
	public TenantAccountVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantAccount tenantAccount);

	@RequestMapping(value = "/tenant-accounts/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

