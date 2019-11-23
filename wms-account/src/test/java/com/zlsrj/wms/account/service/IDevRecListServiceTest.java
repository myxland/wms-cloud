package com.zlsrj.wms.account.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.DevRecList;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IDevRecListServiceTest {

	@Autowired
	private IDevRecListService devRecListService;

	@Test
	public void insertTest() {
		DevRecList devRecList = DevRecList.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.recFlag(RandomUtil.randomInt(0,1000+1))// 应收账标识（1：正常；2：被冲正；3：冲正负记录）
				.recType(RandomUtil.randomInt(0,1+1))// 应收来源（1：抄表；2：换表）
				.deptId(RandomUtil.randomLong())// 部门编号
				.bookletId(RandomUtil.randomLong())// 表册编号
				.custId(RandomUtil.randomLong())// 用户编号
				.custName(TestCaseUtil.name())// 用户名称
				.custAddress(TestCaseUtil.address())// 用户地址
				.devId(RandomUtil.randomLong())// 表具系统编号
				.devAddress(TestCaseUtil.address())// 表具地址
				.readMonth(new Date())// 应收月份
				.recMonth(new Date())// 应收账务统计月份
				.businessId(RandomUtil.randomLong())// 业务（抄表、换表）流水号
				.reader(RandomUtil.randomLong())// 抄表员编号
				.calcDate(new Date())// 计费时间
				.lastDate(new Date())// 上次计费日期
				.lastCode(new BigDecimal(0))// 起数
				.currDate(new Date())// 当前计费日期
				.currCode(new BigDecimal(0))// 止数
				.waterScale(new BigDecimal(0))// 用量占比
				.useNum(new BigDecimal(0))// 计费水量
				.priceTypeId(RandomUtil.randomLong())// 价格编号
				.recMoney(new BigDecimal(0))// 应收金额
				.dueMoney(new BigDecimal(0))// 欠费金额
				.build();

		log.info(ToStringBuilder.reflectionToString(devRecList, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = devRecListService.save(devRecList);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		DevRecList devRecList = DevRecList.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.recFlag(RandomUtil.randomInt(0,1000+1))// 应收账标识（1：正常；2：被冲正；3：冲正负记录）
				.recType(RandomUtil.randomInt(0,1+1))// 应收来源（1：抄表；2：换表）
				.deptId(RandomUtil.randomLong())// 部门编号
				.bookletId(RandomUtil.randomLong())// 表册编号
				.custId(RandomUtil.randomLong())// 用户编号
				.custName(TestCaseUtil.name())// 用户名称
				.custAddress(TestCaseUtil.address())// 用户地址
				.devId(RandomUtil.randomLong())// 表具系统编号
				.devAddress(TestCaseUtil.address())// 表具地址
				.readMonth(new Date())// 应收月份
				.recMonth(new Date())// 应收账务统计月份
				.businessId(RandomUtil.randomLong())// 业务（抄表、换表）流水号
				.reader(RandomUtil.randomLong())// 抄表员编号
				.calcDate(new Date())// 计费时间
				.lastDate(new Date())// 上次计费日期
				.lastCode(new BigDecimal(0))// 起数
				.currDate(new Date())// 当前计费日期
				.currCode(new BigDecimal(0))// 止数
				.waterScale(new BigDecimal(0))// 用量占比
				.useNum(new BigDecimal(0))// 计费水量
				.priceTypeId(RandomUtil.randomLong())// 价格编号
				.recMoney(new BigDecimal(0))// 应收金额
				.dueMoney(new BigDecimal(0))// 欠费金额
				.build();
		devRecList.setId(id);

		log.info(ToStringBuilder.reflectionToString(devRecList, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = devRecListService.updateById(devRecList);

		log.info(Boolean.toString(success));
	}
}
