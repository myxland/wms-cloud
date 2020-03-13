package com.zlsrj.wms.saas.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.dto.TenantRoleAddParam;
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
}
