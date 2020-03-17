package com.zlsrj.wms.admin.controller;

import java.util.Date;

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
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.dto.TenantCustomerTypeAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerTypeUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerTypeControllerTest {
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
		String id = "c9a5f6923e7c49cabd3832816c095b31";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomerType/"+id)//
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
				MockMvcRequestBuilders.get("/tenantCustomerType/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "c9a5f6923e7c49cabd3832816c095b31";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomerType/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "c9a5f6923e7c49cabd3832816c095b31";
		log.info("id={}",id);
		
		TenantCustomerTypeUpdateParam tenantCustomerTypeUpdateParam = new TenantCustomerTypeUpdateParam();
		tenantCustomerTypeUpdateParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantCustomerTypeUpdateParam.setCustomerTypeName(TestCaseUtil.name());// 用户分类名称
		tenantCustomerTypeUpdateParam.setCustomerTypeData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantCustomerTypeUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantCustomerType/update/"+id)//
						.content(JSON.toJSONString(tenantCustomerTypeUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantCustomerTypeAddParam tenantCustomerTypeAddParam = new TenantCustomerTypeAddParam();
		tenantCustomerTypeAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantCustomerTypeAddParam.setCustomerTypeName(TestCaseUtil.name());// 用户分类名称
		tenantCustomerTypeAddParam.setCustomerTypeData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantCustomerTypeAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantCustomerType/create")//
						.content(JSON.toJSONString(tenantCustomerTypeAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
