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
import com.zlsrj.wms.api.dto.TenantBookAddParam;
import com.zlsrj.wms.api.dto.TenantBookUpdateParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantBookControllerTest {
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
		TenantBookAddParam tenantBookAddParam = new TenantBookAddParam();
		tenantBookAddParam.setTenantId("23a60db88e184a3fa82d21dd4b0055c4");// 租户ID
//		tenantBookAddParam.setBookCode(RandomUtil.randomString(4));// 表册编号
		tenantBookAddParam.setBookName("表册名称"+"-"+"新增用例"+"-"+RandomUtil.randomNumbers(4));// 表册名称
		tenantBookAddParam.setBookReaderEmployeeId("62a6017cb94f4279867035dd57727362");// 抄表员
		tenantBookAddParam.setBookChargeEmployeeId("62a6017cb94f4279867035dd57727362");// 收费员
		tenantBookAddParam.setBookMarketingAreaId("220126d5e3a14cca93f5d87131c85cc6");// 营销区域
		tenantBookAddParam.setBookReadCycle(RandomUtil.randomInt(0,1000+1));// 抄表周期
		tenantBookAddParam.setBookLastMonth(RandomUtil.randomString(4));// 最后一次抄表月份
		tenantBookAddParam.setBookReadMonth(RandomUtil.randomString(4));// 下次抄表月份
		tenantBookAddParam.setBookSettleCycle(RandomUtil.randomInt(0,1000+1));// 结算周期
		tenantBookAddParam.setBookSettleLastMonth(RandomUtil.randomString(4));// 最后一次结算月份
		tenantBookAddParam.setBookSettleMonth(RandomUtil.randomString(4));// 下次结算月份
		tenantBookAddParam.setBookStatus(RandomUtil.randomInt(0,1+1));// 有效状态（1：可用；0：禁用）
		tenantBookAddParam.setBookReadStatus(RandomUtil.randomInt(1,2+1));// 表册状态（1：抄表进行中；2：抄表截止）
		tenantBookAddParam.setPriceCalss(RandomUtil.randomInt(0,1000+1));// 级次
		tenantBookAddParam.setPriceMemo(RandomUtil.randomString(4));// 备注
		
		log.info(JSON.toJSONString(tenantBookAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantBook/create")//
						.content(JSON.toJSONString(tenantBookAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "0597d83c48534ca4959ce19be548f7bd";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantBook/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		String id = "7aa249e9591b45248a9d677c775f868c";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantBook/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "23a60db88e184a3fa82d21dd4b0055c4";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantBook/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void countTest() throws Exception {
		String tenantId = "23a60db88e184a3fa82d21dd4b0055c4";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantBook/count")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "0b31d1c0a89a4a5eb0e481f417b0e9a9";
		log.info("id={}",id);
		
		TenantBookUpdateParam tenantBookUpdateParam = new TenantBookUpdateParam();
//		tenantBookUpdateParam.setBookCode(RandomUtil.randomString(4));// 表册编号
		tenantBookUpdateParam.setBookName("表册名称"+"-"+"新增用例"+"-"+RandomUtil.randomNumbers(4));// 表册名称
		tenantBookUpdateParam.setBookReaderEmployeeId("62a6017cb94f4279867035dd57727362");// 抄表员
		tenantBookUpdateParam.setBookChargeEmployeeId("62a6017cb94f4279867035dd57727362");// 收费员
		tenantBookUpdateParam.setBookMarketingAreaId("220126d5e3a14cca93f5d87131c85cc6");// 营销区域
		tenantBookUpdateParam.setBookReadCycle(RandomUtil.randomInt(0,1000+1));// 抄表周期
		tenantBookUpdateParam.setBookLastMonth(RandomUtil.randomString(4));// 最后一次抄表月份
		tenantBookUpdateParam.setBookReadMonth(RandomUtil.randomString(4));// 下次抄表月份
		tenantBookUpdateParam.setBookSettleCycle(RandomUtil.randomInt(0,1000+1));// 结算周期
		tenantBookUpdateParam.setBookSettleLastMonth(RandomUtil.randomString(4));// 最后一次结算月份
		tenantBookUpdateParam.setBookSettleMonth(RandomUtil.randomString(4));// 下次结算月份
		tenantBookUpdateParam.setBookStatus(RandomUtil.randomInt(0,1+1));// 有效状态（1：可用；0：禁用）
		tenantBookUpdateParam.setBookReadStatus(RandomUtil.randomInt(0,1+1));// 表册状态（1：抄表进行中；2：抄表截止）
		tenantBookUpdateParam.setPriceCalss(RandomUtil.randomInt(0,1000+1));// 级次
		tenantBookUpdateParam.setPriceMemo(RandomUtil.randomString(4));// 备注
		
		log.info(JSON.toJSONString(tenantBookUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantBook/update/"+id)//
						.content(JSON.toJSONString(tenantBookUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
}
