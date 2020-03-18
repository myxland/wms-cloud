package com.zlsrj.wms.admin.controller;

import java.math.BigDecimal;
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
import com.zlsrj.wms.api.dto.TenantPriceDetailAddParam;
import com.zlsrj.wms.api.dto.TenantPriceDetailUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceDetailControllerTest {
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
		String id = "61eb14f0680241dfb22ae73619c18427";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantPriceDetail/"+id)//
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
				MockMvcRequestBuilders.get("/tenantPriceDetail/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "61eb14f0680241dfb22ae73619c18427";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantPriceDetail/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "61eb14f0680241dfb22ae73619c18427";
		log.info("id={}",id);
		
		TenantPriceDetailUpdateParam tenantPriceDetailUpdateParam = new TenantPriceDetailUpdateParam();
		tenantPriceDetailUpdateParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");// 租户ID
		tenantPriceDetailUpdateParam.setPriceId(RandomUtil.randomString(4));// 水表列表ID
		tenantPriceDetailUpdateParam.setPriceItemId(RandomUtil.randomString(4));// 费用项目
		tenantPriceDetailUpdateParam.setPriceRuleId(RandomUtil.randomString(4));// 计费规则
		tenantPriceDetailUpdateParam.setDetailPrice(RandomUtil.randomBigDecimal(new BigDecimal(0), new BigDecimal(1000)));// 单价
		
		log.info(JSON.toJSONString(tenantPriceDetailUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantPriceDetail/update/"+id)//
						.content(JSON.toJSONString(tenantPriceDetailUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantPriceDetailAddParam tenantPriceDetailAddParam = new TenantPriceDetailAddParam();
		tenantPriceDetailAddParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");// 租户ID
		tenantPriceDetailAddParam.setPriceId(RandomUtil.randomString(4));// 水表列表ID
		tenantPriceDetailAddParam.setPriceItemId(RandomUtil.randomString(4));// 费用项目
		tenantPriceDetailAddParam.setPriceRuleId(RandomUtil.randomString(4));// 计费规则
		tenantPriceDetailAddParam.setDetailPrice(RandomUtil.randomBigDecimal(new BigDecimal(0), new BigDecimal(1000)));// 单价
		
		log.info(JSON.toJSONString(tenantPriceDetailAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantPriceDetail/create")//
						.content(JSON.toJSONString(tenantPriceDetailAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
