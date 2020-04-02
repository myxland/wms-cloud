package com.zlsrj.wms.saas.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantMeterPrice;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterPriceMapperTest {

	@Resource
	private TenantMeterPriceMapper tenantMeterPriceMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterPrice tenantMeterPrice = tenantMeterPriceMapper.selectById(id);
		log.info(tenantMeterPrice.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterPrice> queryWrapper = new QueryWrapper<TenantMeterPrice>();
		List<TenantMeterPrice> tenantMeterPriceList = tenantMeterPriceMapper.selectList(queryWrapper);
		tenantMeterPriceList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantMeterPrice tenantMeterPrice = TenantMeterPrice.builder()//
					.id(TestCaseUtil.id())// 水表计费ID
					.tenantId(tenantInfo.getId())// 租户ID
					.meterId(TestCaseUtil.id())// 水表ID
					.meterCode(RandomUtil.randomString(4))// 水表编号
					.priceOrder(RandomUtil.randomInt(0,1000+1))// 排序
					.priceId(TestCaseUtil.id())// 水价列表ID
					.priceType(RandomUtil.randomInt(1,0+1))// 计费类别（1：定量；0：定比）
					.priceScale(new BigDecimal(0))// 系数
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantMeterPriceMapper.insert(tenantMeterPrice);
			log.info("count={}",count);
			log.info("tenantMeterPrice={}",tenantMeterPrice);
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantMeterPrice> wrapper = new QueryWrapper<TenantMeterPrice>();
		wrapper.lambda()//
				.eq(TenantMeterPrice::getTenantId, 1L)//
		;
		TenantMeterPrice tenantMeterPriceAggregation = tenantMeterPriceMapper.selectAggregation(wrapper);
		
		log.info("tenantMeterPriceAggregation={}", tenantMeterPriceAggregation);
	}
	
}
