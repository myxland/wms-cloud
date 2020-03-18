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
import com.zlsrj.wms.api.dto.TenantPriceRuleAddParam;
import com.zlsrj.wms.api.dto.TenantPriceRuleUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceRuleControllerTest {
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
		String id = "66be10da02984d0ca6a7f3ec0f744f72";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantPriceRule/"+id)//
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
				MockMvcRequestBuilders.get("/tenantPriceRule/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "66be10da02984d0ca6a7f3ec0f744f72";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantPriceRule/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "66be10da02984d0ca6a7f3ec0f744f72";
		log.info("id={}",id);
		
		TenantPriceRuleUpdateParam tenantPriceRuleUpdateParam = new TenantPriceRuleUpdateParam();
		tenantPriceRuleUpdateParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");// 租户ID
		tenantPriceRuleUpdateParam.setRuleName("计费方法"+"-"+"更新测试"+"-"+RandomUtil.randomNumbers(4));// 计费方法名称
		tenantPriceRuleUpdateParam.setRuleExplain(RandomUtil.randomString(4));// 计费方法说明
		
		log.info(JSON.toJSONString(tenantPriceRuleUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantPriceRule/update/"+id)//
						.content(JSON.toJSONString(tenantPriceRuleUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantPriceRuleAddParam tenantPriceRuleAddParam = new TenantPriceRuleAddParam();
		tenantPriceRuleAddParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");// 租户ID
		tenantPriceRuleAddParam.setRuleName("计费方法"+"-"+"测试"+"-"+RandomUtil.randomNumbers(4));// 计费方法名称
		tenantPriceRuleAddParam.setRuleExplain(RandomUtil.randomString(4));// 计费方法说明
		
		log.info(JSON.toJSONString(tenantPriceRuleAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantPriceRule/create")//
						.content(JSON.toJSONString(tenantPriceRuleAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
