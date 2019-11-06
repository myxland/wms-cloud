package com.zlsrj.wms.admin.service;

import java.util.List;

import com.zlsrj.wms.api.entity.AdminPermission;
import com.zlsrj.wms.api.entity.AdminUser;

public interface IAdminService {

	String login(String username, String password);

	AdminUser getAdminByUsername(String username);

	List<AdminPermission> getPermissionList(Long adminId);

}
