package com.zlsrj.wms.tenant.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantSms;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantSmsServiceTest {

	@Autowired
	private ITenantSmsService tenantSmsService;

	@Test
	public void insertTest() {
		TenantSms tenantSms = TenantSms.builder()//
				.id(TestCaseUtil.id())// 编号ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.smsSignature(RandomUtil.randomString(4))// 签名
				.smsSpService(RandomUtil.randomString(4))// SP服务商
				.smsReadSendOn(RandomUtil.randomInt(0,1+1))// 抄表账单通知
				.smsChargeSendOn(RandomUtil.randomInt(0,1+1))// 缴费成功通知
				.smsQfSendOn(RandomUtil.randomInt(0,1+1))// 是否启用欠费通知短信（1：启用；0：不启用）
				.smsQfSendAfterDays(RandomUtil.randomInt(0,1000+1))// 欠费通知短信发送间隔天数
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantSms, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantSmsService.save(tenantSms);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantSms tenantSms = TenantSms.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.smsSignature(RandomUtil.randomString(4))// 签名
				.smsSpService(RandomUtil.randomString(4))// SP服务商
				.smsReadSendOn(RandomUtil.randomInt(0,1+1))// 抄表账单通知
				.smsChargeSendOn(RandomUtil.randomInt(0,1+1))// 缴费成功通知
				.smsQfSendOn(RandomUtil.randomInt(0,1+1))// 是否启用欠费通知短信（1：启用；0：不启用）
				.smsQfSendAfterDays(RandomUtil.randomInt(0,1000+1))// 欠费通知短信发送间隔天数
				.build();
		tenantSms.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantSms, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantSmsService.updateById(tenantSms);

		log.info(Boolean.toString(success));
	}
}
