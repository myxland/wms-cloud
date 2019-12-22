package com.zlsrj.wms.admin.controller;

import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.TenantRoleMenuClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.client.service.ModuleInfoClientService;
import com.zlsrj.wms.api.dto.TenantRoleMenuQueryParam;
import com.zlsrj.wms.api.entity.TenantRoleMenu;
import com.zlsrj.wms.api.vo.TenantRoleMenuVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.api.vo.ModuleInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "角色菜单", tags = { "角色菜单操作接口" })
@Controller
@RequestMapping("/tenantRoleMenu")
@Slf4j
public class TenantRoleMenuController {

	@Autowired
	private TenantRoleMenuClientService tenantRoleMenuClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	@Autowired
	private ModuleInfoClientService moduleInfoClientService;

	@ApiOperation(value = "根据参数查询角色菜单列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantRoleMenuVo>> list(TenantRoleMenuQueryParam tenantRoleMenuQueryParam, int pageNum,
			int pageSize) {
		Page<TenantRoleMenuVo> tenantRoleMenuVoPage = tenantRoleMenuClientService.page(tenantRoleMenuQueryParam, pageNum, pageSize, "id", "desc");
		tenantRoleMenuVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantRoleMenuVo> tenantRoleMenuCommonPage = CommonPage.restPage(tenantRoleMenuVoPage);

		return CommonResult.success(tenantRoleMenuCommonPage);
	}

	@ApiOperation(value = "新增角色菜单")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantRoleMenuVo> create(@RequestBody TenantRoleMenu tenantRoleMenu) {
		TenantRoleMenuVo tenantRoleMenuVo = tenantRoleMenuClientService.save(tenantRoleMenu);

		return CommonResult.success(tenantRoleMenuVo);
	}

	@ApiOperation(value = "根据ID查询角色菜单")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantRoleMenuVo> getById(@PathVariable("id") Long id) {
		TenantRoleMenuVo tenantRoleMenuVo = tenantRoleMenuClientService.getById(id);
		wrappperVo(tenantRoleMenuVo);

		return CommonResult.success(tenantRoleMenuVo);
	}

	@ApiOperation(value = "根据参数更新角色菜单信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantRoleMenuVo> getById(@RequestBody TenantRoleMenu tenantRoleMenu) {
		Long id = tenantRoleMenu.getId();
		TenantRoleMenuVo tenantRoleMenuVo = tenantRoleMenuClientService.updatePatchById(id, tenantRoleMenu);
		wrappperVo(tenantRoleMenuVo);

		return CommonResult.success(tenantRoleMenuVo);
	}
	
	@ApiOperation(value = "根据ID删除角色菜单")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantRoleMenuClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantRoleMenuVo tenantRoleMenuVo) {
		if (StringUtils.isEmpty(tenantRoleMenuVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantRoleMenuVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantRoleMenuVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		if (StringUtils.isEmpty(tenantRoleMenuVo.getModuleName())) {
			ModuleInfoVo moduleInfoVo = moduleInfoClientService.getById(tenantRoleMenuVo.getModuleId());
			if (moduleInfoVo != null) {
				tenantRoleMenuVo.setModuleName(moduleInfoVo.getModuleName());
			}
		}
	}

}
