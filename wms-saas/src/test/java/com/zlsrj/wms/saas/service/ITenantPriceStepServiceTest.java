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
				.id(TestCaseUtil.id())// 价格阶梯ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.priceTypeId(RandomUtil.randomString(32))// 价格类别ID
				.priceItemId(RandomUtil.randomString(32))// 费用项目ID
				.stepNo(RandomUtil.randomInt(0,1000+1))// 阶梯号
				.startWaters(new BigDecimal(0))// 起始量
				.endWaters(new BigDecimal(0))// 终止量
				.stepPrice(new BigDecimal(0))// 价格
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPriceStep, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceStepService.save(tenantPriceStep);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantPriceStep tenantPriceStep = TenantPriceStep.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.priceTypeId(RandomUtil.randomString(32))// 价格类别ID
				.priceItemId(RandomUtil.randomString(32))// 费用项目ID
				.stepNo(RandomUtil.randomInt(0,1000+1))// 阶梯号
				.startWaters(new BigDecimal(0))// 起始量
				.endWaters(new BigDecimal(0))// 终止量
				.stepPrice(new BigDecimal(0))// 价格
				.build();
		tenantPriceStep.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPriceStep, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceStepService.updateById(tenantPriceStep);

		log.info(Boolean.toString(success));
	}
}
