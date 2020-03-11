package com.zlsrj.wms.admin.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.ModuleInfoClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.client.service.TenantModuleClientService;
import com.zlsrj.wms.api.dto.TenantModuleQueryParam;
import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.api.vo.ModuleInfoVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.api.vo.TenantModuleVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户开通模块清单", tags = { "租户开通模块清单操作接口" })
@Controller
@RequestMapping("/tenantModule")
@Slf4j
public class TenantModuleController {

	@Autowired
	private TenantModuleClientService tenantModuleClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	@Autowired
	private ModuleInfoClientService moduleInfoClientService;

	@ApiOperation(value = "根据参数查询租户开通模块清单列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantModuleVo>> list(TenantModuleQueryParam tenantModuleQueryParam, int pageNum,
			int pageSize) {
		Page<TenantModuleVo> tenantModuleVoPage = tenantModuleClientService.page(tenantModuleQueryParam, pageNum,
				pageSize, "id", "desc");
		tenantModuleVoPage.getRecords().stream().forEach(v -> wrappperVo(v));

		CommonPage<TenantModuleVo> tenantModuleCommonPage = CommonPage.restPage(tenantModuleVoPage);

		return CommonResult.success(tenantModuleCommonPage);
	}

	@ApiOperation(value = "新增租户开通模块清单")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantModuleVo> create(@RequestBody TenantModule tenantModule) {
		TenantModuleVo tenantModuleVo = tenantModuleClientService.save(tenantModule);

		return CommonResult.success(tenantModuleVo);
	}

	@ApiOperation(value = "批量新增租户开通模块清单")
	@RequestMapping(value = "/createBatch", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> createBatch(@RequestBody List<TenantModule> tenantModuleList) {
		CommonResult<Object> commonResult = tenantModuleClientService.saveBatch(tenantModuleList);

		return commonResult;
	}

	@ApiOperation(value = "根据ID查询租户开通模块清单")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantModuleVo> getById(@PathVariable("id") String id) {
		TenantModuleVo tenantModuleVo = tenantModuleClientService.getById(id);
		wrappperVo(tenantModuleVo);

		return CommonResult.success(tenantModuleVo);
	}

	@ApiOperation(value = "根据参数更新租户开通模块清单信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantModuleVo> getById(@RequestBody TenantModule tenantModule) {
		String id = tenantModule.getId();
		TenantModuleVo tenantModuleVo = tenantModuleClientService.updatePatchById(id, tenantModule);
		wrappperVo(tenantModuleVo);

		return CommonResult.success(tenantModuleVo);
	}

	@ApiOperation(value = "根据ID删除租户开通模块清单")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
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
		if (StringUtils.isEmpty(tenantModuleVo.getModuleName())) {
			ModuleInfoVo moduleInfoVo = moduleInfoClientService.getById(tenantModuleVo.getModuleId());
			if (moduleInfoVo != null) {
				tenantModuleVo.setModuleName(moduleInfoVo.getModuleName());
			}
		}
	}

}
