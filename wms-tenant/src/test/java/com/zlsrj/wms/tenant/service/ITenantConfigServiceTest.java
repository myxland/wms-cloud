package com.zlsrj.wms.tenant.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantConfig;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantConfigServiceTest {

	@Autowired
	private ITenantConfigService tenantConfigService;

	@Test
	public void insertTest() {
		TenantConfig tenantConfig = TenantConfig.builder()//
				.id(TestCaseUtil.id())// 租户编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.partChargeOn(RandomUtil.randomInt(0,1+1))// 是否启用部分缴费（1：启用；0：不启用）
				.overDuefineOn(RandomUtil.randomInt(0,1+1))// 是否启用违约金（1：启用；0：不启用）
				.overDuefineDay(RandomUtil.randomInt(0,1000+1))// 违约金宽限天数
				.overDuefineRatio(new BigDecimal(0))// 违约金每天收取比例
				.overDuefineTopRatio(new BigDecimal(0))// 违约金封顶比例（与欠费金额相比）
				.ycdkType(RandomUtil.randomInt(0,1+1))// 预存抵扣方式（1：抄表后即时抵扣；2：人工发起抵扣）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantConfig, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantConfigService.save(tenantConfig);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantConfig tenantConfig = TenantConfig.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.partChargeOn(RandomUtil.randomInt(0,1+1))// 是否启用部分缴费（1：启用；0：不启用）
				.overDuefineOn(RandomUtil.randomInt(0,1+1))// 是否启用违约金（1：启用；0：不启用）
				.overDuefineDay(RandomUtil.randomInt(0,1000+1))// 违约金宽限天数
				.overDuefineRatio(new BigDecimal(0))// 违约金每天收取比例
				.overDuefineTopRatio(new BigDecimal(0))// 违约金封顶比例（与欠费金额相比）
				.ycdkType(RandomUtil.randomInt(0,1+1))// 预存抵扣方式（1：抄表后即时抵扣；2：人工发起抵扣）
				.build();
		tenantConfig.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantConfig, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantConfigService.updateById(tenantConfig);

		log.info(Boolean.toString(success));
	}
}
