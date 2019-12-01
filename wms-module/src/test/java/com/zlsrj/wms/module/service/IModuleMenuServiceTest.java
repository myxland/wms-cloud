package com.zlsrj.wms.module.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.ModuleMenu;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IModuleMenuServiceTest {

	@Autowired
	private IModuleMenuService moduleMenuService;

	@Test
	public void insertTest() {
		ModuleMenu moduleMenu = ModuleMenu.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.parentMenuId(RandomUtil.randomLong())// 父菜单编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.menuName(TestCaseUtil.name())// 菜单名称
				.menuOrder(RandomUtil.randomInt(0,1000+1))// 菜单排序
				.menuIcon(RandomUtil.randomString(4))// 菜单图标
				.basicOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				.advanceOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				.ultimateOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				.basicUrl(RandomUtil.randomString(4))// 基础版链接地址
				.advanceUrl(RandomUtil.randomString(4))// 高级版链接地址
				.ultimateUrl(RandomUtil.randomString(4))// 旗舰版链接地址
				.perms(RandomUtil.randomString(4))// 授权
				.menuType(RandomUtil.randomInt(0,1+1))// 菜单类型（0：目录；1：菜单；2：按钮）
				.build();

		log.info(ToStringBuilder.reflectionToString(moduleMenu, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = moduleMenuService.save(moduleMenu);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		ModuleMenu moduleMenu = ModuleMenu.builder()//
				.parentMenuId(RandomUtil.randomLong())// 父菜单编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.menuName(TestCaseUtil.name())// 菜单名称
				.menuOrder(RandomUtil.randomInt(0,1000+1))// 菜单排序
				.menuIcon(RandomUtil.randomString(4))// 菜单图标
				.basicOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				.advanceOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				.ultimateOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				.basicUrl(RandomUtil.randomString(4))// 基础版链接地址
				.advanceUrl(RandomUtil.randomString(4))// 高级版链接地址
				.ultimateUrl(RandomUtil.randomString(4))// 旗舰版链接地址
				.perms(RandomUtil.randomString(4))// 授权
				.menuType(RandomUtil.randomInt(0,1+1))// 菜单类型（0：目录；1：菜单；2：按钮）
				.build();
		moduleMenu.setId(id);

		log.info(ToStringBuilder.reflectionToString(moduleMenu, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = moduleMenuService.updateById(moduleMenu);

		log.info(Boolean.toString(success));
	}
}
