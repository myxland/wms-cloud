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
import com.zlsrj.wms.api.entity.TenantCustomerStatement;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCustomerStatementServiceTest {

	@Autowired
	private ITenantCustomerStatementService tenantCustomerStatementService;

	@Test
	public void insertTest() {
		TenantCustomerStatement tenantCustomerStatement = TenantCustomerStatement.builder()//
				.id(TestCaseUtil.id())// 用户结算ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.statementMethod(RandomUtil.randomInt(0,1000+1))// 结算方式（1：坐收；2：托收；3：代扣；4：走收）
				.statementBankId(RandomUtil.randomString(4))// 付款银行
				.entrustAgreementNo(RandomUtil.randomString(4))// 委托授权号
				.entrustCode(RandomUtil.randomString(4))// 托收号
				.statementAccountBankId(RandomUtil.randomLong())// 开户银行
				.statementAccountName(TestCaseUtil.name())// 开户名称
				.statementAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.statementRegisterDate(new Date())// 签约日期
				.statementMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantCustomerStatement, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerStatementService.save(tenantCustomerStatement);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantCustomerStatement tenantCustomerStatement = TenantCustomerStatement.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.statementMethod(RandomUtil.randomInt(0,1000+1))// 结算方式（1：坐收；2：托收；3：代扣；4：走收）
				.statementBankId(RandomUtil.randomString(4))// 付款银行
				.entrustAgreementNo(RandomUtil.randomString(4))// 委托授权号
				.entrustCode(RandomUtil.randomString(4))// 托收号
				.statementAccountBankId(RandomUtil.randomLong())// 开户银行
				.statementAccountName(TestCaseUtil.name())// 开户名称
				.statementAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.statementRegisterDate(new Date())// 签约日期
				.statementMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantCustomerStatement.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCustomerStatement, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerStatementService.updateById(tenantCustomerStatement);

		log.info(Boolean.toString(success));
	}
}
