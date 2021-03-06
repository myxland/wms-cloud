package com.zlsrj.wms.tenant.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantModuleServiceTest {

	@Autowired
	private ITenantModuleService tenantModuleService;

	@Test
	public void insertTest() {
		String companyShortName = TestCaseUtil.companyShortName();

		TenantModule tenantModule = TenantModule.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantModule, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantModuleService.save(tenantModule);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantModule tenantModule = TenantModule.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.build();
		tenantModule.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantModule, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantModuleService.updateById(tenantModule);

		log.info(Boolean.toString(success));
	}
}
