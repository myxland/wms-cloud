package com.zlsrj.wms.cust.service;

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
import com.zlsrj.wms.api.entity.CustInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ICustInfoServiceTest {

	@Autowired
	private ICustInfoService custInfoService;

	@Test
	public void insertTest() {
		CustInfo custInfo = CustInfo.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custNo(RandomUtil.randomString(4))// 用户编号
				.custName(TestCaseUtil.name())// 用户名称
				.custAddress(TestCaseUtil.address())// 用户地址
				.custTypeId(RandomUtil.randomLong())// 用户类别编号
				.custRegistDate(new Date())// 立户日期
				.custStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.payType(RandomUtil.randomInt(0,1+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.billType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.billName(TestCaseUtil.name())// 开票名称
				.billTaxnum(RandomUtil.randomString(4))// 税号
				.billAddress(TestCaseUtil.address())// 开票地址
				.billTel(TestCaseUtil.tel())// 开票电话
				.billBank(RandomUtil.randomString(4))// 银行名称
				.billBankName(TestCaseUtil.bankName())// 开户行名称
				.billBankAccount(RandomUtil.randomString(4))// 开户行账号
				.billBankId(RandomUtil.randomString(4))// 开户行号
				.saveMoney(new BigDecimal(0))// 预存余额
				.dueMoney(new BigDecimal(0))// 欠费金额
				.build();

		log.info(ToStringBuilder.reflectionToString(custInfo, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = custInfoService.save(custInfo);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		CustInfo custInfo = CustInfo.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custNo(RandomUtil.randomString(4))// 用户编号
				.custName(TestCaseUtil.name())// 用户名称
				.custAddress(TestCaseUtil.address())// 用户地址
				.custTypeId(RandomUtil.randomLong())// 用户类别编号
				.custRegistDate(new Date())// 立户日期
				.custStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.payType(RandomUtil.randomInt(0,1+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.billType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.billName(TestCaseUtil.name())// 开票名称
				.billTaxnum(RandomUtil.randomString(4))// 税号
				.billAddress(TestCaseUtil.address())// 开票地址
				.billTel(TestCaseUtil.tel())// 开票电话
				.billBank(RandomUtil.randomString(4))// 银行名称
				.billBankName(TestCaseUtil.bankName())// 开户行名称
				.billBankAccount(RandomUtil.randomString(4))// 开户行账号
				.billBankId(RandomUtil.randomString(4))// 开户行号
				.saveMoney(new BigDecimal(0))// 预存余额
				.dueMoney(new BigDecimal(0))// 欠费金额
				.build();
		custInfo.setId(id);

		log.info(ToStringBuilder.reflectionToString(custInfo, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = custInfoService.updateById(custInfo);

		log.info(Boolean.toString(success));
	}
}
