package com.zlsrj.wms.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.api.client.service.TimeoutClientService;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "超时测试", tags = { "超时测试操作接口" })
@Controller
@RequestMapping("/timeout")
@Slf4j
public class TimeoutController {

	@Autowired
	private TimeoutClientService timeoutClientService;
	
	
	@ApiOperation(value = "测试Rest超时时间")
	@RequestMapping(value = "/{second}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> test(@PathVariable("second") Integer second) {
		log.info("second={}",second);
		return timeoutClientService.test(second);
	}
}
