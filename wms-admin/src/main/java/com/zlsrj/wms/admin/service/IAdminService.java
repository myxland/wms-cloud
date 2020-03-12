package com.zlsrj.wms.admin.service;

import java.util.List;

import com.zlsrj.wms.api.entity.AdminPermission;
import com.zlsrj.wms.api.entity.AdminUser;
import com.zlsrj.wms.api.vo.ModuleMenuVo;
import com.zlsrj.wms.api.vo.TenantEmployeeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;

public interface IAdminService {

	String login(String username, String password);

	AdminUser getAdminByUsername(String username);

	List<AdminPermission> getPermissionList(String adminId);

	TenantEmployeeVo getTenantEmployeeById(String employeeId);
	
	TenantInfoVo getTenantInfoById(String tenantId);
	
	List<ModuleMenuVo> getModuleMenuByEmployee(String tenantId,String employeeId);
}
