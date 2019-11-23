package com.zlsrj.wms.cust.rest;

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
import com.zlsrj.wms.api.entity.CustContact;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustContactRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/cust-contacts" + "/" + id))
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
		// params.add("custId",RandomUtil.randomString(4));// 用户编号
		// params.add("contactName",TestCaseUtil.name());// 联系人姓名
		// params.add("mainOn",RandomUtil.randomInt(0,1+1));// 主联系人（1：是；0：否）
		// params.add("gender",RandomUtil.randomInt(0,1000+1));// 性别（1：男；2：女）
		// params.add("birthday",new Date());// 
		// params.add("mobile",RandomUtil.randomString(4));// 手机号码
		// params.add("tel",RandomUtil.randomString(4));// 固定电话号码
		// params.add("email",RandomUtil.randomString(4));// 邮箱地址
		// params.add("personalWx",RandomUtil.randomString(4));// 微信号
		// params.add("qq",RandomUtil.randomInt(0,1000+1));// QQ号
		// params.add("remark",RandomUtil.randomString(4));// 备注

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/cust-contacts").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		CustContact tenantInfo = CustContact.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custId(RandomUtil.randomString(4))// 用户编号
				.contactName(TestCaseUtil.name())// 联系人姓名
				.mainOn(RandomUtil.randomInt(0,1+1))// 主联系人（1：是；0：否）
				.gender(RandomUtil.randomInt(0,1000+1))// 性别（1：男；2：女）
				.birthday(new Date())// 
				.mobile(RandomUtil.randomString(4))// 手机号码
				.tel(RandomUtil.randomString(4))// 固定电话号码
				.email(RandomUtil.randomString(4))// 邮箱地址
				.personalWx(RandomUtil.randomString(4))// 微信号
				.qq(RandomUtil.randomString(4))// QQ号
				.remark(RandomUtil.randomString(4))// 备注
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/cust-contacts").content(JSON.toJSONString(tenantInfo)) //
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

		CustContact tenantInfo = CustContact.builder()//
				//.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custId(RandomUtil.randomString(4))// 用户编号
				.contactName(TestCaseUtil.name())// 联系人姓名
				.mainOn(RandomUtil.randomInt(0,1+1))// 主联系人（1：是；0：否）
				.gender(RandomUtil.randomInt(0,1000+1))// 性别（1：男；2：女）
				.birthday(new Date())// 
				.mobile(RandomUtil.randomString(4))// 手机号码
				.tel(RandomUtil.randomString(4))// 固定电话号码
				.email(RandomUtil.randomString(4))// 邮箱地址
				.personalWx(RandomUtil.randomString(4))// 微信号
				.qq(RandomUtil.randomString(4))// QQ号
				.remark(RandomUtil.randomString(4))// 备注
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/cust-contacts" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		CustContact tenantInfo = CustContact.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.custId(RandomUtil.randomString(4))// 用户编号
				//.contactName(TestCaseUtil.name())// 联系人姓名
				//.mainOn(RandomUtil.randomInt(0,1+1))// 主联系人（1：是；0：否）
				//.gender(RandomUtil.randomInt(0,1000+1))// 性别（1：男；2：女）
				//.birthday(new Date())// 
				//.mobile(RandomUtil.randomString(4))// 手机号码
				//.tel(RandomUtil.randomString(4))// 固定电话号码
				//.email(RandomUtil.randomString(4))// 邮箱地址
				//.personalWx(RandomUtil.randomString(4))// 微信号
				//.qq(RandomUtil.randomInt(0,1000+1))// QQ号
				//.remark(RandomUtil.randomString(4))// 备注
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/cust-contacts" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/cust-contacts" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
