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
import com.zlsrj.wms.api.dto.TenantChargeAgencyAddParam;
import com.zlsrj.wms.api.dto.TenantChargeAgencyUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantChargeAgencyControllerTest {
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
		String id = "4de8362bdd6f47debf6b862d9c3e6ec9";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantChargeAgency/"+id)//
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
				MockMvcRequestBuilders.get("/tenantChargeAgency/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "4de8362bdd6f47debf6b862d9c3e6ec9";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantChargeAgency/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "4de8362bdd6f47debf6b862d9c3e6ec9";
		log.info("id={}",id);
		
		TenantChargeAgencyUpdateParam tenantChargeAgencyUpdateParam = new TenantChargeAgencyUpdateParam();
		tenantChargeAgencyUpdateParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantChargeAgencyUpdateParam.setChargeAgencyName("代收机构名称"+"-"+"更新测试"+RandomUtil.randomNumbers(4));// 代收机构名称
		tenantChargeAgencyUpdateParam.setChargeAgencyData(RandomUtil.randomString(4));// 结构化数据
		tenantChargeAgencyUpdateParam.setCreateType(RandomUtil.randomInt(1,2+1));// 创建类型（1：平台默认创建；2：租户自建）
		tenantChargeAgencyUpdateParam.setApiOn(RandomUtil.randomInt(0,1+1));// 是否开放API（1：开放；0：不开放）
		
		log.info(JSON.toJSONString(tenantChargeAgencyUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantChargeAgency/update/"+id)//
						.content(JSON.toJSONString(tenantChargeAgencyUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantChargeAgencyAddParam tenantChargeAgencyAddParam = new TenantChargeAgencyAddParam();
		tenantChargeAgencyAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantChargeAgencyAddParam.setChargeAgencyName("代收机构名称"+"-"+"测试"+RandomUtil.randomNumbers(4));// 代收机构名称
		tenantChargeAgencyAddParam.setChargeAgencyData(RandomUtil.randomString(4));// 结构化数据
		tenantChargeAgencyAddParam.setCreateType(RandomUtil.randomInt(1,2+1));// 创建类型（1：平台默认创建；2：租户自建）
		tenantChargeAgencyAddParam.setApiOn(RandomUtil.randomInt(0,1+1));// 是否开放API（1：开放；0：不开放）
		
		log.info(JSON.toJSONString(tenantChargeAgencyAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantChargeAgency/create")//
						.content(JSON.toJSONString(tenantChargeAgencyAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
