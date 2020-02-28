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
import com.zlsrj.wms.api.entity.TenantConfigWx;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantConfigWxRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-config-wxs" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 微信配置ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("wxAppid",RandomUtil.randomString(4));// 微信公众号APPID
		// params.add("wxAppsecret",RandomUtil.randomString(4));// 微信公众号AppSecret
		// params.add("wxAccountId",RandomUtil.randomString(4));// 微信商户ID
		// params.add("wxAccountApiKey",RandomUtil.randomString(4));// 微信商户API密钥
		// params.add("wxTitlePictureFileName",TestCaseUtil.name());// 微信公众号首页显示图片名称
		// params.add("wxServiceTel",TestCaseUtil.tel());// 微信公众号显示服务电话
		// params.add("wxReceivableNoticeOn",RandomUtil.randomInt(0,1+1));// 开启出账微信通知（1：开启；0：不开启）
		// params.add("wxPaymentNoticeOn",RandomUtil.randomInt(0,1+1));// 开启付款微信通知（1：开启；0：不开启）
		// params.add("wxBalanceMoneyChangeNoticeOn",RandomUtil.randomInt(0,1+1));// 开启用户预存款变动微信通知（1：开启；0：不开启）
		// params.add("wxArrearsNoticeOn",RandomUtil.randomInt(0,1+1));// 开启欠费催缴微信通知（1：开启；0：不开启）
		// params.add("wxReceivableNoticeTemplate",RandomUtil.randomString(4));// 出账微信通知模板
		// params.add("wxPaymentNoticeTemplate",RandomUtil.randomString(4));// 付款微信通知模板
		// params.add("wxBalanceMoneyChangeNoticeTemplate",RandomUtil.randomString(4));// 用户预存款变动微信通知模板
		// params.add("wxArrearsNoticeTemplate",RandomUtil.randomString(4));// 欠费催缴微信通知模板
		// params.add("wxArrearsDays",RandomUtil.randomInt(0,1000+1));// 进入催缴的欠费天数
		// params.add("wxArrearsNoticeDay",RandomUtil.randomInt(0,1000+1));// 每月多少号进行欠费催缴
		// params.add("wxArrearsNoticeStartTime",new Date());// 欠费催缴开始时间
		// params.add("wxArrearsNoticeEndTime",new Date());// 欠费催缴结束时间

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-config-wxs").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantConfigWx tenantInfo = TenantConfigWx.builder()//
				.id(TestCaseUtil.id())// 微信配置ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.wxAppid(RandomUtil.randomString(4))// 微信公众号APPID
				.wxAppsecret(RandomUtil.randomString(4))// 微信公众号AppSecret
				.wxAccountId(RandomUtil.randomString(4))// 微信商户ID
				.wxAccountApiKey(RandomUtil.randomString(4))// 微信商户API密钥
				.wxTitlePictureFileName(TestCaseUtil.name())// 微信公众号首页显示图片名称
				.wxServiceTel(TestCaseUtil.tel())// 微信公众号显示服务电话
				.wxReceivableNoticeOn(RandomUtil.randomInt(0,1+1))// 开启出账微信通知（1：开启；0：不开启）
				.wxPaymentNoticeOn(RandomUtil.randomInt(0,1+1))// 开启付款微信通知（1：开启；0：不开启）
				.wxBalanceMoneyChangeNoticeOn(RandomUtil.randomInt(0,1+1))// 开启用户预存款变动微信通知（1：开启；0：不开启）
				.wxArrearsNoticeOn(RandomUtil.randomInt(0,1+1))// 开启欠费催缴微信通知（1：开启；0：不开启）
				.wxReceivableNoticeTemplate(RandomUtil.randomString(4))// 出账微信通知模板
				.wxPaymentNoticeTemplate(RandomUtil.randomString(4))// 付款微信通知模板
				.wxBalanceMoneyChangeNoticeTemplate(RandomUtil.randomString(4))// 用户预存款变动微信通知模板
				.wxArrearsNoticeTemplate(RandomUtil.randomString(4))// 欠费催缴微信通知模板
				.wxArrearsDays(RandomUtil.randomInt(0,1000+1))// 进入催缴的欠费天数
				.wxArrearsNoticeDay(RandomUtil.randomInt(0,1000+1))// 每月多少号进行欠费催缴
				.wxArrearsNoticeStartTime(new Date())// 欠费催缴开始时间
				.wxArrearsNoticeEndTime(new Date())// 欠费催缴结束时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-config-wxs").content(JSON.toJSONString(tenantInfo)) //
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

		TenantConfigWx tenantInfo = TenantConfigWx.builder()//
				//.id(TestCaseUtil.id())// 微信配置ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.wxAppid(RandomUtil.randomString(4))// 微信公众号APPID
				.wxAppsecret(RandomUtil.randomString(4))// 微信公众号AppSecret
				.wxAccountId(RandomUtil.randomString(4))// 微信商户ID
				.wxAccountApiKey(RandomUtil.randomString(4))// 微信商户API密钥
				.wxTitlePictureFileName(TestCaseUtil.name())// 微信公众号首页显示图片名称
				.wxServiceTel(TestCaseUtil.tel())// 微信公众号显示服务电话
				.wxReceivableNoticeOn(RandomUtil.randomInt(0,1+1))// 开启出账微信通知（1：开启；0：不开启）
				.wxPaymentNoticeOn(RandomUtil.randomInt(0,1+1))// 开启付款微信通知（1：开启；0：不开启）
				.wxBalanceMoneyChangeNoticeOn(RandomUtil.randomInt(0,1+1))// 开启用户预存款变动微信通知（1：开启；0：不开启）
				.wxArrearsNoticeOn(RandomUtil.randomInt(0,1+1))// 开启欠费催缴微信通知（1：开启；0：不开启）
				.wxReceivableNoticeTemplate(RandomUtil.randomString(4))// 出账微信通知模板
				.wxPaymentNoticeTemplate(RandomUtil.randomString(4))// 付款微信通知模板
				.wxBalanceMoneyChangeNoticeTemplate(RandomUtil.randomString(4))// 用户预存款变动微信通知模板
				.wxArrearsNoticeTemplate(RandomUtil.randomString(4))// 欠费催缴微信通知模板
				.wxArrearsDays(RandomUtil.randomInt(0,1000+1))// 进入催缴的欠费天数
				.wxArrearsNoticeDay(RandomUtil.randomInt(0,1000+1))// 每月多少号进行欠费催缴
				.wxArrearsNoticeStartTime(new Date())// 欠费催缴开始时间
				.wxArrearsNoticeEndTime(new Date())// 欠费催缴结束时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-config-wxs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantConfigWx tenantInfo = TenantConfigWx.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户ID
				//.wxAppid(RandomUtil.randomString(4))// 微信公众号APPID
				//.wxAppsecret(RandomUtil.randomString(4))// 微信公众号AppSecret
				//.wxAccountId(RandomUtil.randomString(4))// 微信商户ID
				//.wxAccountApiKey(RandomUtil.randomString(4))// 微信商户API密钥
				//.wxTitlePictureFileName(TestCaseUtil.name())// 微信公众号首页显示图片名称
				//.wxServiceTel(TestCaseUtil.tel())// 微信公众号显示服务电话
				//.wxReceivableNoticeOn(RandomUtil.randomInt(0,1+1))// 开启出账微信通知（1：开启；0：不开启）
				//.wxPaymentNoticeOn(RandomUtil.randomInt(0,1+1))// 开启付款微信通知（1：开启；0：不开启）
				//.wxBalanceMoneyChangeNoticeOn(RandomUtil.randomInt(0,1+1))// 开启用户预存款变动微信通知（1：开启；0：不开启）
				//.wxArrearsNoticeOn(RandomUtil.randomInt(0,1+1))// 开启欠费催缴微信通知（1：开启；0：不开启）
				//.wxReceivableNoticeTemplate(RandomUtil.randomString(4))// 出账微信通知模板
				//.wxPaymentNoticeTemplate(RandomUtil.randomString(4))// 付款微信通知模板
				//.wxBalanceMoneyChangeNoticeTemplate(RandomUtil.randomString(4))// 用户预存款变动微信通知模板
				//.wxArrearsNoticeTemplate(RandomUtil.randomString(4))// 欠费催缴微信通知模板
				//.wxArrearsDays(RandomUtil.randomInt(0,1000+1))// 进入催缴的欠费天数
				//.wxArrearsNoticeDay(RandomUtil.randomInt(0,1000+1))// 每月多少号进行欠费催缴
				//.wxArrearsNoticeStartTime(new Date())// 欠费催缴开始时间
				//.wxArrearsNoticeEndTime(new Date())// 欠费催缴结束时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-config-wxs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-config-wxs" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
