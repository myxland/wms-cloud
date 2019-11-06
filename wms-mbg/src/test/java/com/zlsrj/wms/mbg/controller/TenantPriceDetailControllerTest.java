package com.zlsrj.wms.mbg.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
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
import com.zlsrj.wms.api.entity.TenantPriceDetail;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceDetailControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantPriceDetail/select" + "/" + id)).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void listTest() throws Exception {
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantPriceDetail/list")).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void pageTest() throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("tenantType", Integer.toString(5));

		params.add("pageNum", Integer.toString(1));
		params.add("pageSize", Integer.toString(10));

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantPriceDetail/page").params(params))
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void insertTest() throws Exception {
		TenantPriceDetail tenantPriceDetail = TenantPriceDetail.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别
				.priceItemId(RandomUtil.randomLong())// 费用项目
				.calcType(RandomUtil.randomInt(0,1+1))// 计算方法（固定单价/固定金额/阶梯价格）
				.price(new BigDecimal(0))// 指定价格（金额）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenantPriceDetail/insert").content(JSONUtil.toJsonStr(tenantPriceDetail)) // 请求的url,请求的方法是get
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
		TenantPriceDetail tenantPriceDetail = TenantPriceDetail.builder()//
				.id(id)// 
				.tenantId(RandomUtil.randomLong())// 租户编号
				.priceTypeId(RandomUtil.randomLong())// 价格类别
				.priceItemId(RandomUtil.randomLong())// 费用项目
				.calcType(RandomUtil.randomInt(0,1+1))// 计算方法（固定单价/固定金额/阶梯价格）
				.price(new BigDecimal(0))// 指定价格（金额）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenantPriceDetail/update").content(JSONUtil.toJsonStr(tenantPriceDetail)) // 请求的url,请求的方法是get
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
				.perform(MockMvcRequestBuilders.put("/tenantPriceDetail/update" + "/" + id + "/status" + "/" + status)
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
				.perform(MockMvcRequestBuilders.delete("/tenantPriceDetail/delete" + "/" + id)
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
				.perform(MockMvcRequestBuilders.delete("/tenantPriceDetail/delete/ids" + "/" + StringUtils.join(list, ','))
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

}
