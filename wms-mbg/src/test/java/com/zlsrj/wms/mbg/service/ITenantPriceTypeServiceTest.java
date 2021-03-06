package com.zlsrj.wms.mbg.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantPriceType;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantPriceTypeServiceTest {

	@Autowired
	private ITenantPriceTypeService tenantPriceTypeService;

	@Test
	public void insertTest() {
		TenantPriceType tenantPriceType = TenantPriceType.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceTypeName(TestCaseUtil.name())// 价格类别名称
				.bottomOn(RandomUtil.randomInt(0,1+1))// 启用保底水量（1：启用；0：不启用）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPriceType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceTypeService.save(tenantPriceType);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantPriceType tenantPriceType = TenantPriceType.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceTypeName(TestCaseUtil.name())// 价格类别名称
				.bottomOn(RandomUtil.randomInt(0,1+1))// 启用保底水量（1：启用；0：不启用）
				.build();
		tenantPriceType.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPriceType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceTypeService.updateById(tenantPriceType);

		log.info(Boolean.toString(success));
	}
}
