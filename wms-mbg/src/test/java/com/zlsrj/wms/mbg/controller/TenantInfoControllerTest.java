package com.zlsrj.wms.mbg.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.zlsrj.wms.mbg.entity.TenantInfo;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantInfoControllerTest {
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
		Long id = 1189861168806760448L;
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantInfo/select" + "/" + id)).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void listTest() throws Exception {
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantInfo/list")).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void pageTest() throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("tenantStatus", Integer.toString(1));

		params.add("pageNum", Integer.toString(2));
		params.add("pageSize", Integer.toString(3));

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantInfo/page").params(params))
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void insertTest() throws Exception {
		TenantInfo tenantInfo = TenantInfo.builder().id(IdUtil.createSnowflake(1L, 1L).nextId())
				.tenantName(RandomUtil.randomString(4)).displayName(RandomUtil.randomString(4))
				.tenantLinkman(RandomUtil.randomString(4))
				.tenantMobile(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 11))
				.tenantTel(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 11))
				.tenantEmail(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 8) + "@qq.com")
				.tenantQq(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 8)).tenantType(1).tenantStatus(1)
				.regTime(new Date()).endDate(new Date()).build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenantInfo/insert").content(JSONUtil.toJsonStr(tenantInfo)) // 请求的url,请求的方法是get
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updateTest() throws Exception {
		Long id = 1189879741449113600L;
		TenantInfo tenantInfo = TenantInfo.builder().id(id).tenantName(RandomUtil.randomString(4))
				.displayName(RandomUtil.randomString(4)).tenantLinkman(RandomUtil.randomString(4))
				.tenantMobile(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 11))
				.tenantTel(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 11))
				.tenantEmail(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 8) + "@qq.com")
				.tenantQq(RandomUtil.randomString(RandomUtil.BASE_NUMBER, 8)).tenantType(1).tenantStatus(1)
				.regTime(new Date()).endDate(new Date()).build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenantInfo/update")
						.content(JSONUtil.toJsonStr(tenantInfo)) // 请求的url,请求的方法是get
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updateStatusTest() throws Exception {
		Long id = 1189861317452894208L;
		Integer status = 0;

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenantInfo/update" + "/" + id + "/status" + "/" + status)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void deleteTest() throws Exception {
		Long id = 1189808734482862080L;

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.delete("/tenantInfo/delete" + "/" + id)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void deleteByIdsTest() throws Exception {
		Long[] ids = new Long[] { 1189808830318514176L, 1189808905383972864L, 1189816879435354112L,
				1189817068195811328L, 1189818722504806400L, };
		List<String> list = new ArrayList<String>();
		Arrays.asList(ids).forEach(id -> list.add(Long.toString(id)));

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.delete("/tenantInfo/delete/ids" + "/" + StringUtils.join(list, ','))
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	

}
