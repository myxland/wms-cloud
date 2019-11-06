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
import com.zlsrj.wms.api.entity.TenantPriceDetail;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantPriceDetailServiceTest {

	@Autowired
	private ITenantPriceDetailService tenantPriceDetailService;

	@Test
	public void insertTest() {
		TenantPriceDetail tenantPriceDetail = TenantPriceDetail.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别
				.priceItemId(RandomUtil.randomLong())// 费用项目
				.calcType(RandomUtil.randomInt(0,1+1))// 计算方法（固定单价/固定金额/阶梯价格）
				.price(new BigDecimal(0))// 指定价格（金额）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPriceDetail, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceDetailService.save(tenantPriceDetail);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantPriceDetail tenantPriceDetail = TenantPriceDetail.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别
				.priceItemId(RandomUtil.randomLong())// 费用项目
				.calcType(RandomUtil.randomInt(0,1+1))// 计算方法（固定单价/固定金额/阶梯价格）
				.price(new BigDecimal(0))// 指定价格（金额）
				.build();
		tenantPriceDetail.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPriceDetail, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceDetailService.updateById(tenantPriceDetail);

		log.info(Boolean.toString(success));
	}
}
