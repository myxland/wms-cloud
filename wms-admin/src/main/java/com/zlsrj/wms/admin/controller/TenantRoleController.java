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
import com.zlsrj.wms.api.client.service.TenantRoleClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantRoleQueryParam;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.api.vo.TenantRoleVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "角色信息", tags = { "角色信息操作接口" })
@Controller
@RequestMapping("/tenantRole")
@Slf4j
public class TenantRoleController {

	@Autowired
	private TenantRoleClientService tenantRoleClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询角色信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantRoleVo>> list(TenantRoleQueryParam tenantRoleQueryParam, int pageNum,
			int pageSize) {
		Page<TenantRoleVo> tenantRoleVoPage = tenantRoleClientService.page(tenantRoleQueryParam, pageNum, pageSize, "id", "desc");
		tenantRoleVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantRoleVo> tenantRoleCommonPage = CommonPage.restPage(tenantRoleVoPage);

		return CommonResult.success(tenantRoleCommonPage);
	}

	@ApiOperation(value = "新增角色信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantRoleVo> create(@RequestBody TenantRole tenantRole) {
		TenantRoleVo tenantRoleVo = tenantRoleClientService.save(tenantRole);

		return CommonResult.success(tenantRoleVo);
	}

	@ApiOperation(value = "根据ID查询角色信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantRoleVo> getById(@PathVariable("id") String id) {
		TenantRoleVo tenantRoleVo = tenantRoleClientService.getById(id);
		wrappperVo(tenantRoleVo);

		return CommonResult.success(tenantRoleVo);
	}

	@ApiOperation(value = "根据参数更新角色信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantRoleVo> getById(@RequestBody TenantRole tenantRole) {
		String id = tenantRole.getId();
		TenantRoleVo tenantRoleVo = tenantRoleClientService.updatePatchById(id, tenantRole);
		wrappperVo(tenantRoleVo);

		return CommonResult.success(tenantRoleVo);
	}
	
	@ApiOperation(value = "根据ID删除角色信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantRoleClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantRoleVo tenantRoleVo) {
		if (StringUtils.isEmpty(tenantRoleVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantRoleVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantRoleVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
