package com.zlsrj.wms.tenant.service;

import java.math.BigDecimal;
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
		String companyShortName = TestCaseUtil.companyShortName();

		TenantModule tenantModule = TenantModule.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.moduleDisplayName(companyShortName)// 模块显示名称
				.moduleOrder(RandomUtil.randomInt(0,1000+1))// 模块排序
				.moduleEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（1：基础版；2：高级版；3：旗舰版）
				.moduleStatus(RandomUtil.randomInt(0,1+1))// 模块状态（1：开通；0：未开通）
				.modulePriceType(RandomUtil.randomInt(0,1+1))// 价格体系（1：标准价格；2：指定价格）
				.moduleOpenDate(new Date())// 开通时间
				.moduleEndDate(new Date())// 到期时间
				.moduleStartChargeDate(new Date())// 开始计费日期
				.moduleArrears(new BigDecimal(0))// 欠费金额
				.moduleOverdraft(new BigDecimal(0))// 透支额度
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantModule, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantModuleService.save(tenantModule);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		String companyShortName = TestCaseUtil.companyShortName();

		TenantModule tenantModule = TenantModule.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.moduleDisplayName(companyShortName)// 模块显示名称
				.moduleOrder(RandomUtil.randomInt(0,1000+1))// 模块排序
				.moduleEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（1：基础版；2：高级版；3：旗舰版）
				.moduleStatus(RandomUtil.randomInt(0,1+1))// 模块状态（1：开通；0：未开通）
				.modulePriceType(RandomUtil.randomInt(0,1+1))// 价格体系（1：标准价格；2：指定价格）
				.moduleOpenDate(new Date())// 开通时间
				.moduleEndDate(new Date())// 到期时间
				.moduleStartChargeDate(new Date())// 开始计费日期
				.moduleArrears(new BigDecimal(0))// 欠费金额
				.moduleOverdraft(new BigDecimal(0))// 透支额度
				.build();
		tenantModule.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantModule, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantModuleService.updateById(tenantModule);

		log.info(Boolean.toString(success));
	}
}
