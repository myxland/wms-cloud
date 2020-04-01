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
import com.zlsrj.wms.api.entity.TenantCustomerContacts;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCustomerContactsServiceTest {

	@Autowired
	private ITenantCustomerContactsService tenantCustomerContactsService;

	@Test
	public void insertTest() {
		TenantCustomerContacts tenantCustomerContacts = TenantCustomerContacts.builder()//
				.id(TestCaseUtil.id())// 用户联系ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.contactsName(TestCaseUtil.name())// 联系人姓名
				.contactsAddress(TestCaseUtil.address())// 联系人地址
				.contactsMain(RandomUtil.randomInt(0,1000+1))// 主联系人（1：是；0：否）
				.contactsSex(RandomUtil.randomInt(0,1000+1))// 性别（1：男；0：女）
				.contactsBirthday(new Date())// 出生日期
				.contactsMobile(TestCaseUtil.mobile())// 手机号码
				.contactsTel(TestCaseUtil.tel())// 固定电话
				.contactsEmail(TestCaseUtil.email(null))// 邮箱地址
				.contactsWx(RandomUtil.randomString(4))// 微信号
				.contactsQq(TestCaseUtil.qq())// QQ号
				.contactsMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantCustomerContacts, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerContactsService.save(tenantCustomerContacts);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantCustomerContacts tenantCustomerContacts = TenantCustomerContacts.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.contactsName(TestCaseUtil.name())// 联系人姓名
				.contactsAddress(TestCaseUtil.address())// 联系人地址
				.contactsMain(RandomUtil.randomInt(0,1000+1))// 主联系人（1：是；0：否）
				.contactsSex(RandomUtil.randomInt(0,1000+1))// 性别（1：男；0：女）
				.contactsBirthday(new Date())// 出生日期
				.contactsMobile(TestCaseUtil.mobile())// 手机号码
				.contactsTel(TestCaseUtil.tel())// 固定电话
				.contactsEmail(TestCaseUtil.email(null))// 邮箱地址
				.contactsWx(RandomUtil.randomString(4))// 微信号
				.contactsQq(TestCaseUtil.qq())// QQ号
				.contactsMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantCustomerContacts.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCustomerContacts, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerContactsService.updateById(tenantCustomerContacts);

		log.info(Boolean.toString(success));
	}
}
