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
import com.zlsrj.wms.api.client.service.TenantModuleClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantModuleQueryParam;
import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.api.vo.TenantModuleVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户模块", tags = { "租户模块操作接口" })
@Controller
@RequestMapping("/tenantModule")
@Slf4j
public class TenantModuleController {

	@Autowired
	private TenantModuleClientService tenantModuleClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询租户模块列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantModuleVo>> list(TenantModuleQueryParam tenantModuleQueryParam, int pageNum,
			int pageSize) {
		Page<TenantModuleVo> tenantModuleVoPage = tenantModuleClientService.page(tenantModuleQueryParam, pageNum, pageSize, "id", "desc");
		tenantModuleVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantModuleVo> tenantModuleCommonPage = CommonPage.restPage(tenantModuleVoPage);

		return CommonResult.success(tenantModuleCommonPage);
	}

	@ApiOperation(value = "更新租户模块状态")
	@RequestMapping(value = "/update/moduleStatus", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateModuleStatus(@RequestParam("ids") String ids,
			@RequestParam("moduleStatus") Integer moduleStatus) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			TenantModule tenantModule = new TenantModule();
			tenantModule.setModuleStatus(moduleStatus);
			tenantModuleClientService.updatePatchById(id, tenantModule);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "新增租户模块")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantModuleVo> create(@RequestBody TenantModule tenantModule) {
		TenantModuleVo tenantModuleVo = tenantModuleClientService.save(tenantModule);

		return CommonResult.success(tenantModuleVo);
	}

	@ApiOperation(value = "根据ID查询租户模块")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantModuleVo> getById(@PathVariable("id") Long id) {
		TenantModuleVo tenantModuleVo = tenantModuleClientService.getById(id);
		wrappperVo(tenantModuleVo);

		return CommonResult.success(tenantModuleVo);
	}

	@ApiOperation(value = "根据参数更新租户模块信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantModuleVo> getById(@RequestBody TenantModule tenantModule) {
		Long id = tenantModule.getId();
		TenantModuleVo tenantModuleVo = tenantModuleClientService.updatePatchById(id, tenantModule);
		wrappperVo(tenantModuleVo);

		return CommonResult.success(tenantModuleVo);
	}
	
	@ApiOperation(value = "根据ID删除租户模块")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantModuleClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantModuleVo tenantModuleVo) {
		if (StringUtils.isEmpty(tenantModuleVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantModuleVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantModuleVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
