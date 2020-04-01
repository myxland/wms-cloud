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
import com.zlsrj.wms.api.dto.TenantCustomerContactsAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerContactsUpdateParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerContactsControllerTest {
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
		for(int i=0;i<3;i++) {
			TenantCustomerContactsAddParam tenantCustomerContactsAddParam = new TenantCustomerContactsAddParam();
			tenantCustomerContactsAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
			tenantCustomerContactsAddParam.setCustomerId(RandomUtil.randomString(4));// 用户ID
			tenantCustomerContactsAddParam.setCustomerCode(RandomUtil.randomString(4));// 用户号
			tenantCustomerContactsAddParam.setContactsName(TestCaseUtil.name());// 联系人姓名
			tenantCustomerContactsAddParam.setContactsAddress(TestCaseUtil.address());// 联系人地址
			tenantCustomerContactsAddParam.setContactsMain(RandomUtil.randomInt(0,1+1));// 主联系人（1：是；0：否）
			tenantCustomerContactsAddParam.setContactsSex(RandomUtil.randomInt(0,1+1));// 性别（1：男；0：女）
			tenantCustomerContactsAddParam.setContactsBirthday(TestCaseUtil.dateBefore());// 出生日期
			tenantCustomerContactsAddParam.setContactsMobile(TestCaseUtil.mobile());// 手机号码
			tenantCustomerContactsAddParam.setContactsTel(TestCaseUtil.tel());// 固定电话
			tenantCustomerContactsAddParam.setContactsEmail(TestCaseUtil.email(null));// 邮箱地址
			tenantCustomerContactsAddParam.setContactsWx(TestCaseUtil.wx());// 微信号
			tenantCustomerContactsAddParam.setContactsQq(TestCaseUtil.qq());// QQ号
			tenantCustomerContactsAddParam.setContactsMemo(RandomUtil.randomString(4));// 备注
			
			log.info(JSON.toJSONString(tenantCustomerContactsAddParam));
			
			String responseString = mockMvc.perform(//
					MockMvcRequestBuilders.post("/tenantCustomerContacts/create")//
							.content(JSON.toJSONString(tenantCustomerContactsAddParam))//
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
				MockMvcRequestBuilders.get("/tenantCustomerContacts/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		String id = "";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomerContacts/"+id)//
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

		params.add("queryCol", "contacts_address");
		params.add("queryType", "like");
		params.add("queryValue", "号");
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantCustomerContacts/list")//
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
	                        MockMvcRequestBuilders.get("/tenantCustomerContacts/page")//
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
				MockMvcRequestBuilders.get("/tenantCustomerContacts/count")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		TenantCustomerContactsUpdateParam tenantCustomerContactsUpdateParam = new TenantCustomerContactsUpdateParam();
		tenantCustomerContactsUpdateParam.setCustomerId(RandomUtil.randomString(4));// 用户ID
		tenantCustomerContactsUpdateParam.setCustomerCode(RandomUtil.randomString(4));// 用户号
		tenantCustomerContactsUpdateParam.setContactsName("联系人姓名"+"-"+"新增用例"+"-"+RandomUtil.randomNumbers(4));// 联系人姓名
		tenantCustomerContactsUpdateParam.setContactsAddress(TestCaseUtil.address());// 联系人地址
		tenantCustomerContactsUpdateParam.setContactsMain(RandomUtil.randomInt(0,1000+1));// 主联系人（1：是；0：否）
		tenantCustomerContactsUpdateParam.setContactsSex(RandomUtil.randomInt(0,1000+1));// 性别（1：男；0：女）
		tenantCustomerContactsUpdateParam.setContactsBirthday(new Date());// 出生日期
		tenantCustomerContactsUpdateParam.setContactsMobile(TestCaseUtil.mobile());// 手机号码
		tenantCustomerContactsUpdateParam.setContactsTel(TestCaseUtil.tel());// 固定电话
		tenantCustomerContactsUpdateParam.setContactsEmail(TestCaseUtil.email(null));// 邮箱地址
		tenantCustomerContactsUpdateParam.setContactsWx(RandomUtil.randomString(4));// 微信号
		tenantCustomerContactsUpdateParam.setContactsQq(TestCaseUtil.qq());// QQ号
		tenantCustomerContactsUpdateParam.setContactsMemo(RandomUtil.randomString(4));// 备注
		
		log.info(JSON.toJSONString(tenantCustomerContactsUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantCustomerContacts/update/"+id)//
						.content(JSON.toJSONString(tenantCustomerContactsUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
}
