package com.zlsrj.wms.saas.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.ModulePrice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IModulePriceServiceTest {

	@Autowired
	private IModulePriceService modulePriceService;

	@Test
	public void insertTest() {
		ModulePrice modulePrice = ModulePrice.builder()//
				.id(TestCaseUtil.id())// 模块价格ID
				.moduleId(RandomUtil.randomString(32))// 模块ID
				.moduleEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（1：基础版；2：高级版；3：旗舰版）
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.priceMoney(new BigDecimal(0))// 价格
				.build();

		log.info(ToStringBuilder.reflectionToString(modulePrice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = modulePriceService.save(modulePrice);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		ModulePrice modulePrice = ModulePrice.builder()//
				.moduleId(RandomUtil.randomString(32))// 模块ID
				.moduleEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（1：基础版；2：高级版；3：旗舰版）
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.priceMoney(new BigDecimal(0))// 价格
				.build();
		modulePrice.setId(id);

		log.info(ToStringBuilder.reflectionToString(modulePrice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = modulePriceService.updateById(modulePrice);

		log.info(Boolean.toString(success));
	}
}
