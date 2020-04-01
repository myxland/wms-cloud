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
import com.zlsrj.wms.api.entity.TenantCustomerInvoice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCustomerInvoiceServiceTest {

	@Autowired
	private ITenantCustomerInvoiceService tenantCustomerInvoiceService;

	@Test
	public void insertTest() {
		TenantCustomerInvoice tenantCustomerInvoice = TenantCustomerInvoice.builder()//
				.id(TestCaseUtil.id())// 用户开票ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类型（1：增值税普通纸质发票；2：增值税普通电子发票；3：增值税专用纸质发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 开票税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBank(RandomUtil.randomString(4))// 开户银行
				.invoiceAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.invoiceMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantCustomerInvoice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerInvoiceService.save(tenantCustomerInvoice);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantCustomerInvoice tenantCustomerInvoice = TenantCustomerInvoice.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类型（1：增值税普通纸质发票；2：增值税普通电子发票；3：增值税专用纸质发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 开票税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBank(RandomUtil.randomString(4))// 开户银行
				.invoiceAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.invoiceMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantCustomerInvoice.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCustomerInvoice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerInvoiceService.updateById(tenantCustomerInvoice);

		log.info(Boolean.toString(success));
	}
}
