package com.zlsrj.wms.mbg.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.mbg.entity.TenantEmployee;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantEmployeeServiceTest {

	@Autowired
	private ITenantEmployeeService tenantEmployeeService;

	@Test
	public void insertTest() {
		TenantEmployee tenantEmployee = TenantEmployee.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.empName(TestCaseUtil.name())// 员工名称
				.empPassword(RandomUtil.randomString(4))// 登录密码
				.deptId(RandomUtil.randomLong())// 员工部门
				.loginOn(RandomUtil.randomInt(0,1+1))// 可登录系统（1可登录，0不能登录）
				.empStatus(RandomUtil.randomInt(0,1+1))// 员工状态（在职/离职/禁用）
				.empMobile(TestCaseUtil.mobile())// 员工手机号
				.empEmail(TestCaseUtil.email(null))// 员工邮箱
				.empPersonalWx(RandomUtil.randomString(4))// 员工个人微信号
				.empEnterpriceWx(RandomUtil.randomString(4))// 员工企业微信号
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantEmployee, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantEmployeeService.save(tenantEmployee);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantEmployee tenantEmployee = TenantEmployee.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.empName(TestCaseUtil.name())// 员工名称
				.empPassword(RandomUtil.randomString(4))// 登录密码
				.deptId(RandomUtil.randomLong())// 员工部门
				.loginOn(RandomUtil.randomInt(0,1+1))// 可登录系统（1可登录，0不能登录）
				.empStatus(RandomUtil.randomInt(0,1+1))// 员工状态（在职/离职/禁用）
				.empMobile(TestCaseUtil.mobile())// 员工手机号
				.empEmail(TestCaseUtil.email(null))// 员工邮箱
				.empPersonalWx(RandomUtil.randomString(4))// 员工个人微信号
				.empEnterpriceWx(RandomUtil.randomString(4))// 员工企业微信号
				.build();
		tenantEmployee.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantEmployee, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantEmployeeService.updateById(tenantEmployee);

		log.info(Boolean.toString(success));
	}
}
