package com.zlsrj.wms.saas.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantDepartment;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantDepartmentServiceTest {

	@Autowired
	private ITenantDepartmentService tenantDepartmentService;

	@Test
	public void insertTest() {
		TenantDepartment tenantDepartment = TenantDepartment.builder()//
				.id(TestCaseUtil.id())// 部门ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.departmentName(TestCaseUtil.name())// 部门名称
				.departmentParentId(RandomUtil.randomLong())// 上级部门ID
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantDepartment, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantDepartmentService.save(tenantDepartment);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantDepartment tenantDepartment = TenantDepartment.builder()//
				.tenantId(RandomUtil.randomLong())// 租户ID
				.departmentName(TestCaseUtil.name())// 部门名称
				.departmentParentId(RandomUtil.randomLong())// 上级部门ID
				.build();
		tenantDepartment.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantDepartment, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantDepartmentService.updateById(tenantDepartment);

		log.info(Boolean.toString(success));
	}
}
