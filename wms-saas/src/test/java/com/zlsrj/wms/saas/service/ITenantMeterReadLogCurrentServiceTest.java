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
import com.zlsrj.wms.api.entity.TenantMeterReadLogCurrent;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterReadLogCurrentServiceTest {

	@Autowired
	private ITenantMeterReadLogCurrentService tenantMeterReadLogCurrentService;

	@Test
	public void insertTest() {
		TenantMeterReadLogCurrent tenantMeterReadLogCurrent = TenantMeterReadLogCurrent.builder()//
				.id(TestCaseUtil.id())// 抄表计划
				.tenantId(RandomUtil.randomLong())// 租户ID
				.readMonth(new Date())// 结算月份
				.meterId(RandomUtil.randomLong())// 水表ID
				.meterYearTotalWatersBefore(new BigDecimal(0))// 结算前水表当年累计水量
				.settleStartTime(new Date())// 结算开始时间
				.settleStartPointer(new BigDecimal(0))// 结算开始指针
				.currentReadTime(new Date())// 本次抄表时间
				.currentReadPointer(new BigDecimal(0))// 本次抄表指针
				.readEmployeeId(RandomUtil.randomLong())// 抄表员ID
				.meterStatusId(RandomUtil.randomLong())// 表次抄表状况
				.settleWaters(new BigDecimal(0))// 应结算水量
				.receivableWaters(new BigDecimal(0))// 应收水量
				.readSource(RandomUtil.randomInt(0,1000+1))// 抄表来源（1：移动抄表；2：人工入账；3：远传表导入；4：远传表接口）
				.readStatus(RandomUtil.randomInt(0,1+1))// 抄表状态（0：未抄；1：已抄）
				.checkResult(RandomUtil.randomInt(0,1000+1))// 检查结果（0：正常；1：异常）
				.processReault(RandomUtil.randomInt(0,1000+1))// 处理结果（1：已处理；2：未处理）
				.processEmployeeId(RandomUtil.randomLong())// 处理人
				.processTime(new Date())// 处理时间
				.processType(RandomUtil.randomInt(0,1+1))// 处理方式（1：重新抄表；2：通过）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantMeterReadLogCurrent, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterReadLogCurrentService.save(tenantMeterReadLogCurrent);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantMeterReadLogCurrent tenantMeterReadLogCurrent = TenantMeterReadLogCurrent.builder()//
				.tenantId(RandomUtil.randomLong())// 租户ID
				.readMonth(new Date())// 结算月份
				.meterId(RandomUtil.randomLong())// 水表ID
				.meterYearTotalWatersBefore(new BigDecimal(0))// 结算前水表当年累计水量
				.settleStartTime(new Date())// 结算开始时间
				.settleStartPointer(new BigDecimal(0))// 结算开始指针
				.currentReadTime(new Date())// 本次抄表时间
				.currentReadPointer(new BigDecimal(0))// 本次抄表指针
				.readEmployeeId(RandomUtil.randomLong())// 抄表员ID
				.meterStatusId(RandomUtil.randomLong())// 表次抄表状况
				.settleWaters(new BigDecimal(0))// 应结算水量
				.receivableWaters(new BigDecimal(0))// 应收水量
				.readSource(RandomUtil.randomInt(0,1000+1))// 抄表来源（1：移动抄表；2：人工入账；3：远传表导入；4：远传表接口）
				.readStatus(RandomUtil.randomInt(0,1+1))// 抄表状态（0：未抄；1：已抄）
				.checkResult(RandomUtil.randomInt(0,1000+1))// 检查结果（0：正常；1：异常）
				.processReault(RandomUtil.randomInt(0,1000+1))// 处理结果（1：已处理；2：未处理）
				.processEmployeeId(RandomUtil.randomLong())// 处理人
				.processTime(new Date())// 处理时间
				.processType(RandomUtil.randomInt(0,1+1))// 处理方式（1：重新抄表；2：通过）
				.build();
		tenantMeterReadLogCurrent.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeterReadLogCurrent, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterReadLogCurrentService.updateById(tenantMeterReadLogCurrent);

		log.info(Boolean.toString(success));
	}
}
