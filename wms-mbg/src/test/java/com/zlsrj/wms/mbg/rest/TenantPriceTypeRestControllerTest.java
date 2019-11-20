package com.zlsrj.wms.mbg.rest;


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
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceTypeRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-price-types" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 系统ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户编号
		// params.add("priceTypeName",TestCaseUtil.name());// 价格类别名称
		// params.add("bottomOn",RandomUtil.randomInt(0,1+1));// 启用保底水量（1：启用；0：不启用）
		// params.add("bottomNum",RandomUtil.randomInt(0,1000+1));// 保底水量
		// params.add("topOn",RandomUtil.randomInt(0,1+1));// 启用封顶水量（1：启用；0：不启用）
		// params.add("topNum",RandomUtil.randomInt(0,1000+1));// 封顶水量
		// params.add("reduceOn",RandomUtil.randomInt(0,1+1));// 启用固定减免（1：启用；0：不启用）
		// params.add("reduceNum",RandomUtil.randomInt(0,1000+1));// 固定减免水量
		// params.add("reduceLowerLimit",RandomUtil.randomInt(0,1000+1));// 减免起始水量
		// params.add("fixedOn",RandomUtil.randomInt(0,1+1));// 启用固定水量征收（1：启用；0：不启用）
		// params.add("fixedNum",RandomUtil.randomInt(0,1000+1));// 固定征收水量

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-price-types").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantPriceType tenantInfo = TenantPriceType.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceTypeName(TestCaseUtil.name())// 价格类别名称
				.bottomOn(RandomUtil.randomInt(0,1+1))// 启用保底水量（1：启用；0：不启用）
				.bottomNum(RandomUtil.randomInt(0,1000+1))// 保底水量
				.topOn(RandomUtil.randomInt(0,1+1))// 启用封顶水量（1：启用；0：不启用）
				.topNum(RandomUtil.randomInt(0,1000+1))// 封顶水量
				.reduceOn(RandomUtil.randomInt(0,1+1))// 启用固定减免（1：启用；0：不启用）
				.reduceNum(RandomUtil.randomInt(0,1000+1))// 固定减免水量
				.reduceLowerLimit(RandomUtil.randomInt(0,1000+1))// 减免起始水量
				.fixedOn(RandomUtil.randomInt(0,1+1))// 启用固定水量征收（1：启用；0：不启用）
				.fixedNum(RandomUtil.randomInt(0,1000+1))// 固定征收水量
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-price-types").content(JSON.toJSONString(tenantInfo)) //
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

		TenantPriceType tenantInfo = TenantPriceType.builder()//
				//.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceTypeName(TestCaseUtil.name())// 价格类别名称
				.bottomOn(RandomUtil.randomInt(0,1+1))// 启用保底水量（1：启用；0：不启用）
				.bottomNum(RandomUtil.randomInt(0,1000+1))// 保底水量
				.topOn(RandomUtil.randomInt(0,1+1))// 启用封顶水量（1：启用；0：不启用）
				.topNum(RandomUtil.randomInt(0,1000+1))// 封顶水量
				.reduceOn(RandomUtil.randomInt(0,1+1))// 启用固定减免（1：启用；0：不启用）
				.reduceNum(RandomUtil.randomInt(0,1000+1))// 固定减免水量
				.reduceLowerLimit(RandomUtil.randomInt(0,1000+1))// 减免起始水量
				.fixedOn(RandomUtil.randomInt(0,1+1))// 启用固定水量征收（1：启用；0：不启用）
				.fixedNum(RandomUtil.randomInt(0,1000+1))// 固定征收水量
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-price-types" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantPriceType tenantInfo = TenantPriceType.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.priceTypeName(TestCaseUtil.name())// 价格类别名称
				//.bottomOn(RandomUtil.randomInt(0,1+1))// 启用保底水量（1：启用；0：不启用）
				//.bottomNum(RandomUtil.randomInt(0,1000+1))// 保底水量
				//.topOn(RandomUtil.randomInt(0,1+1))// 启用封顶水量（1：启用；0：不启用）
				//.topNum(RandomUtil.randomInt(0,1000+1))// 封顶水量
				//.reduceOn(RandomUtil.randomInt(0,1+1))// 启用固定减免（1：启用；0：不启用）
				//.reduceNum(RandomUtil.randomInt(0,1000+1))// 固定减免水量
				//.reduceLowerLimit(RandomUtil.randomInt(0,1000+1))// 减免起始水量
				//.fixedOn(RandomUtil.randomInt(0,1+1))// 启用固定水量征收（1：启用；0：不启用）
				//.fixedNum(RandomUtil.randomInt(0,1000+1))// 固定征收水量
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-price-types" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-price-types" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
