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
import com.zlsrj.wms.api.entity.TenantModule;

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
		TenantModule tenantModule = TenantModule.builder()//
				.id(TestCaseUtil.id())// 租户模块ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.moduleId(RandomUtil.randomLong())// 模块ID
				.moduleEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（1：基础版；2：高级版；3：旗舰版）
				.moduleOpenTime(new Date())// 开通时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantModule, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantModuleService.save(tenantModule);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantModule tenantModule = TenantModule.builder()//
				.id(TestCaseUtil.id())// 租户模块ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.moduleId(RandomUtil.randomLong())// 模块ID
				.moduleEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（1：基础版；2：高级版；3：旗舰版）
				.moduleOpenTime(new Date())// 开通时间
				.build();
		tenantModule.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantModule, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantModuleService.updateById(tenantModule);

		log.info(Boolean.toString(success));
	}
}
