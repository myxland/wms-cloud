package com.zlsrj.wms.tenant.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantSms;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantSmsMapperTest {

	@Resource
	private TenantSmsMapper tenantSmsMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantSms tenantSms = tenantSmsMapper.selectById(id);
		log.info(tenantSms.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantSms> queryWrapper = new QueryWrapper<TenantSms>();
		List<TenantSms> tenantSmsList = tenantSmsMapper.selectList(queryWrapper);
		tenantSmsList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
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
				
		int count = tenantSmsMapper.insert(tenantSms);
		log.info("count={}",count);
		log.info("tenantSms={}",tenantSms);
	}
	
}
