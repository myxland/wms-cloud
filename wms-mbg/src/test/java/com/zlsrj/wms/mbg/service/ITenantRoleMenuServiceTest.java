package com.zlsrj.wms.mbg.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.mbg.entity.TenantRoleMenu;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantRoleMenuServiceTest {

	@Autowired
	private ITenantRoleMenuService tenantRoleMenuService;

	@Test
	public void insertTest() {
		TenantRoleMenu tenantRoleMenu = TenantRoleMenu.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.menuId(RandomUtil.randomLong())// 菜单编号
				.roleMenuOn(RandomUtil.randomInt(0,1+1))// 开放（1开放，0不开放）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantRoleMenu, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantRoleMenuService.save(tenantRoleMenu);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantRoleMenu tenantRoleMenu = TenantRoleMenu.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.menuId(RandomUtil.randomLong())// 菜单编号
				.roleMenuOn(RandomUtil.randomInt(0,1+1))// 开放（1开放，0不开放）
				.build();
		tenantRoleMenu.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantRoleMenu, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantRoleMenuService.updateById(tenantRoleMenu);

		log.info(Boolean.toString(success));
	}
}
