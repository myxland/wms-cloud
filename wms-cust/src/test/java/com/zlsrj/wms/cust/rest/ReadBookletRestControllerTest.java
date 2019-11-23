package com.zlsrj.wms.cust.rest;

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
import com.zlsrj.wms.api.entity.ReadBooklet;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ReadBookletRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/read-booklets" + "/" + id))
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
		// params.add("bookletName",TestCaseUtil.name());// 表册名称
		// params.add("bookletType",RandomUtil.randomInt(0,1+1));// 表册类型（1：远传表；2：机械表）
		// params.add("readEmpId",RandomUtil.randomLong());// 抄表负责人编号
		// params.add("payEmpId",RandomUtil.randomLong());// 收费负责人编号
		// params.add("calcCycleInterval",RandomUtil.randomInt(0,1000+1));// 抄表间隔周期_月
		// params.add("calcMonthLast",new Date());// 最后一次抄表月份
		// params.add("calcMonthNext",new Date());// 下次抄表月份

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/read-booklets").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		ReadBooklet tenantInfo = ReadBooklet.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.bookletName(TestCaseUtil.name())// 表册名称
				.bookletType(RandomUtil.randomInt(0,1+1))// 表册类型（1：远传表；2：机械表）
				.readEmpId(RandomUtil.randomLong())// 抄表负责人编号
				.payEmpId(RandomUtil.randomLong())// 收费负责人编号
				.calcCycleInterval(RandomUtil.randomInt(0,1000+1))// 抄表间隔周期_月
				.calcMonthLast(new Date())// 最后一次抄表月份
				.calcMonthNext(new Date())// 下次抄表月份
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/read-booklets").content(JSON.toJSONString(tenantInfo)) //
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

		ReadBooklet tenantInfo = ReadBooklet.builder()//
				//.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.bookletName(TestCaseUtil.name())// 表册名称
				.bookletType(RandomUtil.randomInt(0,1+1))// 表册类型（1：远传表；2：机械表）
				.readEmpId(RandomUtil.randomLong())// 抄表负责人编号
				.payEmpId(RandomUtil.randomLong())// 收费负责人编号
				.calcCycleInterval(RandomUtil.randomInt(0,1000+1))// 抄表间隔周期_月
				.calcMonthLast(new Date())// 最后一次抄表月份
				.calcMonthNext(new Date())// 下次抄表月份
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/read-booklets" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		ReadBooklet tenantInfo = ReadBooklet.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.bookletName(TestCaseUtil.name())// 表册名称
				//.bookletType(RandomUtil.randomInt(0,1+1))// 表册类型（1：远传表；2：机械表）
				//.readEmpId(RandomUtil.randomLong())// 抄表负责人编号
				//.payEmpId(RandomUtil.randomLong())// 收费负责人编号
				//.calcCycleInterval(RandomUtil.randomInt(0,1000+1))// 抄表间隔周期_月
				//.calcMonthLast(new Date())// 最后一次抄表月份
				//.calcMonthNext(new Date())// 下次抄表月份
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/read-booklets" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/read-booklets" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
