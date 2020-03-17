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
import com.zlsrj.wms.api.entity.TenantMeterSupplyArea;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterSupplyAreaServiceTest {

	@Autowired
	private ITenantMeterSupplyAreaService tenantMeterSupplyAreaService;

	@Test
	public void insertTest() {
		TenantMeterSupplyArea tenantMeterSupplyArea = TenantMeterSupplyArea.builder()//
				.id(TestCaseUtil.id())// 供水区域ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.supplyAreaName(TestCaseUtil.name())// 名称
				.supplyAreaParentId(RandomUtil.randomString(4))// 父级ID
				.supplyAreaData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantMeterSupplyArea, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterSupplyAreaService.save(tenantMeterSupplyArea);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantMeterSupplyArea tenantMeterSupplyArea = TenantMeterSupplyArea.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.supplyAreaName(TestCaseUtil.name())// 名称
				.supplyAreaParentId(RandomUtil.randomString(4))// 父级ID
				.supplyAreaData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantMeterSupplyArea.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeterSupplyArea, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterSupplyAreaService.updateById(tenantMeterSupplyArea);

		log.info(Boolean.toString(success));
	}
}
