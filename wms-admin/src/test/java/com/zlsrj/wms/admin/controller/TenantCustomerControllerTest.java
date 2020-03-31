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
import com.zlsrj.wms.api.dto.TenantCustomerAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerUpdateParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerControllerTest {
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
		TenantCustomerAddParam tenantCustomerAddParam = new TenantCustomerAddParam();
		tenantCustomerAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantCustomerAddParam.setCustomerCode(RandomUtil.randomString(4));// 用户号
		tenantCustomerAddParam.setCustomerName("用户名称"+"-"+"新增用例"+"-"+RandomUtil.randomNumbers(4));// 用户名称
		tenantCustomerAddParam.setCustomerAddress(TestCaseUtil.address());// 用户地址
		tenantCustomerAddParam.setCustomerStatus(RandomUtil.randomInt(0,1+1));// 用户状态（1：正常；2：暂停；3：销户）
		tenantCustomerAddParam.setCustomerType(RandomUtil.randomString(4));// 用户类别
		tenantCustomerAddParam.setCustomerRegisterTime(new Date());// 建档时间
		tenantCustomerAddParam.setCustomerRegisterDate(new Date());// 立户日期
		tenantCustomerAddParam.setCustomerCreditRating(RandomUtil.randomInt(0,1000+1));// 信用等级
		tenantCustomerAddParam.setCustomerRatingDate(new Date());// 最近评估日期
		tenantCustomerAddParam.setCustomerBalanceAmt(new BigDecimal(0));// 预存余额
		tenantCustomerAddParam.setCustomerFreezeBalance(new BigDecimal(0));// 预存冻结金额
		tenantCustomerAddParam.setCustomerOweAmt(new BigDecimal(0));// 欠费金额
		tenantCustomerAddParam.setCustomerPenaltyAmt(new BigDecimal(0));// 违约金
		tenantCustomerAddParam.setCustomerMemo(RandomUtil.randomString(4));// 用户备注
		
		log.info(JSON.toJSONString(tenantCustomerAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantCustomer/create")//
						.content(JSON.toJSONString(tenantCustomerAddParam))//
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
				MockMvcRequestBuilders.get("/tenantCustomer/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		String id = "";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomer/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "933d88d4d23244079cc0b49f99aa2c0b";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		params.add("queryCol", "customer_code");
		params.add("queryType", "=");
		params.add("queryValue", "380000003");
		
		params.add("queryCol", "customer_name");
		params.add("queryType", "like");
		params.add("queryValue", "范旭");
		
		log.info(JSON.toJSONString(params));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomer/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void pageTest() throws Exception {
		String tenantId = "933d88d4d23244079cc0b49f99aa2c0b";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		params.add("queryCol", "customer_code");
		params.add("queryType", "=");
		params.add("queryValue", "380000003");
		
		params.add("queryCol", "customer_name");
		params.add("queryType", "like");
		params.add("queryValue", "范旭");
		
		params.add("page", "1");
		params.add("rows", "20");
		params.add("sort", "add_time");
		params.add("order", "desc");
		
		log.info(JSON.toJSONString(params));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomer/page")//
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
				MockMvcRequestBuilders.get("/tenantCustomer/count")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		TenantCustomerUpdateParam tenantCustomerUpdateParam = new TenantCustomerUpdateParam();
		tenantCustomerUpdateParam.setCustomerCode(RandomUtil.randomString(4));// 用户号
		tenantCustomerUpdateParam.setCustomerName("用户名称"+"-"+"新增用例"+"-"+RandomUtil.randomNumbers(4));// 用户名称
		tenantCustomerUpdateParam.setCustomerAddress(TestCaseUtil.address());// 用户地址
		tenantCustomerUpdateParam.setCustomerStatus(RandomUtil.randomInt(0,1+1));// 用户状态（1：正常；2：暂停；3：销户）
		tenantCustomerUpdateParam.setCustomerType(RandomUtil.randomString(4));// 用户类别
		tenantCustomerUpdateParam.setCustomerRegisterTime(new Date());// 建档时间
		tenantCustomerUpdateParam.setCustomerRegisterDate(new Date());// 立户日期
		tenantCustomerUpdateParam.setCustomerCreditRating(RandomUtil.randomInt(0,1000+1));// 信用等级
		tenantCustomerUpdateParam.setCustomerRatingDate(new Date());// 最近评估日期
		tenantCustomerUpdateParam.setCustomerBalanceAmt(new BigDecimal(0));// 预存余额
		tenantCustomerUpdateParam.setCustomerFreezeBalance(new BigDecimal(0));// 预存冻结金额
		tenantCustomerUpdateParam.setCustomerOweAmt(new BigDecimal(0));// 欠费金额
		tenantCustomerUpdateParam.setCustomerPenaltyAmt(new BigDecimal(0));// 违约金
		tenantCustomerUpdateParam.setCustomerMemo(RandomUtil.randomString(4));// 用户备注
		
		log.info(JSON.toJSONString(tenantCustomerUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantCustomer/update/"+id)//
						.content(JSON.toJSONString(tenantCustomerUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
}
