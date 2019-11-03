package com.zlsrj.wms.id.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/id")
@Slf4j
public class IdController {

	@Value("${id.config.workerId}")
	private Long workerId;/* 终端ID */
	@Value("${id.config.datacenterId}")
	private Long datacenterId;/* 数据中心ID */

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "selectFallback")
	public Long select() {
		log.info("workerId=[{}]",workerId);
		log.info("datacenterId=[{}]",datacenterId);
		return IdUtil.createSnowflake(workerId, datacenterId).nextId();
	}

	public Long selectFallback() {
		return -1L;
	}
}
