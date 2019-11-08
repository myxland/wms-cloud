package com.zlsrj.wms.mbg.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantAccount;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantAccountServiceTest {

	@Autowired
	private ITenantAccountService tenantAccountService;

	@Test
	public void insertTest() {
		TenantAccount tenantAccount = TenantAccount.builder()//
				.id(TestCaseUtil.id())// 编号ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.accountBalance(new BigDecimal(0))// 账户余额
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantAccount, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantAccountService.save(tenantAccount);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantAccount tenantAccount = TenantAccount.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.accountBalance(new BigDecimal(0))// 账户余额
				.build();
		tenantAccount.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantAccount, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantAccountService.updateById(tenantAccount);

		log.info(Boolean.toString(success));
	}
}
