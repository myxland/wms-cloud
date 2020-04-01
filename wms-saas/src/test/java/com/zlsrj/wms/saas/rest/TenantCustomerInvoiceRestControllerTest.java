package com.zlsrj.wms.saas.rest;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.entity.TenantCustomerInvoice;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerInvoiceRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-invoices" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 用户开票ID
		// params.add("tenantId",RandomUtil.randomString(4));// 租户ID
		// params.add("customerId",RandomUtil.randomString(4));// 用户ID
		// params.add("customerCode",RandomUtil.randomString(4));// 用户号
		// params.add("invoiceType",RandomUtil.randomInt(0,1+1));// 开票类型（1：增值税普通纸质发票；2：增值税普通电子发票；3：增值税专用纸质发票）
		// params.add("invoiceName",TestCaseUtil.name());// 开票名称
		// params.add("invoiceTaxNo",RandomUtil.randomString(4));// 开票税号
		// params.add("invoiceAddress",TestCaseUtil.address());// 开票地址
		// params.add("invoiceTel",TestCaseUtil.tel());// 开票电话
		// params.add("invoiceBank",RandomUtil.randomString(4));// 开户银行
		// params.add("invoiceAccountNo",TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户账号
		// params.add("invoiceMemo",RandomUtil.randomString(4));// 备注
		// params.add("addTime",new Date());// 数据新增时间
		// params.add("updateTime",new Date());// 数据修改时间

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-invoices").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantCustomerInvoice tenantInfo = TenantCustomerInvoice.builder()//
				.id(TestCaseUtil.id())// 用户开票ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类型（1：增值税普通纸质发票；2：增值税普通电子发票；3：增值税专用纸质发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 开票税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBank(RandomUtil.randomString(4))// 开户银行
				.invoiceAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.invoiceMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-customer-invoices").content(JSON.toJSONString(tenantInfo)) //
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updateByIdTest() throws Exception {
		String id = "";

		TenantCustomerInvoice tenantInfo = TenantCustomerInvoice.builder()//
				//.id(TestCaseUtil.id())// 用户开票ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类型（1：增值税普通纸质发票；2：增值税普通电子发票；3：增值税专用纸质发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 开票税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBank(RandomUtil.randomString(4))// 开户银行
				.invoiceAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.invoiceMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-customer-invoices" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updatePatchById() throws Exception {
		String id = "";

		TenantCustomerInvoice tenantInfo = TenantCustomerInvoice.builder()//
				//.tenantId(RandomUtil.randomString(4))// 租户ID
				//.customerId(RandomUtil.randomString(4))// 用户ID
				//.customerCode(RandomUtil.randomString(4))// 用户号
				//.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类型（1：增值税普通纸质发票；2：增值税普通电子发票；3：增值税专用纸质发票）
				//.invoiceName(TestCaseUtil.name())// 开票名称
				//.invoiceTaxNo(RandomUtil.randomString(4))// 开票税号
				//.invoiceAddress(TestCaseUtil.address())// 开票地址
				//.invoiceTel(TestCaseUtil.tel())// 开票电话
				//.invoiceBank(RandomUtil.randomString(4))// 开户银行
				//.invoiceAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				//.invoiceMemo(RandomUtil.randomString(4))// 备注
				//.addTime(new Date())// 数据新增时间
				//.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-customer-invoices" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void removeById() throws Exception {
		String id = "";

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-customer-invoices" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
