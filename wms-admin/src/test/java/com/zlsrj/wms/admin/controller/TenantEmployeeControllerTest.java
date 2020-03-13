package com.zlsrj.wms.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.dto.TenantRoleAddParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantEmployeeControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createTest() throws Exception {
		TenantEmployeeAddParam tenantEmployeeAddParam = new TenantEmployeeAddParam();
		tenantEmployeeAddParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");
		tenantEmployeeAddParam.setEmployeeName(TestCaseUtil.name());
		tenantEmployeeAddParam.setEmployeeDepartmentId("EBC639F193694D868C7679F723E72E30");
		tenantEmployeeAddParam.setEmployeeLoginOn(1);
		tenantEmployeeAddParam.setEmployeeStatus(1);
		tenantEmployeeAddParam.setEmployeeMobile(TestCaseUtil.mobile());
		tenantEmployeeAddParam.setEmployeeEmail(TestCaseUtil.email(TestCaseUtil.name()));
		tenantEmployeeAddParam.setEmployeePersonalWx(TestCaseUtil.wx());
		tenantEmployeeAddParam.setEmployeeEnterpriceWx(TestCaseUtil.wx());
		tenantEmployeeAddParam.setEmployeeDingding(TestCaseUtil.wx());
		tenantEmployeeAddParam.setEmployeeCreateType(2);
		
		List<TenantRoleAddParam> tenantRoleAddParamList = new ArrayList<TenantRoleAddParam>();
		
		TenantRoleAddParam tenantRoleAddParam = new TenantRoleAddParam();
		tenantRoleAddParam.setId("84B95609CA9A4883928EAE040890376A");
		tenantRoleAddParamList.add(tenantRoleAddParam);
		
		tenantRoleAddParam = new TenantRoleAddParam();
		tenantRoleAddParam.setId("3ACD1ACEF49B409C8D85F425E7AA1906");
		tenantRoleAddParamList.add(tenantRoleAddParam);
		
		tenantEmployeeAddParam.setTenantRoleList(tenantRoleAddParamList);
		
		log.info(JSON.toJSONString(tenantEmployeeAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantEmployee/create")//
						.content(JSON.toJSONString(tenantEmployeeAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTestSwagger() throws Exception {
		TenantEmployeeAddParam tenantEmployeeAddParam = new TenantEmployeeAddParam();
		tenantEmployeeAddParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");
		tenantEmployeeAddParam.setEmployeeName(TestCaseUtil.name());
		tenantEmployeeAddParam.setEmployeeDepartmentId("EBC639F193694D868C7679F723E72E30");
		tenantEmployeeAddParam.setEmployeeLoginOn(1);
		tenantEmployeeAddParam.setEmployeeStatus(1);
		tenantEmployeeAddParam.setEmployeeMobile(TestCaseUtil.mobile());
		tenantEmployeeAddParam.setEmployeeEmail(TestCaseUtil.email(TestCaseUtil.name()));
		tenantEmployeeAddParam.setEmployeePersonalWx(TestCaseUtil.wx());
		tenantEmployeeAddParam.setEmployeeEnterpriceWx(TestCaseUtil.wx());
		tenantEmployeeAddParam.setEmployeeDingding(TestCaseUtil.wx());
		tenantEmployeeAddParam.setEmployeeCreateType(2);
		
		List<TenantRoleAddParam> tenantRoleAddParamList = new ArrayList<TenantRoleAddParam>();
		
		TenantRoleAddParam tenantRoleAddParam = new TenantRoleAddParam();
		tenantRoleAddParam.setId("84B95609CA9A4883928EAE040890376A");
		tenantRoleAddParamList.add(tenantRoleAddParam);
		
		tenantRoleAddParam = new TenantRoleAddParam();
		tenantRoleAddParam.setId("3ACD1ACEF49B409C8D85F425E7AA1906");
		tenantRoleAddParamList.add(tenantRoleAddParam);
		
		tenantEmployeeAddParam.setTenantRoleList(tenantRoleAddParamList);
		
		log.info(JSON.toJSONString(tenantEmployeeAddParam));
		
	}
}
