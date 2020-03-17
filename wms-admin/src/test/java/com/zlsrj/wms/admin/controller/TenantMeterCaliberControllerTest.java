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
import com.zlsrj.wms.api.dto.TenantMeterCaliberAddParam;
import com.zlsrj.wms.api.dto.TenantMeterCaliberUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterCaliberControllerTest {
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
				MockMvcRequestBuilders.get("/tenantMeterCaliber/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterCaliber/list")//
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
				MockMvcRequestBuilders.get("/tenantMeterCaliber/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		TenantMeterCaliberUpdateParam tenantMeterCaliberUpdateParam = new TenantMeterCaliberUpdateParam();
		tenantMeterCaliberUpdateParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantMeterCaliberUpdateParam.setMeterCaliberName(TestCaseUtil.name());// 名称
		tenantMeterCaliberUpdateParam.setMeterCaliberData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantMeterCaliberUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeterCaliber/update/"+id)//
						.content(JSON.toJSONString(tenantMeterCaliberUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantMeterCaliberAddParam tenantMeterCaliberAddParam = new TenantMeterCaliberAddParam();
		tenantMeterCaliberAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantMeterCaliberAddParam.setMeterCaliberName(TestCaseUtil.name());// 名称
		tenantMeterCaliberAddParam.setMeterCaliberData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantMeterCaliberAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeterCaliber/create")//
						.content(JSON.toJSONString(tenantMeterCaliberAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
