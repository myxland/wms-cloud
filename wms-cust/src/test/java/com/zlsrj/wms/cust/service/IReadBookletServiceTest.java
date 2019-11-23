package com.zlsrj.wms.cust.service;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.ReadBooklet;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IReadBookletServiceTest {

	@Autowired
	private IReadBookletService readBookletService;

	@Test
	public void insertTest() {
		ReadBooklet readBooklet = ReadBooklet.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.bookletName(TestCaseUtil.name())// 表册名称
				.bookletType(RandomUtil.randomInt(0,1+1))// 表册类型（1：远传表；2：机械表）
				.readEmpId(RandomUtil.randomLong())// 抄表负责人编号
				.payEmpId(RandomUtil.randomLong())// 收费负责人编号
				.calcCycleInterval(RandomUtil.randomInt(0,1000+1))// 抄表间隔周期_月
				.calcMonthLast(new Date())// 最后一次抄表月份
				.calcMonthNext(new Date())// 下次抄表月份
				.build();

		log.info(ToStringBuilder.reflectionToString(readBooklet, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = readBookletService.save(readBooklet);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		ReadBooklet readBooklet = ReadBooklet.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.bookletName(TestCaseUtil.name())// 表册名称
				.bookletType(RandomUtil.randomInt(0,1+1))// 表册类型（1：远传表；2：机械表）
				.readEmpId(RandomUtil.randomLong())// 抄表负责人编号
				.payEmpId(RandomUtil.randomLong())// 收费负责人编号
				.calcCycleInterval(RandomUtil.randomInt(0,1000+1))// 抄表间隔周期_月
				.calcMonthLast(new Date())// 最后一次抄表月份
				.calcMonthNext(new Date())// 下次抄表月份
				.build();
		readBooklet.setId(id);

		log.info(ToStringBuilder.reflectionToString(readBooklet, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = readBookletService.updateById(readBooklet);

		log.info(Boolean.toString(success));
	}
}
