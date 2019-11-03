package com.zlsrj.wms.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "WMS-ID")
public interface IdClientService {
	@RequestMapping(value = "/id/select", method = RequestMethod.GET)
	public long select();
}
