package com.zlsrj.wms.mbg.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;
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

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.mbg.entity.TenantEmployee;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantEmployeeControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantEmployee/select" + "/" + id)).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void listTest() throws Exception {
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantEmployee/list")).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void pageTest() throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("tenantType", Integer.toString(5));

		params.add("pageNum", Integer.toString(1));
		params.add("pageSize", Integer.toString(10));

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantEmployee/page").params(params))
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void insertTest() throws Exception {
		TenantEmployee tenantEmployee = TenantEmployee.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomInt(0,1000+1))// 租户编号
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
				.perform(MockMvcRequestBuilders.post("/tenantEmployee/insert").content(JSONUtil.toJsonStr(tenantEmployee)) // 请求的url,请求的方法是get
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updateTest() throws Exception {
		Long id = 1L;
		TenantEmployee tenantEmployee = TenantEmployee.builder()//
				.id(id)// 
				.tenantId(RandomUtil.randomInt(0,1000+1))// 租户编号
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
				.perform(MockMvcRequestBuilders.put("/tenantEmployee/update").content(JSONUtil.toJsonStr(tenantEmployee)) // 请求的url,请求的方法是get
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updateStatusTest() throws Exception {
		Long id = 1L;
		Integer status = 0;

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenantEmployee/update" + "/" + id + "/status" + "/" + status)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void deleteTest() throws Exception {
		Long id = 1L;

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.delete("/tenantEmployee/delete" + "/" + id)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void deleteByIdsTest() throws Exception {
		Long[] ids = new Long[] { 1L, 2L, 3L, 4L, 5L };
		List<String> list = new ArrayList<String>();
		Arrays.asList(ids).forEach(id -> list.add(Long.toString(id)));

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.delete("/tenantEmployee/delete/ids" + "/" + StringUtils.join(list, ','))
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

}
