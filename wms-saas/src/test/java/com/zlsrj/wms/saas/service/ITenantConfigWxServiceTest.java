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
import com.zlsrj.wms.api.entity.TenantConfigWx;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantConfigWxServiceTest {

	@Autowired
	private ITenantConfigWxService tenantConfigWxService;

	@Test
	public void insertTest() {
		TenantConfigWx tenantConfigWx = TenantConfigWx.builder()//
				.id(TestCaseUtil.id())// 微信配置ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.wxAppid(RandomUtil.randomString(4))// 微信公众号APPID
				.wxAppsecret(RandomUtil.randomString(4))// 微信公众号AppSecret
				.wxAccountId(RandomUtil.randomString(4))// 微信商户ID
				.wxAccountApiKey(RandomUtil.randomString(4))// 微信商户API密钥
				.wxTitlePictureFileName(TestCaseUtil.name())// 微信公众号首页显示图片名称
				.wxServiceTel(TestCaseUtil.tel())// 微信公众号显示服务电话
				.wxReceivableNoticeOn(RandomUtil.randomInt(0,1+1))// 开启出账微信通知（1：开启；0：不开启）
				.wxPaymentNoticeOn(RandomUtil.randomInt(0,1+1))// 开启付款微信通知（1：开启；0：不开启）
				.wxBalanceMoneyChangeNoticeOn(RandomUtil.randomInt(0,1+1))// 开启用户预存款变动微信通知（1：开启；0：不开启）
				.wxArrearsNoticeOn(RandomUtil.randomInt(0,1+1))// 开启欠费催缴微信通知（1：开启；0：不开启）
				.wxReceivableNoticeTemplate(RandomUtil.randomString(4))// 出账微信通知模板
				.wxPaymentNoticeTemplate(RandomUtil.randomString(4))// 付款微信通知模板
				.wxBalanceMoneyChangeNoticeTemplate(RandomUtil.randomString(4))// 用户预存款变动微信通知模板
				.wxArrearsNoticeTemplate(RandomUtil.randomString(4))// 欠费催缴微信通知模板
				.wxArrearsDays(RandomUtil.randomInt(0,1000+1))// 进入催缴的欠费天数
				.wxArrearsNoticeDay(RandomUtil.randomInt(0,1000+1))// 每月多少号进行欠费催缴
				.wxArrearsNoticeStartTime(new Date())// 欠费催缴开始时间
				.wxArrearsNoticeEndTime(new Date())// 欠费催缴结束时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantConfigWx, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantConfigWxService.save(tenantConfigWx);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantConfigWx tenantConfigWx = TenantConfigWx.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.wxAppid(RandomUtil.randomString(4))// 微信公众号APPID
				.wxAppsecret(RandomUtil.randomString(4))// 微信公众号AppSecret
				.wxAccountId(RandomUtil.randomString(4))// 微信商户ID
				.wxAccountApiKey(RandomUtil.randomString(4))// 微信商户API密钥
				.wxTitlePictureFileName(TestCaseUtil.name())// 微信公众号首页显示图片名称
				.wxServiceTel(TestCaseUtil.tel())// 微信公众号显示服务电话
				.wxReceivableNoticeOn(RandomUtil.randomInt(0,1+1))// 开启出账微信通知（1：开启；0：不开启）
				.wxPaymentNoticeOn(RandomUtil.randomInt(0,1+1))// 开启付款微信通知（1：开启；0：不开启）
				.wxBalanceMoneyChangeNoticeOn(RandomUtil.randomInt(0,1+1))// 开启用户预存款变动微信通知（1：开启；0：不开启）
				.wxArrearsNoticeOn(RandomUtil.randomInt(0,1+1))// 开启欠费催缴微信通知（1：开启；0：不开启）
				.wxReceivableNoticeTemplate(RandomUtil.randomString(4))// 出账微信通知模板
				.wxPaymentNoticeTemplate(RandomUtil.randomString(4))// 付款微信通知模板
				.wxBalanceMoneyChangeNoticeTemplate(RandomUtil.randomString(4))// 用户预存款变动微信通知模板
				.wxArrearsNoticeTemplate(RandomUtil.randomString(4))// 欠费催缴微信通知模板
				.wxArrearsDays(RandomUtil.randomInt(0,1000+1))// 进入催缴的欠费天数
				.wxArrearsNoticeDay(RandomUtil.randomInt(0,1000+1))// 每月多少号进行欠费催缴
				.wxArrearsNoticeStartTime(new Date())// 欠费催缴开始时间
				.wxArrearsNoticeEndTime(new Date())// 欠费催缴结束时间
				.build();
		tenantConfigWx.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantConfigWx, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantConfigWxService.updateById(tenantConfigWx);

		log.info(Boolean.toString(success));
	}
}
