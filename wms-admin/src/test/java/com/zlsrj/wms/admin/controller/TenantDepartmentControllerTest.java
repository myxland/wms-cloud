package com.zlsrj.wms.admin.controller;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.dto.TenantDepartmentAddParam;
import com.zlsrj.wms.api.dto.TenantDepartmentUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantDepartmentControllerTest {
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
	public void getByIdTest() throws Exception {
		//String id = "9CD528949AAD4B61ABF58E3322CEC566";
		String id = "66BED12184674A59BC7319A536F18C2B";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantDepartment/"+id)//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "AE6492EB900A4CEAB9C6E2DB3E03C344";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantDepartment/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "9CD528949AAD4B61ABF58E3322CEC566";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantDepartment/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "A26EC3449BE3404B8AB82BC912324AE7";
		log.info("id={}",id);
		
		TenantDepartmentUpdateParam tenantDepartmentUpdateParam = new TenantDepartmentUpdateParam();
		tenantDepartmentUpdateParam.setTenantId("640D27A93BA4421495434CA7EE796E64");
		tenantDepartmentUpdateParam.setDepartmentName("质保部");
		tenantDepartmentUpdateParam.setDepartmentParentId("9CD528949AAD4B61ABF58E3322CEC566");
		
		log.info(JSON.toJSONString(tenantDepartmentUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantDepartment/update/"+id)//
						.content(JSON.toJSONString(tenantDepartmentUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantDepartmentAddParam tenantDepartmentAddParam = new TenantDepartmentAddParam();
		tenantDepartmentAddParam.setTenantId("640D27A93BA4421495434CA7EE796E64");
		tenantDepartmentAddParam.setDepartmentName("质保部"+RandomUtil.randomInt(0, 10));
		tenantDepartmentAddParam.setDepartmentParentId("9CD528949AAD4B61ABF58E3322CEC566");
		
		log.info(JSON.toJSONString(tenantDepartmentAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantDepartment/create")//
						.content(JSON.toJSONString(tenantDepartmentAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
