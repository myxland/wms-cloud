package com.zlsrj.wms.saas.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantEmployee;

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
				.id(TestCaseUtil.id())// 员工ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.employeeName(TestCaseUtil.name())// 员工名称
				.employeePassword(RandomUtil.randomString(4))// 登录密码
				.employeeDepartmentId(RandomUtil.randomString(32))// 员工所属部门ID
				.employeeLoginOn(RandomUtil.randomInt(0,1+1))// 可登录系统（1：可登录；0：不能登录）
				.employeeStatus(RandomUtil.randomInt(0,1+1))// 员工状态（1：在职；2：离职；3：禁用）
				.employeeMobile(TestCaseUtil.mobile())// 员工手机号
				.employeeEmail(TestCaseUtil.email(null))// 员工邮箱
				.employeePersonalWx(RandomUtil.randomString(4))// 员工个人微信号
				.employeeEnterpriceWx(RandomUtil.randomString(4))// 员工企业微信号
				.employeeDingding(RandomUtil.randomString(4))// 钉钉号
				.employeeCreateType(RandomUtil.randomInt(0,1+1))// 操作员创建类型（1：平台默认创建；2：租户自建）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantEmployee, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantEmployeeService.save(tenantEmployee);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantEmployee tenantEmployee = TenantEmployee.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.employeeName(TestCaseUtil.name())// 员工名称
				.employeePassword(RandomUtil.randomString(4))// 登录密码
				.employeeDepartmentId(RandomUtil.randomString(32))// 员工所属部门ID
				.employeeLoginOn(RandomUtil.randomInt(0,1+1))// 可登录系统（1：可登录；0：不能登录）
				.employeeStatus(RandomUtil.randomInt(0,1+1))// 员工状态（1：在职；2：离职；3：禁用）
				.employeeMobile(TestCaseUtil.mobile())// 员工手机号
				.employeeEmail(TestCaseUtil.email(null))// 员工邮箱
				.employeePersonalWx(RandomUtil.randomString(4))// 员工个人微信号
				.employeeEnterpriceWx(RandomUtil.randomString(4))// 员工企业微信号
				.employeeDingding(RandomUtil.randomString(4))// 钉钉号
				.employeeCreateType(RandomUtil.randomInt(0,1+1))// 操作员创建类型（1：平台默认创建；2：租户自建）
				.build();
		tenantEmployee.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantEmployee, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantEmployeeService.updateById(tenantEmployee);

		log.info(Boolean.toString(success));
	}
}
