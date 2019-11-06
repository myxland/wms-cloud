package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.common.api.CommonResult;

@FeignClient(value = "WMS-MBG", contextId = "TenantEmployee")
public interface TenantEmployeeClientService {

	@RequestMapping(value = "/tenantEmployee/select/empName/{empName}", method = RequestMethod.GET)
	public CommonResult<TenantEmployee> getByEmpName(@PathVariable("empName") String empName);
}
