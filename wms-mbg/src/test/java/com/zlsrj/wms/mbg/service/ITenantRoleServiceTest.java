package com.zlsrj.wms.mbg.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.mbg.entity.TenantRole;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantRoleServiceTest {

	@Autowired
	private ITenantRoleService tenantRoleService;

	@Test
	public void insertTest() {
		TenantRole tenantRole = TenantRole.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.roleName(TestCaseUtil.name())// 角色名称
				.roleRemark(RandomUtil.randomString(4))// 角色说明
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantRole, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantRoleService.save(tenantRole);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantRole tenantRole = TenantRole.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.roleName(TestCaseUtil.name())// 角色名称
				.roleRemark(RandomUtil.randomString(4))// 角色说明
				.build();
		tenantRole.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantRole, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantRoleService.updateById(tenantRole);

		log.info(Boolean.toString(success));
	}
}
