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
import com.zlsrj.wms.api.dto.TenantMeterPriceAddParam;
import com.zlsrj.wms.api.dto.TenantMeterPriceUpdateParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterPriceControllerTest {
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
	public void createTest() throws Exception {
		TenantMeterPriceAddParam tenantMeterPriceAddParam = new TenantMeterPriceAddParam();
		tenantMeterPriceAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantMeterPriceAddParam.setMeterId(RandomUtil.randomString(4));// 水表ID
		tenantMeterPriceAddParam.setMeterCode(RandomUtil.randomString(4));// 水表编号
		tenantMeterPriceAddParam.setPriceOrder(RandomUtil.randomInt(0,1000+1));// 排序
		tenantMeterPriceAddParam.setPriceId(RandomUtil.randomString(4));// 水价列表ID
		tenantMeterPriceAddParam.setPriceType(RandomUtil.randomInt(0,1+1));// 计费类别（1：定量；0：定比）
		tenantMeterPriceAddParam.setPriceScale(new BigDecimal(0));// 系数
		
		log.info(JSON.toJSONString(tenantMeterPriceAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeterPrice/create")//
						.content(JSON.toJSONString(tenantMeterPriceAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterPrice/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		String id = "";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterPrice/"+id)//
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
				MockMvcRequestBuilders.get("/tenantMeterPrice/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void pageTest() throws Exception {
	        String tenantId = "";

	        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	        params.add("tenantId", tenantId);

	        params.add("page", "1");
	        params.add("rows", "10");

	        log.info(JSON.toJSONString(params));

	        String responseString = mockMvc.perform(//
	                        MockMvcRequestBuilders.get("/tenantMeterPrice/page")//
	                                        .params(params)
	                                        .accept(MediaType.APPLICATION_JSON_UTF8)//
	        ).andReturn().getResponse().getContentAsString();
	        log.info(responseString);
	}
	
	@Test
	public void countTest() throws Exception {
		String tenantId = "";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterPrice/count")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		TenantMeterPriceUpdateParam tenantMeterPriceUpdateParam = new TenantMeterPriceUpdateParam();
		tenantMeterPriceUpdateParam.setMeterId(RandomUtil.randomString(4));// 水表ID
		tenantMeterPriceUpdateParam.setMeterCode(RandomUtil.randomString(4));// 水表编号
		tenantMeterPriceUpdateParam.setPriceOrder(RandomUtil.randomInt(0,1000+1));// 排序
		tenantMeterPriceUpdateParam.setPriceId(RandomUtil.randomString(4));// 水价列表ID
		tenantMeterPriceUpdateParam.setPriceType(RandomUtil.randomInt(0,1+1));// 计费类别（1：定量；0：定比）
		tenantMeterPriceUpdateParam.setPriceScale(new BigDecimal(0));// 系数
		
		log.info(JSON.toJSONString(tenantMeterPriceUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeterPrice/update/"+id)//
						.content(JSON.toJSONString(tenantMeterPriceUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
}
