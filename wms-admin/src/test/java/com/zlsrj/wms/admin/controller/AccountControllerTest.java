package com.zlsrj.wms.admin.controller;

import org.apache.commons.lang3.StringUtils;
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

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AccountControllerTest {
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
	public void getAdminInfoTest() throws Exception {
		String tokenHead = "Bearer";
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxZDVjYzYyZWM5Njg0OGNmYTExNWFlNzRhNWE1M2MxZSIsImNyZWF0ZWQiOjE1ODU4MzAxMDcwNDAsImV4cCI6MTU4NjQzNDkwNywidGVuYW50IjoiZTFkZGI2MDFiNmNjNDhiNzlmOTg5ZDcxMDcxMmY2ZDAifQ.WFTIKlwswJgiqJUVw83-ySI1wCxT-O49mdosSNv0opzDgA2f6rfZZ33pNFF8Ggo6K6HQJ_M2Ry59eOyIVfJ0tg";

		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/account/info")//
						.header("Authorization", StringUtils.join(tokenHead, " ", token))//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
