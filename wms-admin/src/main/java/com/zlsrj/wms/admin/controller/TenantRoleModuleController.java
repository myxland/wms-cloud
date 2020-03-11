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
import com.zlsrj.wms.api.client.service.TenantRoleModuleClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.client.service.ModuleInfoClientService;
import com.zlsrj.wms.api.dto.TenantRoleModuleQueryParam;
import com.zlsrj.wms.api.entity.TenantRoleModule;
import com.zlsrj.wms.api.vo.TenantRoleModuleVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.api.vo.ModuleInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "角色模块", tags = { "角色模块操作接口" })
@Controller
@RequestMapping("/tenantRoleModule")
@Slf4j
public class TenantRoleModuleController {

	@Autowired
	private TenantRoleModuleClientService tenantRoleModuleClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	@Autowired
	private ModuleInfoClientService moduleInfoClientService;

	@ApiOperation(value = "根据参数查询角色模块列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantRoleModuleVo>> list(TenantRoleModuleQueryParam tenantRoleModuleQueryParam, int pageNum,
			int pageSize) {
		Page<TenantRoleModuleVo> tenantRoleModuleVoPage = tenantRoleModuleClientService.page(tenantRoleModuleQueryParam, pageNum, pageSize, "id", "desc");
		tenantRoleModuleVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantRoleModuleVo> tenantRoleModuleCommonPage = CommonPage.restPage(tenantRoleModuleVoPage);

		return CommonResult.success(tenantRoleModuleCommonPage);
	}

	@ApiOperation(value = "新增角色模块")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantRoleModuleVo> create(@RequestBody TenantRoleModule tenantRoleModule) {
		TenantRoleModuleVo tenantRoleModuleVo = tenantRoleModuleClientService.save(tenantRoleModule);

		return CommonResult.success(tenantRoleModuleVo);
	}

	@ApiOperation(value = "根据ID查询角色模块")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantRoleModuleVo> getById(@PathVariable("id") String id) {
		TenantRoleModuleVo tenantRoleModuleVo = tenantRoleModuleClientService.getById(id);
		wrappperVo(tenantRoleModuleVo);

		return CommonResult.success(tenantRoleModuleVo);
	}

	@ApiOperation(value = "根据参数更新角色模块信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantRoleModuleVo> getById(@RequestBody TenantRoleModule tenantRoleModule) {
		String id = tenantRoleModule.getId();
		TenantRoleModuleVo tenantRoleModuleVo = tenantRoleModuleClientService.updatePatchById(id, tenantRoleModule);
		wrappperVo(tenantRoleModuleVo);

		return CommonResult.success(tenantRoleModuleVo);
	}
	
	@ApiOperation(value = "根据ID删除角色模块")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantRoleModuleClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantRoleModuleVo tenantRoleModuleVo) {
		if (StringUtils.isEmpty(tenantRoleModuleVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantRoleModuleVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantRoleModuleVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		if (StringUtils.isEmpty(tenantRoleModuleVo.getModuleName())) {
			ModuleInfoVo moduleInfoVo = moduleInfoClientService.getById(tenantRoleModuleVo.getModuleId());
			if (moduleInfoVo != null) {
				tenantRoleModuleVo.setModuleName(moduleInfoVo.getModuleName());
			}
		}
	}

}
