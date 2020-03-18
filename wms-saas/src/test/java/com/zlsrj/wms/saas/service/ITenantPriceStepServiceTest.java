package com.zlsrj.wms.saas.service;

import java.math.BigDecimal;
import java.util.Date;

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
				.id(TestCaseUtil.id())// 阶梯明细ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.priceDetailId(RandomUtil.randomString(4))// 水价明细ID
				.stepClass(RandomUtil.randomInt(0,1000+1))// 阶梯级次
				.startCode(new BigDecimal(0))// 阶梯起始量
				.endCode(new BigDecimal(0))// 阶梯终止量
				.stepPrice(new BigDecimal(0))// 单价
				.stepUsers(RandomUtil.randomInt(0,1000+1))// 标准用水人数
				.stepUsersAdd(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.TEN))// 超人数增补量
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPriceStep, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceStepService.save(tenantPriceStep);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantPriceStep tenantPriceStep = TenantPriceStep.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.priceDetailId(RandomUtil.randomString(4))// 水价明细ID
				.stepClass(RandomUtil.randomInt(0,1000+1))// 阶梯级次
				.startCode(new BigDecimal(0))// 阶梯起始量
				.endCode(new BigDecimal(0))// 阶梯终止量
				.stepPrice(new BigDecimal(0))// 单价
				.stepUsers(RandomUtil.randomInt(0,1000+1))// 标准用水人数
				.stepUsersAdd(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.TEN))// 超人数增补量
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantPriceStep.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPriceStep, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceStepService.updateById(tenantPriceStep);

		log.info(Boolean.toString(success));
	}
}
