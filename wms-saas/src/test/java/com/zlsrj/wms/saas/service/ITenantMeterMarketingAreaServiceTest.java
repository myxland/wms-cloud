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
import com.zlsrj.wms.api.entity.TenantMeterMarketingArea;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterMarketingAreaServiceTest {

	@Autowired
	private ITenantMeterMarketingAreaService tenantMeterMarketingAreaService;

	@Test
	public void insertTest() {
		TenantMeterMarketingArea tenantMeterMarketingArea = TenantMeterMarketingArea.builder()//
				.id(TestCaseUtil.id())// 营销机构ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.marketingAreaType(RandomUtil.randomInt(0,1+1))// 区域类型（0：内部机构；1：代收机构）
				.marketingAreaName(TestCaseUtil.name())// 名称
				.marketingAreaParentId(RandomUtil.randomString(4))// 父级ID
				.marketingAreaData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantMeterMarketingArea, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterMarketingAreaService.save(tenantMeterMarketingArea);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantMeterMarketingArea tenantMeterMarketingArea = TenantMeterMarketingArea.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.marketingAreaType(RandomUtil.randomInt(0,1+1))// 区域类型（0：内部机构；1：代收机构）
				.marketingAreaName(TestCaseUtil.name())// 名称
				.marketingAreaParentId(RandomUtil.randomString(4))// 父级ID
				.marketingAreaData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantMeterMarketingArea.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeterMarketingArea, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterMarketingAreaService.updateById(tenantMeterMarketingArea);

		log.info(Boolean.toString(success));
	}
}
