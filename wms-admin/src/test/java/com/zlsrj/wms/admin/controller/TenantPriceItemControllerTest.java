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
import com.zlsrj.wms.api.dto.TenantPriceItemAddParam;
import com.zlsrj.wms.api.dto.TenantPriceItemUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceItemControllerTest {
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
		String id = "2a7119dd59474afcb946c8670ae11c06";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantPriceItem/"+id)//
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
				MockMvcRequestBuilders.get("/tenantPriceItem/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "2a7119dd59474afcb946c8670ae11c06";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantPriceItem/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "2a7119dd59474afcb946c8670ae11c06";
		log.info("id={}",id);
		
		TenantPriceItemUpdateParam tenantPriceItemUpdateParam = new TenantPriceItemUpdateParam();
		tenantPriceItemUpdateParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");// 租户ID
		tenantPriceItemUpdateParam.setPriceItemCode(RandomUtil.randomInt(0,1000+1));// 费用项目编码
		tenantPriceItemUpdateParam.setPriceItemName("费用项目"+"-"+"更新测试"+"-"+RandomUtil.randomNumbers(4));// 费用项目名称
		tenantPriceItemUpdateParam.setPriceItemTaxRate(new BigDecimal(0));// 税率
		tenantPriceItemUpdateParam.setPriceItemTaxCode(RandomUtil.randomString(4));// 税收分类编码
		
		log.info(JSON.toJSONString(tenantPriceItemUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantPriceItem/update/"+id)//
						.content(JSON.toJSONString(tenantPriceItemUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantPriceItemAddParam tenantPriceItemAddParam = new TenantPriceItemAddParam();
		tenantPriceItemAddParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");// 租户ID
		tenantPriceItemAddParam.setPriceItemCode(RandomUtil.randomInt(0,1000+1));// 费用项目编码
		tenantPriceItemAddParam.setPriceItemName("费用项目"+"-"+"测试"+"-"+RandomUtil.randomNumbers(4));// 费用项目名称
		tenantPriceItemAddParam.setPriceItemTaxRate(new BigDecimal(0));// 税率
		tenantPriceItemAddParam.setPriceItemTaxCode(RandomUtil.randomString(4));// 税收分类编码
		
		log.info(JSON.toJSONString(tenantPriceItemAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantPriceItem/create")//
						.content(JSON.toJSONString(tenantPriceItemAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
