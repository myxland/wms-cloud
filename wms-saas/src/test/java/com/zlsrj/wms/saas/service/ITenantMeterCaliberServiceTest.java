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
import com.zlsrj.wms.api.entity.TenantMeterCaliber;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterCaliberServiceTest {

	@Autowired
	private ITenantMeterCaliberService tenantMeterCaliberService;

	@Test
	public void insertTest() {
		TenantMeterCaliber tenantMeterCaliber = TenantMeterCaliber.builder()//
				.id(TestCaseUtil.id())// 
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.meterCaliberName(TestCaseUtil.name())// 名称
				.meterCaliberData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantMeterCaliber, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterCaliberService.save(tenantMeterCaliber);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantMeterCaliber tenantMeterCaliber = TenantMeterCaliber.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.meterCaliberName(TestCaseUtil.name())// 名称
				.meterCaliberData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantMeterCaliber.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeterCaliber, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterCaliberService.updateById(tenantMeterCaliber);

		log.info(Boolean.toString(success));
	}
}
