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
import com.zlsrj.wms.api.dto.TenantPriceStepAddParam;
import com.zlsrj.wms.api.dto.TenantPriceStepUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceStepControllerTest {
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
				MockMvcRequestBuilders.get("/tenantPriceStep/"+id)//
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
				MockMvcRequestBuilders.get("/tenantPriceStep/list")//
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
				MockMvcRequestBuilders.get("/tenantPriceStep/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		TenantPriceStepUpdateParam tenantPriceStepUpdateParam = new TenantPriceStepUpdateParam();
		tenantPriceStepUpdateParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantPriceStepUpdateParam.setPriceDetailId(RandomUtil.randomString(4));// 水价明细ID
		tenantPriceStepUpdateParam.setStepClass(RandomUtil.randomInt(0,1000+1));// 阶梯级次
		tenantPriceStepUpdateParam.setStartCode(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.TEN));// 阶梯起始量
		tenantPriceStepUpdateParam.setEndCode(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.TEN));// 阶梯终止量
		tenantPriceStepUpdateParam.setStepPrice(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.TEN));// 单价
		tenantPriceStepUpdateParam.setStepUsers(RandomUtil.randomInt(0,1000+1));// 标准用水人数
		tenantPriceStepUpdateParam.setStepUsersAdd(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.TEN));// 超人数增补量
		
		log.info(JSON.toJSONString(tenantPriceStepUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantPriceStep/update/"+id)//
						.content(JSON.toJSONString(tenantPriceStepUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantPriceStepAddParam tenantPriceStepAddParam = new TenantPriceStepAddParam();
		tenantPriceStepAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantPriceStepAddParam.setPriceDetailId(RandomUtil.randomString(4));// 水价明细ID
		tenantPriceStepAddParam.setStepClass(RandomUtil.randomInt(0,1000+1));// 阶梯级次
		tenantPriceStepAddParam.setStartCode(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.TEN));// 阶梯起始量
		tenantPriceStepAddParam.setEndCode(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.TEN));// 阶梯终止量
		tenantPriceStepAddParam.setStepPrice(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.TEN));// 单价
		tenantPriceStepAddParam.setStepUsers(RandomUtil.randomInt(0,1000+1));// 标准用水人数
		tenantPriceStepAddParam.setStepUsersAdd(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.TEN));// 超人数增补量
		
		log.info(JSON.toJSONString(tenantPriceStepAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantPriceStep/create")//
						.content(JSON.toJSONString(tenantPriceStepAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
