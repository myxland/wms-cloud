package com.zlsrj.wms.mbg.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantSystemPrice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantSystemPriceServiceTest {

	@Autowired
	private ITenantSystemPriceService tenantSystemPriceService;

	@Test
	public void insertTest() {
		TenantSystemPrice tenantSystemPrice = TenantSystemPrice.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（0：基础版；1：高级版；2：旗舰版）
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.price(new BigDecimal(0))// 价格
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantSystemPrice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantSystemPriceService.save(tenantSystemPrice);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantSystemPrice tenantSystemPrice = TenantSystemPrice.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（0：基础版；1：高级版；2：旗舰版）
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.price(new BigDecimal(0))// 价格
				.build();
		tenantSystemPrice.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantSystemPrice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantSystemPriceService.updateById(tenantSystemPrice);

		log.info(Boolean.toString(success));
	}
}
