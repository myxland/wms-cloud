package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantRoleSystemQueryParam;
import com.zlsrj.wms.api.entity.TenantRoleSystem;
import com.zlsrj.wms.api.vo.TenantRoleSystemVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-MBG", contextId = "TenantRoleSystem")
public interface TenantRoleSystemClientService {
	@RequestMapping(value = "/tenant-role-systems/{id}", method = RequestMethod.GET)
	public TenantRoleSystemVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-role-systems", method = RequestMethod.GET)
	public Page<TenantRoleSystemVo> page(@RequestBody TenantRoleSystemQueryParam tenantRoleSystemQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-role-systems", method = RequestMethod.POST)
	public TenantRoleSystemVo save(@RequestBody TenantRoleSystem tenantRoleSystem);

	@RequestMapping(value = "/tenant-role-systems/{id}", method = RequestMethod.PUT)
	public TenantRoleSystemVo updateById(@PathVariable("id") Long id, @RequestBody TenantRoleSystem tenantRoleSystem);

	@RequestMapping(value = "/tenant-role-systems/{id}", method = RequestMethod.PATCH)
	public TenantRoleSystemVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantRoleSystem tenantRoleSystem);

	@RequestMapping(value = "/tenant-role-systems/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

