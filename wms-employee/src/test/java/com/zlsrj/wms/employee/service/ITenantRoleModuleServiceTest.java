package com.zlsrj.wms.employee.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantRoleModule;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantRoleModuleServiceTest {

	@Autowired
	private ITenantRoleModuleService tenantRoleModuleService;

	@Test
	public void insertTest() {
		TenantRoleModule tenantRoleModule = TenantRoleModule.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.roleModuleOn(RandomUtil.randomInt(0,1+1))// 开放（1：开放；0：不开放）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantRoleModule, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantRoleModuleService.save(tenantRoleModule);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantRoleModule tenantRoleModule = TenantRoleModule.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.roleModuleOn(RandomUtil.randomInt(0,1+1))// 开放（1：开放；0：不开放）
				.build();
		tenantRoleModule.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantRoleModule, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantRoleModuleService.updateById(tenantRoleModule);

		log.info(Boolean.toString(success));
	}
}
