package com.zlsrj.wms.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.entity.TenantEmployeeRoleBatch;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.api.enums.EmployeeRoleEnum;
import com.zlsrj.wms.common.exception.ServiceException;
import com.zlsrj.wms.employee.mapper.TenantEmployeeRoleMapper;
import com.zlsrj.wms.employee.service.IIdService;
import com.zlsrj.wms.employee.service.ITenantEmployeeRoleService;
import com.zlsrj.wms.employee.service.ITenantEmployeeService;
import com.zlsrj.wms.employee.service.ITenantRoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantEmployeeRoleServiceImpl extends ServiceImpl<TenantEmployeeRoleMapper, TenantEmployeeRole>
		implements ITenantEmployeeRoleService {
	@Resource
	private IIdService idService;

	@Resource
	private ITenantEmployeeService tenantEmployeeService;

	@Resource
	private ITenantRoleService tenantRoleService;

	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantEmployeeRole> queryWrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		queryWrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantEmployeeRole);
		if (count > 0) {
			log.error("根据租户信息初始化员工角色失败，员工角色已存在。");
			return false;
		}

		QueryWrapper<TenantEmployee> queryWrapperTenantEmployee = new QueryWrapper<TenantEmployee>();
		queryWrapperTenantEmployee.lambda()//
				.eq(TenantEmployee::getTenantId, tenantInfo.getId())//
		;
		TenantEmployee tenantEmployee = tenantEmployeeService.getOne(queryWrapperTenantEmployee);

		QueryWrapper<TenantRole> queryWrapperTenantRole = new QueryWrapper<TenantRole>();
		queryWrapperTenantRole.lambda()//
				.eq(TenantRole::getTenantId, tenantInfo.getId())//
		;
		TenantRole tenantRole = tenantRoleService.getOne(queryWrapperTenantRole);

		List<TenantEmployeeRole> tenantEmployeeRoleList = new ArrayList<TenantEmployeeRole>();
		for (EmployeeRoleEnum employeeRoleEnum : EmployeeRoleEnum.values()) {
			TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
					.id(idService.selectId())// 系统ID
					.tenantId(tenantInfo.getId())// 租户编号
					.empId(tenantEmployee.getId())// 员工编号
					.roleId(tenantRole.getId())// 角色编号
					.empRoleOn(1)// 开放（1：开放；0：不开放）
					.build();
			log.info(tenantEmployeeRole.toString());
			tenantEmployeeRoleList.add(tenantEmployeeRole);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info(tenantEmployeeRoleList.toString());
		return super.saveBatch(tenantEmployeeRoleList);
	}

	@Transactional
	public boolean saveBatchTenantEmployeeRole(TenantEmployeeRoleBatch tenantEmployeeRoleBatch) {
		Long tenantId = tenantEmployeeRoleBatch.getTenantId();
		if (tenantId == null || tenantId.compareTo(0L) < 0) {
			throw new ServiceException("参数租户ID不合法");
		}

		// 删除全部员工与角色关联关系
		QueryWrapper<TenantEmployeeRole> queryWrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		queryWrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantId)//
		;
		boolean success = super.remove(queryWrapperTenantEmployeeRole);
		// 批量新建员工与角色关系
		List<TenantEmployeeRole> tenantEmployeeRoleList = new ArrayList<TenantEmployeeRole>();
		String empIds = tenantEmployeeRoleBatch.getEmpIds();
		String roleIds = tenantEmployeeRoleBatch.getRoleIds();
		if(StringUtils.isNoneEmpty(empIds)) {
			String[] empIdArray = empIds.split(",");
			for(int m=0;m<empIdArray.length;m++) {
				Long empId = Long.parseLong(empIdArray[m]);
				if(StringUtils.isNoneEmpty(roleIds)) {
					String[] roleIdArray = roleIds.split(",");
					for(int n=0;n<roleIdArray.length;n++) {
						Long roleId = Long.parseLong(roleIdArray[n]);
						
						TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
								.id(idService.selectId())// 系统ID
								.tenantId(tenantId)// 租户编号
								.empId(empId)// 员工编号
								.roleId(roleId)// 角色编号
								.empRoleOn(1)// 开放（1：开放；0：不开放）
								.build();
						tenantEmployeeRoleList.add(tenantEmployeeRole);
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		success = super.saveBatch(tenantEmployeeRoleList);
		success = true;
		
		return success;
	}
	
	@Transactional
	public boolean saveBatchTenantEmployeeRoleByEmpId(Long empId,TenantEmployeeRoleBatch tenantEmployeeRoleBatch) {
		Long tenantId = tenantEmployeeRoleBatch.getTenantId();
		if (tenantId == null || tenantId.compareTo(0L) < 0) {
			throw new ServiceException("参数租户ID不合法");
		}

		// 删除全部员工与角色关联关系
		QueryWrapper<TenantEmployeeRole> queryWrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		queryWrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantId)//
				.eq(TenantEmployeeRole::getEmpId, empId)//
		;
		boolean success = super.remove(queryWrapperTenantEmployeeRole);
		// 批量新建员工与角色关系
		List<TenantEmployeeRole> tenantEmployeeRoleList = new ArrayList<TenantEmployeeRole>();
		String roleIds = tenantEmployeeRoleBatch.getRoleIds();
		if(StringUtils.isNoneEmpty(roleIds)) {
			String[] roleIdArray = roleIds.split(",");
			for(int n=0;n<roleIdArray.length;n++) {
				Long roleId = Long.parseLong(roleIdArray[n]);
				
				TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
						.id(idService.selectId())// 系统ID
						.tenantId(tenantId)// 租户编号
						.empId(empId)// 员工编号
						.roleId(roleId)// 角色编号
						.empRoleOn(1)// 开放（1：开放；0：不开放）
						.build();
				tenantEmployeeRoleList.add(tenantEmployeeRole);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		success = super.saveBatch(tenantEmployeeRoleList);
		success = true;
		
		return success;
	}
	
	@Transactional
	public boolean saveBatchTenantEmployeeRoleByRoleId(Long roleId,TenantEmployeeRoleBatch tenantEmployeeRoleBatch) {
		Long tenantId = tenantEmployeeRoleBatch.getTenantId();
		if (tenantId == null || tenantId.compareTo(0L) < 0) {
			throw new ServiceException("参数租户ID不合法");
		}

		// 删除全部员工与角色关联关系
		QueryWrapper<TenantEmployeeRole> queryWrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		queryWrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantId)//
				.eq(TenantEmployeeRole::getRoleId,roleId)//
		;
		boolean success = super.remove(queryWrapperTenantEmployeeRole);
		// 批量新建员工与角色关系
		List<TenantEmployeeRole> tenantEmployeeRoleList = new ArrayList<TenantEmployeeRole>();
		String empIds = tenantEmployeeRoleBatch.getEmpIds();
		if(StringUtils.isNoneEmpty(empIds)) {
			String[] empIdArray = empIds.split(",");
			for(int m=0;m<empIdArray.length;m++) {
				Long empId = Long.parseLong(empIdArray[m]);
				
				TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
						.id(idService.selectId())// 系统ID
						.tenantId(tenantId)// 租户编号
						.empId(empId)// 员工编号
						.roleId(roleId)// 角色编号
						.empRoleOn(1)// 开放（1：开放；0：不开放）
						.build();
				tenantEmployeeRoleList.add(tenantEmployeeRole);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		success = super.saveBatch(tenantEmployeeRoleList);
		success = true;
		
		return success;
	}

}
