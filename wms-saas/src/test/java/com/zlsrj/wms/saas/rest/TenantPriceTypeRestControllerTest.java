package com.zlsrj.wms.saas.rest;

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
		String id = "";
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
		
		// params.add("id",TestCaseUtil.id());// 价格类别ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("priceTypeName",TestCaseUtil.name());// 价格类别名称
		// params.add("bottomOn",RandomUtil.randomInt(0,1+1));// 启用保底水量（1：启用；0：不启用）
		// params.add("bottomWaters",new BigDecimal(0));// 保底水量
		// params.add("topOn",RandomUtil.randomInt(0,1+1));// 启用封顶水量（1：启用；0：不启用）
		// params.add("topWaters",new BigDecimal(0));// 封顶水量
		// params.add("reduceOn",RandomUtil.randomInt(0,1+1));// 启用固定减免（1：启用；0：不启用）
		// params.add("reduceWaters",new BigDecimal(0));// 固定减免水量
		// params.add("reduceLowerlimit",new BigDecimal(0));// 固定减免水量下限
		// params.add("fixedOn",RandomUtil.randomInt(0,1+1));// 启用固定水量征收（1：启用；0：不启用）
		// params.add("fixedWaters",new BigDecimal(0));// 固定征收水量

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-price-types").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantPriceType tenantInfo = TenantPriceType.builder()//
				.id(TestCaseUtil.id())// 价格类别ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.priceTypeName(TestCaseUtil.name())// 价格类别名称
				.bottomOn(RandomUtil.randomInt(0,1+1))// 启用保底水量（1：启用；0：不启用）
				.bottomWaters(new BigDecimal(0))// 保底水量
				.topOn(RandomUtil.randomInt(0,1+1))// 启用封顶水量（1：启用；0：不启用）
				.topWaters(new BigDecimal(0))// 封顶水量
				.reduceOn(RandomUtil.randomInt(0,1+1))// 启用固定减免（1：启用；0：不启用）
				.reduceWaters(new BigDecimal(0))// 固定减免水量
				.reduceLowerlimit(new BigDecimal(0))// 固定减免水量下限
				.fixedOn(RandomUtil.randomInt(0,1+1))// 启用固定水量征收（1：启用；0：不启用）
				.fixedWaters(new BigDecimal(0))// 固定征收水量
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
		String id = "";

		TenantPriceType tenantInfo = TenantPriceType.builder()//
				//.id(TestCaseUtil.id())// 价格类别ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.priceTypeName(TestCaseUtil.name())// 价格类别名称
				.bottomOn(RandomUtil.randomInt(0,1+1))// 启用保底水量（1：启用；0：不启用）
				.bottomWaters(new BigDecimal(0))// 保底水量
				.topOn(RandomUtil.randomInt(0,1+1))// 启用封顶水量（1：启用；0：不启用）
				.topWaters(new BigDecimal(0))// 封顶水量
				.reduceOn(RandomUtil.randomInt(0,1+1))// 启用固定减免（1：启用；0：不启用）
				.reduceWaters(new BigDecimal(0))// 固定减免水量
				.reduceLowerlimit(new BigDecimal(0))// 固定减免水量下限
				.fixedOn(RandomUtil.randomInt(0,1+1))// 启用固定水量征收（1：启用；0：不启用）
				.fixedWaters(new BigDecimal(0))// 固定征收水量
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
		String id = "";

		TenantPriceType tenantInfo = TenantPriceType.builder()//
				//.tenantId(RandomUtil.randomString(32))// 租户ID
				//.priceTypeName(TestCaseUtil.name())// 价格类别名称
				//.bottomOn(RandomUtil.randomInt(0,1+1))// 启用保底水量（1：启用；0：不启用）
				//.bottomWaters(new BigDecimal(0))// 保底水量
				//.topOn(RandomUtil.randomInt(0,1+1))// 启用封顶水量（1：启用；0：不启用）
				//.topWaters(new BigDecimal(0))// 封顶水量
				//.reduceOn(RandomUtil.randomInt(0,1+1))// 启用固定减免（1：启用；0：不启用）
				//.reduceWaters(new BigDecimal(0))// 固定减免水量
				//.reduceLowerlimit(new BigDecimal(0))// 固定减免水量下限
				//.fixedOn(RandomUtil.randomInt(0,1+1))// 启用固定水量征收（1：启用；0：不启用）
				//.fixedWaters(new BigDecimal(0))// 固定征收水量
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
		String id = "";

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-price-types" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
