package com.zlsrj.wms.saas.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantCustomerType;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCustomerTypeServiceTest {

	@Autowired
	private ITenantCustomerTypeService tenantCustomerTypeService;

	@Test
	public void insertTest() {
		TenantCustomerType tenantCustomerType = TenantCustomerType.builder()//
				.id(TestCaseUtil.id())// 用户类别ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.customerTypeName(TestCaseUtil.name())// 用户类别名称
				.customerTypeParentId(RandomUtil.randomString(32))// 上级用户类别ID
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantCustomerType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerTypeService.save(tenantCustomerType);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantCustomerType tenantCustomerType = TenantCustomerType.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.customerTypeName(TestCaseUtil.name())// 用户类别名称
				.customerTypeParentId(RandomUtil.randomString(32))// 上级用户类别ID
				.build();
		tenantCustomerType.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCustomerType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerTypeService.updateById(tenantCustomerType);

		log.info(Boolean.toString(success));
	}
}
