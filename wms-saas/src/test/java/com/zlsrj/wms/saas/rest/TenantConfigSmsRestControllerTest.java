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
import com.zlsrj.wms.api.entity.TenantConfigSms;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantConfigSmsRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-config-smss" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 短信配置ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("smsSignature",RandomUtil.randomString(4));// 短信签名
		// params.add("smsReceivableNoticeOn",RandomUtil.randomInt(0,1+1));// 开启出账短信通知（1：开启；0：不开启）
		// params.add("smsPaymentNoticeOn",RandomUtil.randomInt(0,1+1));// 开启付款短信通知（1：开启；0：不开启）
		// params.add("smsBalanceMoneyChangeNoticeOn",RandomUtil.randomInt(0,1+1));// 开启用户预存款变动短信通知（1：开启；0：不开启）
		// params.add("smsArrearsNoticeOn",RandomUtil.randomInt(0,1+1));// 开启欠费催缴短信通知（1：开启；0：不开启）
		// params.add("smsReceivableNoticeTemplate",RandomUtil.randomString(4));// 出账短信通知模板
		// params.add("smsPaymentNoticeTemplate",RandomUtil.randomString(4));// 付款短信通知模板
		// params.add("smsBalanceMoneyChangeNoticeTemplate",RandomUtil.randomString(4));// 用户预存款变动短信通知模板
		// params.add("smsArrearsNoticeTemplate",RandomUtil.randomString(4));// 欠费催缴短信通知模板
		// params.add("smsArrearsDays",RandomUtil.randomInt(0,1000+1));// 进入催缴的欠费天数
		// params.add("smsArrearsNoticeDay",RandomUtil.randomInt(0,1000+1));// 每月多少号进行欠费催缴
		// params.add("smsArrearsNoticeStartTime",new Date());// 欠费催缴开始时间
		// params.add("smsArrearsNoticeEndTime",new Date());// 欠费催缴结束时间

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-config-smss").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantConfigSms tenantInfo = TenantConfigSms.builder()//
				.id(TestCaseUtil.id())// 短信配置ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.smsSignature(RandomUtil.randomString(4))// 短信签名
				.smsReceivableNoticeOn(RandomUtil.randomInt(0,1+1))// 开启出账短信通知（1：开启；0：不开启）
				.smsPaymentNoticeOn(RandomUtil.randomInt(0,1+1))// 开启付款短信通知（1：开启；0：不开启）
				.smsBalanceMoneyChangeNoticeOn(RandomUtil.randomInt(0,1+1))// 开启用户预存款变动短信通知（1：开启；0：不开启）
				.smsArrearsNoticeOn(RandomUtil.randomInt(0,1+1))// 开启欠费催缴短信通知（1：开启；0：不开启）
				.smsReceivableNoticeTemplate(RandomUtil.randomString(4))// 出账短信通知模板
				.smsPaymentNoticeTemplate(RandomUtil.randomString(4))// 付款短信通知模板
				.smsBalanceMoneyChangeNoticeTemplate(RandomUtil.randomString(4))// 用户预存款变动短信通知模板
				.smsArrearsNoticeTemplate(RandomUtil.randomString(4))// 欠费催缴短信通知模板
				.smsArrearsDays(RandomUtil.randomInt(0,1000+1))// 进入催缴的欠费天数
				.smsArrearsNoticeDay(RandomUtil.randomInt(0,1000+1))// 每月多少号进行欠费催缴
				.smsArrearsNoticeStartTime(new Date())// 欠费催缴开始时间
				.smsArrearsNoticeEndTime(new Date())// 欠费催缴结束时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-config-smss").content(JSON.toJSONString(tenantInfo)) //
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

		TenantConfigSms tenantInfo = TenantConfigSms.builder()//
				//.id(TestCaseUtil.id())// 短信配置ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.smsSignature(RandomUtil.randomString(4))// 短信签名
				.smsReceivableNoticeOn(RandomUtil.randomInt(0,1+1))// 开启出账短信通知（1：开启；0：不开启）
				.smsPaymentNoticeOn(RandomUtil.randomInt(0,1+1))// 开启付款短信通知（1：开启；0：不开启）
				.smsBalanceMoneyChangeNoticeOn(RandomUtil.randomInt(0,1+1))// 开启用户预存款变动短信通知（1：开启；0：不开启）
				.smsArrearsNoticeOn(RandomUtil.randomInt(0,1+1))// 开启欠费催缴短信通知（1：开启；0：不开启）
				.smsReceivableNoticeTemplate(RandomUtil.randomString(4))// 出账短信通知模板
				.smsPaymentNoticeTemplate(RandomUtil.randomString(4))// 付款短信通知模板
				.smsBalanceMoneyChangeNoticeTemplate(RandomUtil.randomString(4))// 用户预存款变动短信通知模板
				.smsArrearsNoticeTemplate(RandomUtil.randomString(4))// 欠费催缴短信通知模板
				.smsArrearsDays(RandomUtil.randomInt(0,1000+1))// 进入催缴的欠费天数
				.smsArrearsNoticeDay(RandomUtil.randomInt(0,1000+1))// 每月多少号进行欠费催缴
				.smsArrearsNoticeStartTime(new Date())// 欠费催缴开始时间
				.smsArrearsNoticeEndTime(new Date())// 欠费催缴结束时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-config-smss" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantConfigSms tenantInfo = TenantConfigSms.builder()//
				//.tenantId(RandomUtil.randomString(32))// 租户ID
				//.smsSignature(RandomUtil.randomString(4))// 短信签名
				//.smsReceivableNoticeOn(RandomUtil.randomInt(0,1+1))// 开启出账短信通知（1：开启；0：不开启）
				//.smsPaymentNoticeOn(RandomUtil.randomInt(0,1+1))// 开启付款短信通知（1：开启；0：不开启）
				//.smsBalanceMoneyChangeNoticeOn(RandomUtil.randomInt(0,1+1))// 开启用户预存款变动短信通知（1：开启；0：不开启）
				//.smsArrearsNoticeOn(RandomUtil.randomInt(0,1+1))// 开启欠费催缴短信通知（1：开启；0：不开启）
				//.smsReceivableNoticeTemplate(RandomUtil.randomString(4))// 出账短信通知模板
				//.smsPaymentNoticeTemplate(RandomUtil.randomString(4))// 付款短信通知模板
				//.smsBalanceMoneyChangeNoticeTemplate(RandomUtil.randomString(4))// 用户预存款变动短信通知模板
				//.smsArrearsNoticeTemplate(RandomUtil.randomString(4))// 欠费催缴短信通知模板
				//.smsArrearsDays(RandomUtil.randomInt(0,1000+1))// 进入催缴的欠费天数
				//.smsArrearsNoticeDay(RandomUtil.randomInt(0,1000+1))// 每月多少号进行欠费催缴
				//.smsArrearsNoticeStartTime(new Date())// 欠费催缴开始时间
				//.smsArrearsNoticeEndTime(new Date())// 欠费催缴结束时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-config-smss" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-config-smss" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
