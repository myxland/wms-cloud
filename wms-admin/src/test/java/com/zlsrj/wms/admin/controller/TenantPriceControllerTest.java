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
import com.zlsrj.wms.api.dto.TenantPriceAddParam;
import com.zlsrj.wms.api.dto.TenantPriceUpdateParam;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceControllerTest {
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
		String id = "3121b26b85f34efc994645cdf0d205ee";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantPrice/"+id)//
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
				MockMvcRequestBuilders.get("/tenantPrice/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "3121b26b85f34efc994645cdf0d205ee";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantPrice/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "3121b26b85f34efc994645cdf0d205ee";
		log.info("id={}",id);
		
		TenantPriceUpdateParam tenantPriceUpdateParam = new TenantPriceUpdateParam();
		tenantPriceUpdateParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");// 租户ID
		tenantPriceUpdateParam.setPriceOrder(RandomUtil.randomInt(0,1000+1));// 排序
		tenantPriceUpdateParam.setPriceName("水价名称"+"-"+"更新测试"+"-"+RandomUtil.randomNumbers(4));// 水价名称
		tenantPriceUpdateParam.setPriceParentId("");// 父级ID
		tenantPriceUpdateParam.setPriceVersion(RandomUtil.randomString(4));// 水价版本
		tenantPriceUpdateParam.setPriceVersionMemo(RandomUtil.randomString(4));// 版本说明
		tenantPriceUpdateParam.setMarketingAreaId(RandomUtil.randomString(4));// 营销区域
		tenantPriceUpdateParam.setPriceMemo(RandomUtil.randomString(4));// 备注
		
		log.info(JSON.toJSONString(tenantPriceUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantPrice/update/"+id)//
						.content(JSON.toJSONString(tenantPriceUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		TenantPriceAddParam tenantPriceAddParam = new TenantPriceAddParam();
		tenantPriceAddParam.setTenantId("AE6492EB900A4CEAB9C6E2DB3E03C344");// 租户ID
		tenantPriceAddParam.setPriceOrder(RandomUtil.randomInt(0,1000+1));// 排序
		tenantPriceAddParam.setPriceName("水价名称"+"-"+"测试"+"-"+RandomUtil.randomNumbers(4));// 水价名称
		tenantPriceAddParam.setPriceParentId("");// 父级ID
		tenantPriceAddParam.setPriceVersion(RandomUtil.randomString(4));// 水价版本
		tenantPriceAddParam.setPriceVersionMemo(RandomUtil.randomString(4));// 版本说明
		tenantPriceAddParam.setMarketingAreaId(RandomUtil.randomString(4));// 营销区域
		tenantPriceAddParam.setPriceMemo(RandomUtil.randomString(4));// 备注
		
		log.info(JSON.toJSONString(tenantPriceAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantPrice/create")//
						.content(JSON.toJSONString(tenantPriceAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}