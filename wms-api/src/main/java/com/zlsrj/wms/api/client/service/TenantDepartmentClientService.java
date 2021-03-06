package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantDepartmentQueryParam;
import com.zlsrj.wms.api.entity.TenantDepartment;
import com.zlsrj.wms.api.vo.TenantDepartmentVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantDepartment")
public interface TenantDepartmentClientService {
	@RequestMapping(value = "/tenant-departments/{id}", method = RequestMethod.GET)
	public TenantDepartmentVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-departments", method = RequestMethod.GET)
	public Page<TenantDepartmentVo> page(@RequestBody TenantDepartmentQueryParam tenantDepartmentQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-departments", method = RequestMethod.POST)
	public TenantDepartmentVo save(@RequestBody TenantDepartment tenantDepartment);

	@RequestMapping(value = "/tenant-departments/{id}", method = RequestMethod.PUT)
	public TenantDepartmentVo updateById(@PathVariable("id") Long id, @RequestBody TenantDepartment tenantDepartment);

	@RequestMapping(value = "/tenant-departments/{id}", method = RequestMethod.PATCH)
	public TenantDepartmentVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantDepartment tenantDepartment);

	@RequestMapping(value = "/tenant-departments/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

