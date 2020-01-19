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
import com.zlsrj.wms.api.entity.TenantPaymentDetail;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantPaymentDetailServiceTest {

	@Autowired
	private ITenantPaymentDetailService tenantPaymentDetailService;

	@Test
	public void insertTest() {
		TenantPaymentDetail tenantPaymentDetail = TenantPaymentDetail.builder()//
				.id(TestCaseUtil.id())// 实收明细账ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.paymentId(RandomUtil.randomLong())// 实收总账ID
				.receivableTime(new Date())// 对应的应收账时间
				.receivableId(RandomUtil.randomLong())// 应收总账ID
				.receivableDetailId(RandomUtil.randomLong())// 应收明细账ID
				.stepNo(RandomUtil.randomInt(0,1000+1))// 阶梯号
				.payWaters(new BigDecimal(0))// 收费水量
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.priceItemId(RandomUtil.randomLong())// 费用项目ID
				.payPrice(new BigDecimal(0))// 价格
				.payMoney(new BigDecimal(0))// 收费金额
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPaymentDetail, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPaymentDetailService.save(tenantPaymentDetail);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantPaymentDetail tenantPaymentDetail = TenantPaymentDetail.builder()//
				.tenantId(RandomUtil.randomLong())// 租户ID
				.paymentId(RandomUtil.randomLong())// 实收总账ID
				.receivableTime(new Date())// 对应的应收账时间
				.receivableId(RandomUtil.randomLong())// 应收总账ID
				.receivableDetailId(RandomUtil.randomLong())// 应收明细账ID
				.stepNo(RandomUtil.randomInt(0,1000+1))// 阶梯号
				.payWaters(new BigDecimal(0))// 收费水量
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.priceItemId(RandomUtil.randomLong())// 费用项目ID
				.payPrice(new BigDecimal(0))// 价格
				.payMoney(new BigDecimal(0))// 收费金额
				.build();
		tenantPaymentDetail.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPaymentDetail, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPaymentDetailService.updateById(tenantPaymentDetail);

		log.info(Boolean.toString(success));
	}
}
