package com.zlsrj.wms.system.mapper;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.SystemPrice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SystemPriceMapperTest {

	@Resource
	private SystemPriceMapper systemPriceMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		SystemPrice systemPrice = systemPriceMapper.selectById(id);
		log.info(systemPrice.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<SystemPrice> queryWrapper = new QueryWrapper<SystemPrice>();
		List<SystemPrice> systemPriceList = systemPriceMapper.selectList(queryWrapper);
		systemPriceList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		SystemPrice systemPrice = SystemPrice.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（1：基础版；2：高级版；3：旗舰版）
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.price(new BigDecimal(0))// 价格
				.build();
				
		int count = systemPriceMapper.insert(systemPrice);
		log.info("count={}",count);
		log.info("systemPrice={}",systemPrice);
	}
	
}
