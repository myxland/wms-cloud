package com.zlsrj.wms.saas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zlsrj.wms.saas.service.RedisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "Redis", tags = { "Redis操作接口" })
@RestController
@Slf4j
public class RedisRestController {

	@Autowired
	private RedisService<String,String> redisService;

	@ApiOperation(value = "redis设置值")
	@RequestMapping(value = "/redis/set", method = RequestMethod.GET)
	public Object setValue() {
		redisService.setValue("t", new java.util.Date().toString());
		String d = redisService.getValue("t");

		return d;
	}
}
