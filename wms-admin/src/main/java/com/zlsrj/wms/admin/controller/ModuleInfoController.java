package com.zlsrj.wms.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.api.client.service.ModuleInfoClientService;
import com.zlsrj.wms.api.dto.ModuleInfoAddParam;
import com.zlsrj.wms.api.dto.ModuleInfoQueryParam;
import com.zlsrj.wms.api.dto.ModuleInfoUpdateParam;
import com.zlsrj.wms.api.vo.ModuleInfoVo;
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
	public CommonResult<List<ModuleInfoVo>> list(ModuleInfoQueryParam moduleInfoQueryParam) {
		List<ModuleInfoVo> moduleInfoVoList = moduleInfoClientService.list(moduleInfoQueryParam);
		moduleInfoVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(moduleInfoVoList);
	}

	@ApiOperation(value = "新增模块信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody ModuleInfoAddParam moduleInfoAddParam) {
		String id = moduleInfoClientService.save(moduleInfoAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询模块信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<ModuleInfoVo> getById(@PathVariable("id") String id) {
		ModuleInfoVo moduleInfoVo = moduleInfoClientService.getById(id);
		wrappperVo(moduleInfoVo);

		return CommonResult.success(moduleInfoVo);
	}

	@ApiOperation(value = "根据参数更新模块信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody ModuleInfoUpdateParam moduleInfoUpdateParam) {
		boolean success = moduleInfoClientService.updateById(id, moduleInfoUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除模块信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = moduleInfoClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(ModuleInfoVo moduleInfoVo) {
		
	}

}
