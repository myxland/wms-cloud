package com.zlsrj.wms.saas.service;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.dto.TenantDepartmentAddParam;
import com.zlsrj.wms.api.entity.TenantDepartment;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantDepartmentServiceTest {

	@Autowired
	private ITenantDepartmentService tenantDepartmentService;

	@Test
	public void insertTest() {
		for(int i=0;i<2;i++) {
			TenantDepartmentAddParam tenantDepartmentAddParam = new TenantDepartmentAddParam();
			tenantDepartmentAddParam.setTenantId("e1ddb601b6cc48b79f989d710712f6d0");
			tenantDepartmentAddParam.setDepartmentName("部门-03-02-01"+"-"+StringUtils.leftPad(Integer.toString(i), 2, "0"));
			tenantDepartmentAddParam.setDepartmentParentId("19e07f62aa2c41568533a6a36b941080");

			log.info(ToStringBuilder.reflectionToString(tenantDepartmentAddParam, ToStringStyle.MULTI_LINE_STYLE));

			String id = tenantDepartmentService.save(tenantDepartmentAddParam);

			log.info("id={}",id);
		}
	
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantDepartment tenantDepartment = TenantDepartment.builder()//
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.departmentName(TestCaseUtil.name())// 部门名称
				.departmentParentId(RandomUtil.randomString(32))// 上级部门ID
				.build();
		tenantDepartment.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantDepartment, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantDepartmentService.updateById(tenantDepartment);

		log.info(Boolean.toString(success));
	}
	
	@Test
	public void getParentListTest() {
		String id = "2a37b8b58fea464abb308c81cf317f4c";
		log.info("id={}",id);
		
		List<TenantDepartment> tenantDepartmentList  = tenantDepartmentService.getParentList(id);
		tenantDepartmentList.forEach(e->log.info(JSON.toJSONString(e)));

	}
	
	@Test
	public void getChildrenListTest() {
		String id = "4fb8525231194664b712209beebda5b8";
		log.info("id={}",id);
		
		List<TenantDepartment> tenantDepartmentList  = tenantDepartmentService.getChildrenList(id);
		tenantDepartmentList.forEach(e->log.info(JSON.toJSONString(e)));

	}
	
}
