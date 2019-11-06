package com.zlsrj.wms.mbg.mapper;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantPriceStep;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceStepMapperTest {

	@Resource
	private TenantPriceStepMapper tenantPriceStepMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
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
		TenantPriceStep tenantPriceStep = TenantPriceStep.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别
				.priceItemId(RandomUtil.randomLong())// 费用项目
				.stepId(RandomUtil.randomLong())// 阶梯号
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.price(new BigDecimal(0))// 价格
				.build();
				
		int count = tenantPriceStepMapper.insert(tenantPriceStep);
		log.info("count={}",count);
		log.info("tenantPriceStep={}",tenantPriceStep);
	}
	
}
