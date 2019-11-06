package com.zlsrj.wms.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zlsrj.wms.api.client.service.IdClientService;
import com.zlsrj.wms.api.client.service.TenantEmployeeClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.common.api.CommonResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {

	@Autowired
	private IdClientService idClientService;

	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@Autowired
	private TenantEmployeeClientService tenantEmployeeClientService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		Long id = idClientService.select();
		CommonResult<TenantEmployee> tenantEmployeeCommonResult = tenantEmployeeClientService.getByEmpName("董天宇");
		TenantEmployee tenantEmployee = null;
		if (tenantEmployeeCommonResult.getCode() == 200L) {
			tenantEmployee = tenantEmployeeCommonResult.getData();
		}

		return "hello " + id + " " + tenantEmployee.toString();
	}

}
