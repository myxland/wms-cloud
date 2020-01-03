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
				.build();

		log.info(ToStringBuilder.reflectionToString(moduleMenu, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = moduleMenuService.save(moduleMenu);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		ModuleMenu moduleMenu = ModuleMenu.builder()//
				.build();
		moduleMenu.setId(id);

		log.info(ToStringBuilder.reflectionToString(moduleMenu, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = moduleMenuService.updateById(moduleMenu);

		log.info(Boolean.toString(success));
	}
}
