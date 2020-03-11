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
import com.zlsrj.wms.api.entity.TenantCustomerLinkman;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerLinkmanRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-linkmans" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 联系人ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("customerId",RandomUtil.randomLong());// 客户ID
		// params.add("linkmanName",TestCaseUtil.name());// 联系人姓名
		// params.add("linkmanAddress",TestCaseUtil.address());// 联系人地址
		// params.add("linkmanMainOn",RandomUtil.randomInt(0,1+1));// 主联系人（1：是；0：否）
		// params.add("linkmanSex",RandomUtil.randomInt(0,1000+1));// 性别（1：男；2：女）
		// params.add("linkmanBirthday",new Date());// 出生日期
		// params.add("linkmanMobile",TestCaseUtil.mobile());// 手机号码
		// params.add("linkmanTel",TestCaseUtil.tel());// 固定电话号码
		// params.add("linkmanEmail",TestCaseUtil.email(null));// 邮箱地址
		// params.add("linkmanPersonalWx",RandomUtil.randomString(4));// 微信号
		// params.add("linkmanQq",TestCaseUtil.qq());// QQ号
		// params.add("linkmanRemark",RandomUtil.randomString(4));// 备注

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-linkmans").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantCustomerLinkman tenantInfo = TenantCustomerLinkman.builder()//
				.id(TestCaseUtil.id())// 联系人ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.customerId(RandomUtil.randomString(32))// 客户ID
				.linkmanName(TestCaseUtil.name())// 联系人姓名
				.linkmanAddress(TestCaseUtil.address())// 联系人地址
				.linkmanMainOn(RandomUtil.randomInt(0,1+1))// 主联系人（1：是；0：否）
				.linkmanSex(RandomUtil.randomInt(0,1000+1))// 性别（1：男；2：女）
				.linkmanBirthday(new Date())// 出生日期
				.linkmanMobile(TestCaseUtil.mobile())// 手机号码
				.linkmanTel(TestCaseUtil.tel())// 固定电话号码
				.linkmanEmail(TestCaseUtil.email(null))// 邮箱地址
				.linkmanPersonalWx(RandomUtil.randomString(4))// 微信号
				.linkmanQq(TestCaseUtil.qq())// QQ号
				.linkmanRemark(RandomUtil.randomString(4))// 备注
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-customer-linkmans").content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerLinkman tenantInfo = TenantCustomerLinkman.builder()//
				//.id(TestCaseUtil.id())// 联系人ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.customerId(RandomUtil.randomString(32))// 客户ID
				.linkmanName(TestCaseUtil.name())// 联系人姓名
				.linkmanAddress(TestCaseUtil.address())// 联系人地址
				.linkmanMainOn(RandomUtil.randomInt(0,1+1))// 主联系人（1：是；0：否）
				.linkmanSex(RandomUtil.randomInt(0,1000+1))// 性别（1：男；2：女）
				.linkmanBirthday(new Date())// 出生日期
				.linkmanMobile(TestCaseUtil.mobile())// 手机号码
				.linkmanTel(TestCaseUtil.tel())// 固定电话号码
				.linkmanEmail(TestCaseUtil.email(null))// 邮箱地址
				.linkmanPersonalWx(RandomUtil.randomString(4))// 微信号
				.linkmanQq(TestCaseUtil.qq())// QQ号
				.linkmanRemark(RandomUtil.randomString(4))// 备注
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-customer-linkmans" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerLinkman tenantInfo = TenantCustomerLinkman.builder()//
				//.tenantId(RandomUtil.randomString(32))// 租户ID
				//.customerId(RandomUtil.randomString(32))// 客户ID
				//.linkmanName(TestCaseUtil.name())// 联系人姓名
				//.linkmanAddress(TestCaseUtil.address())// 联系人地址
				//.linkmanMainOn(RandomUtil.randomInt(0,1+1))// 主联系人（1：是；0：否）
				//.linkmanSex(RandomUtil.randomInt(0,1000+1))// 性别（1：男；2：女）
				//.linkmanBirthday(new Date())// 出生日期
				//.linkmanMobile(TestCaseUtil.mobile())// 手机号码
				//.linkmanTel(TestCaseUtil.tel())// 固定电话号码
				//.linkmanEmail(TestCaseUtil.email(null))// 邮箱地址
				//.linkmanPersonalWx(RandomUtil.randomString(4))// 微信号
				//.linkmanQq(TestCaseUtil.qq())// QQ号
				//.linkmanRemark(RandomUtil.randomString(4))// 备注
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-customer-linkmans" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-customer-linkmans" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
