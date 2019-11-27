package com.zlsrj.wms.tenant.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantInvoice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantInvoiceServiceTest {

	@Autowired
	private ITenantInvoiceService tenantInvoiceService;

	@Test
	public void insertTest() {
		TenantInvoice tenantInvoice = TenantInvoice.builder()//
				.id(TestCaseUtil.id())// 租户编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.creditNumber(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.bankNo(TestCaseUtil.bankNo())// 开户行行号
				.bankName(TestCaseUtil.bankName())// 开户行名称
				.accountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantInvoice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantInvoiceService.save(tenantInvoice);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantInvoice tenantInvoice = TenantInvoice.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.creditNumber(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.bankNo(TestCaseUtil.bankNo())// 开户行行号
				.bankName(TestCaseUtil.bankName())// 开户行名称
				.accountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.build();
		tenantInvoice.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantInvoice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantInvoiceService.updateById(tenantInvoice);

		log.info(Boolean.toString(success));
	}
}
