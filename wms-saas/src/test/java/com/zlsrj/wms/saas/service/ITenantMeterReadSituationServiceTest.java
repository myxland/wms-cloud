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
import com.zlsrj.wms.api.entity.TenantMeterReadSituation;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterReadSituationServiceTest {

	@Autowired
	private ITenantMeterReadSituationService tenantMeterReadSituationService;

	@Test
	public void insertTest() {
		TenantMeterReadSituation tenantMeterReadSituation = TenantMeterReadSituation.builder()//
				.id(TestCaseUtil.id())// 抄表表况ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.readSituationName(TestCaseUtil.name())// 名称
				.readSituationData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantMeterReadSituation, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterReadSituationService.save(tenantMeterReadSituation);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantMeterReadSituation tenantMeterReadSituation = TenantMeterReadSituation.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.readSituationName(TestCaseUtil.name())// 名称
				.readSituationData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantMeterReadSituation.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeterReadSituation, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterReadSituationService.updateById(tenantMeterReadSituation);

		log.info(Boolean.toString(success));
	}
}
