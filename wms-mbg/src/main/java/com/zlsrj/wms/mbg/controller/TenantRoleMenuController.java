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
import com.zlsrj.wms.api.dto.TenantRoleMenuQueryParam;
import com.zlsrj.wms.api.entity.TenantRoleMenu;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.ITenantRoleMenuService;

@RestController
@RequestMapping("/tenantRoleMenu")
public class TenantRoleMenuController {

	@Autowired
	private ITenantRoleMenuService tenantRoleMenuService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantRoleMenu tenantRoleMenu = tenantRoleMenuService.getById(id);

		return CommonResult.success(tenantRoleMenu);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantRoleMenu> tenantRoleMenuList = tenantRoleMenuService.list();

		return tenantRoleMenuList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantRoleMenuQueryParam tenantRoleMenuQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantRoleMenu> page = new Page<TenantRoleMenu>(pageNum, pageSize);
		QueryWrapper<TenantRoleMenu> queryWrapperTenantRoleMenu = new QueryWrapper<TenantRoleMenu>();
		queryWrapperTenantRoleMenu.lambda()
				.eq(tenantRoleMenuQueryParam.getId() != null, TenantRoleMenu::getId, tenantRoleMenuQueryParam.getId())
				.eq(tenantRoleMenuQueryParam.getTenantId() != null, TenantRoleMenu::getTenantId, tenantRoleMenuQueryParam.getTenantId())
				.eq(tenantRoleMenuQueryParam.getRoleId() != null, TenantRoleMenu::getRoleId, tenantRoleMenuQueryParam.getRoleId())
				.eq(tenantRoleMenuQueryParam.getSysId() != null, TenantRoleMenu::getSysId, tenantRoleMenuQueryParam.getSysId())
				.eq(tenantRoleMenuQueryParam.getMenuId() != null, TenantRoleMenu::getMenuId, tenantRoleMenuQueryParam.getMenuId())
				.eq(tenantRoleMenuQueryParam.getRoleMenuOn() != null, TenantRoleMenu::getRoleMenuOn, tenantRoleMenuQueryParam.getRoleMenuOn())
				;

		IPage<TenantRoleMenu> tenantRoleMenuPage = tenantRoleMenuService.page(page, queryWrapperTenantRoleMenu);

		return CommonPage.restPage(tenantRoleMenuPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantRoleMenu tenantRoleMenu) {
		boolean success = tenantRoleMenuService.save(tenantRoleMenu);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantRoleMenu tenantRoleMenu) {
		boolean success = tenantRoleMenuService.updateById(tenantRoleMenu);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantRoleMenuService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantRoleMenuService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
