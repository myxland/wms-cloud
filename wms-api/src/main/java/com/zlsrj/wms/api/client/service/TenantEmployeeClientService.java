package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantEmployeeQueryParam;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.vo.TenantEmployeeVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-EMPLOYEE", contextId = "TenantEmployee")
public interface TenantEmployeeClientService {
	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.GET)
	public TenantEmployeeVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-employees", method = RequestMethod.GET)
	public Page<TenantEmployeeVo> page(@RequestBody TenantEmployeeQueryParam tenantEmployeeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-employees", method = RequestMethod.POST)
	public TenantEmployeeVo save(@RequestBody TenantEmployee tenantEmployee);

	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.PUT)
	public TenantEmployeeVo updateById(@PathVariable("id") Long id, @RequestBody TenantEmployee tenantEmployee);

	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.PATCH)
	public TenantEmployeeVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantEmployee tenantEmployee);

	@RequestMapping(value = "/tenant-employees/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

