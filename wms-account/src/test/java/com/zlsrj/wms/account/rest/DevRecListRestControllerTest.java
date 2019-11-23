package com.zlsrj.wms.account.rest;

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
import com.zlsrj.wms.api.entity.DevRecList;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DevRecListRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/dev-rec-lists" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 系统编号
		// params.add("tenantId",RandomUtil.randomLong());// 租户编号
		// params.add("recFlag",RandomUtil.randomInt(0,1000+1));// 应收账标识（1：正常；2：被冲正；3：冲正负记录）
		// params.add("recType",RandomUtil.randomInt(0,1+1));// 应收来源（1：抄表；2：换表）
		// params.add("deptId",RandomUtil.randomLong());// 部门编号
		// params.add("bookletId",RandomUtil.randomLong());// 表册编号
		// params.add("custId",RandomUtil.randomLong());// 用户编号
		// params.add("custName",TestCaseUtil.name());// 用户名称
		// params.add("custAddress",TestCaseUtil.address());// 用户地址
		// params.add("devId",RandomUtil.randomLong());// 表具系统编号
		// params.add("devAddress",TestCaseUtil.address());// 表具地址
		// params.add("readMonth",new Date());// 应收月份
		// params.add("recMonth",new Date());// 应收账务统计月份
		// params.add("businessId",RandomUtil.randomLong());// 业务（抄表、换表）流水号
		// params.add("reader",RandomUtil.randomLong());// 抄表员编号
		// params.add("calcDate",new Date());// 计费时间
		// params.add("lastDate",new Date());// 上次计费日期
		// params.add("lastCode",new BigDecimal(0));// 起数
		// params.add("currDate",new Date());// 当前计费日期
		// params.add("currCode",new BigDecimal(0));// 止数
		// params.add("waterScale",new BigDecimal(0));// 用量占比
		// params.add("useNum",new BigDecimal(0));// 计费水量
		// params.add("priceTypeId",RandomUtil.randomLong());// 价格编号
		// params.add("recMoney",new BigDecimal(0));// 应收金额
		// params.add("dueMoney",new BigDecimal(0));// 欠费金额

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/dev-rec-lists").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		DevRecList tenantInfo = DevRecList.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.recFlag(RandomUtil.randomInt(0,1000+1))// 应收账标识（1：正常；2：被冲正；3：冲正负记录）
				.recType(RandomUtil.randomInt(0,1+1))// 应收来源（1：抄表；2：换表）
				.deptId(RandomUtil.randomLong())// 部门编号
				.bookletId(RandomUtil.randomLong())// 表册编号
				.custId(RandomUtil.randomLong())// 用户编号
				.custName(TestCaseUtil.name())// 用户名称
				.custAddress(TestCaseUtil.address())// 用户地址
				.devId(RandomUtil.randomLong())// 表具系统编号
				.devAddress(TestCaseUtil.address())// 表具地址
				.readMonth(new Date())// 应收月份
				.recMonth(new Date())// 应收账务统计月份
				.businessId(RandomUtil.randomLong())// 业务（抄表、换表）流水号
				.reader(RandomUtil.randomLong())// 抄表员编号
				.calcDate(new Date())// 计费时间
				.lastDate(new Date())// 上次计费日期
				.lastCode(new BigDecimal(0))// 起数
				.currDate(new Date())// 当前计费日期
				.currCode(new BigDecimal(0))// 止数
				.waterScale(new BigDecimal(0))// 用量占比
				.useNum(new BigDecimal(0))// 计费水量
				.priceTypeId(RandomUtil.randomLong())// 价格编号
				.recMoney(new BigDecimal(0))// 应收金额
				.dueMoney(new BigDecimal(0))// 欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/dev-rec-lists").content(JSON.toJSONString(tenantInfo)) //
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

		DevRecList tenantInfo = DevRecList.builder()//
				//.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.recFlag(RandomUtil.randomInt(0,1000+1))// 应收账标识（1：正常；2：被冲正；3：冲正负记录）
				.recType(RandomUtil.randomInt(0,1+1))// 应收来源（1：抄表；2：换表）
				.deptId(RandomUtil.randomLong())// 部门编号
				.bookletId(RandomUtil.randomLong())// 表册编号
				.custId(RandomUtil.randomLong())// 用户编号
				.custName(TestCaseUtil.name())// 用户名称
				.custAddress(TestCaseUtil.address())// 用户地址
				.devId(RandomUtil.randomLong())// 表具系统编号
				.devAddress(TestCaseUtil.address())// 表具地址
				.readMonth(new Date())// 应收月份
				.recMonth(new Date())// 应收账务统计月份
				.businessId(RandomUtil.randomLong())// 业务（抄表、换表）流水号
				.reader(RandomUtil.randomLong())// 抄表员编号
				.calcDate(new Date())// 计费时间
				.lastDate(new Date())// 上次计费日期
				.lastCode(new BigDecimal(0))// 起数
				.currDate(new Date())// 当前计费日期
				.currCode(new BigDecimal(0))// 止数
				.waterScale(new BigDecimal(0))// 用量占比
				.useNum(new BigDecimal(0))// 计费水量
				.priceTypeId(RandomUtil.randomLong())// 价格编号
				.recMoney(new BigDecimal(0))// 应收金额
				.dueMoney(new BigDecimal(0))// 欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/dev-rec-lists" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		DevRecList tenantInfo = DevRecList.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.recFlag(RandomUtil.randomInt(0,1000+1))// 应收账标识（1：正常；2：被冲正；3：冲正负记录）
				//.recType(RandomUtil.randomInt(0,1+1))// 应收来源（1：抄表；2：换表）
				//.deptId(RandomUtil.randomLong())// 部门编号
				//.bookletId(RandomUtil.randomLong())// 表册编号
				//.custId(RandomUtil.randomLong())// 用户编号
				//.custName(TestCaseUtil.name())// 用户名称
				//.custAddress(TestCaseUtil.address())// 用户地址
				//.devId(RandomUtil.randomLong())// 表具系统编号
				//.devAddress(TestCaseUtil.address())// 表具地址
				//.readMonth(new Date())// 应收月份
				//.recMonth(new Date())// 应收账务统计月份
				//.businessId(RandomUtil.randomLong())// 业务（抄表、换表）流水号
				//.reader(RandomUtil.randomLong())// 抄表员编号
				//.calcDate(new Date())// 计费时间
				//.lastDate(new Date())// 上次计费日期
				//.lastCode(new BigDecimal(0))// 起数
				//.currDate(new Date())// 当前计费日期
				//.currCode(new BigDecimal(0))// 止数
				//.waterScale(new BigDecimal(0))// 用量占比
				//.useNum(new BigDecimal(0))// 计费水量
				//.priceTypeId(RandomUtil.randomLong())// 价格编号
				//.recMoney(new BigDecimal(0))// 应收金额
				//.dueMoney(new BigDecimal(0))// 欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/dev-rec-lists" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/dev-rec-lists" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
