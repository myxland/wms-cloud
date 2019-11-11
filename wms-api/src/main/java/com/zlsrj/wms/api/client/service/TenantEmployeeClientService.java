package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zlsrj.wms.api.vo.TenantEmployeeVo;

@FeignClient(value = "WMS-MBG", contextId = "TenantEmployee")
public interface TenantEmployeeClientService {

	@RequestMapping(value = "/tenant-employees/emp-name/{empName}", method = RequestMethod.GET)
	public TenantEmployeeVo getByEmpName(@PathVariable("empName") String empName);
}
