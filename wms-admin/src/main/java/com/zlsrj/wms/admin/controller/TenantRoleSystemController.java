package com.zlsrj.wms.admin.controller;

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
import com.zlsrj.wms.api.client.service.TenantRoleSystemClientService;
import com.zlsrj.wms.api.dto.TenantRoleSystemQueryParam;
import com.zlsrj.wms.api.entity.TenantRoleSystem;
import com.zlsrj.wms.api.vo.TenantRoleSystemVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "角色模块", tags = { "角色模块操作接口" })
@Controller
@RequestMapping("/tenantRoleSystem")
@Slf4j
public class TenantRoleSystemController {

	@Autowired
	private TenantRoleSystemClientService tenantRoleSystemClientService;

	@ApiOperation(value = "根据参数查询角色模块列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantRoleSystemVo>> list(TenantRoleSystemQueryParam tenantRoleSystemQueryParam, int pageNum,
			int pageSize) {
		Page<TenantRoleSystemVo> tenantRoleSystemVoPage = tenantRoleSystemClientService.page(tenantRoleSystemQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantRoleSystemVo> tenantRoleSystemCommonPage = CommonPage.restPage(tenantRoleSystemVoPage);

		return CommonResult.success(tenantRoleSystemCommonPage);
	}

	@ApiOperation(value = "新增角色模块")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantRoleSystemVo> create(@RequestBody TenantRoleSystem tenantRoleSystem) {
		TenantRoleSystemVo tenantRoleSystemVo = tenantRoleSystemClientService.save(tenantRoleSystem);

		return CommonResult.success(tenantRoleSystemVo);
	}

	@ApiOperation(value = "根据ID查询角色模块")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantRoleSystemVo> getById(@PathVariable("id") String id) {
		TenantRoleSystemVo tenantRoleSystemVo = tenantRoleSystemClientService.getById(id);

		return CommonResult.success(tenantRoleSystemVo);
	}

	@ApiOperation(value = "根据参数更新角色模块信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantRoleSystemVo> getById(@RequestBody TenantRoleSystem tenantRoleSystem) {
		String id = tenantRoleSystem.getId();
		TenantRoleSystemVo tenantRoleSystemVo = tenantRoleSystemClientService.updatePatchById(id, tenantRoleSystem);

		return CommonResult.success(tenantRoleSystemVo);
	}
	
	@ApiOperation(value = "根据ID删除角色模块")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantRoleSystemClientService.removeById(id);

		return commonResult;
	}

}
