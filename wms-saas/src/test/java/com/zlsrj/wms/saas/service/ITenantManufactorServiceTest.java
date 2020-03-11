package com.zlsrj.wms.saas.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantManufactor;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantManufactorServiceTest {

	@Autowired
	private ITenantManufactorService tenantManufactorService;

	@Test
	public void insertTest() {
		TenantManufactor tenantManufactor = TenantManufactor.builder()//
				.id(TestCaseUtil.id())// 制造商ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.manufactorName(TestCaseUtil.name())// 制造商名称
				.manufactorApikey(RandomUtil.randomString(4))// 远传表接入APIKEY
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantManufactor, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantManufactorService.save(tenantManufactor);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantManufactor tenantManufactor = TenantManufactor.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.manufactorName(TestCaseUtil.name())// 制造商名称
				.manufactorApikey(RandomUtil.randomString(4))// 远传表接入APIKEY
				.build();
		tenantManufactor.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantManufactor, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantManufactorService.updateById(tenantManufactor);

		log.info(Boolean.toString(success));
	}
}
