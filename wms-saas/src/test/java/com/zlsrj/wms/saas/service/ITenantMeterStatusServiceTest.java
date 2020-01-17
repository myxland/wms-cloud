package com.zlsrj.wms.saas.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantMeterStatus;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterStatusServiceTest {

	@Autowired
	private ITenantMeterStatusService tenantMeterStatusService;

	@Test
	public void insertTest() {
		TenantMeterStatus tenantMeterStatus = TenantMeterStatus.builder()//
				.id(TestCaseUtil.id())// 表况ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.meterStatusName(TestCaseUtil.name())// 表况名称
				.usenumCalcType(RandomUtil.randomInt(0,1+1))// 水量计算方式（1：自动计算；2：手工输入）
				.workBillType(RandomUtil.randomInt(0,1+1))// 生成工单类型（0：不生成；1：故障换表；3：周期换表）
				.createType(RandomUtil.randomInt(0,1+1))// 创建方式（1：平台创建；2：客户自建）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantMeterStatus, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterStatusService.save(tenantMeterStatus);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantMeterStatus tenantMeterStatus = TenantMeterStatus.builder()//
				.tenantId(RandomUtil.randomLong())// 租户ID
				.meterStatusName(TestCaseUtil.name())// 表况名称
				.usenumCalcType(RandomUtil.randomInt(0,1+1))// 水量计算方式（1：自动计算；2：手工输入）
				.workBillType(RandomUtil.randomInt(0,1+1))// 生成工单类型（0：不生成；1：故障换表；3：周期换表）
				.createType(RandomUtil.randomInt(0,1+1))// 创建方式（1：平台创建；2：客户自建）
				.build();
		tenantMeterStatus.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeterStatus, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterStatusService.updateById(tenantMeterStatus);

		log.info(Boolean.toString(success));
	}
}
