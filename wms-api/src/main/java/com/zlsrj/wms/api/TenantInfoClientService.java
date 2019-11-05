package com.zlsrj.wms.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "WMS-MBG")
public interface TenantInfoClientService {
	@RequestMapping(value = "/tenantInfo/list", method = RequestMethod.GET)
	public Object list();
}
