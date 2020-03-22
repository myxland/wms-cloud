package com.zlsrj.wms.saas.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.saas.mapper.TenantEmployeeMapper;
import com.zlsrj.wms.saas.mapper.TenantEmployeeRoleMapper;
import com.zlsrj.wms.saas.mapper.TenantRoleMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantEmployeeRoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantEmployeeRoleServiceImpl extends ServiceImpl<TenantEmployeeRoleMapper, TenantEmployeeRole> implements ITenantEmployeeRoleService {
	@Resource
	private IIdService idService;
	
	@Resource
	private TenantEmployeeMapper tenantEmployeeMapper;
	
	@Resource
	private TenantRoleMapper tenantRoleMapper;
	
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
		TenantEmployee tenantEmployee = tenantEmployeeMapper.selectOne(queryWrapperTenantEmployee);
		
		QueryWrapper<TenantRole> queryWrapperTenantRole = new QueryWrapper<TenantRole>();
		queryWrapperTenantRole.lambda()//
				.eq(TenantRole::getTenantId, tenantInfo.getId())//
				.eq(TenantRole::getRoleName, "系统管理员")//
				.eq(TenantRole::getCreateType,1)//
		;
		TenantRole tenantRole = tenantRoleMapper.selectOne(queryWrapperTenantRole);
		
		TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
				.id(idService.selectId())//
				.tenantId(tenantInfo.getId())//
				.employeeId(tenantEmployee.getId())
				.roleId(tenantRole.getId())//
				.build();
		
		boolean success = this.save(tenantEmployeeRole);

		return success;
	}
}
