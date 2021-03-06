package com.zlsrj.wms.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zlsrj.wms.admin.component.JwtTokenUtil;
import com.zlsrj.wms.admin.service.IAdminService;
import com.zlsrj.wms.api.client.service.AdminUserClientService;
import com.zlsrj.wms.api.dto.AdminLoginParam;
import com.zlsrj.wms.api.entity.AdminPermission;
import com.zlsrj.wms.api.entity.AdminUser;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private  AdminUserClientService adminUserClientService;

	public AdminUser getAdminByUsername(String username) {
//		AdminUser admin = AdminUser.builder()//
//				.id(TestCaseUtil.id())//
//				.username(username)//
//				.password(SecureUtil.md5("123456"))//
//				.status(1)//
//				.build();
		AdminLoginParam adminLoginParam = new AdminLoginParam();
		adminLoginParam.setUsername(username);
		AdminUser admin = adminUserClientService.login(adminLoginParam);

		return admin;
	}

	public List<AdminPermission> getPermissionList(Long adminId) {
		List<AdminPermission> permissionList = new ArrayList<AdminPermission>();

		AdminPermission permission = AdminPermission.builder()//
				.id(TestCaseUtil.id())//
				.name("控制中心")//
				.status(1)//
				.uri("http://www.baidu.com")//
				.value("admin:user:read")//
				.build();

		permissionList.add(permission);

		permission = AdminPermission.builder()//
				.id(TestCaseUtil.id())//
				.name("用户管理")//
				.status(1)//
				.uri("http://www.360.com")//
				.value("admin:user:list")//
				.build();

		permissionList.add(permission);

		permission = AdminPermission.builder()//
				.id(TestCaseUtil.id())//
				.name("角色管理")//
				.status(1)//
				.uri("http://www.so.com")//
				.value("admin:role:list")//
				.build();

		permissionList.add(permission);

		return permissionList;
	}

	public String login(String username, String password) {
		String token = null;
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				throw new BadCredentialsException("密码不正确");
			}
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			token = jwtTokenUtil.generateToken(userDetails);
		} catch (AuthenticationException e) {
			log.error("登录异常:{}", e.getMessage());
		}
		return token;
	}

}
