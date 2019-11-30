package com.zlsrj.wms.employee.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.entity.TenantEmployeeRoleBatch;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantEmployeeRoleServiceTest {

	@Autowired
	private ITenantEmployeeRoleService tenantEmployeeRoleService;

	@Test
	public void insertTest() {
		TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.empId(RandomUtil.randomLong())// 员工编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.empRoleOn(RandomUtil.randomInt(0, 1 + 1))// 开放（1：开放；0：不开放）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantEmployeeRole, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantEmployeeRoleService.save(tenantEmployeeRole);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.empId(RandomUtil.randomLong())// 员工编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.empRoleOn(RandomUtil.randomInt(0, 1 + 1))// 开放（1：开放；0：不开放）
				.build();
		tenantEmployeeRole.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantEmployeeRole, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantEmployeeRoleService.updateById(tenantEmployeeRole);

		log.info(Boolean.toString(success));
	}

	@Test
	public void saveBatchTenantEmployeeRoleTest() {
		TenantEmployeeRoleBatch tenantEmployeeRoleBatch = TenantEmployeeRoleBatch.builder()//
				.tenantId(1200378781429272576L)//
				.empIds("1200378788890394624")//
				.roleIds("1200378788944920576,1200400585165750272,1200400605705256960")//
				.build();

		tenantEmployeeRoleService.saveBatchTenantEmployeeRole(tenantEmployeeRoleBatch);
	}
}
