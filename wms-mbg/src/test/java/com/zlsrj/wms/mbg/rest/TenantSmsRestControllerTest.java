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
import com.zlsrj.wms.api.entity.TenantSms;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantSmsRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-smss" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 编号ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户编号
		// params.add("smsSignature",RandomUtil.randomString(4));// 短信签名
		// params.add("smsSpService",RandomUtil.randomString(4));// 短信SP服务商
		// params.add("smsReadSendOn",RandomUtil.randomInt(0,1+1));// 是否启用抄表账单通知短信（启用/不启用）
		// params.add("smsChargeSendOn",RandomUtil.randomInt(0,1+1));// 是否启用缴费成功通知短信（启用/不启用）
		// params.add("smsQfSendOn",RandomUtil.randomInt(0,1+1));// 是否启用欠费通知短信（启用/不启用）
		// params.add("smsQfSendAfterDays",RandomUtil.randomInt(0,1000+1));// 欠费通知短信发送间隔天数（欠费多少天后，催费多少天后仍然未缴）

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-smss").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantSms tenantInfo = TenantSms.builder()//
				.id(TestCaseUtil.id())// 编号ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.smsSignature(RandomUtil.randomString(4))// 短信签名
				.smsSpService(RandomUtil.randomString(4))// 短信SP服务商
				.smsReadSendOn(RandomUtil.randomInt(0,1+1))// 是否启用抄表账单通知短信（启用/不启用）
				.smsChargeSendOn(RandomUtil.randomInt(0,1+1))// 是否启用缴费成功通知短信（启用/不启用）
				.smsQfSendOn(RandomUtil.randomInt(0,1+1))// 是否启用欠费通知短信（启用/不启用）
				.smsQfSendAfterDays(RandomUtil.randomInt(0,1000+1))// 欠费通知短信发送间隔天数（欠费多少天后，催费多少天后仍然未缴）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-smss").content(JSON.toJSONString(tenantInfo)) //
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

		TenantSms tenantInfo = TenantSms.builder()//
				//.id(TestCaseUtil.id())// 编号ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.smsSignature(RandomUtil.randomString(4))// 短信签名
				.smsSpService(RandomUtil.randomString(4))// 短信SP服务商
				.smsReadSendOn(RandomUtil.randomInt(0,1+1))// 是否启用抄表账单通知短信（启用/不启用）
				.smsChargeSendOn(RandomUtil.randomInt(0,1+1))// 是否启用缴费成功通知短信（启用/不启用）
				.smsQfSendOn(RandomUtil.randomInt(0,1+1))// 是否启用欠费通知短信（启用/不启用）
				.smsQfSendAfterDays(RandomUtil.randomInt(0,1000+1))// 欠费通知短信发送间隔天数（欠费多少天后，催费多少天后仍然未缴）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-smss" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantSms tenantInfo = TenantSms.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.smsSignature(RandomUtil.randomString(4))// 短信签名
				//.smsSpService(RandomUtil.randomString(4))// 短信SP服务商
				//.smsReadSendOn(RandomUtil.randomInt(0,1+1))// 是否启用抄表账单通知短信（启用/不启用）
				//.smsChargeSendOn(RandomUtil.randomInt(0,1+1))// 是否启用缴费成功通知短信（启用/不启用）
				//.smsQfSendOn(RandomUtil.randomInt(0,1+1))// 是否启用欠费通知短信（启用/不启用）
				//.smsQfSendAfterDays(RandomUtil.randomInt(0,1000+1))// 欠费通知短信发送间隔天数（欠费多少天后，催费多少天后仍然未缴）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-smss" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-smss" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
