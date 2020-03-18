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
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceStepMapperTest {

	@Resource
	private TenantPriceStepMapper tenantPriceStepMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantPriceStep tenantPriceStep = tenantPriceStepMapper.selectById(id);
		log.info(tenantPriceStep.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantPriceStep> queryWrapper = new QueryWrapper<TenantPriceStep>();
		List<TenantPriceStep> tenantPriceStepList = tenantPriceStepMapper.selectList(queryWrapper);
		tenantPriceStepList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantPriceStep tenantPriceStep = TenantPriceStep.builder()//
					.id(TestCaseUtil.id())// 阶梯明细ID
					.tenantId(tenantInfo.getId())// 租户ID
					.priceDetailId(TestCaseUtil.id())// 水价明细ID
					.stepClass(RandomUtil.randomInt(0,1000+1))// 阶梯级次
					.startCode(TestCaseUtil.water())// 阶梯起始量
					.endCode(TestCaseUtil.water())// 阶梯终止量
					.stepPrice(TestCaseUtil.money())// 单价
					.stepUsers(RandomUtil.randomInt(0,1000+1))// 标准用水人数
					.stepUsersAdd(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.TEN))// 超人数增补量
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantPriceStepMapper.insert(tenantPriceStep);
			log.info("count={}",count);
			log.info("tenantPriceStep={}",tenantPriceStep);
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantPriceStep> wrapper = new QueryWrapper<TenantPriceStep>();
		wrapper.lambda()//
				.eq(TenantPriceStep::getTenantId, 1L)//
		;
		TenantPriceStep tenantPriceStepAggregation = tenantPriceStepMapper.selectAggregation(wrapper);
		
		log.info("tenantPriceStepAggregation={}", tenantPriceStepAggregation);
	}
	
}
