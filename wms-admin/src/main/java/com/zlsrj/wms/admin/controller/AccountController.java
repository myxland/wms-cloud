package com.zlsrj.wms.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.admin.component.JwtTokenUtil;
import com.zlsrj.wms.admin.service.IAdminService;
import com.zlsrj.wms.api.dto.AccountLoginParam;
import com.zlsrj.wms.api.entity.AdminUser;
import com.zlsrj.wms.api.vo.ModuleMenuDataVo;
import com.zlsrj.wms.api.vo.ModuleMenuVo;
import com.zlsrj.wms.api.vo.TenantEmployeeDataVo;
import com.zlsrj.wms.api.vo.TenantEmployeeVo;
import com.zlsrj.wms.api.vo.TenantInfoDataVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${jwt.tokenHeader}")
	private String tokenHeader;
	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	private IAdminService adminService;

	@ApiOperation(value = "系统登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public CommonResult<Object> login(@RequestBody AccountLoginParam accountLoginParam) {
		log.info("登录参数，adminLoginParam=[{}]", accountLoginParam.toString());

		String logintype = accountLoginParam.getLogintype();
		if (logintype == null || logintype.trim().length() == 0) {
			return CommonResult.validateFailed("登录类型参数错误");
		}

		switch (logintype) {
		case "1":
			return accountLogin(accountLoginParam);
		case "2":
			return scanCodeLogin(accountLoginParam);
		default:
			return CommonResult.validateFailed("系统暂不支持此登录方式");
		}
	}

	/**
	 * 账号登录
	 */
	private CommonResult<Object> accountLogin(AccountLoginParam accountLoginParam) {
		String token = adminService.login(accountLoginParam.getLoginname(), accountLoginParam.getLoginpassword());
		if (token == null) {
			return CommonResult.validateFailed("用户名或密码错误");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		return CommonResult.success(tokenMap);

	}

	/**
	 * 扫码登录
	 */
	private CommonResult<Object> scanCodeLogin(AccountLoginParam accountLoginParam) {
		return CommonResult.validateFailed("扫码登录暂未开通");
	}

	@ApiOperation(value = "用户信息")
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public CommonResult<TenantEmployeeDataVo> getAdminInfo(HttpServletRequest request) {
		String authHeader = request.getHeader(this.tokenHeader);
		if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
			String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
			String username = jwtTokenUtil.getUserNameFromToken(authToken);
			
			AdminUser admin = adminService.getAdminByUsername(username);
			
			TenantEmployeeVo tenantEmployeeVo= adminService.getTenantEmployeeById(admin.getId());
			String jsonString = JSON.toJSONString(tenantEmployeeVo);
			TenantEmployeeDataVo tenantEmployeeDataVo = JSON.parseObject(jsonString, TenantEmployeeDataVo.class);
			
			TenantInfoVo tenantInfoVo = adminService.getTenantInfoById(tenantEmployeeDataVo.getTenantId());
			jsonString = JSON.toJSONString(tenantInfoVo);
			TenantInfoDataVo tenantInfoDataVo = JSON.parseObject(jsonString, TenantInfoDataVo.class);
			
			tenantEmployeeDataVo.setTenantInfo(tenantInfoDataVo);
			
			List<ModuleMenuVo> moduleMenuVoList = adminService.getModuleMenuByEmployee(tenantEmployeeDataVo.getTenantId(), tenantEmployeeDataVo.getId());
			
			
			List<ModuleMenuDataVo> moduleMenuDataVoList = moduleMenuVoList.stream()//
			.map(e -> JSON.parseObject(JSON.toJSONString(e),ModuleMenuDataVo.class))//
			.collect(Collectors.toList());
			
			tenantEmployeeDataVo.setModuleMenuList(moduleMenuDataVoList);
			
			return CommonResult.success(tenantEmployeeDataVo);
		}
		return CommonResult.failed("非法token");
	}

}
