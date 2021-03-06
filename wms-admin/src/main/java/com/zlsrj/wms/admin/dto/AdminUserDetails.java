package com.zlsrj.wms.admin.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zlsrj.wms.api.entity.AdminPermission;
import com.zlsrj.wms.api.entity.AdminUser;

public class AdminUserDetails implements UserDetails {

	private static final long serialVersionUID = 7497995345934388331L;

	private AdminUser admin;
	private List<AdminPermission> permissionList;

	public AdminUserDetails(AdminUser admin, List<AdminPermission> permissionList) {
		this.admin = admin;
		this.permissionList = permissionList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 返回当前用户的权限
		return permissionList.stream().filter(permission -> permission.getValue() != null)
				.map(permission -> new SimpleGrantedAuthority(permission.getValue())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		return admin.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return 1 == admin.getStatus();
	}

}
