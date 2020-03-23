package com.zlsrj.wms.saas.service;

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
import com.zlsrj.wms.api.entity.TenantPriceDetail;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantPriceDetailServiceTest {

	@Autowired
	private ITenantPriceDetailService tenantPriceDetailService;

	@Test
	public void insertTest() {
		TenantPriceDetail tenantPriceDetail = TenantPriceDetail.builder()//
				.id(TestCaseUtil.id())// 水价明细ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.priceId(RandomUtil.randomString(4))// 水表列表ID
				.priceItemId(RandomUtil.randomString(4))// 费用项目
				.priceRule(1)// 计费规则
				.detailPrice(new BigDecimal(0))// 单价
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPriceDetail, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceDetailService.save(tenantPriceDetail);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantPriceDetail tenantPriceDetail = TenantPriceDetail.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.priceId(RandomUtil.randomString(4))// 水表列表ID
				.priceItemId(RandomUtil.randomString(4))// 费用项目
				.priceRule(1)// 计费规则
				.detailPrice(new BigDecimal(0))// 单价
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantPriceDetail.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPriceDetail, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceDetailService.updateById(tenantPriceDetail);

		log.info(Boolean.toString(success));
	}
}
