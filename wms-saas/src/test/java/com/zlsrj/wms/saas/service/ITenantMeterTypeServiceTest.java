package com.zlsrj.wms.saas.service;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantMeterType;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterTypeServiceTest {

	@Autowired
	private ITenantMeterTypeService tenantMeterTypeService;

	@Test
	public void insertTest() {
		TenantMeterType tenantMeterType = TenantMeterType.builder()//
				.id(TestCaseUtil.id())// 水表类型ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.meterTypeName(TestCaseUtil.name())// 名称
				.meterTypeData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantMeterType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterTypeService.save(tenantMeterType);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantMeterType tenantMeterType = TenantMeterType.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.meterTypeName(TestCaseUtil.name())// 名称
				.meterTypeData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantMeterType.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeterType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterTypeService.updateById(tenantMeterType);

		log.info(Boolean.toString(success));
	}
}
