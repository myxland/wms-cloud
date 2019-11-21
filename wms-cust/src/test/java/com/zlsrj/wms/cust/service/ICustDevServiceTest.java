package com.zlsrj.wms.cust.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.CustDev;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ICustDevServiceTest {

	@Autowired
	private ICustDevService custDevService;

	@Test
	public void insertTest() {
		CustDev custDev = CustDev.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.devId(RandomUtil.randomLong())// 表具编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别编号
				.waterTypeId(RandomUtil.randomLong())// 用水类别编号
				.devOrder(RandomUtil.randomInt(0,1000+1))// 排序
				.waterMixType(RandomUtil.randomInt(0,1+1))// 混合类型（1：定量；2：比例）
				.waterScale(new BigDecimal(0))// 本价格用水量占比或定量
				.waterCalcType(RandomUtil.randomInt(0,1+1))// 水量计算方法（1：向上取整；2：向下取整；3：保留两位小数）
				.build();

		log.info(ToStringBuilder.reflectionToString(custDev, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = custDevService.save(custDev);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		CustDev custDev = CustDev.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.devId(RandomUtil.randomLong())// 表具编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别编号
				.waterTypeId(RandomUtil.randomLong())// 用水类别编号
				.devOrder(RandomUtil.randomInt(0,1000+1))// 排序
				.waterMixType(RandomUtil.randomInt(0,1+1))// 混合类型（1：定量；2：比例）
				.waterScale(new BigDecimal(0))// 本价格用水量占比或定量
				.waterCalcType(RandomUtil.randomInt(0,1+1))// 水量计算方法（1：向上取整；2：向下取整；3：保留两位小数）
				.build();
		custDev.setId(id);

		log.info(ToStringBuilder.reflectionToString(custDev, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = custDevService.updateById(custDev);

		log.info(Boolean.toString(success));
	}
}
