package com.zlsrj.wms.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.api.client.service.SystemDictionariesClientService;
import com.zlsrj.wms.api.dto.SystemDictionariesQueryParam;
import com.zlsrj.wms.api.vo.SystemDictionariesVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "基础字典表", tags = { "基础字典表操作接口" })
@Controller
@RequestMapping("/systemDictionaries")
@Slf4j
public class SystemDictionariesController {

	@Autowired
	private SystemDictionariesClientService systemDictionariesClientService;

	@ApiOperation(value = "根据参数查询基础字典表列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<SystemDictionariesVo>> list(SystemDictionariesQueryParam systemDictionariesQueryParam) {
		List<SystemDictionariesVo> systemDictionariesVoList = systemDictionariesClientService.list(systemDictionariesQueryParam);

		return CommonResult.success(systemDictionariesVoList);
	}
}
