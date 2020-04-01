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
import com.zlsrj.wms.api.dto.TenantCustomerStatementAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerStatementUpdateParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerStatementControllerTest {
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
		for(int i=0;i<8;i++) {
			TenantCustomerStatementAddParam tenantCustomerStatementAddParam = new TenantCustomerStatementAddParam();
			tenantCustomerStatementAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
			tenantCustomerStatementAddParam.setCustomerId(RandomUtil.randomString(4));// 用户ID
			tenantCustomerStatementAddParam.setCustomerCode(RandomUtil.randomString(4));// 用户号
			tenantCustomerStatementAddParam.setStatementMethod(RandomUtil.randomInt(1,4+1));// 结算方式（1：坐收；2：托收；3：代扣；4：走收）
			tenantCustomerStatementAddParam.setStatementBankId(TestCaseUtil.bank());// 付款银行
			tenantCustomerStatementAddParam.setEntrustAgreementNo(RandomUtil.randomNumbers(12));// 委托授权号
			tenantCustomerStatementAddParam.setEntrustCode(RandomUtil.randomNumbers(6));// 托收号
			tenantCustomerStatementAddParam.setStatementAccountBankId(new Long(RandomUtil.randomInt(1000000,10000000)));// 开户银行
			tenantCustomerStatementAddParam.setStatementAccountName(TestCaseUtil.name());// 开户名称
			tenantCustomerStatementAddParam.setStatementAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户账号
			tenantCustomerStatementAddParam.setStatementRegisterDate(TestCaseUtil.dateBefore());// 签约日期
			tenantCustomerStatementAddParam.setStatementMemo(RandomUtil.randomString(4));// 备注
			
			log.info(JSON.toJSONString(tenantCustomerStatementAddParam));
			
			String responseString = mockMvc.perform(//
					MockMvcRequestBuilders.post("/tenantCustomerStatement/create")//
							.content(JSON.toJSONString(tenantCustomerStatementAddParam))//
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
				MockMvcRequestBuilders.get("/tenantCustomerStatement/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		String id = "";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomerStatement/"+id)//
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

		params.add("queryCol", "statement_account_no");
		params.add("queryType", "like");
		params.add("queryValue", "2068");
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomerStatement/list")//
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
	                        MockMvcRequestBuilders.get("/tenantCustomerStatement/page")//
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
				MockMvcRequestBuilders.get("/tenantCustomerStatement/count")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		TenantCustomerStatementUpdateParam tenantCustomerStatementUpdateParam = new TenantCustomerStatementUpdateParam();
		tenantCustomerStatementUpdateParam.setCustomerId(RandomUtil.randomString(4));// 用户ID
		tenantCustomerStatementUpdateParam.setCustomerCode(RandomUtil.randomString(4));// 用户号
		tenantCustomerStatementUpdateParam.setStatementMethod(RandomUtil.randomInt(0,1000+1));// 结算方式（1：坐收；2：托收；3：代扣；4：走收）
		tenantCustomerStatementUpdateParam.setStatementBankId(RandomUtil.randomString(4));// 付款银行
		tenantCustomerStatementUpdateParam.setEntrustAgreementNo(RandomUtil.randomString(4));// 委托授权号
		tenantCustomerStatementUpdateParam.setEntrustCode(RandomUtil.randomString(4));// 托收号
		tenantCustomerStatementUpdateParam.setStatementAccountBankId(RandomUtil.randomLong());// 开户银行
		tenantCustomerStatementUpdateParam.setStatementAccountName("开户名称"+"-"+"新增用例"+"-"+RandomUtil.randomNumbers(4));// 开户名称
		tenantCustomerStatementUpdateParam.setStatementAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户账号
		tenantCustomerStatementUpdateParam.setStatementRegisterDate(new Date());// 签约日期
		tenantCustomerStatementUpdateParam.setStatementMemo(RandomUtil.randomString(4));// 备注
		
		log.info(JSON.toJSONString(tenantCustomerStatementUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantCustomerStatement/update/"+id)//
						.content(JSON.toJSONString(tenantCustomerStatementUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
}
