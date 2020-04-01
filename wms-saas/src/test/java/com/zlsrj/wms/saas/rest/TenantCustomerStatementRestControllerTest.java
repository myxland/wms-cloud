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
import com.zlsrj.wms.api.entity.TenantCustomerStatement;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerStatementRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-statements" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 用户结算ID
		// params.add("tenantId",RandomUtil.randomString(4));// 租户ID
		// params.add("customerId",RandomUtil.randomString(4));// 用户ID
		// params.add("customerCode",RandomUtil.randomString(4));// 用户号
		// params.add("statementMethod",RandomUtil.randomInt(0,1000+1));// 结算方式（1：坐收；2：托收；3：代扣；4：走收）
		// params.add("statementBankId",RandomUtil.randomString(4));// 付款银行
		// params.add("entrustAgreementNo",RandomUtil.randomString(4));// 委托授权号
		// params.add("entrustCode",RandomUtil.randomString(4));// 托收号
		// params.add("statementAccountBankId",RandomUtil.randomLong());// 开户银行
		// params.add("statementAccountName",TestCaseUtil.name());// 开户名称
		// params.add("statementAccountNo",TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户账号
		// params.add("statementRegisterDate",new Date());// 签约日期
		// params.add("statementMemo",RandomUtil.randomString(4));// 备注
		// params.add("addTime",new Date());// 数据新增时间
		// params.add("updateTime",new Date());// 数据修改时间

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-statements").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantCustomerStatement tenantInfo = TenantCustomerStatement.builder()//
				.id(TestCaseUtil.id())// 用户结算ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.statementMethod(RandomUtil.randomInt(0,1000+1))// 结算方式（1：坐收；2：托收；3：代扣；4：走收）
				.statementBankId(RandomUtil.randomString(4))// 付款银行
				.entrustAgreementNo(RandomUtil.randomString(4))// 委托授权号
				.entrustCode(RandomUtil.randomString(4))// 托收号
				.statementAccountBankId(RandomUtil.randomLong())// 开户银行
				.statementAccountName(TestCaseUtil.name())// 开户名称
				.statementAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.statementRegisterDate(new Date())// 签约日期
				.statementMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-customer-statements").content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerStatement tenantInfo = TenantCustomerStatement.builder()//
				//.id(TestCaseUtil.id())// 用户结算ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.statementMethod(RandomUtil.randomInt(0,1000+1))// 结算方式（1：坐收；2：托收；3：代扣；4：走收）
				.statementBankId(RandomUtil.randomString(4))// 付款银行
				.entrustAgreementNo(RandomUtil.randomString(4))// 委托授权号
				.entrustCode(RandomUtil.randomString(4))// 托收号
				.statementAccountBankId(RandomUtil.randomLong())// 开户银行
				.statementAccountName(TestCaseUtil.name())// 开户名称
				.statementAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.statementRegisterDate(new Date())// 签约日期
				.statementMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-customer-statements" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerStatement tenantInfo = TenantCustomerStatement.builder()//
				//.tenantId(RandomUtil.randomString(4))// 租户ID
				//.customerId(RandomUtil.randomString(4))// 用户ID
				//.customerCode(RandomUtil.randomString(4))// 用户号
				//.statementMethod(RandomUtil.randomInt(0,1000+1))// 结算方式（1：坐收；2：托收；3：代扣；4：走收）
				//.statementBankId(RandomUtil.randomString(4))// 付款银行
				//.entrustAgreementNo(RandomUtil.randomString(4))// 委托授权号
				//.entrustCode(RandomUtil.randomString(4))// 托收号
				//.statementAccountBankId(RandomUtil.randomLong())// 开户银行
				//.statementAccountName(TestCaseUtil.name())// 开户名称
				//.statementAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				//.statementRegisterDate(new Date())// 签约日期
				//.statementMemo(RandomUtil.randomString(4))// 备注
				//.addTime(new Date())// 数据新增时间
				//.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-customer-statements" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-customer-statements" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
