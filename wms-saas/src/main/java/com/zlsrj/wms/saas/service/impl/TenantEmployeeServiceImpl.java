package com.zlsrj.wms.saas.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.dto.TenantEmployeeUpdateParam;
import com.zlsrj.wms.api.dto.TenantRoleAddParam;
import com.zlsrj.wms.api.dto.TenantRoleUpdateParam;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.saas.mapper.TenantEmployeeMapper;
import com.zlsrj.wms.saas.mapper.TenantEmployeeRoleMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantEmployeeService;
import com.zlsrj.wms.saas.strategy.password.PasswordContext;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantEmployeeServiceImpl extends ServiceImpl<TenantEmployeeMapper, TenantEmployee>
		implements ITenantEmployeeService {
	@Resource
	private IIdService idService;

	@Autowired
	private PasswordContext passwordContext;

	@Value("${system.config.password.mode}")
	private String passwordMode;

	@Resource
	private TenantEmployeeRoleMapper tenantEmployeeRoleMapper;

	@Override
	@Transactional
	public String save(TenantEmployeeAddParam tenantEmployeeAddParam) {
		String jsonString = JSON.toJSONString(tenantEmployeeAddParam);
		TenantEmployee tenantEmployee = JSON.parseObject(jsonString, TenantEmployee.class);
		if (tenantEmployee.getId() == null || tenantEmployee.getId().trim().length() == 0) {
			tenantEmployee.setId(idService.selectId());
		}
		String password = passwordContext.getInstance(passwordMode).getPassword(tenantEmployee);
		tenantEmployee.setEmployeePassword(password);
		this.save(tenantEmployee);

		QueryWrapper<TenantEmployeeRole> wrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		wrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantEmployee.getTenantId())
				.eq(TenantEmployeeRole::getEmployeeId, tenantEmployee.getId());
		tenantEmployeeRoleMapper.delete(wrapperTenantEmployeeRole);

		List<TenantRoleAddParam> tenantRoleAddParamList = tenantEmployeeAddParam.getTenantRoleList();
		if (tenantRoleAddParamList != null && tenantRoleAddParamList.size() > 0) {
			for (TenantRoleAddParam tenantRoleAddParam : tenantRoleAddParamList) {
				TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
						.id(idService.selectId())//
						.tenantId(tenantEmployee.getTenantId())//
						.employeeId(tenantEmployee.getId())//
						.roleId(tenantRoleAddParam.getId())//
						.build();
				tenantEmployeeRoleMapper.insert(tenantEmployeeRole);
			}
		}else {
			log.info("新增员工未配角角色信息");
		}

		return tenantEmployee.getId();
	}
	
	@Override
	@Transactional
    public boolean removeById(Serializable id) {
		QueryWrapper<TenantEmployeeRole> wrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		wrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getEmployeeId, id);
		tenantEmployeeRoleMapper.delete(wrapperTenantEmployeeRole);
		
        return super.removeById(id);
    }
	
	@Override
	@Transactional
    public boolean updateById(String id,TenantEmployeeUpdateParam tenantEmployeeUpdateParam) {
		boolean success = false;
		String jsonString = JSON.toJSONString(tenantEmployeeUpdateParam);
		TenantEmployee tenantEmployee = JSON.parseObject(jsonString, TenantEmployee.class);
		tenantEmployee.setId(id);
		super.updateById(tenantEmployee);
		
		QueryWrapper<TenantEmployeeRole> wrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		wrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantEmployee.getTenantId())
				.eq(TenantEmployeeRole::getEmployeeId, tenantEmployee.getId());
		tenantEmployeeRoleMapper.delete(wrapperTenantEmployeeRole);

		List<TenantRoleUpdateParam> tenantRoleUpdateParamList = tenantEmployeeUpdateParam.getTenantRoleList();
		if (tenantRoleUpdateParamList != null && tenantRoleUpdateParamList.size() > 0) {
			for (TenantRoleUpdateParam tenantRoleUpdateParam : tenantRoleUpdateParamList) {
				TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
						.id(idService.selectId())//
						.tenantId(tenantEmployee.getTenantId())//
						.employeeId(tenantEmployee.getId())//
						.roleId(tenantRoleUpdateParam.getId())//
						.build();
				tenantEmployeeRoleMapper.insert(tenantEmployeeRole);
			}
		}else {
			log.info("更新员工未配角角色信息");
		}
		
		success = true;
		
		return success;
    }
	
	@Override
	@Transactional
	public boolean update(String id,TenantEmployeeUpdateParam tenantEmployeeUpdateParam) {
		boolean success = false;
		String jsonString = JSON.toJSONString(tenantEmployeeUpdateParam);
		TenantEmployee tenantEmployee = JSON.parseObject(jsonString, TenantEmployee.class);
		tenantEmployee.setId(id);
		
		TenantEmployee tenantEmployeeWhere = TenantEmployee.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantEmployee> updateWrapperTenantEmployee = new UpdateWrapper<TenantEmployee>();
		updateWrapperTenantEmployee.setEntity(tenantEmployeeWhere);
		updateWrapperTenantEmployee.lambda()//
				//.eq(TenantEmployee::getId, id)
				// .set(tenantEmployee.getId() != null, TenantEmployee::getId, tenantEmployee.getId())
				.set(tenantEmployee.getTenantId() != null, TenantEmployee::getTenantId, tenantEmployee.getTenantId())
				.set(tenantEmployee.getEmployeeName() != null, TenantEmployee::getEmployeeName, tenantEmployee.getEmployeeName())
				.set(tenantEmployee.getEmployeePassword() != null, TenantEmployee::getEmployeePassword, tenantEmployee.getEmployeePassword())
				.set(tenantEmployee.getEmployeeDepartmentId() != null, TenantEmployee::getEmployeeDepartmentId, tenantEmployee.getEmployeeDepartmentId())
				.set(tenantEmployee.getEmployeeLoginOn() != null, TenantEmployee::getEmployeeLoginOn, tenantEmployee.getEmployeeLoginOn())
				.set(tenantEmployee.getEmployeeStatus() != null, TenantEmployee::getEmployeeStatus, tenantEmployee.getEmployeeStatus())
				.set(tenantEmployee.getEmployeeMobile() != null, TenantEmployee::getEmployeeMobile, tenantEmployee.getEmployeeMobile())
				.set(tenantEmployee.getEmployeeEmail() != null, TenantEmployee::getEmployeeEmail, tenantEmployee.getEmployeeEmail())
				.set(tenantEmployee.getEmployeePersonalWx() != null, TenantEmployee::getEmployeePersonalWx, tenantEmployee.getEmployeePersonalWx())
				.set(tenantEmployee.getEmployeeEnterpriceWx() != null, TenantEmployee::getEmployeeEnterpriceWx, tenantEmployee.getEmployeeEnterpriceWx())
				.set(tenantEmployee.getEmployeeDingding() != null, TenantEmployee::getEmployeeDingding, tenantEmployee.getEmployeeDingding())
				.set(tenantEmployee.getEmployeeCreateType() != null, TenantEmployee::getEmployeeCreateType, tenantEmployee.getEmployeeCreateType())
				;

		super.update(updateWrapperTenantEmployee);
		
		QueryWrapper<TenantEmployeeRole> wrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		wrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantEmployee.getTenantId())
				.eq(TenantEmployeeRole::getEmployeeId, tenantEmployee.getId());
		tenantEmployeeRoleMapper.delete(wrapperTenantEmployeeRole);

		List<TenantRoleUpdateParam> tenantRoleUpdateParamList = tenantEmployeeUpdateParam.getTenantRoleList();
		if (tenantRoleUpdateParamList != null && tenantRoleUpdateParamList.size() > 0) {
			for (TenantRoleUpdateParam tenantRoleUpdateParam : tenantRoleUpdateParamList) {
				TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
						.id(idService.selectId())//
						.tenantId(tenantEmployee.getTenantId())//
						.employeeId(tenantEmployee.getId())//
						.roleId(tenantRoleUpdateParam.getId())//
						.build();
				tenantEmployeeRoleMapper.insert(tenantEmployeeRole);
			}
		}else {
			log.info("更新员工未配角角色信息");
		}
		
		success = true;
		
		return success;
	}
}
