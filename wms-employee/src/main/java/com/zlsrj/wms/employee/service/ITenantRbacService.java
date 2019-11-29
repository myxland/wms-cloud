package com.zlsrj.wms.employee.service;

import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantRbacService {
	/*
	 * 基于角色的权限访问控制（Role-Based Access Control） 创建部门 创建员工 创建角色 创建系统（模块） 创建菜单
	 * 创建角色与系统（模块）关系 创建角色与菜单关系 创建员工与角色关系
	 */
	boolean initByTenant(TenantInfo tenantInfo);
}
