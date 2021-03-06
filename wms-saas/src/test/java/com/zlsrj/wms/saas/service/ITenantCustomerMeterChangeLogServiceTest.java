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
import com.zlsrj.wms.api.entity.TenantCustomerMeterChangeLog;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCustomerMeterChangeLogServiceTest {

	@Autowired
	private ITenantCustomerMeterChangeLogService tenantCustomerMeterChangeLogService;

	@Test
	public void insertTest() {
		TenantCustomerMeterChangeLog tenantCustomerMeterChangeLog = TenantCustomerMeterChangeLog.builder()//
				.id(TestCaseUtil.id())// 变更日志ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.customerId(RandomUtil.randomLong())// 用户ID
				.csutomerIdNew(RandomUtil.randomLong())// 新用户ID
				.customerName(TestCaseUtil.name())// 用户名称
				.customerNameNew(RandomUtil.randomString(4))// 新用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.customerAddressNew(RandomUtil.randomString(4))// 新用户地址
				.customerTypeId(RandomUtil.randomLong())// 用户类别ID
				.customerTypeIdNew(RandomUtil.randomLong())// 新用户类别ID
				.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.customerStatusNew(RandomUtil.randomInt(0,1000+1))// 新用户状态（1：正常；2：暂停；3：消户）
				.customerPaymentMethod(RandomUtil.randomInt(0,1000+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.customerPaymentMethodNew(RandomUtil.randomInt(0,1000+1))// 新收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceTypeNew(RandomUtil.randomInt(0,1000+1))// 新开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceNameNew(RandomUtil.randomString(4))// 新开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceTaxNoNew(RandomUtil.randomString(4))// 新税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceAddressNew(RandomUtil.randomString(4))// 新开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceTelNew(RandomUtil.randomString(4))// 新开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankCodeNew(RandomUtil.randomString(4))// 新开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankNameNew(RandomUtil.randomString(4))// 新开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
				.invoiceBankAccountNoNew(RandomUtil.randomString(4))// 新开户行账号
				.meterId(RandomUtil.randomLong())// 水表ID
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.priceTypeIdNew(RandomUtil.randomLong())// 新价格分类ID
				.meterLastSettlePointer(new BigDecimal(0))// 水表止码
				.meterLastSettlePointerNew(new BigDecimal(0))// 新水表止码
				.manufactorId(RandomUtil.randomLong())// 水表厂商ID
				.manufactorIdNew(RandomUtil.randomLong())// 新水表厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.meterTypeNew(RandomUtil.randomInt(0,1000+1))// 新水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomLong())// 水表口径ID
				.caliberIdNew(RandomUtil.randomLong())// 新水表口径ID
				.meterMachineCode(RandomUtil.randomString(4))// 水表表身码
				.meterMachineCodeNew(RandomUtil.randomString(4))// 新水表表身码
				.meterIotCode(RandomUtil.randomString(4))// 远传系统编号
				.meterIotCodeNew(RandomUtil.randomString(4))// 新远传系统编号
				.meterPeoples(RandomUtil.randomInt(0,1000+1))// 人口数
				.meterPeoplesNew(RandomUtil.randomInt(0,1000+1))// 新人口数
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantCustomerMeterChangeLog, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerMeterChangeLogService.save(tenantCustomerMeterChangeLog);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantCustomerMeterChangeLog tenantCustomerMeterChangeLog = TenantCustomerMeterChangeLog.builder()//
				.tenantId(RandomUtil.randomLong())// 租户ID
				.customerId(RandomUtil.randomLong())// 用户ID
				.csutomerIdNew(RandomUtil.randomLong())// 新用户ID
				.customerName(TestCaseUtil.name())// 用户名称
				.customerNameNew(RandomUtil.randomString(4))// 新用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.customerAddressNew(RandomUtil.randomString(4))// 新用户地址
				.customerTypeId(RandomUtil.randomLong())// 用户类别ID
				.customerTypeIdNew(RandomUtil.randomLong())// 新用户类别ID
				.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.customerStatusNew(RandomUtil.randomInt(0,1000+1))// 新用户状态（1：正常；2：暂停；3：消户）
				.customerPaymentMethod(RandomUtil.randomInt(0,1000+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.customerPaymentMethodNew(RandomUtil.randomInt(0,1000+1))// 新收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceTypeNew(RandomUtil.randomInt(0,1000+1))// 新开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceNameNew(RandomUtil.randomString(4))// 新开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceTaxNoNew(RandomUtil.randomString(4))// 新税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceAddressNew(RandomUtil.randomString(4))// 新开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceTelNew(RandomUtil.randomString(4))// 新开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankCodeNew(RandomUtil.randomString(4))// 新开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankNameNew(RandomUtil.randomString(4))// 新开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
				.invoiceBankAccountNoNew(RandomUtil.randomString(4))// 新开户行账号
				.meterId(RandomUtil.randomLong())// 水表ID
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.priceTypeIdNew(RandomUtil.randomLong())// 新价格分类ID
				.meterLastSettlePointer(new BigDecimal(0))// 水表止码
				.meterLastSettlePointerNew(new BigDecimal(0))// 新水表止码
				.manufactorId(RandomUtil.randomLong())// 水表厂商ID
				.manufactorIdNew(RandomUtil.randomLong())// 新水表厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.meterTypeNew(RandomUtil.randomInt(0,1000+1))// 新水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomLong())// 水表口径ID
				.caliberIdNew(RandomUtil.randomLong())// 新水表口径ID
				.meterMachineCode(RandomUtil.randomString(4))// 水表表身码
				.meterMachineCodeNew(RandomUtil.randomString(4))// 新水表表身码
				.meterIotCode(RandomUtil.randomString(4))// 远传系统编号
				.meterIotCodeNew(RandomUtil.randomString(4))// 新远传系统编号
				.meterPeoples(RandomUtil.randomInt(0,1000+1))// 人口数
				.meterPeoplesNew(RandomUtil.randomInt(0,1000+1))// 新人口数
				.build();
		tenantCustomerMeterChangeLog.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCustomerMeterChangeLog, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerMeterChangeLogService.updateById(tenantCustomerMeterChangeLog);

		log.info(Boolean.toString(success));
	}
}
