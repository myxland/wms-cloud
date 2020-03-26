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
import com.zlsrj.wms.api.entity.TenantConsumptionBill;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantConsumptionBillMapperTest {

	@Resource
	private TenantConsumptionBillMapper tenantConsumptionBillMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantConsumptionBill tenantConsumptionBill = tenantConsumptionBillMapper.selectById(id);
		log.info(tenantConsumptionBill.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantConsumptionBill> queryWrapper = new QueryWrapper<TenantConsumptionBill>();
		List<TenantConsumptionBill> tenantConsumptionBillList = tenantConsumptionBillMapper.selectList(queryWrapper);
		tenantConsumptionBillList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantConsumptionBill tenantConsumptionBill = TenantConsumptionBill.builder()//
					.id(TestCaseUtil.id())// 租户账单ID
					.tenantId(tenantInfo.getId())// 租户ID
					.consumptionBillType(RandomUtil.randomInt(1,2+1))// 账单类型（1：充值；2：消费）
					.consumptionBillTime(TestCaseUtil.dateBefore())// 账单时间
					.consumptionBillName(TestCaseUtil.name())// 账单名称[账户充值/短信平台/...]
					.consumptionBillMoney(TestCaseUtil.money())// 账单金额
					.tenantBalance(new BigDecimal(0))// 租户账户余额
					.consumptionBillRemark(RandomUtil.randomString(4))// 备注
					.build();
				
			int count = tenantConsumptionBillMapper.insert(tenantConsumptionBill);
			log.info("count={}",count);
			log.info("tenantConsumptionBill={}",tenantConsumptionBill);
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantConsumptionBill> wrapper = new QueryWrapper<TenantConsumptionBill>();
		wrapper.lambda()//
				.eq(TenantConsumptionBill::getTenantId, 1L)//
		;
		TenantConsumptionBill tenantConsumptionBillAggregation = tenantConsumptionBillMapper.selectAggregation(wrapper);
		
		log.info("tenantConsumptionBillAggregation={}", tenantConsumptionBillAggregation);
	}
	
}
