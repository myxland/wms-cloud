package com.zlsrj.wms.mbg.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantDept;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantDeptServiceTest {

	@Autowired
	private ITenantDeptService tenantDeptService;

	@Test
	public void insertTest() {
		TenantDept tenantDept = TenantDept.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.parentDeptId(RandomUtil.randomLong())// 上级部门
				.tenantId(RandomUtil.randomLong())// 租户编号
				.deptName(TestCaseUtil.name())// 部门名称
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantDept, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantDeptService.save(tenantDept);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantDept tenantDept = TenantDept.builder()//
				.parentDeptId(RandomUtil.randomLong())// 上级部门
				.tenantId(RandomUtil.randomLong())// 租户编号
				.deptName(TestCaseUtil.name())// 部门名称
				.build();
		tenantDept.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantDept, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantDeptService.updateById(tenantDept);

		log.info(Boolean.toString(success));
	}
}
