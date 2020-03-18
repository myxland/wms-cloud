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
import com.zlsrj.wms.api.entity.TenantPriceDetail;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceDetailMapperTest {

	@Resource
	private TenantPriceDetailMapper tenantPriceDetailMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantPriceDetail tenantPriceDetail = tenantPriceDetailMapper.selectById(id);
		log.info(tenantPriceDetail.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantPriceDetail> queryWrapper = new QueryWrapper<TenantPriceDetail>();
		List<TenantPriceDetail> tenantPriceDetailList = tenantPriceDetailMapper.selectList(queryWrapper);
		tenantPriceDetailList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantPriceDetail tenantPriceDetail = TenantPriceDetail.builder()//
					.id(TestCaseUtil.id())// 水价明细ID
					.tenantId(tenantInfo.getId())// 租户ID
					.priceId(TestCaseUtil.id())// 水表列表ID
					.priceItemId(TestCaseUtil.id())// 费用项目
					.priceRuleId(TestCaseUtil.id())// 计费规则
					.detailPrice(TestCaseUtil.money())// 单价
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantPriceDetailMapper.insert(tenantPriceDetail);
			log.info("count={}",count);
			log.info("tenantPriceDetail={}",tenantPriceDetail);
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantPriceDetail> wrapper = new QueryWrapper<TenantPriceDetail>();
		wrapper.lambda()//
				.eq(TenantPriceDetail::getTenantId, 1L)//
		;
		TenantPriceDetail tenantPriceDetailAggregation = tenantPriceDetailMapper.selectAggregation(wrapper);
		
		log.info("tenantPriceDetailAggregation={}", tenantPriceDetailAggregation);
	}
	
}
