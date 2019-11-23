package com.zlsrj.wms.cust.service;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.CustContact;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ICustContactServiceTest {

	@Autowired
	private ICustContactService custContactService;

	@Test
	public void insertTest() {
		CustContact custContact = CustContact.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custId(RandomUtil.randomString(4))// 用户编号
				.contactName(TestCaseUtil.name())// 联系人姓名
				.mainOn(RandomUtil.randomInt(0,1+1))// 主联系人（1：是；0：否）
				.gender(RandomUtil.randomInt(0,1000+1))// 性别（1：男；2：女）
				.birthday(new Date())// 
				.mobile(RandomUtil.randomString(4))// 手机号码
				.tel(RandomUtil.randomString(4))// 固定电话号码
				.email(RandomUtil.randomString(4))// 邮箱地址
				.personalWx(RandomUtil.randomString(4))// 微信号
				.qq(RandomUtil.randomString(4))// QQ号
				.remark(RandomUtil.randomString(4))// 备注
				.build();

		log.info(ToStringBuilder.reflectionToString(custContact, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = custContactService.save(custContact);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		CustContact custContact = CustContact.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custId(RandomUtil.randomString(4))// 用户编号
				.contactName(TestCaseUtil.name())// 联系人姓名
				.mainOn(RandomUtil.randomInt(0,1+1))// 主联系人（1：是；0：否）
				.gender(RandomUtil.randomInt(0,1000+1))// 性别（1：男；2：女）
				.birthday(new Date())// 
				.mobile(RandomUtil.randomString(4))// 手机号码
				.tel(RandomUtil.randomString(4))// 固定电话号码
				.email(RandomUtil.randomString(4))// 邮箱地址
				.personalWx(RandomUtil.randomString(4))// 微信号
				.qq(RandomUtil.randomString(4))// QQ号
				.remark(RandomUtil.randomString(4))// 备注
				.build();
		custContact.setId(id);

		log.info(ToStringBuilder.reflectionToString(custContact, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = custContactService.updateById(custContact);

		log.info(Boolean.toString(success));
	}
}
