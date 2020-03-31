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
import com.zlsrj.wms.api.entity.TenantCustomerArchives;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerArchivesRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-archivess" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 档案ID
		// params.add("tenantId",RandomUtil.randomString(4));// 租户ID
		// params.add("customerId",RandomUtil.randomString(4));// 用户ID
		// params.add("customerCode",RandomUtil.randomString(4));// 用户号
		// params.add("archivesName",TestCaseUtil.name());// 档案名称
		// params.add("archivesCreateTime",new Date());// 创建时间
		// params.add("archivesCreateDate",new Date());// 创建日期
		// params.add("archivesFilename",RandomUtil.randomString(4));// 存储文件名称JSON串
		// params.add("archivesInformation",RandomUtil.randomString(4));// 证件信息JSON串，例如身份证号、地址等
		// params.add("archivesCode",RandomUtil.randomString(4));// 证件号码，例如身份证号等
		// params.add("addTime",new Date());// 数据新增时间
		// params.add("updateTime",new Date());// 数据修改时间

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-archivess").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantCustomerArchives tenantInfo = TenantCustomerArchives.builder()//
				.id(TestCaseUtil.id())// 档案ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.archivesName(TestCaseUtil.name())// 档案名称
				.archivesCreateTime(new Date())// 创建时间
				.archivesCreateDate(new Date())// 创建日期
				.archivesFilename(RandomUtil.randomString(4))// 存储文件名称JSON串
				.archivesInformation(RandomUtil.randomString(4))// 证件信息JSON串，例如身份证号、地址等
				.archivesCode(RandomUtil.randomString(4))// 证件号码，例如身份证号等
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-customer-archivess").content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerArchives tenantInfo = TenantCustomerArchives.builder()//
				//.id(TestCaseUtil.id())// 档案ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.archivesName(TestCaseUtil.name())// 档案名称
				.archivesCreateTime(new Date())// 创建时间
				.archivesCreateDate(new Date())// 创建日期
				.archivesFilename(RandomUtil.randomString(4))// 存储文件名称JSON串
				.archivesInformation(RandomUtil.randomString(4))// 证件信息JSON串，例如身份证号、地址等
				.archivesCode(RandomUtil.randomString(4))// 证件号码，例如身份证号等
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-customer-archivess" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerArchives tenantInfo = TenantCustomerArchives.builder()//
				//.tenantId(RandomUtil.randomString(4))// 租户ID
				//.customerId(RandomUtil.randomString(4))// 用户ID
				//.customerCode(RandomUtil.randomString(4))// 用户号
				//.archivesName(TestCaseUtil.name())// 档案名称
				//.archivesCreateTime(new Date())// 创建时间
				//.archivesCreateDate(new Date())// 创建日期
				//.archivesFilename(RandomUtil.randomString(4))// 存储文件名称JSON串
				//.archivesInformation(RandomUtil.randomString(4))// 证件信息JSON串，例如身份证号、地址等
				//.archivesCode(RandomUtil.randomString(4))// 证件号码，例如身份证号等
				//.addTime(new Date())// 数据新增时间
				//.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-customer-archivess" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-customer-archivess" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
