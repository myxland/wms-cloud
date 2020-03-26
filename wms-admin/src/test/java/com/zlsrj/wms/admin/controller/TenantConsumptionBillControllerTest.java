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
import com.zlsrj.wms.api.dto.TenantConsumptionBillAddParam;
import com.zlsrj.wms.api.dto.TenantConsumptionBillUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantConsumptionBillControllerTest {
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
				MockMvcRequestBuilders.get("/tenantConsumptionBill/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "5776fb77e26e45209d56e39f9ceb7308";
		String consumptionBillType ="1";
		String consumptionBillTimeStart ="2020-03-26";
		String consumptionBillTimeEnd ="2020-03-26";
		String consumptionBillName ="账户充值";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		params.add("consumptionBillType", consumptionBillType);
		params.add("consumptionBillTimeStart", consumptionBillTimeStart);
		params.add("consumptionBillTimeEnd", consumptionBillTimeEnd);
		params.add("consumptionBillName", consumptionBillName);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantConsumptionBill/list")//
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
				MockMvcRequestBuilders.get("/tenantConsumptionBill/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		TenantConsumptionBillUpdateParam tenantConsumptionBillUpdateParam = new TenantConsumptionBillUpdateParam();
		tenantConsumptionBillUpdateParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantConsumptionBillUpdateParam.setConsumptionBillType(RandomUtil.randomInt(0,1+1));// 账单类型（1：充值；2：消费）
		tenantConsumptionBillUpdateParam.setConsumptionBillTime(new Date());// 账单时间
		tenantConsumptionBillUpdateParam.setConsumptionBillName(TestCaseUtil.name());// 账单名称[账户充值/短信平台/...]
		tenantConsumptionBillUpdateParam.setConsumptionBillMoney(new BigDecimal(0));// 账单金额
		tenantConsumptionBillUpdateParam.setTenantBalance(new BigDecimal(0));// 租户账户余额
		tenantConsumptionBillUpdateParam.setConsumptionBillRemark(RandomUtil.randomString(4));// 备注
		
		log.info(JSON.toJSONString(tenantConsumptionBillUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantConsumptionBill/update/"+id)//
						.content(JSON.toJSONString(tenantConsumptionBillUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantConsumptionBillAddParam tenantConsumptionBillAddParam = new TenantConsumptionBillAddParam();
		tenantConsumptionBillAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantConsumptionBillAddParam.setConsumptionBillType(RandomUtil.randomInt(0,1+1));// 账单类型（1：充值；2：消费）
		tenantConsumptionBillAddParam.setConsumptionBillTime(new Date());// 账单时间
		tenantConsumptionBillAddParam.setConsumptionBillName(TestCaseUtil.name());// 账单名称[账户充值/短信平台/...]
		tenantConsumptionBillAddParam.setConsumptionBillMoney(new BigDecimal(0));// 账单金额
		tenantConsumptionBillAddParam.setTenantBalance(new BigDecimal(0));// 租户账户余额
		tenantConsumptionBillAddParam.setConsumptionBillRemark(RandomUtil.randomString(4));// 备注
		
		log.info(JSON.toJSONString(tenantConsumptionBillAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantConsumptionBill/create")//
						.content(JSON.toJSONString(tenantConsumptionBillAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
