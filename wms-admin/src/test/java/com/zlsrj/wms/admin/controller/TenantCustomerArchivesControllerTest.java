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
import com.zlsrj.wms.api.dto.TenantCustomerArchivesAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesUpdateParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerArchivesControllerTest {
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
		TenantCustomerArchivesAddParam tenantCustomerArchivesAddParam = new TenantCustomerArchivesAddParam();
		tenantCustomerArchivesAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantCustomerArchivesAddParam.setCustomerId(RandomUtil.randomString(4));// 用户ID
		tenantCustomerArchivesAddParam.setCustomerCode(RandomUtil.randomString(4));// 用户号
		tenantCustomerArchivesAddParam.setArchivesName("档案名称"+"-"+"新增用例"+"-"+RandomUtil.randomNumbers(4));// 档案名称
		tenantCustomerArchivesAddParam.setArchivesCreateTime(new Date());// 创建时间
		tenantCustomerArchivesAddParam.setArchivesCreateDate(new Date());// 创建日期
		tenantCustomerArchivesAddParam.setArchivesFilename(RandomUtil.randomString(4));// 存储文件名称JSON串
		tenantCustomerArchivesAddParam.setArchivesInformation(RandomUtil.randomString(4));// 证件信息JSON串，例如身份证号、地址等
		tenantCustomerArchivesAddParam.setArchivesCode(RandomUtil.randomString(4));// 证件号码，例如身份证号等
		
		log.info(JSON.toJSONString(tenantCustomerArchivesAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantCustomerArchives/create")//
						.content(JSON.toJSONString(tenantCustomerArchivesAddParam))//
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
				MockMvcRequestBuilders.get("/tenantCustomerArchives/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		String id = "";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomerArchives/"+id)//
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
				MockMvcRequestBuilders.get("/tenantCustomerArchives/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void pageTest() throws Exception {
	        String tenantId = "030c9a1874b14034bccbb73912e56a48";

	        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	        params.add("tenantId", tenantId);

	        params.add("page", "1");
	        params.add("rows", "10");
	        
	        params.add("queryCol", "customer_code");
	        params.add("queryType", "=");
	        params.add("queryValue", "250000008");
	        
	        params.add("queryCol", "archives_name");
	        params.add("queryType", "like");
	        params.add("queryValue", "新增用例");

	        log.info(JSON.toJSONString(params));

	        String responseString = mockMvc.perform(//
	                        MockMvcRequestBuilders.get("/tenantCustomerArchives/page")//
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
				MockMvcRequestBuilders.get("/tenantCustomerArchives/count")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		TenantCustomerArchivesUpdateParam tenantCustomerArchivesUpdateParam = new TenantCustomerArchivesUpdateParam();
		tenantCustomerArchivesUpdateParam.setCustomerId(RandomUtil.randomString(4));// 用户ID
		tenantCustomerArchivesUpdateParam.setCustomerCode(RandomUtil.randomString(4));// 用户号
		tenantCustomerArchivesUpdateParam.setArchivesName("档案名称"+"-"+"新增用例"+"-"+RandomUtil.randomNumbers(4));// 档案名称
		tenantCustomerArchivesUpdateParam.setArchivesCreateTime(new Date());// 创建时间
		tenantCustomerArchivesUpdateParam.setArchivesCreateDate(new Date());// 创建日期
		tenantCustomerArchivesUpdateParam.setArchivesFilename(RandomUtil.randomString(4));// 存储文件名称JSON串
		tenantCustomerArchivesUpdateParam.setArchivesInformation(RandomUtil.randomString(4));// 证件信息JSON串，例如身份证号、地址等
		tenantCustomerArchivesUpdateParam.setArchivesCode(RandomUtil.randomString(4));// 证件号码，例如身份证号等
		
		log.info(JSON.toJSONString(tenantCustomerArchivesUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantCustomerArchives/update/"+id)//
						.content(JSON.toJSONString(tenantCustomerArchivesUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
}
