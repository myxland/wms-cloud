package com.zlsrj.wms.mbg.service;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantCustType;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCustTypeServiceTest {

	@Autowired
	private ITenantCustTypeService tenantCustTypeService;

	@Test
	public void insertTest() {
		TenantCustType tenantCustType = TenantCustType.builder()//
				.id(TestCaseUtil.id())// 用户类型
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custTypeName(TestCaseUtil.name())// 用户类别名称
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantCustType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustTypeService.save(tenantCustType);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		TenantCustType tenantCustType = TenantCustType.builder()//
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custTypeName(TestCaseUtil.name())// 用户类别名称
				.build();
		tenantCustType.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCustType, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustTypeService.updateById(tenantCustType);

		log.info(Boolean.toString(success));
	}
}
