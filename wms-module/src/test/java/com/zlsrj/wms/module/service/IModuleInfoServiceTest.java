package com.zlsrj.wms.module.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.ModuleInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IModuleInfoServiceTest {

	@Autowired
	private IModuleInfoService moduleInfoService;

	@Test
	public void insertTest() {
		ModuleInfo moduleInfo = ModuleInfo.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.build();

		log.info(ToStringBuilder.reflectionToString(moduleInfo, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = moduleInfoService.save(moduleInfo);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		ModuleInfo moduleInfo = ModuleInfo.builder()//
				.build();
		moduleInfo.setId(id);

		log.info(ToStringBuilder.reflectionToString(moduleInfo, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = moduleInfoService.updateById(moduleInfo);

		log.info(Boolean.toString(success));
	}
}
