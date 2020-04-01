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
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceUpdateParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerInvoiceControllerTest {
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
		for(int i=0;i<5;i++) {
			TenantCustomerInvoiceAddParam tenantCustomerInvoiceAddParam = new TenantCustomerInvoiceAddParam();
			tenantCustomerInvoiceAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
			tenantCustomerInvoiceAddParam.setCustomerId(RandomUtil.randomString(4));// 用户ID
			tenantCustomerInvoiceAddParam.setCustomerCode(RandomUtil.randomString(4));// 用户号
			tenantCustomerInvoiceAddParam.setInvoiceType(RandomUtil.randomInt(1,3+1));// 开票类型（1：增值税普通纸质发票；2：增值税普通电子发票；3：增值税专用纸质发票）
			tenantCustomerInvoiceAddParam.setInvoiceName(TestCaseUtil.name());// 开票名称
			tenantCustomerInvoiceAddParam.setInvoiceTaxNo(RandomUtil.randomString(4));// 开票税号
			tenantCustomerInvoiceAddParam.setInvoiceAddress(TestCaseUtil.address());// 开票地址
			tenantCustomerInvoiceAddParam.setInvoiceTel(TestCaseUtil.tel());// 开票电话
			tenantCustomerInvoiceAddParam.setInvoiceBank(TestCaseUtil.bank());// 开户银行
			tenantCustomerInvoiceAddParam.setInvoiceAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户账号
			tenantCustomerInvoiceAddParam.setInvoiceMemo(RandomUtil.randomString(4));// 备注
			
			log.info(JSON.toJSONString(tenantCustomerInvoiceAddParam));
			
			String responseString = mockMvc.perform(//
					MockMvcRequestBuilders.post("/tenantCustomerInvoice/create")//
							.content(JSON.toJSONString(tenantCustomerInvoiceAddParam))//
							.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
							.accept(MediaType.APPLICATION_JSON_UTF8)//
			).andReturn().getResponse().getContentAsString();
			log.info(responseString);
		}
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomerInvoice/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		String id = "";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomerInvoice/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "030c9a1874b14034bccbb73912e56a48";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		params.add("queryCol", "customer_code");
		params.add("queryType", "=");
		params.add("queryValue", "250000008");

		params.add("queryCol", "invoice_name");
		params.add("queryType", "like");
		params.add("queryValue", "绍齐");
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomerInvoice/list")//
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
	                        MockMvcRequestBuilders.get("/tenantCustomerInvoice/page")//
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
				MockMvcRequestBuilders.get("/tenantCustomerInvoice/count")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		TenantCustomerInvoiceUpdateParam tenantCustomerInvoiceUpdateParam = new TenantCustomerInvoiceUpdateParam();
		tenantCustomerInvoiceUpdateParam.setCustomerId(RandomUtil.randomString(4));// 用户ID
		tenantCustomerInvoiceUpdateParam.setCustomerCode(RandomUtil.randomString(4));// 用户号
		tenantCustomerInvoiceUpdateParam.setInvoiceType(RandomUtil.randomInt(0,1+1));// 开票类型（1：增值税普通纸质发票；2：增值税普通电子发票；3：增值税专用纸质发票）
		tenantCustomerInvoiceUpdateParam.setInvoiceName("开票名称"+"-"+"新增用例"+"-"+RandomUtil.randomNumbers(4));// 开票名称
		tenantCustomerInvoiceUpdateParam.setInvoiceTaxNo(RandomUtil.randomString(4));// 开票税号
		tenantCustomerInvoiceUpdateParam.setInvoiceAddress(TestCaseUtil.address());// 开票地址
		tenantCustomerInvoiceUpdateParam.setInvoiceTel(TestCaseUtil.tel());// 开票电话
		tenantCustomerInvoiceUpdateParam.setInvoiceBank(RandomUtil.randomString(4));// 开户银行
		tenantCustomerInvoiceUpdateParam.setInvoiceAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户账号
		tenantCustomerInvoiceUpdateParam.setInvoiceMemo(RandomUtil.randomString(4));// 备注
		
		log.info(JSON.toJSONString(tenantCustomerInvoiceUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantCustomerInvoice/update/"+id)//
						.content(JSON.toJSONString(tenantCustomerInvoiceUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
}
