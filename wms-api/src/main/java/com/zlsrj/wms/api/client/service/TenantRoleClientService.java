package com.zlsrj.wms.api.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantRoleAddParam;
import com.zlsrj.wms.api.dto.TenantRoleQueryParam;
import com.zlsrj.wms.api.dto.TenantRoleUpdateParam;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.api.vo.TenantRoleVo;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "TenantRole")
public interface TenantRoleClientService {
	@RequestMapping(value = "/tenant-roles/{id}", method = RequestMethod.GET)
	public TenantRoleVo getById(@PathVariable("id") String id);
	
	@RequestMapping(value = "/tenant-roles/dictionary/{id}", method = RequestMethod.GET)
	public TenantRoleVo getDictionaryById(@PathVariable("id") String id);

	@RequestMapping(value = "/tenant-roles", method = RequestMethod.GET)
	public Page<TenantRoleVo> page(@RequestBody TenantRoleQueryParam tenantRoleQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);
	
	@RequestMapping(value = "/tenant-roles/list", method = RequestMethod.GET)
	public List<TenantRoleVo> list(@RequestBody TenantRoleQueryParam tenantRoleQueryParam);

	@RequestMapping(value = "/tenant-roles", method = RequestMethod.POST)
	public String save(@RequestBody TenantRoleAddParam tenantRoleAddParam);

	@RequestMapping(value = "/tenant-roles/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantRoleUpdateParam tenantRoleUpdateParam);

	
	@RequestMapping(value = "/tenant-roles/{id}", method = RequestMethod.PATCH)
	public TenantRoleVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantRole tenantRole);

	@RequestMapping(value = "/tenant-roles/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}

