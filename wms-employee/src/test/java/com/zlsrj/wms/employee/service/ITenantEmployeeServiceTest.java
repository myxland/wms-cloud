package com.zlsrj.wms.employee.service;


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
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
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
				.build();
		tenantEmployee.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantEmployee, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantEmployeeService.updateById(tenantEmployee);

		log.info(Boolean.toString(success));
	}
}
