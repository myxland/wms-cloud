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
import com.zlsrj.wms.api.entity.TenantChargeAgency;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantChargeAgencyRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-charge-agencys" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 代收机构ID
		// params.add("tenantId",RandomUtil.randomString(4));// 租户ID
		// params.add("chargeAgencyName",TestCaseUtil.name());// 代收机构名称
		// params.add("chargeAgencyData",RandomUtil.randomString(4));// 结构化数据
		// params.add("createType",RandomUtil.randomInt(0,1+1));// 创建类型（1：平台默认创建；2：租户自建）
		// params.add("apiOn",RandomUtil.randomInt(0,1+1));// 是否开放API（1：开放；0：不开放）
		// params.add("addTime",new Date());// 数据新增时间
		// params.add("updateTime",new Date());// 数据修改时间

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-charge-agencys").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantChargeAgency tenantInfo = TenantChargeAgency.builder()//
				.id(TestCaseUtil.id())// 代收机构ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.chargeAgencyName(TestCaseUtil.name())// 代收机构名称
				.chargeAgencyData(RandomUtil.randomString(4))// 结构化数据
				.createType(RandomUtil.randomInt(0,1+1))// 创建类型（1：平台默认创建；2：租户自建）
				.apiOn(RandomUtil.randomInt(0,1+1))// 是否开放API（1：开放；0：不开放）
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-charge-agencys").content(JSON.toJSONString(tenantInfo)) //
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

		TenantChargeAgency tenantInfo = TenantChargeAgency.builder()//
				//.id(TestCaseUtil.id())// 代收机构ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.chargeAgencyName(TestCaseUtil.name())// 代收机构名称
				.chargeAgencyData(RandomUtil.randomString(4))// 结构化数据
				.createType(RandomUtil.randomInt(0,1+1))// 创建类型（1：平台默认创建；2：租户自建）
				.apiOn(RandomUtil.randomInt(0,1+1))// 是否开放API（1：开放；0：不开放）
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-charge-agencys" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantChargeAgency tenantInfo = TenantChargeAgency.builder()//
				//.tenantId(RandomUtil.randomString(4))// 租户ID
				//.chargeAgencyName(TestCaseUtil.name())// 代收机构名称
				//.chargeAgencyData(RandomUtil.randomString(4))// 结构化数据
				//.createType(RandomUtil.randomInt(0,1+1))// 创建类型（1：平台默认创建；2：租户自建）
				//.apiOn(RandomUtil.randomInt(0,1+1))// 是否开放API（1：开放；0：不开放）
				//.addTime(new Date())// 数据新增时间
				//.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-charge-agencys" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-charge-agencys" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}