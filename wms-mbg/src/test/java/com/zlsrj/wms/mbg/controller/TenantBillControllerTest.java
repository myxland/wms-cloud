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
import com.zlsrj.wms.api.entity.TenantBill;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantBillControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantBill/select" + "/" + id)).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void listTest() throws Exception {
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantBill/list")).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void pageTest() throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("tenantType", Integer.toString(5));

		params.add("pageNum", Integer.toString(1));
		params.add("pageSize", Integer.toString(10));

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantBill/page").params(params))
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void insertTest() throws Exception {
		TenantBill tenantBill = TenantBill.builder()//
				.id(TestCaseUtil.id())// 编号ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.billPrintType(RandomUtil.randomInt(0,1+1))// 用户发票开具方式（按实收开票/按应收开票）
				.billRemark(RandomUtil.randomString(4))// 发票备注定义
				.billService(RandomUtil.randomString(4))// 电子发票服务商（百望/航天信息）
				.billJrdm(RandomUtil.randomString(4))// 接入代码
				.billQmcs(RandomUtil.randomString(4))// 签名值参数
				.billSkpbh(RandomUtil.randomString(4))// 税控盘编号
				.billSkpkl(RandomUtil.randomString(4))// 税控盘口令
				.billKeypwd(RandomUtil.randomString(4))// 税务数字证书密码
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenantBill/insert").content(JSONUtil.toJsonStr(tenantBill)) // 请求的url,请求的方法是get
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
		TenantBill tenantBill = TenantBill.builder()//
				.id(id)// 
				.tenantId(RandomUtil.randomLong())// 租户编号
				.billPrintType(RandomUtil.randomInt(0,1+1))// 用户发票开具方式（按实收开票/按应收开票）
				.billRemark(RandomUtil.randomString(4))// 发票备注定义
				.billService(RandomUtil.randomString(4))// 电子发票服务商（百望/航天信息）
				.billJrdm(RandomUtil.randomString(4))// 接入代码
				.billQmcs(RandomUtil.randomString(4))// 签名值参数
				.billSkpbh(RandomUtil.randomString(4))// 税控盘编号
				.billSkpkl(RandomUtil.randomString(4))// 税控盘口令
				.billKeypwd(RandomUtil.randomString(4))// 税务数字证书密码
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenantBill/update").content(JSONUtil.toJsonStr(tenantBill)) // 请求的url,请求的方法是get
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
				.perform(MockMvcRequestBuilders.put("/tenantBill/update" + "/" + id + "/status" + "/" + status)
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
				.perform(MockMvcRequestBuilders.delete("/tenantBill/delete" + "/" + id)
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
				.perform(MockMvcRequestBuilders.delete("/tenantBill/delete/ids" + "/" + StringUtils.join(list, ','))
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

}
