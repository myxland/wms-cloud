package com.zlsrj.wms.api.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zlsrj.wms.api.dto.AdminLoginParam;
import com.zlsrj.wms.api.entity.AdminUser;

@FeignClient(value = "WMS-IAM", contextId = "AdminUser")
public interface AdminUserClientService {
	// 根据用户名，密码获取登录结果
	@RequestMapping(value = "/adminUser/login", method = RequestMethod.POST)
	public AdminUser login(@RequestBody AdminLoginParam adminLoginParam);
}
