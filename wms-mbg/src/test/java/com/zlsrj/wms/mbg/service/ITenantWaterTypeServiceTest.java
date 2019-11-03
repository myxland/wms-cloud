package com.zlsrj.wms.mbg.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.mbg.entity.TenantWaterType;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantWaterTypeServiceTest {

	@Autowired
	private ITenantWaterTypeService tenantWaterTypeService;

	@Test
	public void insertTest() {
		TenantWaterType tenantWaterType = TenantWaterType.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.waterTypeName(TestCaseUtil.name())// 用水类别名称
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantWaterType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantWaterTypeService.save(tenantWaterType);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantWaterType tenantWaterType = TenantWaterType.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.waterTypeName(TestCaseUtil.name())// 用水类别名称
				.build();
		tenantWaterType.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantWaterType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantWaterTypeService.updateById(tenantWaterType);

		log.info(Boolean.toString(success));
	}
}
