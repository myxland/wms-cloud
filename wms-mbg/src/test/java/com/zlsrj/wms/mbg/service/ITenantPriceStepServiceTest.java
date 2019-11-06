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
import com.zlsrj.wms.api.entity.TenantPriceStep;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantPriceStepServiceTest {

	@Autowired
	private ITenantPriceStepService tenantPriceStepService;

	@Test
	public void insertTest() {
		TenantPriceStep tenantPriceStep = TenantPriceStep.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别
				.priceItemId(RandomUtil.randomLong())// 费用项目
				.stepId(RandomUtil.randomLong())// 阶梯号
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.price(new BigDecimal(0))// 价格
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPriceStep, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceStepService.save(tenantPriceStep);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantPriceStep tenantPriceStep = TenantPriceStep.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别
				.priceItemId(RandomUtil.randomLong())// 费用项目
				.stepId(RandomUtil.randomLong())// 阶梯号
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.price(new BigDecimal(0))// 价格
				.build();
		tenantPriceStep.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPriceStep, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceStepService.updateById(tenantPriceStep);

		log.info(Boolean.toString(success));
	}
}
