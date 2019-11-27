package com.zlsrj.wms.tenant.rest;


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
import com.zlsrj.wms.api.entity.TenantBill;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantBillRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-bills" + "/" + id))
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
		// params.add("billPrintType",RandomUtil.randomInt(0,1+1));// 用户发票开具方式（1：按实收开票；2：按应收开票）
		// params.add("billRemark",RandomUtil.randomString(4));// 发票备注定义
		// params.add("billService",RandomUtil.randomString(4));// 电子发票服务商（百望/航天信息）
		// params.add("billJrdm",RandomUtil.randomString(4));// 接入代码
		// params.add("billQmcs",RandomUtil.randomString(4));// 签名值参数
		// params.add("billSkpbh",RandomUtil.randomString(4));// 税控盘编号
		// params.add("billSkpkl",RandomUtil.randomString(4));// 税控盘口令
		// params.add("billKeypwd",RandomUtil.randomString(4));// 税务数字证书密码

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-bills").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantBill tenantInfo = TenantBill.builder()//
				.id(TestCaseUtil.id())// 编号ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.billPrintType(RandomUtil.randomInt(0,1+1))// 用户发票开具方式（1：按实收开票；2：按应收开票）
				.billRemark(RandomUtil.randomString(4))// 发票备注定义
				.billService(RandomUtil.randomString(4))// 电子发票服务商（百望/航天信息）
				.billJrdm(RandomUtil.randomString(4))// 接入代码
				.billQmcs(RandomUtil.randomString(4))// 签名值参数
				.billSkpbh(RandomUtil.randomString(4))// 税控盘编号
				.billSkpkl(RandomUtil.randomString(4))// 税控盘口令
				.billKeypwd(RandomUtil.randomString(4))// 税务数字证书密码
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-bills").content(JSON.toJSONString(tenantInfo)) //
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

		TenantBill tenantInfo = TenantBill.builder()//
				//.id(TestCaseUtil.id())// 编号ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.billPrintType(RandomUtil.randomInt(0,1+1))// 用户发票开具方式（1：按实收开票；2：按应收开票）
				.billRemark(RandomUtil.randomString(4))// 发票备注定义
				.billService(RandomUtil.randomString(4))// 电子发票服务商（百望/航天信息）
				.billJrdm(RandomUtil.randomString(4))// 接入代码
				.billQmcs(RandomUtil.randomString(4))// 签名值参数
				.billSkpbh(RandomUtil.randomString(4))// 税控盘编号
				.billSkpkl(RandomUtil.randomString(4))// 税控盘口令
				.billKeypwd(RandomUtil.randomString(4))// 税务数字证书密码
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-bills" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantBill tenantInfo = TenantBill.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.billPrintType(RandomUtil.randomInt(0,1+1))// 用户发票开具方式（1：按实收开票；2：按应收开票）
				//.billRemark(RandomUtil.randomString(4))// 发票备注定义
				//.billService(RandomUtil.randomString(4))// 电子发票服务商（百望/航天信息）
				//.billJrdm(RandomUtil.randomString(4))// 接入代码
				//.billQmcs(RandomUtil.randomString(4))// 签名值参数
				//.billSkpbh(RandomUtil.randomString(4))// 税控盘编号
				//.billSkpkl(RandomUtil.randomString(4))// 税控盘口令
				//.billKeypwd(RandomUtil.randomString(4))// 税务数字证书密码
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-bills" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-bills" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
