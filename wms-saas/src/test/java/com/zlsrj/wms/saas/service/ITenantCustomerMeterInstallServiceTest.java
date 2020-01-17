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
import com.zlsrj.wms.api.entity.TenantCustomerMeterInstall;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCustomerMeterInstallServiceTest {

	@Autowired
	private ITenantCustomerMeterInstallService tenantCustomerMeterInstallService;

	@Test
	public void insertTest() {
		TenantCustomerMeterInstall tenantCustomerMeterInstall = TenantCustomerMeterInstall.builder()//
				.id(TestCaseUtil.id())// 水表立户ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.meterId(RandomUtil.randomLong())// 已经立户的水表ID
				.meterCode(RandomUtil.randomString(4))// 已经立户的水表代码
				.custName(TestCaseUtil.name())// 用户名称
				.meterAddress(TestCaseUtil.address())// 水表地址
				.meterMachineCode(RandomUtil.randomString(4))// 表身号码[钢印号等]
				.manufactorId(RandomUtil.randomLong())// 厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomLong())// 水表口径ID
				.waterTypeId(RandomUtil.randomLong())// 用水分类ID
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.meterIotCode(RandomUtil.randomString(4))// 采集系统编号
				.meterInstallDate(new Date())// 水表安装日期
				.meterLastSettleTime(new Date())// 最后一次结算时间
				.meterLastSettlePointer(new BigDecimal(0))// 最后一次结算指针
				.linkmanMobile(TestCaseUtil.mobile())// 联系人手机号码
				.custmerIdNo(RandomUtil.randomString(4))// 用户身份证编号
				.oldCode(RandomUtil.randomString(4))// 老系统编号
				.inputType(RandomUtil.randomInt(0,1+1))// 录入方式（1：手工录入；2：文件导入）
				.inputTime(new Date())// 录入日期
				.createOn(RandomUtil.randomInt(0,1+1))// 是否已经立户（1：是；0：否）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantCustomerMeterInstall, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerMeterInstallService.save(tenantCustomerMeterInstall);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantCustomerMeterInstall tenantCustomerMeterInstall = TenantCustomerMeterInstall.builder()//
				.tenantId(RandomUtil.randomLong())// 租户ID
				.meterId(RandomUtil.randomLong())// 已经立户的水表ID
				.meterCode(RandomUtil.randomString(4))// 已经立户的水表代码
				.custName(TestCaseUtil.name())// 用户名称
				.meterAddress(TestCaseUtil.address())// 水表地址
				.meterMachineCode(RandomUtil.randomString(4))// 表身号码[钢印号等]
				.manufactorId(RandomUtil.randomLong())// 厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomLong())// 水表口径ID
				.waterTypeId(RandomUtil.randomLong())// 用水分类ID
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.meterIotCode(RandomUtil.randomString(4))// 采集系统编号
				.meterInstallDate(new Date())// 水表安装日期
				.meterLastSettleTime(new Date())// 最后一次结算时间
				.meterLastSettlePointer(new BigDecimal(0))// 最后一次结算指针
				.linkmanMobile(TestCaseUtil.mobile())// 联系人手机号码
				.custmerIdNo(RandomUtil.randomString(4))// 用户身份证编号
				.oldCode(RandomUtil.randomString(4))// 老系统编号
				.inputType(RandomUtil.randomInt(0,1+1))// 录入方式（1：手工录入；2：文件导入）
				.inputTime(new Date())// 录入日期
				.createOn(RandomUtil.randomInt(0,1+1))// 是否已经立户（1：是；0：否）
				.build();
		tenantCustomerMeterInstall.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCustomerMeterInstall, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerMeterInstallService.updateById(tenantCustomerMeterInstall);

		log.info(Boolean.toString(success));
	}
}
