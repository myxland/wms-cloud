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
import com.zlsrj.wms.api.client.service.ModuleMenuClientService;
import com.zlsrj.wms.api.client.service.ModuleInfoClientService;
import com.zlsrj.wms.api.dto.ModuleMenuQueryParam;
import com.zlsrj.wms.api.entity.ModuleMenu;
import com.zlsrj.wms.api.vo.ModuleMenuVo;
import com.zlsrj.wms.api.vo.ModuleInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "模块菜单", tags = { "模块菜单操作接口" })
@Controller
@RequestMapping("/moduleMenu")
@Slf4j
public class ModuleMenuController {

	@Autowired
	private ModuleMenuClientService moduleMenuClientService;
	@Autowired
	private ModuleInfoClientService moduleInfoClientService;

	@ApiOperation(value = "根据参数查询模块菜单列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<ModuleMenuVo>> list(ModuleMenuQueryParam moduleMenuQueryParam, int pageNum,
			int pageSize) {
		Page<ModuleMenuVo> moduleMenuVoPage = moduleMenuClientService.page(moduleMenuQueryParam, pageNum, pageSize, "id", "desc");
		moduleMenuVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<ModuleMenuVo> moduleMenuCommonPage = CommonPage.restPage(moduleMenuVoPage);

		return CommonResult.success(moduleMenuCommonPage);
	}

	@ApiOperation(value = "更新模块菜单开放基础版")
	@RequestMapping(value = "/update/basicOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateBasicOn(@RequestParam("ids") String ids,
			@RequestParam("basicOn") Integer basicOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			ModuleMenu moduleMenu = new ModuleMenu();
			moduleMenu.setBasicOn(basicOn);
			moduleMenuClientService.updatePatchById(id, moduleMenu);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新模块菜单开放高级版")
	@RequestMapping(value = "/update/advanceOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateAdvanceOn(@RequestParam("ids") String ids,
			@RequestParam("advanceOn") Integer advanceOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			ModuleMenu moduleMenu = new ModuleMenu();
			moduleMenu.setAdvanceOn(advanceOn);
			moduleMenuClientService.updatePatchById(id, moduleMenu);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新模块菜单开放旗舰版")
	@RequestMapping(value = "/update/ultimateOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateUltimateOn(@RequestParam("ids") String ids,
			@RequestParam("ultimateOn") Integer ultimateOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			ModuleMenu moduleMenu = new ModuleMenu();
			moduleMenu.setUltimateOn(ultimateOn);
			moduleMenuClientService.updatePatchById(id, moduleMenu);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "新增模块菜单")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<ModuleMenuVo> create(@RequestBody ModuleMenu moduleMenu) {
		ModuleMenuVo moduleMenuVo = moduleMenuClientService.save(moduleMenu);

		return CommonResult.success(moduleMenuVo);
	}

	@ApiOperation(value = "根据ID查询模块菜单")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<ModuleMenuVo> getById(@PathVariable("id") Long id) {
		ModuleMenuVo moduleMenuVo = moduleMenuClientService.getById(id);
		wrappperVo(moduleMenuVo);

		return CommonResult.success(moduleMenuVo);
	}

	@ApiOperation(value = "根据参数更新模块菜单信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<ModuleMenuVo> getById(@RequestBody ModuleMenu moduleMenu) {
		Long id = moduleMenu.getId();
		ModuleMenuVo moduleMenuVo = moduleMenuClientService.updatePatchById(id, moduleMenu);
		wrappperVo(moduleMenuVo);

		return CommonResult.success(moduleMenuVo);
	}
	
	@ApiOperation(value = "根据ID删除模块菜单")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = moduleMenuClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(ModuleMenuVo moduleMenuVo) {
		if (StringUtils.isEmpty(moduleMenuVo.getModuleName())) {
			ModuleInfoVo moduleInfoVo = moduleInfoClientService.getById(moduleMenuVo.getModuleId());
			if (moduleInfoVo != null) {
				moduleMenuVo.setModuleName(moduleInfoVo.getModuleName());
			}
		}
	}

}
