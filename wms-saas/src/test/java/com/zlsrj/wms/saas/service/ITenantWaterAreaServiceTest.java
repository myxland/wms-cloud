package com.zlsrj.wms.saas.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantWaterArea;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantWaterAreaServiceTest {

	@Autowired
	private ITenantWaterAreaService tenantWaterAreaService;

	@Test
	public void insertTest() {
		TenantWaterArea tenantWaterArea = TenantWaterArea.builder()//
				.id(TestCaseUtil.id())// 供水区域ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.waterAreaName(TestCaseUtil.name())// 供水区域名称
				.waterAreaParentId(RandomUtil.randomLong())// 上级供水区域ID
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantWaterArea, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantWaterAreaService.save(tenantWaterArea);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantWaterArea tenantWaterArea = TenantWaterArea.builder()//
				.tenantId(RandomUtil.randomLong())// 租户ID
				.waterAreaName(TestCaseUtil.name())// 供水区域名称
				.waterAreaParentId(RandomUtil.randomLong())// 上级供水区域ID
				.build();
		tenantWaterArea.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantWaterArea, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantWaterAreaService.updateById(tenantWaterArea);

		log.info(Boolean.toString(success));
	}
}
