package com.zlsrj.wms.cust.mapper;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.CustDev;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustDevMapperTest {

	@Resource
	private CustDevMapper custDevMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		CustDev custDev = custDevMapper.selectById(id);
		log.info(custDev.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<CustDev> queryWrapper = new QueryWrapper<CustDev>();
		List<CustDev> custDevList = custDevMapper.selectList(queryWrapper);
		custDevList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		CustDev custDev = CustDev.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.devId(RandomUtil.randomLong())// 表具编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别编号
				.waterTypeId(RandomUtil.randomLong())// 用水类别编号
				.devOrder(RandomUtil.randomInt(0,1000+1))// 排序
				.waterMixType(RandomUtil.randomInt(1,2+1))// 混合类型（1：定量；2：比例）
				.waterScale(new BigDecimal(0))// 本价格用水量占比或定量
				.waterCalcType(RandomUtil.randomInt(1,2+1))// 水量计算方法（1：向上取整；2：向下取整；3：保留两位小数）
				.build();
				
		int count = custDevMapper.insert(custDev);
		log.info("count={}",count);
		log.info("custDev={}",custDev);
	}
	
}
