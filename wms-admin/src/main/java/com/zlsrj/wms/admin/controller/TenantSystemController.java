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
import com.zlsrj.wms.api.client.service.TenantSystemClientService;
import com.zlsrj.wms.api.dto.TenantSystemQueryParam;
import com.zlsrj.wms.api.entity.TenantSystem;
import com.zlsrj.wms.api.vo.TenantSystemVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户模块", tags = { "租户模块操作接口" })
@Controller
@RequestMapping("/tenantSystem")
@Slf4j
public class TenantSystemController {

	@Autowired
	private TenantSystemClientService tenantSystemClientService;

	@ApiOperation(value = "根据参数查询租户模块列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantSystemVo>> list(TenantSystemQueryParam tenantSystemQueryParam, int pageNum,
			int pageSize) {
		Page<TenantSystemVo> tenantSystemVoPage = tenantSystemClientService.page(tenantSystemQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantSystemVo> tenantSystemCommonPage = CommonPage.restPage(tenantSystemVoPage);

		return CommonResult.success(tenantSystemCommonPage);
	}

	@ApiOperation(value = "更新租户模块模块状态")
	@RequestMapping(value = "/update/sysStatus", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateSysStatus(@RequestParam("ids") String ids,
			@RequestParam("sysStatus") Integer sysStatus) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			String id = n;
			TenantSystem tenantSystem = new TenantSystem();
			tenantSystem.setSysStatus(sysStatus);
			tenantSystemClientService.updatePatchById(id, tenantSystem);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "新增租户模块")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantSystemVo> create(@RequestBody TenantSystem tenantSystem) {
		TenantSystemVo tenantSystemVo = tenantSystemClientService.save(tenantSystem);

		return CommonResult.success(tenantSystemVo);
	}

	@ApiOperation(value = "根据ID查询租户模块")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantSystemVo> getById(@PathVariable("id") String id) {
		TenantSystemVo tenantSystemVo = tenantSystemClientService.getById(id);

		return CommonResult.success(tenantSystemVo);
	}

	@ApiOperation(value = "根据参数更新租户模块信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantSystemVo> getById(@RequestBody TenantSystem tenantSystem) {
		String id = tenantSystem.getId();
		TenantSystemVo tenantSystemVo = tenantSystemClientService.updatePatchById(id, tenantSystem);

		return CommonResult.success(tenantSystemVo);
	}
	
	@ApiOperation(value = "根据ID删除租户模块")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantSystemClientService.removeById(id);

		return commonResult;
	}

}
