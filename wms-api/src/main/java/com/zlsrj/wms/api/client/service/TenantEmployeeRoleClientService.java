package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantEmployeeRoleQueryParam;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.vo.TenantEmployeeRoleVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantEmployeeRole")
public interface TenantEmployeeRoleClientService {
	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.GET)
	public TenantEmployeeRoleVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-employee-roles", method = RequestMethod.GET)
	public Page<TenantEmployeeRoleVo> page(@RequestBody TenantEmployeeRoleQueryParam tenantEmployeeRoleQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-employee-roles", method = RequestMethod.POST)
	public TenantEmployeeRoleVo save(@RequestBody TenantEmployeeRole tenantEmployeeRole);

	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.PUT)
	public TenantEmployeeRoleVo updateById(@PathVariable("id") String id, @RequestBody TenantEmployeeRole tenantEmployeeRole);

	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.PATCH)
	public TenantEmployeeRoleVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantEmployeeRole tenantEmployeeRole);

	@RequestMapping(value = "/tenant-employee-roles/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

