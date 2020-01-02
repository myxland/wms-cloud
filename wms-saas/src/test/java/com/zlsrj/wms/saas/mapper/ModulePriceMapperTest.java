package com.zlsrj.wms.saas.mapper;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.ModulePrice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ModulePriceMapperTest {

	@Resource
	private ModulePriceMapper modulePriceMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		ModulePrice modulePrice = modulePriceMapper.selectById(id);
		log.info(modulePrice.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<ModulePrice> queryWrapper = new QueryWrapper<ModulePrice>();
		List<ModulePrice> modulePriceList = modulePriceMapper.selectList(queryWrapper);
		modulePriceList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		ModulePrice modulePrice = ModulePrice.builder()//
				.id(TestCaseUtil.id())// 模块价格ID
				.moduleId(RandomUtil.randomLong())// 模块ID
				.moduleEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（1：基础版；2：高级版；3：旗舰版）
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.priceMoney(new BigDecimal(0))// 价格
				.build();
				
		int count = modulePriceMapper.insert(modulePrice);
		log.info("count={}",count);
		log.info("modulePrice={}",modulePrice);
	}
	
}
