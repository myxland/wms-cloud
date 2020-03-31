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
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerMapperTest {

	@Resource
	private TenantCustomerMapper tenantCustomerMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantCustomer tenantCustomer = tenantCustomerMapper.selectById(id);
		log.info(tenantCustomer.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCustomer> queryWrapper = new QueryWrapper<TenantCustomer>();
		List<TenantCustomer> tenantCustomerList = tenantCustomerMapper.selectList(queryWrapper);
		tenantCustomerList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantCustomer tenantCustomer = TenantCustomer.builder()//
					.id(TestCaseUtil.id())// 用户ID
					.tenantId(tenantInfo.getId())// 租户ID
					.customerCode(RandomUtil.randomString(4))// 用户号
					.customerName(TestCaseUtil.name())// 用户名称
					.customerAddress(TestCaseUtil.address())// 用户地址
					.customerStatus(RandomUtil.randomInt(1,3+1))// 用户状态（1：正常；2：暂停；3：销户）
					.customerType(RandomUtil.randomString(4))// 用户类别
					.customerRegisterTime(TestCaseUtil.dateBefore())// 建档时间
					.customerRegisterDate(TestCaseUtil.dateBefore())// 立户日期
					.customerCreditRating(RandomUtil.randomInt(0,1000+1))// 信用等级
					.customerRatingDate(TestCaseUtil.dateBefore())// 最近评估日期
					.customerBalanceAmt(new BigDecimal(0))// 预存余额
					.customerFreezeBalance(new BigDecimal(0))// 预存冻结金额
					.customerOweAmt(new BigDecimal(0))// 欠费金额
					.customerPenaltyAmt(new BigDecimal(0))// 违约金
					.customerMemo(RandomUtil.randomString(4))// 用户备注
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantCustomerMapper.insert(tenantCustomer);
			log.info("count={}",count);
			log.info("tenantCustomer={}",tenantCustomer);
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantCustomer> wrapper = new QueryWrapper<TenantCustomer>();
		wrapper.lambda()//
				.eq(TenantCustomer::getTenantId, 1L)//
		;
		TenantCustomer tenantCustomerAggregation = tenantCustomerMapper.selectAggregation(wrapper);
		
		log.info("tenantCustomerAggregation={}", tenantCustomerAggregation);
	}
	
}
