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
import com.zlsrj.wms.api.entity.TenantMeter;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterServiceTest {

	@Autowired
	private ITenantMeterService tenantMeterService;

	@Test
	public void insertTest() {
		TenantMeter tenantMeter = TenantMeter.builder()//
				.id(TestCaseUtil.id())// 水表ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.customerId(RandomUtil.randomString(32))// 用户ID
				.meterBookletId(RandomUtil.randomString(32))// 水表表册ID
				.meterParentId(RandomUtil.randomString(32))// 上级水表ID
				.meterCode(RandomUtil.randomString(4))// 水表编号
				.meterAddress(TestCaseUtil.address())// 水表地址
				.meterPeoples(RandomUtil.randomInt(0,1000+1))// 家庭人数
				.meterMachineCode(RandomUtil.randomString(4))// 表身号码[钢印号等]
				.meterUseType(RandomUtil.randomInt(0,1+1))// 用途（1：水费结算；2：水量考核）
				.meterManufactorId(RandomUtil.randomString(32))// 厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomString(32))// 水表口径ID
				.meterWaterTypeId(RandomUtil.randomString(32))// 用水分类ID
				.priceTypeId(RandomUtil.randomString(32))// 价格分类ID
				.meterIotCode(RandomUtil.randomString(4))// 采集系统代码
				.meterInstallDate(new Date())// 水表安装日期
				.meterRegisterTime(new Date())// 水表建档日期
				.meterStatus(RandomUtil.randomInt(0,1+1))// 水表状态（1：正常；2：暂停；3：拆表）
				.meterYearTotalWaters(new BigDecimal(0))// 年累计用水量
				.meterLastSettleTime(new Date())// 最后一次结算日期
				.meterLastSettlePointer(new BigDecimal(0))// 最后一次结算指针
				.meterArrearsMoney(new BigDecimal(0))// 水表欠费总金额
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantMeter, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterService.save(tenantMeter);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantMeter tenantMeter = TenantMeter.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.customerId(RandomUtil.randomString(32))// 用户ID
				.meterBookletId(RandomUtil.randomString(32))// 水表表册ID
				.meterParentId(RandomUtil.randomString(32))// 上级水表ID
				.meterCode(RandomUtil.randomString(4))// 水表编号
				.meterAddress(TestCaseUtil.address())// 水表地址
				.meterPeoples(RandomUtil.randomInt(0,1000+1))// 家庭人数
				.meterMachineCode(RandomUtil.randomString(4))// 表身号码[钢印号等]
				.meterUseType(RandomUtil.randomInt(0,1+1))// 用途（1：水费结算；2：水量考核）
				.meterManufactorId(RandomUtil.randomString(32))// 厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomString(32))// 水表口径ID
				.meterWaterTypeId(RandomUtil.randomString(32))// 用水分类ID
				.priceTypeId(RandomUtil.randomString(32))// 价格分类ID
				.meterIotCode(RandomUtil.randomString(4))// 采集系统代码
				.meterInstallDate(new Date())// 水表安装日期
				.meterRegisterTime(new Date())// 水表建档日期
				.meterStatus(RandomUtil.randomInt(0,1+1))// 水表状态（1：正常；2：暂停；3：拆表）
				.meterYearTotalWaters(new BigDecimal(0))// 年累计用水量
				.meterLastSettleTime(new Date())// 最后一次结算日期
				.meterLastSettlePointer(new BigDecimal(0))// 最后一次结算指针
				.meterArrearsMoney(new BigDecimal(0))// 水表欠费总金额
				.build();
		tenantMeter.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeter, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterService.updateById(tenantMeter);

		log.info(Boolean.toString(success));
	}
}
