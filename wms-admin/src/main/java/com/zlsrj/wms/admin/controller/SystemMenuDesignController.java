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
import com.zlsrj.wms.api.client.service.SystemMenuDesignClientService;
import com.zlsrj.wms.api.dto.SystemMenuDesignQueryParam;
import com.zlsrj.wms.api.entity.SystemMenuDesign;
import com.zlsrj.wms.api.vo.SystemMenuDesignVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "模块菜单", tags = { "模块菜单操作接口" })
@Controller
@RequestMapping("/systemMenuDesign")
@Slf4j
public class SystemMenuDesignController {

	@Autowired
	private SystemMenuDesignClientService systemMenuDesignClientService;

	@ApiOperation(value = "根据参数查询模块菜单列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<SystemMenuDesignVo>> list(SystemMenuDesignQueryParam systemMenuDesignQueryParam, int pageNum,
			int pageSize) {
		Page<SystemMenuDesignVo> systemMenuDesignVoPage = systemMenuDesignClientService.page(systemMenuDesignQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<SystemMenuDesignVo> systemMenuDesignCommonPage = CommonPage.restPage(systemMenuDesignVoPage);

		return CommonResult.success(systemMenuDesignCommonPage);
	}

	@ApiOperation(value = "更新模块菜单开放基础版")
	@RequestMapping(value = "/update/basicOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateBasicOn(@RequestParam("ids") String ids,
			@RequestParam("basicOn") Integer basicOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			String id = n;
			SystemMenuDesign systemMenuDesign = new SystemMenuDesign();
			systemMenuDesign.setBasicOn(basicOn);
			systemMenuDesignClientService.updatePatchById(id, systemMenuDesign);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新模块菜单开放高级版")
	@RequestMapping(value = "/update/advanceOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateAdvanceOn(@RequestParam("ids") String ids,
			@RequestParam("advanceOn") Integer advanceOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			String id = n;
			SystemMenuDesign systemMenuDesign = new SystemMenuDesign();
			systemMenuDesign.setAdvanceOn(advanceOn);
			systemMenuDesignClientService.updatePatchById(id, systemMenuDesign);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新模块菜单开放旗舰版")
	@RequestMapping(value = "/update/ultimateOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateUltimateOn(@RequestParam("ids") String ids,
			@RequestParam("ultimateOn") Integer ultimateOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			String id = n;
			SystemMenuDesign systemMenuDesign = new SystemMenuDesign();
			systemMenuDesign.setUltimateOn(ultimateOn);
			systemMenuDesignClientService.updatePatchById(id, systemMenuDesign);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "新增模块菜单")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<SystemMenuDesignVo> create(@RequestBody SystemMenuDesign systemMenuDesign) {
		SystemMenuDesignVo systemMenuDesignVo = systemMenuDesignClientService.save(systemMenuDesign);

		return CommonResult.success(systemMenuDesignVo);
	}

	@ApiOperation(value = "根据ID查询模块菜单")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<SystemMenuDesignVo> getById(@PathVariable("id") String id) {
		SystemMenuDesignVo systemMenuDesignVo = systemMenuDesignClientService.getById(id);

		return CommonResult.success(systemMenuDesignVo);
	}

	@ApiOperation(value = "根据参数更新模块菜单信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<SystemMenuDesignVo> getById(@RequestBody SystemMenuDesign systemMenuDesign) {
		String id = systemMenuDesign.getId();
		SystemMenuDesignVo systemMenuDesignVo = systemMenuDesignClientService.updatePatchById(id, systemMenuDesign);

		return CommonResult.success(systemMenuDesignVo);
	}
	
	@ApiOperation(value = "根据ID删除模块菜单")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = systemMenuDesignClientService.removeById(id);

		return commonResult;
	}

}
