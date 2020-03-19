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
import com.zlsrj.wms.api.entity.TenantBusinessRules;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantBusinessRulesServiceTest {

	@Autowired
	private ITenantBusinessRulesService tenantBusinessRulesService;

	@Test
	public void insertTest() {
		TenantBusinessRules tenantBusinessRules = TenantBusinessRules.builder()//
				.id(TestCaseUtil.id())// 业务规则ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.rulesType("业务规则类型"+"-"+"测试"+"-"+RandomUtil.randomNumbers(4))// 业务规则类型
				.rulesData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantBusinessRules, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantBusinessRulesService.save(tenantBusinessRules);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantBusinessRules tenantBusinessRules = TenantBusinessRules.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.rulesType("业务规则类型"+"-"+"测试"+"-"+RandomUtil.randomNumbers(4))// 业务规则类型
				.rulesData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantBusinessRules.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantBusinessRules, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantBusinessRulesService.updateById(tenantBusinessRules);

		log.info(Boolean.toString(success));
	}
}
