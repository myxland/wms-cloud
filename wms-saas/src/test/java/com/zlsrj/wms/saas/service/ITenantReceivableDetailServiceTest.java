package com.zlsrj.wms.saas.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantReceivableDetail;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantReceivableDetailServiceTest {

	@Autowired
	private ITenantReceivableDetailService tenantReceivableDetailService;

	@Test
	public void insertTest() {
		TenantReceivableDetail tenantReceivableDetail = TenantReceivableDetail.builder()//
				.id(TestCaseUtil.id())// 应收明细账ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.receivableId(RandomUtil.randomString(32))// 应收总账ID
				.priceStepId(RandomUtil.randomString(32))// 价格阶梯ID
				.receivableWaters(new BigDecimal(0))// 应收水量
				.arrearsWaters(new BigDecimal(0))// 欠费水量
				.priceItemId(RandomUtil.randomString(32))// 费用项目ID
				.detailPrice(new BigDecimal(0))// 价格
				.receivableMoney(new BigDecimal(0))// 应收金额
				.arrearsMoney(new BigDecimal(0))// 欠费金额
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantReceivableDetail, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantReceivableDetailService.save(tenantReceivableDetail);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantReceivableDetail tenantReceivableDetail = TenantReceivableDetail.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.receivableId(RandomUtil.randomString(32))// 应收总账ID
				.priceStepId(RandomUtil.randomString(32))// 价格阶梯ID
				.receivableWaters(new BigDecimal(0))// 应收水量
				.arrearsWaters(new BigDecimal(0))// 欠费水量
				.priceItemId(RandomUtil.randomString(32))// 费用项目ID
				.detailPrice(new BigDecimal(0))// 价格
				.receivableMoney(new BigDecimal(0))// 应收金额
				.arrearsMoney(new BigDecimal(0))// 欠费金额
				.build();
		tenantReceivableDetail.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantReceivableDetail, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantReceivableDetailService.updateById(tenantReceivableDetail);

		log.info(Boolean.toString(success));
	}
}
