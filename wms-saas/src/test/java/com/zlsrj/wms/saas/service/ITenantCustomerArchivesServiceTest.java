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
import com.zlsrj.wms.api.entity.TenantCustomerArchives;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCustomerArchivesServiceTest {

	@Autowired
	private ITenantCustomerArchivesService tenantCustomerArchivesService;

	@Test
	public void insertTest() {
		TenantCustomerArchives tenantCustomerArchives = TenantCustomerArchives.builder()//
				.id(TestCaseUtil.id())// 档案ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.archivesName(TestCaseUtil.name())// 档案名称
				.archivesCreateTime(new Date())// 创建时间
				.archivesCreateDate(new Date())// 创建日期
				.archivesFilename(RandomUtil.randomString(4))// 存储文件名称JSON串
				.archivesInformation(RandomUtil.randomString(4))// 证件信息JSON串，例如身份证号、地址等
				.archivesCode(RandomUtil.randomString(4))// 证件号码，例如身份证号等
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantCustomerArchives, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerArchivesService.save(tenantCustomerArchives);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantCustomerArchives tenantCustomerArchives = TenantCustomerArchives.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.archivesName(TestCaseUtil.name())// 档案名称
				.archivesCreateTime(new Date())// 创建时间
				.archivesCreateDate(new Date())// 创建日期
				.archivesFilename(RandomUtil.randomString(4))// 存储文件名称JSON串
				.archivesInformation(RandomUtil.randomString(4))// 证件信息JSON串，例如身份证号、地址等
				.archivesCode(RandomUtil.randomString(4))// 证件号码，例如身份证号等
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantCustomerArchives.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCustomerArchives, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerArchivesService.updateById(tenantCustomerArchives);

		log.info(Boolean.toString(success));
	}
}
