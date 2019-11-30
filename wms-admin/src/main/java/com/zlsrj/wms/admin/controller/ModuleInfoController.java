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
import com.zlsrj.wms.api.client.service.ModuleInfoClientService;
import com.zlsrj.wms.api.dto.ModuleInfoQueryParam;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.vo.ModuleInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "模块信息", tags = { "模块信息操作接口" })
@Controller
@RequestMapping("/moduleInfo")
@Slf4j
public class ModuleInfoController {

	@Autowired
	private ModuleInfoClientService moduleInfoClientService;

	@ApiOperation(value = "根据参数查询模块信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<ModuleInfoVo>> list(ModuleInfoQueryParam moduleInfoQueryParam, int pageNum,
			int pageSize) {
		Page<ModuleInfoVo> moduleInfoVoPage = moduleInfoClientService.page(moduleInfoQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<ModuleInfoVo> moduleInfoCommonPage = CommonPage.restPage(moduleInfoVoPage);

		return CommonResult.success(moduleInfoCommonPage);
	}

	@ApiOperation(value = "更新模块信息开放基础版")
	@RequestMapping(value = "/update/basicOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateBasicOn(@RequestParam("ids") String ids,
			@RequestParam("basicOn") Integer basicOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			ModuleInfo moduleInfo = new ModuleInfo();
			moduleInfo.setBasicOn(basicOn);
			moduleInfoClientService.updatePatchById(id, moduleInfo);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新模块信息开放高级版")
	@RequestMapping(value = "/update/advanceOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateAdvanceOn(@RequestParam("ids") String ids,
			@RequestParam("advanceOn") Integer advanceOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			ModuleInfo moduleInfo = new ModuleInfo();
			moduleInfo.setAdvanceOn(advanceOn);
			moduleInfoClientService.updatePatchById(id, moduleInfo);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新模块信息开放旗舰版")
	@RequestMapping(value = "/update/ultimateOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateUltimateOn(@RequestParam("ids") String ids,
			@RequestParam("ultimateOn") Integer ultimateOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			ModuleInfo moduleInfo = new ModuleInfo();
			moduleInfo.setUltimateOn(ultimateOn);
			moduleInfoClientService.updatePatchById(id, moduleInfo);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新模块信息功能发布")
	@RequestMapping(value = "/update/moduleReleaseOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateModuleReleaseOn(@RequestParam("ids") String ids,
			@RequestParam("moduleReleaseOn") Integer moduleReleaseOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			ModuleInfo moduleInfo = new ModuleInfo();
			moduleInfo.setModuleReleaseOn(moduleReleaseOn);
			moduleInfoClientService.updatePatchById(id, moduleInfo);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "新增模块信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<ModuleInfoVo> create(@RequestBody ModuleInfo moduleInfo) {
		ModuleInfoVo moduleInfoVo = moduleInfoClientService.save(moduleInfo);

		return CommonResult.success(moduleInfoVo);
	}

	@ApiOperation(value = "根据ID查询模块信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<ModuleInfoVo> getById(@PathVariable("id") Long id) {
		ModuleInfoVo moduleInfoVo = moduleInfoClientService.getById(id);

		return CommonResult.success(moduleInfoVo);
	}

	@ApiOperation(value = "根据参数更新模块信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<ModuleInfoVo> getById(@RequestBody ModuleInfo moduleInfo) {
		Long id = moduleInfo.getId();
		ModuleInfoVo moduleInfoVo = moduleInfoClientService.updatePatchById(id, moduleInfo);

		return CommonResult.success(moduleInfoVo);
	}
	
	@ApiOperation(value = "根据ID删除模块信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = moduleInfoClientService.removeById(id);

		return commonResult;
	}

}
