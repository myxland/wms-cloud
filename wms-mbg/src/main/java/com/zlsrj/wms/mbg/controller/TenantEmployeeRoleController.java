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
import com.zlsrj.wms.api.dto.TenantEmployeeRoleQueryParam;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.ITenantEmployeeRoleService;

@RestController
@RequestMapping("/tenantEmployeeRole")
public class TenantEmployeeRoleController {

	@Autowired
	private ITenantEmployeeRoleService tenantEmployeeRoleService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantEmployeeRole tenantEmployeeRole = tenantEmployeeRoleService.getById(id);

		return CommonResult.success(tenantEmployeeRole);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantEmployeeRole> tenantEmployeeRoleList = tenantEmployeeRoleService.list();

		return tenantEmployeeRoleList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantEmployeeRoleQueryParam tenantEmployeeRoleQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantEmployeeRole> page = new Page<TenantEmployeeRole>(pageNum, pageSize);
		QueryWrapper<TenantEmployeeRole> queryWrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		queryWrapperTenantEmployeeRole.lambda()
				.eq(tenantEmployeeRoleQueryParam.getId() != null, TenantEmployeeRole::getId, tenantEmployeeRoleQueryParam.getId())
				.eq(tenantEmployeeRoleQueryParam.getTenantId() != null, TenantEmployeeRole::getTenantId, tenantEmployeeRoleQueryParam.getTenantId())
				.eq(tenantEmployeeRoleQueryParam.getEmpId() != null, TenantEmployeeRole::getEmpId, tenantEmployeeRoleQueryParam.getEmpId())
				.eq(tenantEmployeeRoleQueryParam.getRoleId() != null, TenantEmployeeRole::getRoleId, tenantEmployeeRoleQueryParam.getRoleId())
				.eq(tenantEmployeeRoleQueryParam.getEmpRoleOn() != null, TenantEmployeeRole::getEmpRoleOn, tenantEmployeeRoleQueryParam.getEmpRoleOn())
				;

		IPage<TenantEmployeeRole> tenantEmployeeRolePage = tenantEmployeeRoleService.page(page, queryWrapperTenantEmployeeRole);

		return CommonPage.restPage(tenantEmployeeRolePage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantEmployeeRole tenantEmployeeRole) {
		boolean success = tenantEmployeeRoleService.save(tenantEmployeeRole);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantEmployeeRole tenantEmployeeRole) {
		boolean success = tenantEmployeeRoleService.updateById(tenantEmployeeRole);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantEmployeeRoleService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantEmployeeRoleService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
