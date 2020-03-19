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
import com.zlsrj.wms.api.dto.TenantBusinessRulesAddParam;
import com.zlsrj.wms.api.dto.TenantBusinessRulesUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantBusinessRulesControllerTest {
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
		String id = "";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantBusinessRules/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "ri3q";
		String rulesType = "业务规则类型-测试-3423";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		params.add("rulesType", rulesType);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantBusinessRules/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantBusinessRules/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		TenantBusinessRulesUpdateParam tenantBusinessRulesUpdateParam = new TenantBusinessRulesUpdateParam();
		tenantBusinessRulesUpdateParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantBusinessRulesUpdateParam.setRulesType("业务规则类型"+"-"+"测试"+"-"+RandomUtil.randomNumbers(4));// 业务规则类型
		tenantBusinessRulesUpdateParam.setRulesData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantBusinessRulesUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantBusinessRules/update/"+id)//
						.content(JSON.toJSONString(tenantBusinessRulesUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantBusinessRulesAddParam tenantBusinessRulesAddParam = new TenantBusinessRulesAddParam();
		tenantBusinessRulesAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantBusinessRulesAddParam.setRulesType("业务规则类型"+"-"+"测试"+"-"+RandomUtil.randomNumbers(4));// 业务规则类型
		tenantBusinessRulesAddParam.setRulesData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantBusinessRulesAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantBusinessRules/create")//
						.content(JSON.toJSONString(tenantBusinessRulesAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
