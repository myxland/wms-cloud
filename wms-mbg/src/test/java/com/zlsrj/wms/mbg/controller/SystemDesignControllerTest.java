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
import com.zlsrj.wms.mbg.entity.SystemDesign;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SystemDesignControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/systemDesign/select" + "/" + id)).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void listTest() throws Exception {
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/systemDesign/list")).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void pageTest() throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("tenantType", Integer.toString(5));

		params.add("pageNum", Integer.toString(1));
		params.add("pageSize", Integer.toString(10));

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/systemDesign/page").params(params))
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void insertTest() throws Exception {
		SystemDesign systemDesign = SystemDesign.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.relyId(RandomUtil.randomLong())// 依赖模块编码
				.moduleName(TestCaseUtil.name())// 模块名称
				.openTenantType(RandomUtil.randomInt(0,1+1))// 开放对象（1：使用单位；2：供应单位；3：内部运营）
				.runEnvType(RandomUtil.randomInt(0,1+1))// 运行环境（1：PC；2：移动端）
				.pricePolicyType(RandomUtil.randomInt(0,1+1))// 价格政策（0：免费；1：按量付费；2：固定价格）
				.billCycleType(RandomUtil.randomInt(0,1+1))// 计费周期（1：实时；2：按天；3：按月；4：按年）
				.basicOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				.advanceOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				.ultimateOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				.moduleReleaseOn(RandomUtil.randomInt(0,1+1))// 功能发布（1：已发布；0：未发布）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/systemDesign/insert").content(JSONUtil.toJsonStr(systemDesign)) // 请求的url,请求的方法是get
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
		SystemDesign systemDesign = SystemDesign.builder()//
				.id(id)// 
				.relyId(RandomUtil.randomLong())// 依赖模块编码
				.moduleName(TestCaseUtil.name())// 模块名称
				.openTenantType(RandomUtil.randomInt(0,1+1))// 开放对象（1：使用单位；2：供应单位；3：内部运营）
				.runEnvType(RandomUtil.randomInt(0,1+1))// 运行环境（1：PC；2：移动端）
				.pricePolicyType(RandomUtil.randomInt(0,1+1))// 价格政策（0：免费；1：按量付费；2：固定价格）
				.billCycleType(RandomUtil.randomInt(0,1+1))// 计费周期（1：实时；2：按天；3：按月；4：按年）
				.basicOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				.advanceOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				.ultimateOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				.moduleReleaseOn(RandomUtil.randomInt(0,1+1))// 功能发布（1：已发布；0：未发布）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/systemDesign/update").content(JSONUtil.toJsonStr(systemDesign)) // 请求的url,请求的方法是get
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
				.perform(MockMvcRequestBuilders.put("/systemDesign/update" + "/" + id + "/status" + "/" + status)
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
				.perform(MockMvcRequestBuilders.delete("/systemDesign/delete" + "/" + id)
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
				.perform(MockMvcRequestBuilders.delete("/systemDesign/delete/ids" + "/" + StringUtils.join(list, ','))
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

}
