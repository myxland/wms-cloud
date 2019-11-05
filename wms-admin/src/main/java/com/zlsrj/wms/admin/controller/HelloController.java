package com.zlsrj.wms.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zlsrj.wms.api.IdClientService;
import com.zlsrj.wms.api.TenantInfoClientService;

@RestController
public class HelloController {

	@Autowired
	private IdClientService idClientService;

	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		Long id = idClientService.select();
		return "hello " + id;
	}

	@RequestMapping(value = "/tenantInfo/list", method = RequestMethod.GET)
	public Object tenantInfoList() {
		return tenantInfoClientService.list();
	}
}
