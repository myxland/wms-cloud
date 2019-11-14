package com.zlsrj.wms.iam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zlsrj.wms.api.dto.AdminLoginParam;
import com.zlsrj.wms.api.entity.AdminUser;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.iam.service.ITenantEmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AdminUserController {

	@Autowired
	private ITenantEmployeeService tenantEmployeeService;

	// 根据用户名，密码获取登录结果
	@RequestMapping(value = "/adminUser/login", method = RequestMethod.POST)
	public AdminUser login(@RequestBody AdminLoginParam adminLoginParam) {
		String username = adminLoginParam.getUsername();
//		String password = adminLoginParam.getPassword();

		TenantEmployee tenantEmployee = tenantEmployeeService.getByEmpName(username);
		AdminUser adminUser = AdminUser.builder()//
				.id(tenantEmployee.getId())//
				.username(tenantEmployee.getEmpName())//
				.password(tenantEmployee.getEmpPassword())//
				.status(tenantEmployee.getEmpStatus())//
				.icon(null)//
				.build();

		return adminUser;
	}
	// 根据用户ID，获取相关权限信息
	// 根据用户名，获取用户信息
}
