package com.zlsrj.wms.admin.service;

import java.util.List;

import com.zlsrj.wms.admin.entity.Admin;
import com.zlsrj.wms.admin.entity.Permission;

public interface IAdminService {

	String login(String username, String password);

	Admin getAdminByUsername(String username);

	List<Permission> getPermissionList(Long adminId);

}
