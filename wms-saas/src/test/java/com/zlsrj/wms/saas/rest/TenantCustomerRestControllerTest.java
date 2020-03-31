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
		String id = "";
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
		
		// params.add("id",TestCaseUtil.id());// 用户ID
		// params.add("tenantId",RandomUtil.randomString(4));// 租户ID
		// params.add("customerCode",RandomUtil.randomString(4));// 用户号
		// params.add("customerName",TestCaseUtil.name());// 用户名称
		// params.add("customerAddress",TestCaseUtil.address());// 用户地址
		// params.add("customerStatus",RandomUtil.randomInt(0,1+1));// 用户状态（1：正常；2：暂停；3：销户）
		// params.add("customerType",RandomUtil.randomInt(0,1+1));// 用户类别
		// params.add("customerRegisterTime",new Date());// 建档时间
		// params.add("customerRegisterDate",new Date());// 立户日期
		// params.add("customerCreditRating",RandomUtil.randomInt(0,1000+1));// 信用等级
		// params.add("customerRatingDate",new Date());// 最近评估日期
		// params.add("customerBalanceAmt",new BigDecimal(0));// 预存余额
		// params.add("customerFreezeBalance",new BigDecimal(0));// 预存冻结金额
		// params.add("customerOweAmt",new BigDecimal(0));// 欠费金额
		// params.add("customerPenaltyAmt",new BigDecimal(0));// 违约金
		// params.add("customerMemo",RandomUtil.randomString(4));// 用户备注
		// params.add("addTime",new Date());// 数据新增时间
		// params.add("updateTime",new Date());// 数据修改时间

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customers").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantCustomer tenantInfo = TenantCustomer.builder()//
				.id(TestCaseUtil.id())// 用户ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.customerName(TestCaseUtil.name())// 用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：销户）
				.customerType(RandomUtil.randomString(4))// 用户类别
				.customerRegisterTime(new Date())// 建档时间
				.customerRegisterDate(new Date())// 立户日期
				.customerCreditRating(RandomUtil.randomInt(0,1000+1))// 信用等级
				.customerRatingDate(new Date())// 最近评估日期
				.customerBalanceAmt(new BigDecimal(0))// 预存余额
				.customerFreezeBalance(new BigDecimal(0))// 预存冻结金额
				.customerOweAmt(new BigDecimal(0))// 欠费金额
				.customerPenaltyAmt(new BigDecimal(0))// 违约金
				.customerMemo(RandomUtil.randomString(4))// 用户备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
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
		String id = "";

		TenantCustomer tenantInfo = TenantCustomer.builder()//
				//.id(TestCaseUtil.id())// 用户ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.customerName(TestCaseUtil.name())// 用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：销户）
				.customerType(RandomUtil.randomString(4))// 用户类别
				.customerRegisterTime(new Date())// 建档时间
				.customerRegisterDate(new Date())// 立户日期
				.customerCreditRating(RandomUtil.randomInt(0,1000+1))// 信用等级
				.customerRatingDate(new Date())// 最近评估日期
				.customerBalanceAmt(new BigDecimal(0))// 预存余额
				.customerFreezeBalance(new BigDecimal(0))// 预存冻结金额
				.customerOweAmt(new BigDecimal(0))// 欠费金额
				.customerPenaltyAmt(new BigDecimal(0))// 违约金
				.customerMemo(RandomUtil.randomString(4))// 用户备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
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
		String id = "";

		TenantCustomer tenantInfo = TenantCustomer.builder()//
				//.tenantId(RandomUtil.randomString(4))// 租户ID
				//.customerCode(RandomUtil.randomString(4))// 用户号
				//.customerName(TestCaseUtil.name())// 用户名称
				//.customerAddress(TestCaseUtil.address())// 用户地址
				//.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：销户）
				//.customerType(RandomUtil.randomInt(0,1+1))// 用户类别
				//.customerRegisterTime(new Date())// 建档时间
				//.customerRegisterDate(new Date())// 立户日期
				//.customerCreditRating(RandomUtil.randomInt(0,1000+1))// 信用等级
				//.customerRatingDate(new Date())// 最近评估日期
				//.customerBalanceAmt(new BigDecimal(0))// 预存余额
				//.customerFreezeBalance(new BigDecimal(0))// 预存冻结金额
				//.customerOweAmt(new BigDecimal(0))// 欠费金额
				//.customerPenaltyAmt(new BigDecimal(0))// 违约金
				//.customerMemo(RandomUtil.randomString(4))// 用户备注
				//.addTime(new Date())// 数据新增时间
				//.updateTime(new Date())// 数据修改时间
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
		String id = "";

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-customers" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
