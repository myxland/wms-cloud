package com.zlsrj.wms.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.entity.TenantEmployeeRoleBatch;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.exception.ServiceException;

public interface ITenantEmployeeRoleService extends IService<TenantEmployeeRole> {
	/**
	 * 根据新建租户信息创建默认用户类型
	 * @param tenantInfo
	 * @return
	 */
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
	
	/**
	 * 批量新增员工与角色关系
	 * @param tenantEmployeeRoleBatch
	 * @return
	 */
	boolean saveBatchTenantEmployeeRole(TenantEmployeeRoleBatch tenantEmployeeRoleBatch);
	
	/**
	 * 批量新增员工与角色关系(根据用户ID)
	 */
	boolean saveBatchTenantEmployeeRoleByEmpId(Long empId,TenantEmployeeRoleBatch tenantEmployeeRoleBatch);
	
	/**
	 * 批量新增员工与角色关系(根据角色ID)
	 */
	boolean saveBatchTenantEmployeeRoleByRoleId(Long roleId,TenantEmployeeRoleBatch tenantEmployeeRoleBatch);
}
