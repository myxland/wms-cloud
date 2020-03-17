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
import com.zlsrj.wms.api.dto.TenantMeterIndustryAddParam;
import com.zlsrj.wms.api.dto.TenantMeterIndustryUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterIndustryControllerTest {
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
		String id = "9d881e18050c4232a4a8ee20365bb1e1";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterIndustry/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "g8mx";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterIndustry/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "9d881e18050c4232a4a8ee20365bb1e1";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterIndustry/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "9d881e18050c4232a4a8ee20365bb1e1";
		log.info("id={}",id);
		
		TenantMeterIndustryUpdateParam tenantMeterIndustryUpdateParam = new TenantMeterIndustryUpdateParam();
		tenantMeterIndustryUpdateParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantMeterIndustryUpdateParam.setMeterIndustryName(TestCaseUtil.name());// 名称
		tenantMeterIndustryUpdateParam.setMeterIndustryParentId(RandomUtil.randomString(4));// 父级ID
		tenantMeterIndustryUpdateParam.setMeterIndustryData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantMeterIndustryUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeterIndustry/update/"+id)//
						.content(JSON.toJSONString(tenantMeterIndustryUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantMeterIndustryAddParam tenantMeterIndustryAddParam = new TenantMeterIndustryAddParam();
		tenantMeterIndustryAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantMeterIndustryAddParam.setMeterIndustryName(TestCaseUtil.name());// 名称
		tenantMeterIndustryAddParam.setMeterIndustryParentId(RandomUtil.randomString(4));// 父级ID
		tenantMeterIndustryAddParam.setMeterIndustryData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantMeterIndustryAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeterIndustry/create")//
						.content(JSON.toJSONString(tenantMeterIndustryAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
