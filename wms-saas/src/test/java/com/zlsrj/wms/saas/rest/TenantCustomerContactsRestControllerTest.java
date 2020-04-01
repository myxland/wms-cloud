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
import com.zlsrj.wms.api.entity.TenantCustomerContacts;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerContactsRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-contactss" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 用户联系ID
		// params.add("tenantId",RandomUtil.randomString(4));// 租户ID
		// params.add("customerId",RandomUtil.randomString(4));// 用户ID
		// params.add("customerCode",RandomUtil.randomString(4));// 用户号
		// params.add("contactsName",TestCaseUtil.name());// 联系人姓名
		// params.add("contactsAddress",TestCaseUtil.address());// 联系人地址
		// params.add("contactsMain",RandomUtil.randomInt(0,1000+1));// 主联系人（1：是；0：否）
		// params.add("contactsSex",RandomUtil.randomInt(0,1000+1));// 性别（1：男；0：女）
		// params.add("contactsBirthday",new Date());// 出生日期
		// params.add("contactsMobile",TestCaseUtil.mobile());// 手机号码
		// params.add("contactsTel",TestCaseUtil.tel());// 固定电话
		// params.add("contactsEmail",TestCaseUtil.email(null));// 邮箱地址
		// params.add("contactsWx",RandomUtil.randomString(4));// 微信号
		// params.add("contactsQq",TestCaseUtil.qq());// QQ号
		// params.add("contactsMemo",RandomUtil.randomString(4));// 备注
		// params.add("addTime",new Date());// 数据新增时间
		// params.add("updateTime",new Date());// 数据修改时间

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-contactss").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantCustomerContacts tenantInfo = TenantCustomerContacts.builder()//
				.id(TestCaseUtil.id())// 用户联系ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.contactsName(TestCaseUtil.name())// 联系人姓名
				.contactsAddress(TestCaseUtil.address())// 联系人地址
				.contactsMain(RandomUtil.randomInt(0,1000+1))// 主联系人（1：是；0：否）
				.contactsSex(RandomUtil.randomInt(0,1000+1))// 性别（1：男；0：女）
				.contactsBirthday(new Date())// 出生日期
				.contactsMobile(TestCaseUtil.mobile())// 手机号码
				.contactsTel(TestCaseUtil.tel())// 固定电话
				.contactsEmail(TestCaseUtil.email(null))// 邮箱地址
				.contactsWx(RandomUtil.randomString(4))// 微信号
				.contactsQq(TestCaseUtil.qq())// QQ号
				.contactsMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-customer-contactss").content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerContacts tenantInfo = TenantCustomerContacts.builder()//
				//.id(TestCaseUtil.id())// 用户联系ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.contactsName(TestCaseUtil.name())// 联系人姓名
				.contactsAddress(TestCaseUtil.address())// 联系人地址
				.contactsMain(RandomUtil.randomInt(0,1000+1))// 主联系人（1：是；0：否）
				.contactsSex(RandomUtil.randomInt(0,1000+1))// 性别（1：男；0：女）
				.contactsBirthday(new Date())// 出生日期
				.contactsMobile(TestCaseUtil.mobile())// 手机号码
				.contactsTel(TestCaseUtil.tel())// 固定电话
				.contactsEmail(TestCaseUtil.email(null))// 邮箱地址
				.contactsWx(RandomUtil.randomString(4))// 微信号
				.contactsQq(TestCaseUtil.qq())// QQ号
				.contactsMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-customer-contactss" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerContacts tenantInfo = TenantCustomerContacts.builder()//
				//.tenantId(RandomUtil.randomString(4))// 租户ID
				//.customerId(RandomUtil.randomString(4))// 用户ID
				//.customerCode(RandomUtil.randomString(4))// 用户号
				//.contactsName(TestCaseUtil.name())// 联系人姓名
				//.contactsAddress(TestCaseUtil.address())// 联系人地址
				//.contactsMain(RandomUtil.randomInt(0,1000+1))// 主联系人（1：是；0：否）
				//.contactsSex(RandomUtil.randomInt(0,1000+1))// 性别（1：男；0：女）
				//.contactsBirthday(new Date())// 出生日期
				//.contactsMobile(TestCaseUtil.mobile())// 手机号码
				//.contactsTel(TestCaseUtil.tel())// 固定电话
				//.contactsEmail(TestCaseUtil.email(null))// 邮箱地址
				//.contactsWx(RandomUtil.randomString(4))// 微信号
				//.contactsQq(TestCaseUtil.qq())// QQ号
				//.contactsMemo(RandomUtil.randomString(4))// 备注
				//.addTime(new Date())// 数据新增时间
				//.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-customer-contactss" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-customer-contactss" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
