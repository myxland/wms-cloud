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
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.dto.TenantRoleSystemQueryParam;
import com.zlsrj.wms.mbg.entity.TenantRoleSystem;
import com.zlsrj.wms.mbg.service.ITenantRoleSystemService;

@RestController
@RequestMapping("/tenantRoleSystem")
public class TenantRoleSystemController {

	@Autowired
	private ITenantRoleSystemService tenantRoleSystemService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantRoleSystem tenantRoleSystem = tenantRoleSystemService.getById(id);

		return CommonResult.success(tenantRoleSystem);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantRoleSystem> tenantRoleSystemList = tenantRoleSystemService.list();

		return tenantRoleSystemList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantRoleSystemQueryParam tenantRoleSystemQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantRoleSystem> page = new Page<TenantRoleSystem>(pageNum, pageSize);
		QueryWrapper<TenantRoleSystem> queryWrapperTenantRoleSystem = new QueryWrapper<TenantRoleSystem>();
		queryWrapperTenantRoleSystem.lambda()
				.eq(tenantRoleSystemQueryParam.getId() != null, TenantRoleSystem::getId, tenantRoleSystemQueryParam.getId())
				.eq(tenantRoleSystemQueryParam.getTenantId() != null, TenantRoleSystem::getTenantId, tenantRoleSystemQueryParam.getTenantId())
				.eq(tenantRoleSystemQueryParam.getRoleId() != null, TenantRoleSystem::getRoleId, tenantRoleSystemQueryParam.getRoleId())
				.eq(tenantRoleSystemQueryParam.getSysId() != null, TenantRoleSystem::getSysId, tenantRoleSystemQueryParam.getSysId())
				.eq(tenantRoleSystemQueryParam.getRoleSysOn() != null, TenantRoleSystem::getRoleSysOn, tenantRoleSystemQueryParam.getRoleSysOn())
				;

		IPage<TenantRoleSystem> tenantRoleSystemPage = tenantRoleSystemService.page(page, queryWrapperTenantRoleSystem);

		return CommonPage.restPage(tenantRoleSystemPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantRoleSystem tenantRoleSystem) {
		boolean success = tenantRoleSystemService.save(tenantRoleSystem);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantRoleSystem tenantRoleSystem) {
		boolean success = tenantRoleSystemService.updateById(tenantRoleSystem);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantRoleSystemService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantRoleSystemService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
