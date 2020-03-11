package com.zlsrj.wms.saas.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantWaterType;

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
				.id(TestCaseUtil.id())// 用水类别ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.waterTypeName(TestCaseUtil.name())// 用水类别名称
				.waterTypeParentId(RandomUtil.randomString(32))// 上级用水类别编号
				.defaultPriceTypeId(RandomUtil.randomString(32))// 默认价格分类ID
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantWaterType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantWaterTypeService.save(tenantWaterType);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantWaterType tenantWaterType = TenantWaterType.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.waterTypeName(TestCaseUtil.name())// 用水类别名称
				.waterTypeParentId(RandomUtil.randomString(32))// 上级用水类别编号
				.defaultPriceTypeId(RandomUtil.randomString(32))// 默认价格分类ID
				.build();
		tenantWaterType.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantWaterType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantWaterTypeService.updateById(tenantWaterType);

		log.info(Boolean.toString(success));
	}
}
