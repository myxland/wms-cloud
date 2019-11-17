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
import com.zlsrj.wms.api.entity.TenantSystemPrice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantSystemPriceMapperTest {

	@Resource
	private TenantSystemPriceMapper tenantSystemPriceMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantSystemPrice tenantSystemPrice = tenantSystemPriceMapper.selectById(id);
		log.info(tenantSystemPrice.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantSystemPrice> queryWrapper = new QueryWrapper<TenantSystemPrice>();
		List<TenantSystemPrice> tenantSystemPriceList = tenantSystemPriceMapper.selectList(queryWrapper);
		tenantSystemPriceList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantSystemPrice tenantSystemPrice = TenantSystemPrice.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（0：基础版；1：高级版；2：旗舰版）
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.price(new BigDecimal(0))// 价格
				.build();
				
		int count = tenantSystemPriceMapper.insert(tenantSystemPrice);
		log.info("count={}",count);
		log.info("tenantSystemPrice={}",tenantSystemPrice);
	}
	
}
