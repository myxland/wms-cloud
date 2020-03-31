package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-SAAS", contextId = "Timeout")
public interface TimeoutClientService {
	@RequestMapping(value = "/timeout/{second}", method = RequestMethod.GET)
	public CommonResult<Object> test(@PathVariable("second") Integer second);
}
