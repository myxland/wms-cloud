package com.zlsrj.wms.saas.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "超时测试", tags = { "超时测试操作接口" })
@RestController
@Slf4j
public class TimeoutRestController {


	@ApiOperation(value = "测试Rest超时时间")
	@RequestMapping(value = "/timeout/{second}", method = RequestMethod.GET)
	public CommonResult<Object> test(@PathVariable("second") Integer second) {
		log.info("timeout test begin , second={}",second);
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.info("timeout test exception , second={}",second);
			CommonResult.failed(e.getMessage());
		}
		log.info("timeout test end , second={}",second);
		return CommonResult.success(second);
	}


}
