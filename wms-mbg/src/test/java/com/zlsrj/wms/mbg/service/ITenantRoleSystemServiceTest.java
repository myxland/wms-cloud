package com.zlsrj.wms.mbg.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantRoleSystem;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantRoleSystemServiceTest {

	@Autowired
	private ITenantRoleSystemService tenantRoleSystemService;

	@Test
	public void insertTest() {
		TenantRoleSystem tenantRoleSystem = TenantRoleSystem.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.roleSysOn(RandomUtil.randomInt(0,1+1))// 开放（1开放，0不开放）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantRoleSystem, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantRoleSystemService.save(tenantRoleSystem);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantRoleSystem tenantRoleSystem = TenantRoleSystem.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.roleSysOn(RandomUtil.randomInt(0,1+1))// 开放（1开放，0不开放）
				.build();
		tenantRoleSystem.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantRoleSystem, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantRoleSystemService.updateById(tenantRoleSystem);

		log.info(Boolean.toString(success));
	}
}
