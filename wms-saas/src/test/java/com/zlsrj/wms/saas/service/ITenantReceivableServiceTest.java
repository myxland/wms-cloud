package com.zlsrj.wms.saas.service;

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
import com.zlsrj.wms.api.entity.TenantReceivable;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantReceivableServiceTest {

	@Autowired
	private ITenantReceivableService tenantReceivableService;

	@Test
	public void insertTest() {
		TenantReceivable tenantReceivable = TenantReceivable.builder()//
				.id(TestCaseUtil.id())// 应收账ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.receivableStatus(RandomUtil.randomInt(0,1+1))// 应收账状态（1：正常；2：被冲正；3：冲正负记录）
				.receivableType(RandomUtil.randomInt(0,1+1))// 应收类型（1：抄表；2：换表；3：追补）
				.departmentId(RandomUtil.randomLong())// 部门ID
				.bookletId(RandomUtil.randomLong())// 表册ID
				.bookletCode(RandomUtil.randomString(4))// 表册代码
				.customerId(RandomUtil.randomLong())// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户代码
				.customerName(TestCaseUtil.name())// 用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.meterId(RandomUtil.randomLong())// 水表ID
				.meterCode(RandomUtil.randomString(4))// 水表代码
				.meterAddress(TestCaseUtil.address())// 表具地址
				.readEmployeeId(RandomUtil.randomLong())// 抄表员ID
				.receivableTime(new Date())// 应收账时间
				.settleStartTime(new Date())// 结算开始时间
				.settleStartPointer(new BigDecimal(0))// 结算开始指针
				.settleEndTime(new Date())// 结算截止时间
				.settleEndPointer(new BigDecimal(0))// 结算截止指针
				.settleWaters(new BigDecimal(0))// 应结算水量
				.priceTypeId(RandomUtil.randomLong())// 价格类别ID
				.receivableWaters(new BigDecimal(0))// 应收水量
				.receivableMoney(new BigDecimal(0))// 应收金额
				.arrearsMoney(new BigDecimal(0))// 欠费金额
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantReceivable, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantReceivableService.save(tenantReceivable);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantReceivable tenantReceivable = TenantReceivable.builder()//
				.tenantId(RandomUtil.randomLong())// 租户ID
				.receivableStatus(RandomUtil.randomInt(0,1+1))// 应收账状态（1：正常；2：被冲正；3：冲正负记录）
				.receivableType(RandomUtil.randomInt(0,1+1))// 应收类型（1：抄表；2：换表；3：追补）
				.departmentId(RandomUtil.randomLong())// 部门ID
				.bookletId(RandomUtil.randomLong())// 表册ID
				.bookletCode(RandomUtil.randomString(4))// 表册代码
				.customerId(RandomUtil.randomLong())// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户代码
				.customerName(TestCaseUtil.name())// 用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.meterId(RandomUtil.randomLong())// 水表ID
				.meterCode(RandomUtil.randomString(4))// 水表代码
				.meterAddress(TestCaseUtil.address())// 表具地址
				.readEmployeeId(RandomUtil.randomLong())// 抄表员ID
				.receivableTime(new Date())// 应收账时间
				.settleStartTime(new Date())// 结算开始时间
				.settleStartPointer(new BigDecimal(0))// 结算开始指针
				.settleEndTime(new Date())// 结算截止时间
				.settleEndPointer(new BigDecimal(0))// 结算截止指针
				.settleWaters(new BigDecimal(0))// 应结算水量
				.priceTypeId(RandomUtil.randomLong())// 价格类别ID
				.receivableWaters(new BigDecimal(0))// 应收水量
				.receivableMoney(new BigDecimal(0))// 应收金额
				.arrearsMoney(new BigDecimal(0))// 欠费金额
				.build();
		tenantReceivable.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantReceivable, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantReceivableService.updateById(tenantReceivable);

		log.info(Boolean.toString(success));
	}
}
