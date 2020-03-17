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
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterSupplyAreaUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterSupplyAreaControllerTest {
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
		String id = "8ed2da2c124e4839b9c1e2cd1d0c091c";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterSupplyArea/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "znqy";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterSupplyArea/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "8ed2da2c124e4839b9c1e2cd1d0c091c";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterSupplyArea/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "8ed2da2c124e4839b9c1e2cd1d0c091c";
		log.info("id={}",id);
		
		TenantMeterSupplyAreaUpdateParam tenantMeterSupplyAreaUpdateParam = new TenantMeterSupplyAreaUpdateParam();
		tenantMeterSupplyAreaUpdateParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantMeterSupplyAreaUpdateParam.setSupplyAreaName(TestCaseUtil.name());// 名称
		tenantMeterSupplyAreaUpdateParam.setSupplyAreaParentId(RandomUtil.randomString(4));// 父级ID
		tenantMeterSupplyAreaUpdateParam.setSupplyAreaData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantMeterSupplyAreaUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeterSupplyArea/update/"+id)//
						.content(JSON.toJSONString(tenantMeterSupplyAreaUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantMeterSupplyAreaAddParam tenantMeterSupplyAreaAddParam = new TenantMeterSupplyAreaAddParam();
		tenantMeterSupplyAreaAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantMeterSupplyAreaAddParam.setSupplyAreaName(TestCaseUtil.name());// 名称
		tenantMeterSupplyAreaAddParam.setSupplyAreaParentId(RandomUtil.randomString(4));// 父级ID
		tenantMeterSupplyAreaAddParam.setSupplyAreaData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantMeterSupplyAreaAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeterSupplyArea/create")//
						.content(JSON.toJSONString(tenantMeterSupplyAreaAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
