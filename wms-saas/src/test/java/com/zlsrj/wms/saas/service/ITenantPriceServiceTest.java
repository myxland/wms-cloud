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
import com.zlsrj.wms.api.entity.TenantPrice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantPriceServiceTest {

	@Autowired
	private ITenantPriceService tenantPriceService;

	@Test
	public void insertTest() {
		TenantPrice tenantPrice = TenantPrice.builder()//
				.id(TestCaseUtil.id())// 
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.priceOrder(RandomUtil.randomInt(0,1000+1))// 排序
				.priceName(TestCaseUtil.name())// 水价名称
				.priceParentId(RandomUtil.randomString(4))// 父级ID
				.priceVersion(RandomUtil.randomString(4))// 水价版本
				.priceVersionMemo(RandomUtil.randomString(4))// 版本说明
				.marketingAreaId(RandomUtil.randomString(4))// 营销区域
				.priceMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPrice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceService.save(tenantPrice);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantPrice tenantPrice = TenantPrice.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.priceOrder(RandomUtil.randomInt(0,1000+1))// 排序
				.priceName(TestCaseUtil.name())// 水价名称
				.priceParentId(RandomUtil.randomString(4))// 父级ID
				.priceVersion(RandomUtil.randomString(4))// 水价版本
				.priceVersionMemo(RandomUtil.randomString(4))// 版本说明
				.marketingAreaId(RandomUtil.randomString(4))// 营销区域
				.priceMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantPrice.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPrice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceService.updateById(tenantPrice);

		log.info(Boolean.toString(success));
	}
}
