package com.zlsrj.wms.saas.rest;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customers" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("customerCode",RandomUtil.randomString(4));// 用户代码
		// params.add("customerName",TestCaseUtil.name());// 用户名称
		// params.add("customerAddress",TestCaseUtil.address());// 用户地址
		// params.add("customerTypeId",RandomUtil.randomLong());// 用户类别ID
		// params.add("customerRegisterTime",new Date());// 建档时间
		// params.add("customerStatus",RandomUtil.randomInt(0,1+1));// 用户状态（1：正常；2：暂停；3：消户）
		// params.add("customerPaymentMethod",RandomUtil.randomInt(0,1000+1));// 用户缴费方式（1：坐收；2：走收；3：代扣；4：托收）
		// params.add("invoiceType",RandomUtil.randomInt(0,1+1));// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
		// params.add("invoiceName",TestCaseUtil.name());// 开票名称
		// params.add("invoiceTaxNo",RandomUtil.randomString(4));// 税号
		// params.add("invoiceAddress",TestCaseUtil.address());// 开票地址
		// params.add("invoiceTel",TestCaseUtil.tel());// 开票电话
		// params.add("invoiceBankCode",RandomUtil.randomString(4));// 开户行行号
		// params.add("invoiceBankName",TestCaseUtil.bankName());// 开户行名称
		// params.add("invoiceBankAccountNo",TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户行账号
		// params.add("customerBalanceMoney",new BigDecimal(0));// 用户预存余额
		// params.add("customerArrearsMoney",new BigDecimal(0));// 用户欠费金额

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customers").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantCustomer tenantInfo = TenantCustomer.builder()//
				.id(TestCaseUtil.id())// 
				.tenantId(RandomUtil.randomLong())// 租户ID
				.customerCode(RandomUtil.randomString(4))// 用户代码
				.customerName(TestCaseUtil.name())// 用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.customerTypeId(RandomUtil.randomLong())// 用户类别ID
				.customerRegisterTime(new Date())// 建档时间
				.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.customerPaymentMethod(RandomUtil.randomInt(0,1000+1))// 用户缴费方式（1：坐收；2：走收；3：代扣；4：托收）
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
				.customerBalanceMoney(new BigDecimal(0))// 用户预存余额
				.customerArrearsMoney(new BigDecimal(0))// 用户欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-customers").content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomer tenantInfo = TenantCustomer.builder()//
				//.id(TestCaseUtil.id())// 
				.tenantId(RandomUtil.randomLong())// 租户ID
				.customerCode(RandomUtil.randomString(4))// 用户代码
				.customerName(TestCaseUtil.name())// 用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.customerTypeId(RandomUtil.randomLong())// 用户类别ID
				.customerRegisterTime(new Date())// 建档时间
				.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.customerPaymentMethod(RandomUtil.randomInt(0,1000+1))// 用户缴费方式（1：坐收；2：走收；3：代扣；4：托收）
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
				.customerBalanceMoney(new BigDecimal(0))// 用户预存余额
				.customerArrearsMoney(new BigDecimal(0))// 用户欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-customers" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomer tenantInfo = TenantCustomer.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户ID
				//.customerCode(RandomUtil.randomString(4))// 用户代码
				//.customerName(TestCaseUtil.name())// 用户名称
				//.customerAddress(TestCaseUtil.address())// 用户地址
				//.customerTypeId(RandomUtil.randomLong())// 用户类别ID
				//.customerRegisterTime(new Date())// 建档时间
				//.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				//.customerPaymentMethod(RandomUtil.randomInt(0,1000+1))// 用户缴费方式（1：坐收；2：走收；3：代扣；4：托收）
				//.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				//.invoiceName(TestCaseUtil.name())// 开票名称
				//.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				//.invoiceAddress(TestCaseUtil.address())// 开票地址
				//.invoiceTel(TestCaseUtil.tel())// 开票电话
				//.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				//.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				//.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
				//.customerBalanceMoney(new BigDecimal(0))// 用户预存余额
				//.customerArrearsMoney(new BigDecimal(0))// 用户欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-customers" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-customers" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
