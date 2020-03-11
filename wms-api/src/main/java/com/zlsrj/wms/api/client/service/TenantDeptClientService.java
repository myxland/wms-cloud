package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantDeptQueryParam;
import com.zlsrj.wms.api.entity.TenantDept;
import com.zlsrj.wms.api.vo.TenantDeptVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-EMPLOYEE", contextId = "TenantDept")
public interface TenantDeptClientService {
	@RequestMapping(value = "/tenant-depts/{id}", method = RequestMethod.GET)
	public TenantDeptVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-depts", method = RequestMethod.GET)
	public Page<TenantDeptVo> page(@RequestBody TenantDeptQueryParam tenantDeptQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-depts", method = RequestMethod.POST)
	public TenantDeptVo save(@RequestBody TenantDept tenantDept);

	@RequestMapping(value = "/tenant-depts/{id}", method = RequestMethod.PUT)
	public TenantDeptVo updateById(@PathVariable("id") String id, @RequestBody TenantDept tenantDept);

	@RequestMapping(value = "/tenant-depts/{id}", method = RequestMethod.PATCH)
	public TenantDeptVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantDept tenantDept);

	@RequestMapping(value = "/tenant-depts/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

