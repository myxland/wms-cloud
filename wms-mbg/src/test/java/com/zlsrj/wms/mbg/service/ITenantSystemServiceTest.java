package com.zlsrj.wms.mbg.service;

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
import com.zlsrj.wms.api.entity.TenantSystem;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantSystemServiceTest {

	@Autowired
	private ITenantSystemService tenantSystemService;

	@Test
	public void insertTest() {
		TenantSystem tenantSystem = TenantSystem.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysDispName(TestCaseUtil.name())// 模块显示名称
				.sysOrder(RandomUtil.randomInt(0,1000+1))// 模块排序
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（基础版/高级版/旗舰版）
				.sysStatus(RandomUtil.randomInt(0,1+1))// 模块状态（1开通/0未开通）
				.sysPriceType(RandomUtil.randomInt(0,1+1))// 价格体系（标准价格/指定价格）
				.sysOpenDate(new Date())// 开通时间
				.sysEndDate(new Date())// 到期时间
				.sysStartChargeDate(new Date())// 开始计费日期
				.sysArrears(new BigDecimal(0))// 欠费金额
				.sysOverdraft(new BigDecimal(0))// 透支额度
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantSystem, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantSystemService.save(tenantSystem);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantSystem tenantSystem = TenantSystem.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysDispName(TestCaseUtil.name())// 模块显示名称
				.sysOrder(RandomUtil.randomInt(0,1000+1))// 模块排序
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（基础版/高级版/旗舰版）
				.sysStatus(RandomUtil.randomInt(0,1+1))// 模块状态（1开通/0未开通）
				.sysPriceType(RandomUtil.randomInt(0,1+1))// 价格体系（标准价格/指定价格）
				.sysOpenDate(new Date())// 开通时间
				.sysEndDate(new Date())// 到期时间
				.sysStartChargeDate(new Date())// 开始计费日期
				.sysArrears(new BigDecimal(0))// 欠费金额
				.sysOverdraft(new BigDecimal(0))// 透支额度
				.build();
		tenantSystem.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantSystem, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantSystemService.updateById(tenantSystem);

		log.info(Boolean.toString(success));
	}
}
