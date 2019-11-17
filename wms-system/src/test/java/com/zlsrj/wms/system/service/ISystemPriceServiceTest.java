package com.zlsrj.wms.system.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.SystemPrice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ISystemPriceServiceTest {

	@Autowired
	private ISystemPriceService systemPriceService;

	@Test
	public void insertTest() {
		SystemPrice systemPrice = SystemPrice.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（1：基础版；2：高级版；3：旗舰版）
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.price(new BigDecimal(0))// 价格
				.build();

		log.info(ToStringBuilder.reflectionToString(systemPrice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = systemPriceService.save(systemPrice);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		SystemPrice systemPrice = SystemPrice.builder()//
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（1：基础版；2：高级版；3：旗舰版）
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.price(new BigDecimal(0))// 价格
				.build();
		systemPrice.setId(id);

		log.info(ToStringBuilder.reflectionToString(systemPrice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = systemPriceService.updateById(systemPrice);

		log.info(Boolean.toString(success));
	}
}
