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
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantEmployeeRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-employees" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 系统ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户编号
		// params.add("empName",TestCaseUtil.name());// 员工名称
		// params.add("empPassword",RandomUtil.randomString(4));// 登录密码
		// params.add("deptId",RandomUtil.randomLong());// 员工部门
		// params.add("loginOn",RandomUtil.randomInt(0,1+1));// 可登录系统（1可登录，0不能登录）
		// params.add("empStatus",RandomUtil.randomInt(0,1+1));// 员工状态（在职/离职/禁用）
		// params.add("empMobile",TestCaseUtil.mobile());// 员工手机号
		// params.add("empEmail",TestCaseUtil.email(null));// 员工邮箱
		// params.add("empPersonalWx",RandomUtil.randomString(4));// 员工个人微信号
		// params.add("empEnterpriceWx",RandomUtil.randomString(4));// 员工企业微信号

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-employees").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantEmployee tenantInfo = TenantEmployee.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.empName(TestCaseUtil.name())// 员工名称
				.empPassword(RandomUtil.randomString(4))// 登录密码
				.deptId(RandomUtil.randomLong())// 员工部门
				.loginOn(RandomUtil.randomInt(0,1+1))// 可登录系统（1可登录，0不能登录）
				.empStatus(RandomUtil.randomInt(0,1+1))// 员工状态（在职/离职/禁用）
				.empMobile(TestCaseUtil.mobile())// 员工手机号
				.empEmail(TestCaseUtil.email(null))// 员工邮箱
				.empPersonalWx(RandomUtil.randomString(4))// 员工个人微信号
				.empEnterpriceWx(RandomUtil.randomString(4))// 员工企业微信号
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-employees").content(JSON.toJSONString(tenantInfo)) //
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

		TenantEmployee tenantInfo = TenantEmployee.builder()//
				//.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.empName(TestCaseUtil.name())// 员工名称
				.empPassword(RandomUtil.randomString(4))// 登录密码
				.deptId(RandomUtil.randomLong())// 员工部门
				.loginOn(RandomUtil.randomInt(0,1+1))// 可登录系统（1可登录，0不能登录）
				.empStatus(RandomUtil.randomInt(0,1+1))// 员工状态（在职/离职/禁用）
				.empMobile(TestCaseUtil.mobile())// 员工手机号
				.empEmail(TestCaseUtil.email(null))// 员工邮箱
				.empPersonalWx(RandomUtil.randomString(4))// 员工个人微信号
				.empEnterpriceWx(RandomUtil.randomString(4))// 员工企业微信号
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-employees" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantEmployee tenantInfo = TenantEmployee.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.empName(TestCaseUtil.name())// 员工名称
				//.empPassword(RandomUtil.randomString(4))// 登录密码
				//.deptId(RandomUtil.randomLong())// 员工部门
				//.loginOn(RandomUtil.randomInt(0,1+1))// 可登录系统（1可登录，0不能登录）
				//.empStatus(RandomUtil.randomInt(0,1+1))// 员工状态（在职/离职/禁用）
				//.empMobile(TestCaseUtil.mobile())// 员工手机号
				//.empEmail(TestCaseUtil.email(null))// 员工邮箱
				//.empPersonalWx(RandomUtil.randomString(4))// 员工个人微信号
				//.empEnterpriceWx(RandomUtil.randomString(4))// 员工企业微信号
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-employees" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-employees" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
