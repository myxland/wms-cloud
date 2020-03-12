package com.zlsrj.wms.admin.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.admin.service.IAdminService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.AdminLoginParam;
import com.zlsrj.wms.api.dto.TenantInfoQueryParam;
import com.zlsrj.wms.api.entity.AdminUser;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "登录信息", tags = { "登录操作接口" }, hidden = true)
@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	private IAdminService adminService;

	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value="",hidden=true)
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

	@ApiOperation(value="",hidden=true)
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Map<String, Object>> getAdminInfo(Principal principal) {
		String username = principal.getName();
		AdminUser admin = adminService.getAdminByUsername(username);
		Map<String, Object> data = new HashMap<>();
		data.put("username", admin.getUsername());
		data.put("roles", new String[] { "TEST" });
		data.put("icon", null);
		data.put("tenantInfoList", getTenantInfoListByAdminUser(admin));
		//
		return CommonResult.success(data);
	}

	private List<TenantInfoVo> getTenantInfoListByAdminUser(AdminUser admin) {
		TenantInfoQueryParam tenantInfoQueryParam = new TenantInfoQueryParam();
		Page<TenantInfoVo> tenantInfoVoPage = tenantInfoClientService.page(tenantInfoQueryParam, 1, 500, "id", "asc");
		tenantInfoVoPage.getRecords().stream().forEach(v -> wrappperVo(v));

		CommonPage<TenantInfoVo> tenantInfoCommonPage = CommonPage.restPage(tenantInfoVoPage);

		return tenantInfoCommonPage.getList();
	}
	
	private void wrappperVo(TenantInfoVo tenantInfoVo) {
		
	}
}
