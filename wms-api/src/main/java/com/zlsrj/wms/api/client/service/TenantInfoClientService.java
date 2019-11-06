package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zlsrj.wms.api.dto.TenantInfoQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.api.CommonPage;

@FeignClient(value = "WMS-MBG", contextId = "TenantInfo")
public interface TenantInfoClientService {
	@RequestMapping(value = "/tenantInfo/list", method = RequestMethod.GET)
	public Object list();

	@RequestMapping(value = "/tenantInfo/page", method = RequestMethod.GET)
	public CommonPage<TenantInfo> page(@RequestParam TenantInfoQueryParam tenantInfoQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize);
}
