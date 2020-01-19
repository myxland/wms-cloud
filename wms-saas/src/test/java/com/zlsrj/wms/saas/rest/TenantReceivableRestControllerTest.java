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
import com.zlsrj.wms.api.entity.TenantReceivable;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantReceivableRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-receivables" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 应收账ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("receivableStatus",RandomUtil.randomInt(0,1+1));// 应收账状态（1：正常；2：被冲正；3：冲正负记录）
		// params.add("receivableType",RandomUtil.randomInt(0,1+1));// 应收类型（1：抄表；2：换表；3：追补）
		// params.add("departmentId",RandomUtil.randomLong());// 部门ID
		// params.add("bookletId",RandomUtil.randomLong());// 表册ID
		// params.add("bookletCode",RandomUtil.randomString(4));// 表册代码
		// params.add("customerId",RandomUtil.randomLong());// 用户ID
		// params.add("customerCode",RandomUtil.randomString(4));// 用户代码
		// params.add("customerName",TestCaseUtil.name());// 用户名称
		// params.add("customerAddress",TestCaseUtil.address());// 用户地址
		// params.add("meterId",RandomUtil.randomLong());// 水表ID
		// params.add("meterCode",RandomUtil.randomString(4));// 水表代码
		// params.add("meterAddress",TestCaseUtil.address());// 表具地址
		// params.add("readEmployeeId",RandomUtil.randomLong());// 抄表员ID
		// params.add("receivableTime",new Date());// 应收账时间
		// params.add("settleStartTime",new Date());// 结算开始时间
		// params.add("settleStartPointer",new BigDecimal(0));// 结算开始指针
		// params.add("settleEndTime",new Date());// 结算截止时间
		// params.add("settleEndPointer",new BigDecimal(0));// 结算截止指针
		// params.add("settleWaters",new BigDecimal(0));// 应结算水量
		// params.add("priceTypeId",RandomUtil.randomLong());// 价格类别ID
		// params.add("receivableWaters",new BigDecimal(0));// 应收水量
		// params.add("receivableMoney",new BigDecimal(0));// 应收金额
		// params.add("arrearsMoney",new BigDecimal(0));// 欠费金额

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-receivables").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantReceivable tenantInfo = TenantReceivable.builder()//
				.id(TestCaseUtil.id())// 应收账ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.receivableStatus(RandomUtil.randomInt(0,1+1))// 应收账状态（1：正常；2：被冲正；3：冲正负记录）
				.receivableType(RandomUtil.randomInt(0,1+1))// 应收类型（1：抄表；2：换表；3：追补）
				.departmentId(RandomUtil.randomLong())// 部门ID
				.bookletId(RandomUtil.randomLong())// 表册ID
				.bookletCode(RandomUtil.randomString(4))// 表册代码
				.customerId(RandomUtil.randomLong())// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户代码
				.customerName(TestCaseUtil.name())// 用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.meterId(RandomUtil.randomLong())// 水表ID
				.meterCode(RandomUtil.randomString(4))// 水表代码
				.meterAddress(TestCaseUtil.address())// 表具地址
				.readEmployeeId(RandomUtil.randomLong())// 抄表员ID
				.receivableTime(new Date())// 应收账时间
				.settleStartTime(new Date())// 结算开始时间
				.settleStartPointer(new BigDecimal(0))// 结算开始指针
				.settleEndTime(new Date())// 结算截止时间
				.settleEndPointer(new BigDecimal(0))// 结算截止指针
				.settleWaters(new BigDecimal(0))// 应结算水量
				.priceTypeId(RandomUtil.randomLong())// 价格类别ID
				.receivableWaters(new BigDecimal(0))// 应收水量
				.receivableMoney(new BigDecimal(0))// 应收金额
				.arrearsMoney(new BigDecimal(0))// 欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-receivables").content(JSON.toJSONString(tenantInfo)) //
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

		TenantReceivable tenantInfo = TenantReceivable.builder()//
				//.id(TestCaseUtil.id())// 应收账ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.receivableStatus(RandomUtil.randomInt(0,1+1))// 应收账状态（1：正常；2：被冲正；3：冲正负记录）
				.receivableType(RandomUtil.randomInt(0,1+1))// 应收类型（1：抄表；2：换表；3：追补）
				.departmentId(RandomUtil.randomLong())// 部门ID
				.bookletId(RandomUtil.randomLong())// 表册ID
				.bookletCode(RandomUtil.randomString(4))// 表册代码
				.customerId(RandomUtil.randomLong())// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户代码
				.customerName(TestCaseUtil.name())// 用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.meterId(RandomUtil.randomLong())// 水表ID
				.meterCode(RandomUtil.randomString(4))// 水表代码
				.meterAddress(TestCaseUtil.address())// 表具地址
				.readEmployeeId(RandomUtil.randomLong())// 抄表员ID
				.receivableTime(new Date())// 应收账时间
				.settleStartTime(new Date())// 结算开始时间
				.settleStartPointer(new BigDecimal(0))// 结算开始指针
				.settleEndTime(new Date())// 结算截止时间
				.settleEndPointer(new BigDecimal(0))// 结算截止指针
				.settleWaters(new BigDecimal(0))// 应结算水量
				.priceTypeId(RandomUtil.randomLong())// 价格类别ID
				.receivableWaters(new BigDecimal(0))// 应收水量
				.receivableMoney(new BigDecimal(0))// 应收金额
				.arrearsMoney(new BigDecimal(0))// 欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-receivables" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantReceivable tenantInfo = TenantReceivable.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户ID
				//.receivableStatus(RandomUtil.randomInt(0,1+1))// 应收账状态（1：正常；2：被冲正；3：冲正负记录）
				//.receivableType(RandomUtil.randomInt(0,1+1))// 应收类型（1：抄表；2：换表；3：追补）
				//.departmentId(RandomUtil.randomLong())// 部门ID
				//.bookletId(RandomUtil.randomLong())// 表册ID
				//.bookletCode(RandomUtil.randomString(4))// 表册代码
				//.customerId(RandomUtil.randomLong())// 用户ID
				//.customerCode(RandomUtil.randomString(4))// 用户代码
				//.customerName(TestCaseUtil.name())// 用户名称
				//.customerAddress(TestCaseUtil.address())// 用户地址
				//.meterId(RandomUtil.randomLong())// 水表ID
				//.meterCode(RandomUtil.randomString(4))// 水表代码
				//.meterAddress(TestCaseUtil.address())// 表具地址
				//.readEmployeeId(RandomUtil.randomLong())// 抄表员ID
				//.receivableTime(new Date())// 应收账时间
				//.settleStartTime(new Date())// 结算开始时间
				//.settleStartPointer(new BigDecimal(0))// 结算开始指针
				//.settleEndTime(new Date())// 结算截止时间
				//.settleEndPointer(new BigDecimal(0))// 结算截止指针
				//.settleWaters(new BigDecimal(0))// 应结算水量
				//.priceTypeId(RandomUtil.randomLong())// 价格类别ID
				//.receivableWaters(new BigDecimal(0))// 应收水量
				//.receivableMoney(new BigDecimal(0))// 应收金额
				//.arrearsMoney(new BigDecimal(0))// 欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-receivables" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-receivables" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
