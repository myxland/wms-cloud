package com.zlsrj.wms.admin.controller;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.zlsrj.wms.api.dto.AdminLoginParam;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AdminControllerTest {
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
	public void loginTest() throws Exception {
		AdminLoginParam adminLoginParam = new AdminLoginParam();
		adminLoginParam.setUsername("admin");
		adminLoginParam.setPassword("123456");
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/admin/login")//
						.content(JSONUtil.toJsonStr(adminLoginParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
