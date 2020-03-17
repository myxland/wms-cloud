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
import com.zlsrj.wms.api.entity.TenantMeterIndustry;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterIndustryServiceTest {

	@Autowired
	private ITenantMeterIndustryService tenantMeterIndustryService;

	@Test
	public void insertTest() {
		TenantMeterIndustry tenantMeterIndustry = TenantMeterIndustry.builder()//
				.id(TestCaseUtil.id())// 行业分类ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.meterIndustryName(TestCaseUtil.name())// 名称
				.meterIndustryParentId(RandomUtil.randomString(4))// 父级ID
				.meterIndustryData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantMeterIndustry, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterIndustryService.save(tenantMeterIndustry);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantMeterIndustry tenantMeterIndustry = TenantMeterIndustry.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.meterIndustryName(TestCaseUtil.name())// 名称
				.meterIndustryParentId(RandomUtil.randomString(4))// 父级ID
				.meterIndustryData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantMeterIndustry.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeterIndustry, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterIndustryService.updateById(tenantMeterIndustry);

		log.info(Boolean.toString(success));
	}
}
