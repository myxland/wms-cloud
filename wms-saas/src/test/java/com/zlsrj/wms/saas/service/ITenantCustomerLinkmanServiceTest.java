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
import com.zlsrj.wms.api.entity.TenantCustomerLinkman;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCustomerLinkmanServiceTest {

	@Autowired
	private ITenantCustomerLinkmanService tenantCustomerLinkmanService;

	@Test
	public void insertTest() {
		TenantCustomerLinkman tenantCustomerLinkman = TenantCustomerLinkman.builder()//
				.id(TestCaseUtil.id())// 联系人ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.customerId(RandomUtil.randomString(32))// 客户ID
				.linkmanName(TestCaseUtil.name())// 联系人姓名
				.linkmanAddress(TestCaseUtil.address())// 联系人地址
				.linkmanMainOn(RandomUtil.randomInt(0,1+1))// 主联系人（1：是；0：否）
				.linkmanSex(RandomUtil.randomInt(0,1000+1))// 性别（1：男；2：女）
				.linkmanBirthday(new Date())// 出生日期
				.linkmanMobile(TestCaseUtil.mobile())// 手机号码
				.linkmanTel(TestCaseUtil.tel())// 固定电话号码
				.linkmanEmail(TestCaseUtil.email(null))// 邮箱地址
				.linkmanPersonalWx(RandomUtil.randomString(4))// 微信号
				.linkmanQq(TestCaseUtil.qq())// QQ号
				.linkmanRemark(RandomUtil.randomString(4))// 备注
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantCustomerLinkman, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerLinkmanService.save(tenantCustomerLinkman);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantCustomerLinkman tenantCustomerLinkman = TenantCustomerLinkman.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.customerId(RandomUtil.randomString(32))// 客户ID
				.linkmanName(TestCaseUtil.name())// 联系人姓名
				.linkmanAddress(TestCaseUtil.address())// 联系人地址
				.linkmanMainOn(RandomUtil.randomInt(0,1+1))// 主联系人（1：是；0：否）
				.linkmanSex(RandomUtil.randomInt(0,1000+1))// 性别（1：男；2：女）
				.linkmanBirthday(new Date())// 出生日期
				.linkmanMobile(TestCaseUtil.mobile())// 手机号码
				.linkmanTel(TestCaseUtil.tel())// 固定电话号码
				.linkmanEmail(TestCaseUtil.email(null))// 邮箱地址
				.linkmanPersonalWx(RandomUtil.randomString(4))// 微信号
				.linkmanQq(TestCaseUtil.qq())// QQ号
				.linkmanRemark(RandomUtil.randomString(4))// 备注
				.build();
		tenantCustomerLinkman.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCustomerLinkman, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerLinkmanService.updateById(tenantCustomerLinkman);

		log.info(Boolean.toString(success));
	}
}
