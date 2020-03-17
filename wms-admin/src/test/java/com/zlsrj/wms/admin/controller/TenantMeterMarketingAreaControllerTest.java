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
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterMarketingAreaControllerTest {
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
		String id = "e2dc58fc79d942c6997879f20593807e";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterMarketingArea/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "oxc4";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterMarketingArea/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "a23b3e6fc0dd4b108c3a84d119f3aea0";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeterMarketingArea/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "706b96279e564d7ca0050422d50e7bdc";
		log.info("id={}",id);
		
		TenantMeterMarketingAreaUpdateParam tenantMeterMarketingAreaUpdateParam = new TenantMeterMarketingAreaUpdateParam();
		tenantMeterMarketingAreaUpdateParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantMeterMarketingAreaUpdateParam.setMarketingAreaType(RandomUtil.randomInt(0,1+1));// 区域类型（0：内部机构；1：代收机构）
		tenantMeterMarketingAreaUpdateParam.setMarketingAreaName(TestCaseUtil.name());// 名称
		tenantMeterMarketingAreaUpdateParam.setMarketingAreaParentId(RandomUtil.randomString(4));// 父级ID
		tenantMeterMarketingAreaUpdateParam.setMarketingAreaData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantMeterMarketingAreaUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeterMarketingArea/update/"+id)//
						.content(JSON.toJSONString(tenantMeterMarketingAreaUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantMeterMarketingAreaAddParam tenantMeterMarketingAreaAddParam = new TenantMeterMarketingAreaAddParam();
		tenantMeterMarketingAreaAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantMeterMarketingAreaAddParam.setMarketingAreaType(RandomUtil.randomInt(0,1+1));// 区域类型（0：内部机构；1：代收机构）
		tenantMeterMarketingAreaAddParam.setMarketingAreaName(TestCaseUtil.name());// 名称
		tenantMeterMarketingAreaAddParam.setMarketingAreaParentId(RandomUtil.randomString(4));// 父级ID
		tenantMeterMarketingAreaAddParam.setMarketingAreaData(RandomUtil.randomString(4));// 结构化数据
		
		log.info(JSON.toJSONString(tenantMeterMarketingAreaAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeterMarketingArea/create")//
						.content(JSON.toJSONString(tenantMeterMarketingAreaAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
