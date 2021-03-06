package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantRoleMenuQueryParam;
import com.zlsrj.wms.api.entity.TenantRoleMenu;
import com.zlsrj.wms.api.vo.TenantRoleMenuVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-EMPLOYEE", contextId = "TenantRoleMenu")
public interface TenantRoleMenuClientService {
	@RequestMapping(value = "/tenant-role-menus/{id}", method = RequestMethod.GET)
	public TenantRoleMenuVo getById(@PathVariable("id") Long id);

	@RequestMapping(value = "/tenant-role-menus", method = RequestMethod.GET)
	public Page<TenantRoleMenuVo> page(@RequestBody TenantRoleMenuQueryParam tenantRoleMenuQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/tenant-role-menus", method = RequestMethod.POST)
	public TenantRoleMenuVo save(@RequestBody TenantRoleMenu tenantRoleMenu);

	@RequestMapping(value = "/tenant-role-menus/{id}", method = RequestMethod.PUT)
	public TenantRoleMenuVo updateById(@PathVariable("id") Long id, @RequestBody TenantRoleMenu tenantRoleMenu);

	@RequestMapping(value = "/tenant-role-menus/{id}", method = RequestMethod.PATCH)
	public TenantRoleMenuVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantRoleMenu tenantRoleMenu);

	@RequestMapping(value = "/tenant-role-menus/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

