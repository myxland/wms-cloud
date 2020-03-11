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
import com.zlsrj.wms.api.entity.TenantPayment;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantPaymentServiceTest {

	@Autowired
	private ITenantPaymentService tenantPaymentService;

	@Test
	public void insertTest() {
		TenantPayment tenantPayment = TenantPayment.builder()//
				.id(TestCaseUtil.id())// 实收账ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.outTransno(RandomUtil.randomString(4))// 内部生成的订单号
				.inTransno(RandomUtil.randomString(4))// 外部如微信支付宝传入的订单号
				.payTime(new Date())// 付款时间
				.paymentStatus(RandomUtil.randomInt(0,1+1))// 实收账状态（1：正常；2：被退款；3：退款记录）
				.customerId(RandomUtil.randomString(32))// 用户ID
				.chargeDepartmentId(RandomUtil.randomString(32))// 收款部门ID
				.chargeEmployeeId(RandomUtil.randomString(32))// 收费员ID
				.payChannels(RandomUtil.randomInt(0,1000+1))// 付款途径（1：柜台；2：银行；3：线上；4：走收；5：系统处理）
				.payMethod(RandomUtil.randomInt(0,1000+1))// 付款方式（0：预存抵扣；1：现金；2：支票；3：刷卡；4：电汇；5：代扣；6：托收；7：微信生活缴费；8：支付宝生活缴费；9：微信公众号；10：微信扫码[用户被扫]；11：支付宝扫码[用户被扫]；12：微信扫码[用户主扫]；13：支付宝扫码[用户主扫]）
				.customerBalanceMoneyBefore(new BigDecimal(0))// 用户上期预存余额
				.customerPayMoney(new BigDecimal(0))// 用户付款金额
				.customerBalanceMoneyHappen(new BigDecimal(0))// 用户预存发生值
				.payTheArrearsMoney(new BigDecimal(0))// 所缴欠费金额
				.payTheLateFeeMoney(new BigDecimal(0))// 所缴违约金金额
				.customerBalanceMoneyAfter(new BigDecimal(0))// 用户本期预存余额
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPayment, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPaymentService.save(tenantPayment);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantPayment tenantPayment = TenantPayment.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.outTransno(RandomUtil.randomString(4))// 内部生成的订单号
				.inTransno(RandomUtil.randomString(4))// 外部如微信支付宝传入的订单号
				.payTime(new Date())// 付款时间
				.paymentStatus(RandomUtil.randomInt(0,1+1))// 实收账状态（1：正常；2：被退款；3：退款记录）
				.customerId(RandomUtil.randomString(32))// 用户ID
				.chargeDepartmentId(RandomUtil.randomString(32))// 收款部门ID
				.chargeEmployeeId(RandomUtil.randomString(32))// 收费员ID
				.payChannels(RandomUtil.randomInt(0,1000+1))// 付款途径（1：柜台；2：银行；3：线上；4：走收；5：系统处理）
				.payMethod(RandomUtil.randomInt(0,1000+1))// 付款方式（0：预存抵扣；1：现金；2：支票；3：刷卡；4：电汇；5：代扣；6：托收；7：微信生活缴费；8：支付宝生活缴费；9：微信公众号；10：微信扫码[用户被扫]；11：支付宝扫码[用户被扫]；12：微信扫码[用户主扫]；13：支付宝扫码[用户主扫]）
				.customerBalanceMoneyBefore(new BigDecimal(0))// 用户上期预存余额
				.customerPayMoney(new BigDecimal(0))// 用户付款金额
				.customerBalanceMoneyHappen(new BigDecimal(0))// 用户预存发生值
				.payTheArrearsMoney(new BigDecimal(0))// 所缴欠费金额
				.payTheLateFeeMoney(new BigDecimal(0))// 所缴违约金金额
				.customerBalanceMoneyAfter(new BigDecimal(0))// 用户本期预存余额
				.build();
		tenantPayment.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPayment, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPaymentService.updateById(tenantPayment);

		log.info(Boolean.toString(success));
	}
}
