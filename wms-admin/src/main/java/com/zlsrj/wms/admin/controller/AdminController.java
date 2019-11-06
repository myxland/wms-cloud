package com.zlsrj.wms.admin.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.admin.service.IAdminService;
import com.zlsrj.wms.api.dto.AdminLoginParam;
import com.zlsrj.wms.api.entity.AdminUser;
import com.zlsrj.wms.common.api.CommonResult;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	private IAdminService adminService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> login(@RequestBody AdminLoginParam adminLoginParam) {
		log.info("登录参数，adminLoginParam=[{}]", adminLoginParam.toString());
		String token = adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
		if (token == null) {
			return CommonResult.validateFailed("用户名或密码错误");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		return CommonResult.success(tokenMap);
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Map<String, Object>> getAdminInfo(Principal principal) {
		String username = principal.getName();
		AdminUser admin = adminService.getAdminByUsername(username);
		Map<String, Object> data = new HashMap<>();
		data.put("username", admin.getUsername());
		data.put("roles", new String[] { "TEST" });
		data.put("icon", null);
		return CommonResult.success(data);
	}
}
