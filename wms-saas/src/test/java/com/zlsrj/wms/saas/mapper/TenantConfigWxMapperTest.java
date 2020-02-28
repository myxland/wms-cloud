package com.zlsrj.wms.saas.mapper;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantConfigWx;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantConfigWxMapperTest {

	@Resource
	private TenantConfigWxMapper tenantConfigWxMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantConfigWx tenantConfigWx = tenantConfigWxMapper.selectById(id);
		log.info(tenantConfigWx.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantConfigWx> queryWrapper = new QueryWrapper<TenantConfigWx>();
		List<TenantConfigWx> tenantConfigWxList = tenantConfigWxMapper.selectList(queryWrapper);
		tenantConfigWxList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantConfigWx tenantConfigWx = TenantConfigWx.builder()//
					.id(TestCaseUtil.id())// 微信配置ID
					.tenantId(tenantInfo.getId())// 租户ID
					.wxAppid(RandomUtil.randomString(4))// 微信公众号APPID
					.wxAppsecret(RandomUtil.randomString(4))// 微信公众号AppSecret
					.wxAccountId(RandomUtil.randomString(4))// 微信商户ID
					.wxAccountApiKey(RandomUtil.randomString(4))// 微信商户API密钥
					.wxTitlePictureFileName(TestCaseUtil.name())// 微信公众号首页显示图片名称
					.wxServiceTel(TestCaseUtil.tel())// 微信公众号显示服务电话
					.wxReceivableNoticeOn(RandomUtil.randomInt(1,0+1))// 开启出账微信通知（1：开启；0：不开启）
					.wxPaymentNoticeOn(RandomUtil.randomInt(1,0+1))// 开启付款微信通知（1：开启；0：不开启）
					.wxBalanceMoneyChangeNoticeOn(RandomUtil.randomInt(1,0+1))// 开启用户预存款变动微信通知（1：开启；0：不开启）
					.wxArrearsNoticeOn(RandomUtil.randomInt(1,0+1))// 开启欠费催缴微信通知（1：开启；0：不开启）
					.wxReceivableNoticeTemplate(RandomUtil.randomString(4))// 出账微信通知模板
					.wxPaymentNoticeTemplate(RandomUtil.randomString(4))// 付款微信通知模板
					.wxBalanceMoneyChangeNoticeTemplate(RandomUtil.randomString(4))// 用户预存款变动微信通知模板
					.wxArrearsNoticeTemplate(RandomUtil.randomString(4))// 欠费催缴微信通知模板
					.wxArrearsDays(RandomUtil.randomInt(0,1000+1))// 进入催缴的欠费天数
					.wxArrearsNoticeDay(RandomUtil.randomInt(0,1000+1))// 每月多少号进行欠费催缴
					.wxArrearsNoticeStartTime(TestCaseUtil.dateBefore())// 欠费催缴开始时间
					.wxArrearsNoticeEndTime(TestCaseUtil.dateBefore())// 欠费催缴结束时间
					.build();
				
			int count = tenantConfigWxMapper.insert(tenantConfigWx);
			log.info("count={}",count);
			log.info("tenantConfigWx={}",tenantConfigWx);
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantConfigWx> wrapper = new QueryWrapper<TenantConfigWx>();
		wrapper.lambda()//
				.eq(TenantConfigWx::getTenantId, 1L)//
		;
		TenantConfigWx tenantConfigWxAggregation = tenantConfigWxMapper.selectAggregation(wrapper);
		
		log.info("tenantConfigWxAggregation={}", tenantConfigWxAggregation);
	}
	
}
