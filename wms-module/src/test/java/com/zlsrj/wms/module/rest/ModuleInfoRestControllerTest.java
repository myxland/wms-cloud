package com.zlsrj.wms.module.rest;


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
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ModuleInfoRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/module-infos" + "/" + id))
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
		// params.add("relyId",RandomUtil.randomLong());// 依赖模块编码
		// params.add("moduleName",TestCaseUtil.name());// 模块名称
		// params.add("openTenantType",RandomUtil.randomInt(0,1+1));// 开放对象（1：使用单位；2：供应单位；3：内部运营）
		// params.add("runEnvType",RandomUtil.randomInt(0,1+1));// 运行环境（1：PC；2：移动端）
		// params.add("pricePolicyType",RandomUtil.randomInt(0,1+1));// 价格政策（0：免费；1：按量付费；2：固定价格）
		// params.add("billCycleType",RandomUtil.randomInt(0,1+1));// 计费周期（1：实时；2：按天；3：按月；4：按年）
		// params.add("basicOn",RandomUtil.randomInt(0,1+1));// 开放基础版（1：开放；0：不开放）
		// params.add("advanceOn",RandomUtil.randomInt(0,1+1));// 开放高级版（1：开放；0：不开放）
		// params.add("ultimateOn",RandomUtil.randomInt(0,1+1));// 开放旗舰版（1：开放；0：不开放）
		// params.add("moduleReleaseOn",RandomUtil.randomInt(0,1+1));// 功能发布（1：已发布；0：未发布）

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/module-infos").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		ModuleInfo tenantInfo = ModuleInfo.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/module-infos").content(JSON.toJSONString(tenantInfo)) //
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

		ModuleInfo tenantInfo = ModuleInfo.builder()//
				//.id(TestCaseUtil.id())// 系统ID
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/module-infos" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		ModuleInfo tenantInfo = ModuleInfo.builder()//
				//.relyId(RandomUtil.randomLong())// 依赖模块编码
				//.moduleName(TestCaseUtil.name())// 模块名称
				//.openTenantType(RandomUtil.randomInt(0,1+1))// 开放对象（1：使用单位；2：供应单位；3：内部运营）
				//.runEnvType(RandomUtil.randomInt(0,1+1))// 运行环境（1：PC；2：移动端）
				//.pricePolicyType(RandomUtil.randomInt(0,1+1))// 价格政策（0：免费；1：按量付费；2：固定价格）
				//.billCycleType(RandomUtil.randomInt(0,1+1))// 计费周期（1：实时；2：按天；3：按月；4：按年）
				//.basicOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				//.advanceOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				//.ultimateOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				//.moduleReleaseOn(RandomUtil.randomInt(0,1+1))// 功能发布（1：已发布；0：未发布）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/module-infos" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/module-infos" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
