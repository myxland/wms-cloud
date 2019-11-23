package com.zlsrj.wms.cust.rest;

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
import com.zlsrj.wms.api.entity.DevReadCurrHis;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DevReadCurrHisRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/dev-read-curr-hiss" + "/" + id))
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
		// params.add("readMonth",new Date());// 抄表月份
		// params.add("devId",RandomUtil.randomLong());// 表具系统编号
		// params.add("parentDevId",RandomUtil.randomLong());// 父表具编号
		// params.add("yearUseNum",new BigDecimal(0));// 本次计费前当年累计水量
		// params.add("lastCalcDate",new Date());// 上次计费日期
		// params.add("lastCalcCode",new BigDecimal(0));// 上次计费止码
		// params.add("currReadEmpId",RandomUtil.randomLong());// 抄表人编号
		// params.add("currReadDate",new Date());// 本次抄表日期
		// params.add("currReadCode",new BigDecimal(0));// 本次抄表止码
		// params.add("currDevStatus",RandomUtil.randomInt(0,1+1));// 表次表具状况
		// params.add("currUseNum",new BigDecimal(0));// 本次抄表水量
		// params.add("currCalcUseNum",new BigDecimal(0));// 本次计费水量
		// params.add("readSource",RandomUtil.randomInt(0,1000+1));// 抄表来源（1：移动抄表；2：人工入账；3：远传表）
		// params.add("readStatus",RandomUtil.randomInt(0,1+1));// 抄表状态（1：未抄；2：已抄）
		// params.add("checkResult",RandomUtil.randomInt(0,1000+1));// 审核状态（1：正常；2：异常）
		// params.add("procReault",RandomUtil.randomInt(0,1000+1));// 处理状态（1：已计费；2：待审核；3：已审核）
		// params.add("procMan",RandomUtil.randomLong());// 审核人
		// params.add("procTime",new Date());// 审核时间

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/dev-read-curr-hiss").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		DevReadCurrHis tenantInfo = DevReadCurrHis.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.readMonth(new Date())// 抄表月份
				.devId(RandomUtil.randomLong())// 表具系统编号
				.parentDevId(RandomUtil.randomLong())// 父表具编号
				.yearUseNum(new BigDecimal(0))// 本次计费前当年累计水量
				.lastCalcDate(new Date())// 上次计费日期
				.lastCalcCode(new BigDecimal(0))// 上次计费止码
				.currReadEmpId(RandomUtil.randomLong())// 抄表人编号
				.currReadDate(new Date())// 本次抄表日期
				.currReadCode(new BigDecimal(0))// 本次抄表止码
				.currDevStatus(RandomUtil.randomInt(0,1+1))// 表次表具状况
				.currUseNum(new BigDecimal(0))// 本次抄表水量
				.currCalcUseNum(new BigDecimal(0))// 本次计费水量
				.readSource(RandomUtil.randomInt(0,1000+1))// 抄表来源（1：移动抄表；2：人工入账；3：远传表）
				.readStatus(RandomUtil.randomInt(0,1+1))// 抄表状态（1：未抄；2：已抄）
				.checkResult(RandomUtil.randomInt(0,1000+1))// 审核状态（1：正常；2：异常）
				.procReault(RandomUtil.randomInt(0,1000+1))// 处理状态（1：已计费；2：待审核；3：已审核）
				.procMan(RandomUtil.randomLong())// 审核人
				.procTime(new Date())// 审核时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/dev-read-curr-hiss").content(JSON.toJSONString(tenantInfo)) //
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

		DevReadCurrHis tenantInfo = DevReadCurrHis.builder()//
				//.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.readMonth(new Date())// 抄表月份
				.devId(RandomUtil.randomLong())// 表具系统编号
				.parentDevId(RandomUtil.randomLong())// 父表具编号
				.yearUseNum(new BigDecimal(0))// 本次计费前当年累计水量
				.lastCalcDate(new Date())// 上次计费日期
				.lastCalcCode(new BigDecimal(0))// 上次计费止码
				.currReadEmpId(RandomUtil.randomLong())// 抄表人编号
				.currReadDate(new Date())// 本次抄表日期
				.currReadCode(new BigDecimal(0))// 本次抄表止码
				.currDevStatus(RandomUtil.randomInt(0,1+1))// 表次表具状况
				.currUseNum(new BigDecimal(0))// 本次抄表水量
				.currCalcUseNum(new BigDecimal(0))// 本次计费水量
				.readSource(RandomUtil.randomInt(0,1000+1))// 抄表来源（1：移动抄表；2：人工入账；3：远传表）
				.readStatus(RandomUtil.randomInt(0,1+1))// 抄表状态（1：未抄；2：已抄）
				.checkResult(RandomUtil.randomInt(0,1000+1))// 审核状态（1：正常；2：异常）
				.procReault(RandomUtil.randomInt(0,1000+1))// 处理状态（1：已计费；2：待审核；3：已审核）
				.procMan(RandomUtil.randomLong())// 审核人
				.procTime(new Date())// 审核时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/dev-read-curr-hiss" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		DevReadCurrHis tenantInfo = DevReadCurrHis.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.readMonth(new Date())// 抄表月份
				//.devId(RandomUtil.randomLong())// 表具系统编号
				//.parentDevId(RandomUtil.randomLong())// 父表具编号
				//.yearUseNum(new BigDecimal(0))// 本次计费前当年累计水量
				//.lastCalcDate(new Date())// 上次计费日期
				//.lastCalcCode(new BigDecimal(0))// 上次计费止码
				//.currReadEmpId(RandomUtil.randomLong())// 抄表人编号
				//.currReadDate(new Date())// 本次抄表日期
				//.currReadCode(new BigDecimal(0))// 本次抄表止码
				//.currDevStatus(RandomUtil.randomInt(0,1+1))// 表次表具状况
				//.currUseNum(new BigDecimal(0))// 本次抄表水量
				//.currCalcUseNum(new BigDecimal(0))// 本次计费水量
				//.readSource(RandomUtil.randomInt(0,1000+1))// 抄表来源（1：移动抄表；2：人工入账；3：远传表）
				//.readStatus(RandomUtil.randomInt(0,1+1))// 抄表状态（1：未抄；2：已抄）
				//.checkResult(RandomUtil.randomInt(0,1000+1))// 审核状态（1：正常；2：异常）
				//.procReault(RandomUtil.randomInt(0,1000+1))// 处理状态（1：已计费；2：待审核；3：已审核）
				//.procMan(RandomUtil.randomLong())// 审核人
				//.procTime(new Date())// 审核时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/dev-read-curr-hiss" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/dev-read-curr-hiss" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
