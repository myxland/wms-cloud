package com.zlsrj.wms.saas.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantCaliber;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCaliberServiceTest {

	@Autowired
	private ITenantCaliberService tenantCaliberService;

	@Test
	public void insertTest() {
		TenantCaliber tenantCaliber = TenantCaliber.builder()//
				.id(TestCaseUtil.id())// 口径ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.caliberName(TestCaseUtil.name())// 口径名称
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantCaliber, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCaliberService.save(tenantCaliber);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantCaliber tenantCaliber = TenantCaliber.builder()//
				.tenantId(RandomUtil.randomLong())// 租户ID
				.caliberName(TestCaseUtil.name())// 口径名称
				.build();
		tenantCaliber.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCaliber, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCaliberService.updateById(tenantCaliber);

		log.info(Boolean.toString(success));
	}
}
