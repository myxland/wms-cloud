package com.zlsrj.wms.mbg.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantRoleQueryParam;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.ITenantRoleService;

@RestController
@RequestMapping("/tenantRole")
public class TenantRoleController {

	@Autowired
	private ITenantRoleService tenantRoleService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantRole tenantRole = tenantRoleService.getById(id);

		return CommonResult.success(tenantRole);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantRole> tenantRoleList = tenantRoleService.list();

		return tenantRoleList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantRoleQueryParam tenantRoleQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantRole> page = new Page<TenantRole>(pageNum, pageSize);
		QueryWrapper<TenantRole> queryWrapperTenantRole = new QueryWrapper<TenantRole>();
		queryWrapperTenantRole.lambda()
				.eq(tenantRoleQueryParam.getId() != null, TenantRole::getId, tenantRoleQueryParam.getId())
				.eq(tenantRoleQueryParam.getTenantId() != null, TenantRole::getTenantId, tenantRoleQueryParam.getTenantId())
				.eq(tenantRoleQueryParam.getRoleName() != null, TenantRole::getRoleName, tenantRoleQueryParam.getRoleName())
				.eq(tenantRoleQueryParam.getRoleRemark() != null, TenantRole::getRoleRemark, tenantRoleQueryParam.getRoleRemark())
				;

		IPage<TenantRole> tenantRolePage = tenantRoleService.page(page, queryWrapperTenantRole);

		return CommonPage.restPage(tenantRolePage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantRole tenantRole) {
		boolean success = tenantRoleService.save(tenantRole);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantRole tenantRole) {
		boolean success = tenantRoleService.updateById(tenantRole);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantRoleService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantRoleService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
