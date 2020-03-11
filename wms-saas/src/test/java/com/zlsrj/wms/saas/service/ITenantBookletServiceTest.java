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
import com.zlsrj.wms.api.entity.TenantBooklet;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantBookletServiceTest {

	@Autowired
	private ITenantBookletService tenantBookletService;

	@Test
	public void insertTest() {
		TenantBooklet tenantBooklet = TenantBooklet.builder()//
				.id(TestCaseUtil.id())// 表册ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.bookletDepartmentId(RandomUtil.randomString(32))// 所属部门ID
				.bookletWaterAreaId(RandomUtil.randomString(32))// 所属供水区域ID
				.bookletCode(RandomUtil.randomString(4))// 表册代码
				.bookletName(TestCaseUtil.name())// 表册名称
				.bookletReadEmployeeId(RandomUtil.randomString(32))// 抄表员ID
				.bookletChargeEmployeeId(RandomUtil.randomString(32))// 收费员ID
				.bookletSettleCycleInterval(RandomUtil.randomInt(0,1000+1))// 结算间隔周期[月]
				.bookletLastSettleMonth(new Date())// 最后一次结算月份
				.bookletNextSettleMonth(new Date())// 下次计划结算月份
				.bookletStatus(RandomUtil.randomInt(0,1+1))// 表册状态（1：抄表进行中；2：抄表截止）
				.bookletOn(RandomUtil.randomInt(0,1+1))// 表册可用状态（1：可用；0：禁用）
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantBooklet, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantBookletService.save(tenantBooklet);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantBooklet tenantBooklet = TenantBooklet.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.bookletDepartmentId(RandomUtil.randomString(32))// 所属部门ID
				.bookletWaterAreaId(RandomUtil.randomString(32))// 所属供水区域ID
				.bookletCode(RandomUtil.randomString(4))// 表册代码
				.bookletName(TestCaseUtil.name())// 表册名称
				.bookletReadEmployeeId(RandomUtil.randomString(32))// 抄表员ID
				.bookletChargeEmployeeId(RandomUtil.randomString(32))// 收费员ID
				.bookletSettleCycleInterval(RandomUtil.randomInt(0,1000+1))// 结算间隔周期[月]
				.bookletLastSettleMonth(new Date())// 最后一次结算月份
				.bookletNextSettleMonth(new Date())// 下次计划结算月份
				.bookletStatus(RandomUtil.randomInt(0,1+1))// 表册状态（1：抄表进行中；2：抄表截止）
				.bookletOn(RandomUtil.randomInt(0,1+1))// 表册可用状态（1：可用；0：禁用）
				.build();
		tenantBooklet.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantBooklet, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantBookletService.updateById(tenantBooklet);

		log.info(Boolean.toString(success));
	}
}
