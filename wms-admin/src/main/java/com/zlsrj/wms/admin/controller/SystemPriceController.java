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
import com.zlsrj.wms.api.client.service.SystemPriceClientService;
import com.zlsrj.wms.api.dto.SystemPriceQueryParam;
import com.zlsrj.wms.api.entity.SystemPrice;
import com.zlsrj.wms.api.vo.SystemPriceVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "模块各版本价格定义表", tags = { "模块各版本价格定义表操作接口" })
@Controller
@RequestMapping("/systemPrice")
@Slf4j
public class SystemPriceController {

	@Autowired
	private SystemPriceClientService systemPriceClientService;

	@ApiOperation(value = "根据参数查询模块各版本价格定义表列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<SystemPriceVo>> list(SystemPriceQueryParam systemPriceQueryParam, int pageNum,
			int pageSize) {
		Page<SystemPriceVo> systemPriceVoPage = systemPriceClientService.page(systemPriceQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<SystemPriceVo> systemPriceCommonPage = CommonPage.restPage(systemPriceVoPage);

		return CommonResult.success(systemPriceCommonPage);
	}

	@ApiOperation(value = "新增模块各版本价格定义表")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<SystemPriceVo> create(@RequestBody SystemPrice systemPrice) {
		SystemPriceVo systemPriceVo = systemPriceClientService.save(systemPrice);

		return CommonResult.success(systemPriceVo);
	}

	@ApiOperation(value = "根据ID查询模块各版本价格定义表")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<SystemPriceVo> getById(@PathVariable("id") Long id) {
		SystemPriceVo systemPriceVo = systemPriceClientService.getById(id);

		return CommonResult.success(systemPriceVo);
	}

	@ApiOperation(value = "根据参数更新模块各版本价格定义表信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<SystemPriceVo> getById(@RequestBody SystemPrice systemPrice) {
		Long id = systemPrice.getId();
		SystemPriceVo systemPriceVo = systemPriceClientService.updatePatchById(id, systemPrice);

		return CommonResult.success(systemPriceVo);
	}
	
	@ApiOperation(value = "根据ID删除模块各版本价格定义表")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = systemPriceClientService.removeById(id);

		return commonResult;
	}

}
