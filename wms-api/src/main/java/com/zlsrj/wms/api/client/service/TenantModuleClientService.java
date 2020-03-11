package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantModuleQueryParam;
import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.api.vo.TenantModuleVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.ApiOperation;

@FeignClient(value = "WMS-SAAS", contextId = "TenantModule")
public interface TenantModuleClientService {
	@RequestMapping(value = "/tenant-modules/{id}", method = RequestMethod.GET)
	public TenantModuleVo getById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-modules", method = RequestMethod.GET)
	public Page<TenantModuleVo> page(@RequestBody TenantModuleQueryParam tenantModuleQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-modules", method = RequestMethod.POST)
	public TenantModuleVo save(@RequestBody TenantModule tenantModule);
	
	@RequestMapping(value = "/tenant-modules/batch", method = RequestMethod.POST)
	public CommonResult<Object> saveBatch(@RequestBody List<TenantModule> tenantModuleList);

	@RequestMapping(value = "/tenant-modules/{id}", method = RequestMethod.PUT)
	public TenantModuleVo updateById(@PathVariable("id") String id, @RequestBody TenantModule tenantModule);

	@RequestMapping(value = "/tenant-modules/{id}", method = RequestMethod.PATCH)
	public TenantModuleVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantModule tenantModule);

	@RequestMapping(value = "/tenant-modules/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

