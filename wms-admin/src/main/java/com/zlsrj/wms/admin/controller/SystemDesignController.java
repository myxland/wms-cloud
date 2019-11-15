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
import com.zlsrj.wms.api.client.service.SystemDesignClientService;
import com.zlsrj.wms.api.dto.SystemDesignQueryParam;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.api.vo.SystemDesignVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "系统定义", tags = { "系统定义操作接口" })
@Controller
@RequestMapping("/systemDesign")
@Slf4j
public class SystemDesignController {

	@Autowired
	private SystemDesignClientService systemDesignClientService;

	@ApiOperation(value = "根据参数查询系统定义列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<SystemDesignVo>> list(SystemDesignQueryParam systemDesignQueryParam, int pageNum,
			int pageSize) {
		Page<SystemDesignVo> systemDesignVoPage = systemDesignClientService.page(systemDesignQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<SystemDesignVo> systemDesignCommonPage = CommonPage.restPage(systemDesignVoPage);

		return CommonResult.success(systemDesignCommonPage);
	}

	@ApiOperation(value = "更新系统定义开放基础版")
	@RequestMapping(value = "/update/basicOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateBasicOn(@RequestParam("ids") String ids,
			@RequestParam("basicOn") Integer basicOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			SystemDesign systemDesign = new SystemDesign();
			systemDesign.setBasicOn(basicOn);
			systemDesignClientService.updatePatchById(id, systemDesign);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新系统定义开放高级版")
	@RequestMapping(value = "/update/advanceOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateAdvanceOn(@RequestParam("ids") String ids,
			@RequestParam("advanceOn") Integer advanceOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			SystemDesign systemDesign = new SystemDesign();
			systemDesign.setAdvanceOn(advanceOn);
			systemDesignClientService.updatePatchById(id, systemDesign);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新系统定义开放旗舰版")
	@RequestMapping(value = "/update/ultimateOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateUltimateOn(@RequestParam("ids") String ids,
			@RequestParam("ultimateOn") Integer ultimateOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			SystemDesign systemDesign = new SystemDesign();
			systemDesign.setUltimateOn(ultimateOn);
			systemDesignClientService.updatePatchById(id, systemDesign);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新系统定义功能发布")
	@RequestMapping(value = "/update/moduleReleaseOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateModuleReleaseOn(@RequestParam("ids") String ids,
			@RequestParam("moduleReleaseOn") Integer moduleReleaseOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			SystemDesign systemDesign = new SystemDesign();
			systemDesign.setModuleReleaseOn(moduleReleaseOn);
			systemDesignClientService.updatePatchById(id, systemDesign);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "新增系统定义")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<SystemDesignVo> create(@RequestBody SystemDesign systemDesign) {
		SystemDesignVo systemDesignVo = systemDesignClientService.save(systemDesign);

		return CommonResult.success(systemDesignVo);
	}

	@ApiOperation(value = "根据ID查询系统定义")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<SystemDesignVo> getById(@PathVariable("id") Long id) {
		SystemDesignVo systemDesignVo = systemDesignClientService.getById(id);

		return CommonResult.success(systemDesignVo);
	}

	@ApiOperation(value = "根据参数更新系统定义信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<SystemDesignVo> getById(@RequestBody SystemDesign systemDesign) {
		Long id = systemDesign.getId();
		SystemDesignVo systemDesignVo = systemDesignClientService.updatePatchById(id, systemDesign);

		return CommonResult.success(systemDesignVo);
	}
	
	@ApiOperation(value = "根据ID删除系统定义")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = systemDesignClientService.removeById(id);

		return commonResult;
	}

}
