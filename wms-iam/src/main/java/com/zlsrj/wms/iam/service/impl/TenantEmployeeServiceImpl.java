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
				.empName(tenantInfo.getTenantLinkman())// 员工名称
				.empPassword(DigestUtil.md5Hex(RandomUtil.randomString(8)))// 登录密码
				.deptId(null)// 员工部门
				.loginOn(1)// 可登录系统（1可登录，0不能登录）
				.empStatus(1)// 员工状态（在职/离职/禁用）
				.empMobile(tenantInfo.getTenantMobile())// 员工手机号
				.empEmail(tenantInfo.getTenantEmail())// 员工邮箱
				.empPersonalWx(null)// 员工个人微信号
				.empEnterpriceWx(null)// 员工企业微信号
				.build();

		boolean success = retBool(baseMapper.insert(tenantEmployee));

		return success;
	}

	@Override
	public TenantEmployee getByEmpName(String empName) {
		QueryWrapper<TenantEmployee> queryWrapperTenantEmployee = new QueryWrapper<TenantEmployee>();
		queryWrapperTenantEmployee.lambda()//
				.eq(TenantEmployee::getEmpName, empName);
		TenantEmployee tenantEmployee = baseMapper.selectOne(queryWrapperTenantEmployee);

		return tenantEmployee;
	}
}
