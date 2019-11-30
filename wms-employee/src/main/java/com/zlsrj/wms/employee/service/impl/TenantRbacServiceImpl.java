package com.zlsrj.wms.employee.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.employee.service.ITenantDeptService;
import com.zlsrj.wms.employee.service.ITenantEmployeeRoleService;
import com.zlsrj.wms.employee.service.ITenantEmployeeService;
import com.zlsrj.wms.employee.service.ITenantRbacService;
import com.zlsrj.wms.employee.service.ITenantRoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantRbacServiceImpl implements ITenantRbacService {
	@Resource
	private ITenantDeptService tenantDeptService;
	@Resource
	private ITenantEmployeeService tenantEmployeeService;
	@Resource
	private ITenantRoleService tenantRoleService;
	@Resource
	private ITenantEmployeeRoleService tenantEmployeeRoleService;

	/*
	 * 基于角色的权限访问控制（Role-Based Access Control） 创建部门 创建员工 创建角色 创建系统（模块） 创建菜单
	 * 创建角色与系统（模块）关系 创建角色与菜单关系 创建员工与角色关系
	 */
	public boolean initByTenant(TenantInfo tenantInfo) {

		boolean success = tenantDeptService.saveBatchByTenantInfo(tenantInfo);

		success = tenantEmployeeService.saveBatchByTenantInfo(tenantInfo);

		success = tenantRoleService.saveBatchByTenantInfo(tenantInfo);

		success = tenantEmployeeRoleService.saveBatchByTenantInfo(tenantInfo);

		return success;
	}

}
