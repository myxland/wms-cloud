package com.zlsrj.wms.tenant.rest;


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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.entity.TenantInvoice;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantInvoiceRestControllerTest {
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
		Long id = 1L;
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-invoices" + "/" + id))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void pageTest() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("page", "1");
		params.add("rows", "10");
		params.add("sort", "id");
		params.add("order", "desc");
		
		// params.add("id",TestCaseUtil.id());// 租户编号
		// params.add("tenantId",RandomUtil.randomLong());// 租户编号
		// params.add("creditNumber",RandomUtil.randomString(4));// 税号
		// params.add("invoiceAddress",TestCaseUtil.address());// 开票地址
		// params.add("bankNo",TestCaseUtil.bankNo());// 开户行行号
		// params.add("bankName",TestCaseUtil.bankName());// 开户行名称
		// params.add("accountNo",TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户账号

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-invoices").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantInvoice tenantInfo = TenantInvoice.builder()//
				.id(TestCaseUtil.id())// 租户编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.creditNumber(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.bankNo(TestCaseUtil.bankNo())// 开户行行号
				.bankName(TestCaseUtil.bankName())// 开户行名称
				.accountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-invoices").content(JSON.toJSONString(tenantInfo)) //
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updateByIdTest() throws Exception {
		Long id = 1L;

		TenantInvoice tenantInfo = TenantInvoice.builder()//
				//.id(TestCaseUtil.id())// 租户编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.creditNumber(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.bankNo(TestCaseUtil.bankNo())// 开户行行号
				.bankName(TestCaseUtil.bankName())// 开户行名称
				.accountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-invoices" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updatePatchById() throws Exception {
		Long id = 1L;

		TenantInvoice tenantInfo = TenantInvoice.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.creditNumber(RandomUtil.randomString(4))// 税号
				//.invoiceAddress(TestCaseUtil.address())// 开票地址
				//.bankNo(TestCaseUtil.bankNo())// 开户行行号
				//.bankName(TestCaseUtil.bankName())// 开户行名称
				//.accountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-invoices" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void removeById() throws Exception {
		Long id = 1L;

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-invoices" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
