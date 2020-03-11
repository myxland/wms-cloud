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
import com.zlsrj.wms.api.entity.TenantConsumptionBill;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantConsumptionBillServiceTest {

	@Autowired
	private ITenantConsumptionBillService tenantConsumptionBillService;

	@Test
	public void insertTest() {
		TenantConsumptionBill tenantConsumptionBill = TenantConsumptionBill.builder()//
				.id(TestCaseUtil.id())// 租户账单ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.consumptionBillType(RandomUtil.randomInt(0,1+1))// 账单类型（1：充值；2：消费）
				.consumptionBillTime(new Date())// 账单时间
				.consumptionBillName(TestCaseUtil.name())// 账单名称[账户充值/短信平台/...]
				.consumptionBillMoney(new BigDecimal(0))// 账单金额
				.tenantBalance(new BigDecimal(0))// 租户账户余额
				.consumptionBillRemark(RandomUtil.randomString(4))// 备注
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantConsumptionBill, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantConsumptionBillService.save(tenantConsumptionBill);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantConsumptionBill tenantConsumptionBill = TenantConsumptionBill.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.consumptionBillType(RandomUtil.randomInt(0,1+1))// 账单类型（1：充值；2：消费）
				.consumptionBillTime(new Date())// 账单时间
				.consumptionBillName(TestCaseUtil.name())// 账单名称[账户充值/短信平台/...]
				.consumptionBillMoney(new BigDecimal(0))// 账单金额
				.tenantBalance(new BigDecimal(0))// 租户账户余额
				.consumptionBillRemark(RandomUtil.randomString(4))// 备注
				.build();
		tenantConsumptionBill.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantConsumptionBill, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantConsumptionBillService.updateById(tenantConsumptionBill);

		log.info(Boolean.toString(success));
	}
}
