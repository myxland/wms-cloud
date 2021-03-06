package com.zlsrj.wms.iam.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.iam.mapper.TenantEmployeeMapper;
import com.zlsrj.wms.iam.service.ITenantEmployeeService;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;

@Service
public class TenantEmployeeServiceImpl extends ServiceImpl<TenantEmployeeMapper, TenantEmployee>
		implements ITenantEmployeeService {
	/**
	 * 初始化租户员工
	 * 
	 * @param tenantId
	 * @return
	 */
	public boolean initByTenant(TenantInfo tenantInfo) {
		TenantEmployee tenantEmployee = TenantEmployee.builder()//
				.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
				.tenantId(tenantInfo.getId())// 租户编号
				.build();

		boolean success = retBool(baseMapper.insert(tenantEmployee));

		return success;
	}

	@Override
	public TenantEmployee getByEmpName(String empName) {
		QueryWrapper<TenantEmployee> queryWrapperTenantEmployee = new QueryWrapper<TenantEmployee>();
		queryWrapperTenantEmployee.lambda()//
				.eq(TenantEmployee::getEmployeeName, empName);
		TenantEmployee tenantEmployee = baseMapper.selectOne(queryWrapperTenantEmployee);

		return tenantEmployee;
	}
}
