package com.zlsrj.wms.mbg.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.mbg.entity.SystemMenuDesign;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ISystemMenuDesignServiceTest {

	@Autowired
	private ISystemMenuDesignService systemMenuDesignService;

	@Test
	public void insertTest() {
		SystemMenuDesign systemMenuDesign = SystemMenuDesign.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.parentMenuId(RandomUtil.randomLong())// 父菜单编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.menuName(TestCaseUtil.name())// 菜单名称
				.menuOrder(RandomUtil.randomInt(0,1000+1))// 菜单排序
				.menuIcon(RandomUtil.randomString(4))// 菜单图标
				.basicOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1开放0不开放）
				.advanceOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1开放0不开放）
				.ultimateOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1开放0不开放）
				.basicUrl(RandomUtil.randomString(4))// 基础版链接地址
				.advanceUrl(RandomUtil.randomString(4))// 高级版链接地址
				.ultimateUrl(RandomUtil.randomString(4))// 旗舰版链接地址
				.build();

		log.info(ToStringBuilder.reflectionToString(systemMenuDesign, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = systemMenuDesignService.save(systemMenuDesign);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		SystemMenuDesign systemMenuDesign = SystemMenuDesign.builder()//
				.parentMenuId(RandomUtil.randomLong())// 父菜单编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.menuName(TestCaseUtil.name())// 菜单名称
				.menuOrder(RandomUtil.randomInt(0,1000+1))// 菜单排序
				.menuIcon(RandomUtil.randomString(4))// 菜单图标
				.basicOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1开放0不开放）
				.advanceOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1开放0不开放）
				.ultimateOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1开放0不开放）
				.basicUrl(RandomUtil.randomString(4))// 基础版链接地址
				.advanceUrl(RandomUtil.randomString(4))// 高级版链接地址
				.ultimateUrl(RandomUtil.randomString(4))// 旗舰版链接地址
				.build();
		systemMenuDesign.setId(id);

		log.info(ToStringBuilder.reflectionToString(systemMenuDesign, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = systemMenuDesignService.updateById(systemMenuDesign);

		log.info(Boolean.toString(success));
	}
}
