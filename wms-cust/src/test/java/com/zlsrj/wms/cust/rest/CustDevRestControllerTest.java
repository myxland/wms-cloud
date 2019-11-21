package com.zlsrj.wms.cust.rest;

import java.math.BigDecimal;

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
import com.zlsrj.wms.api.entity.CustDev;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustDevRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/cust-devs" + "/" + id))
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
		// params.add("devId",RandomUtil.randomLong());// 表具编号
		// params.add("priceTypeId",RandomUtil.randomLong());// 价格类别编号
		// params.add("waterTypeId",RandomUtil.randomLong());// 用水类别编号
		// params.add("devOrder",RandomUtil.randomInt(0,1000+1));// 排序
		// params.add("waterMixType",RandomUtil.randomInt(0,1+1));// 混合类型（1：定量；2：比例）
		// params.add("waterScale",new BigDecimal(0));// 本价格用水量占比或定量
		// params.add("waterCalcType",RandomUtil.randomInt(0,1+1));// 水量计算方法（1：向上取整；2：向下取整；3：保留两位小数）

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/cust-devs").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		CustDev tenantInfo = CustDev.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.devId(RandomUtil.randomLong())// 表具编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别编号
				.waterTypeId(RandomUtil.randomLong())// 用水类别编号
				.devOrder(RandomUtil.randomInt(0,1000+1))// 排序
				.waterMixType(RandomUtil.randomInt(0,1+1))// 混合类型（1：定量；2：比例）
				.waterScale(new BigDecimal(0))// 本价格用水量占比或定量
				.waterCalcType(RandomUtil.randomInt(0,1+1))// 水量计算方法（1：向上取整；2：向下取整；3：保留两位小数）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/cust-devs").content(JSON.toJSONString(tenantInfo)) //
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

		CustDev tenantInfo = CustDev.builder()//
				//.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.devId(RandomUtil.randomLong())// 表具编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别编号
				.waterTypeId(RandomUtil.randomLong())// 用水类别编号
				.devOrder(RandomUtil.randomInt(0,1000+1))// 排序
				.waterMixType(RandomUtil.randomInt(0,1+1))// 混合类型（1：定量；2：比例）
				.waterScale(new BigDecimal(0))// 本价格用水量占比或定量
				.waterCalcType(RandomUtil.randomInt(0,1+1))// 水量计算方法（1：向上取整；2：向下取整；3：保留两位小数）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/cust-devs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		CustDev tenantInfo = CustDev.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.devId(RandomUtil.randomLong())// 表具编号
				//.priceTypeId(RandomUtil.randomLong())// 价格类别编号
				//.waterTypeId(RandomUtil.randomLong())// 用水类别编号
				//.devOrder(RandomUtil.randomInt(0,1000+1))// 排序
				//.waterMixType(RandomUtil.randomInt(0,1+1))// 混合类型（1：定量；2：比例）
				//.waterScale(new BigDecimal(0))// 本价格用水量占比或定量
				//.waterCalcType(RandomUtil.randomInt(0,1+1))// 水量计算方法（1：向上取整；2：向下取整；3：保留两位小数）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/cust-devs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/cust-devs" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
