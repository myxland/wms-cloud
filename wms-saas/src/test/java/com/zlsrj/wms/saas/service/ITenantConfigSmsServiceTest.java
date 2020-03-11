package com.zlsrj.wms.saas.service;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantConfigSms;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantConfigSmsServiceTest {

	@Autowired
	private ITenantConfigSmsService tenantConfigSmsService;

	@Test
	public void insertTest() {
		TenantConfigSms tenantConfigSms = TenantConfigSms.builder()//
				.id(TestCaseUtil.id())// 短信配置ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.smsSignature(RandomUtil.randomString(4))// 短信签名
				.smsReceivableNoticeOn(RandomUtil.randomInt(0,1+1))// 开启出账短信通知（1：开启；0：不开启）
				.smsPaymentNoticeOn(RandomUtil.randomInt(0,1+1))// 开启付款短信通知（1：开启；0：不开启）
				.smsBalanceMoneyChangeNoticeOn(RandomUtil.randomInt(0,1+1))// 开启用户预存款变动短信通知（1：开启；0：不开启）
				.smsArrearsNoticeOn(RandomUtil.randomInt(0,1+1))// 开启欠费催缴短信通知（1：开启；0：不开启）
				.smsReceivableNoticeTemplate(RandomUtil.randomString(4))// 出账短信通知模板
				.smsPaymentNoticeTemplate(RandomUtil.randomString(4))// 付款短信通知模板
				.smsBalanceMoneyChangeNoticeTemplate(RandomUtil.randomString(4))// 用户预存款变动短信通知模板
				.smsArrearsNoticeTemplate(RandomUtil.randomString(4))// 欠费催缴短信通知模板
				.smsArrearsDays(RandomUtil.randomInt(0,1000+1))// 进入催缴的欠费天数
				.smsArrearsNoticeDay(RandomUtil.randomInt(0,1000+1))// 每月多少号进行欠费催缴
				.smsArrearsNoticeStartTime(new Date())// 欠费催缴开始时间
				.smsArrearsNoticeEndTime(new Date())// 欠费催缴结束时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantConfigSms, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantConfigSmsService.save(tenantConfigSms);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantConfigSms tenantConfigSms = TenantConfigSms.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.smsSignature(RandomUtil.randomString(4))// 短信签名
				.smsReceivableNoticeOn(RandomUtil.randomInt(0,1+1))// 开启出账短信通知（1：开启；0：不开启）
				.smsPaymentNoticeOn(RandomUtil.randomInt(0,1+1))// 开启付款短信通知（1：开启；0：不开启）
				.smsBalanceMoneyChangeNoticeOn(RandomUtil.randomInt(0,1+1))// 开启用户预存款变动短信通知（1：开启；0：不开启）
				.smsArrearsNoticeOn(RandomUtil.randomInt(0,1+1))// 开启欠费催缴短信通知（1：开启；0：不开启）
				.smsReceivableNoticeTemplate(RandomUtil.randomString(4))// 出账短信通知模板
				.smsPaymentNoticeTemplate(RandomUtil.randomString(4))// 付款短信通知模板
				.smsBalanceMoneyChangeNoticeTemplate(RandomUtil.randomString(4))// 用户预存款变动短信通知模板
				.smsArrearsNoticeTemplate(RandomUtil.randomString(4))// 欠费催缴短信通知模板
				.smsArrearsDays(RandomUtil.randomInt(0,1000+1))// 进入催缴的欠费天数
				.smsArrearsNoticeDay(RandomUtil.randomInt(0,1000+1))// 每月多少号进行欠费催缴
				.smsArrearsNoticeStartTime(new Date())// 欠费催缴开始时间
				.smsArrearsNoticeEndTime(new Date())// 欠费催缴结束时间
				.build();
		tenantConfigSms.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantConfigSms, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantConfigSmsService.updateById(tenantConfigSms);

		log.info(Boolean.toString(success));
	}
}
