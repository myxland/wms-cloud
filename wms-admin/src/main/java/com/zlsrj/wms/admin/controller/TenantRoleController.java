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
import com.zlsrj.wms.api.client.service.TenantRoleClientService;
import com.zlsrj.wms.api.dto.TenantRoleQueryParam;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.api.vo.TenantRoleVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户角色", tags = { "租户角色操作接口" })
@Controller
@RequestMapping("/tenantRole")
@Slf4j
public class TenantRoleController {

	@Autowired
	private TenantRoleClientService tenantRoleClientService;

	@ApiOperation(value = "根据参数查询租户角色列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantRoleVo>> list(TenantRoleQueryParam tenantRoleQueryParam, int pageNum,
			int pageSize) {
		Page<TenantRoleVo> tenantRoleVoPage = tenantRoleClientService.page(tenantRoleQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantRoleVo> tenantRoleCommonPage = CommonPage.restPage(tenantRoleVoPage);

		return CommonResult.success(tenantRoleCommonPage);
	}

	@ApiOperation(value = "新增租户角色")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantRoleVo> create(@RequestBody TenantRole tenantRole) {
		TenantRoleVo tenantRoleVo = tenantRoleClientService.save(tenantRole);

		return CommonResult.success(tenantRoleVo);
	}

	@ApiOperation(value = "根据ID查询租户角色")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantRoleVo> getById(@PathVariable("id") Long id) {
		TenantRoleVo tenantRoleVo = tenantRoleClientService.getById(id);

		return CommonResult.success(tenantRoleVo);
	}

	@ApiOperation(value = "根据参数更新租户角色信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantRoleVo> getById(@RequestBody TenantRole tenantRole) {
		Long id = tenantRole.getId();
		TenantRoleVo tenantRoleVo = tenantRoleClientService.updatePatchById(id, tenantRole);

		return CommonResult.success(tenantRoleVo);
	}
	
	@ApiOperation(value = "根据ID删除租户角色")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantRoleClientService.removeById(id);

		return commonResult;
	}

}
