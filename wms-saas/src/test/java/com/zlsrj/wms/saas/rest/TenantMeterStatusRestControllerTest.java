package com.zlsrj.wms.saas.rest;


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
import com.zlsrj.wms.api.entity.TenantMeterStatus;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterStatusRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-meter-statuss" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 表况ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("meterStatusName",TestCaseUtil.name());// 表况名称
		// params.add("usenumCalcType",RandomUtil.randomInt(0,1+1));// 水量计算方式（1：自动计算；2：手工输入）
		// params.add("workBillType",RandomUtil.randomInt(0,1+1));// 生成工单类型（0：不生成；1：故障换表；3：周期换表）
		// params.add("createType",RandomUtil.randomInt(0,1+1));// 创建方式（1：平台创建；2：客户自建）

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-meter-statuss").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantMeterStatus tenantInfo = TenantMeterStatus.builder()//
				.id(TestCaseUtil.id())// 表况ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.meterStatusName(TestCaseUtil.name())// 表况名称
				.usenumCalcType(RandomUtil.randomInt(0,1+1))// 水量计算方式（1：自动计算；2：手工输入）
				.workBillType(RandomUtil.randomInt(0,1+1))// 生成工单类型（0：不生成；1：故障换表；3：周期换表）
				.createType(RandomUtil.randomInt(0,1+1))// 创建方式（1：平台创建；2：客户自建）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-meter-statuss").content(JSON.toJSONString(tenantInfo)) //
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

		TenantMeterStatus tenantInfo = TenantMeterStatus.builder()//
				//.id(TestCaseUtil.id())// 表况ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.meterStatusName(TestCaseUtil.name())// 表况名称
				.usenumCalcType(RandomUtil.randomInt(0,1+1))// 水量计算方式（1：自动计算；2：手工输入）
				.workBillType(RandomUtil.randomInt(0,1+1))// 生成工单类型（0：不生成；1：故障换表；3：周期换表）
				.createType(RandomUtil.randomInt(0,1+1))// 创建方式（1：平台创建；2：客户自建）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-meter-statuss" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantMeterStatus tenantInfo = TenantMeterStatus.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户ID
				//.meterStatusName(TestCaseUtil.name())// 表况名称
				//.usenumCalcType(RandomUtil.randomInt(0,1+1))// 水量计算方式（1：自动计算；2：手工输入）
				//.workBillType(RandomUtil.randomInt(0,1+1))// 生成工单类型（0：不生成；1：故障换表；3：周期换表）
				//.createType(RandomUtil.randomInt(0,1+1))// 创建方式（1：平台创建；2：客户自建）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-meter-statuss" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-meter-statuss" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
