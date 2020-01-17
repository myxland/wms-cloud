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
import com.zlsrj.wms.api.entity.TenantCustomer;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCustomerServiceTest {

	@Autowired
	private ITenantCustomerService tenantCustomerService;

	@Test
	public void insertTest() {
		TenantCustomer tenantCustomer = TenantCustomer.builder()//
				.id(TestCaseUtil.id())// 
				.tenantId(RandomUtil.randomLong())// 租户ID
				.customerCode(RandomUtil.randomString(4))// 用户代码
				.customerName(TestCaseUtil.name())// 用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.customerTypeId(RandomUtil.randomLong())// 用户类别ID
				.customerRegisterTime(new Date())// 建档时间
				.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.customerPaymentMethod(RandomUtil.randomInt(0,1000+1))// 用户缴费方式（1：坐收；2：走收；3：代扣；4：托收）
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
				.customerBalanceMoney(new BigDecimal(0))// 用户预存余额
				.customerArrearsMoney(new BigDecimal(0))// 用户欠费金额
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantCustomer, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerService.save(tenantCustomer);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantCustomer tenantCustomer = TenantCustomer.builder()//
				.tenantId(RandomUtil.randomLong())// 租户ID
				.customerCode(RandomUtil.randomString(4))// 用户代码
				.customerName(TestCaseUtil.name())// 用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.customerTypeId(RandomUtil.randomLong())// 用户类别ID
				.customerRegisterTime(new Date())// 建档时间
				.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.customerPaymentMethod(RandomUtil.randomInt(0,1000+1))// 用户缴费方式（1：坐收；2：走收；3：代扣；4：托收）
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
				.customerBalanceMoney(new BigDecimal(0))// 用户预存余额
				.customerArrearsMoney(new BigDecimal(0))// 用户欠费金额
				.build();
		tenantCustomer.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCustomer, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerService.updateById(tenantCustomer);

		log.info(Boolean.toString(success));
	}
}
