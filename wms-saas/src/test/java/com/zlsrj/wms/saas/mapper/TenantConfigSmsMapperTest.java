package com.zlsrj.wms.saas.mapper;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.entity.TenantConfigSms;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantConfigSmsMapperTest {

	@Resource
	private TenantConfigSmsMapper tenantConfigSmsMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;


	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantConfigSms tenantConfigSms = tenantConfigSmsMapper.selectById(id);
		log.info(tenantConfigSms.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantConfigSms> queryWrapper = new QueryWrapper<TenantConfigSms>();
		List<TenantConfigSms> tenantConfigSmsList = tenantConfigSmsMapper.selectList(queryWrapper);
		tenantConfigSmsList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantConfigSms tenantConfigSms = TenantConfigSms.builder()//
					.id(TestCaseUtil.id())// 短信配置ID
					.tenantId(tenantInfo.getId())// 租户ID
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
					
			int count = tenantConfigSmsMapper.insert(tenantConfigSms);
			log.info("count={}",count);
			log.info("tenantConfigSms={}",tenantConfigSms);
		}
		
	}
	
}
