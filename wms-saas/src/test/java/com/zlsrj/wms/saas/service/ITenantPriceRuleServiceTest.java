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
import com.zlsrj.wms.api.entity.TenantPriceRule;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantPriceRuleServiceTest {

	@Autowired
	private ITenantPriceRuleService tenantPriceRuleService;

	@Test
	public void insertTest() {
		TenantPriceRule tenantPriceRule = TenantPriceRule.builder()//
				.id(TestCaseUtil.id())// 
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.ruleName(TestCaseUtil.name())// 计费方法名称
				.ruleExplain(RandomUtil.randomString(4))// 计费方法说明
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPriceRule, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceRuleService.save(tenantPriceRule);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantPriceRule tenantPriceRule = TenantPriceRule.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.ruleName(TestCaseUtil.name())// 计费方法名称
				.ruleExplain(RandomUtil.randomString(4))// 计费方法说明
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantPriceRule.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPriceRule, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceRuleService.updateById(tenantPriceRule);

		log.info(Boolean.toString(success));
	}
}
