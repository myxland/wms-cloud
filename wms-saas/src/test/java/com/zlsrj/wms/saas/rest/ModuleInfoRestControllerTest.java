package com.zlsrj.wms.saas.rest;


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
		String id = "";
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
		
		// params.add("id",TestCaseUtil.id());// 
		// params.add("moduleName",TestCaseUtil.name());// 服务模块名称
		// params.add("openTarget",RandomUtil.randomInt(0,1000+1));// 开放对象（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
		// params.add("runEnv",RandomUtil.randomInt(0,1000+1));// 运行环境（1：PC端；2：移动端；3：微信端；4：支付宝端；5：API接口；6：自助终端）
		// params.add("relyModuleId",RandomUtil.randomString(4));// 所依赖的模块ID列表
		// params.add("billingMode",RandomUtil.randomInt(0,1000+1));// 计费模式（1：默认开通；2：免费；3：按量付费；4：固定价格；5：阶梯价格）
		// params.add("billingCycle",RandomUtil.randomInt(0,1000+1));// 计费周期（0：不计费；1：实时；2：按天；3：按月；4：按年）
		// params.add("basicEditionOn",RandomUtil.randomInt(0,1+1));// 开放基础版（1：开放；0：不开放）
		// params.add("advanceEditionOn",RandomUtil.randomInt(0,1+1));// 开放高级版（1：开放；0：不开放）
		// params.add("ultimateEditionOn",RandomUtil.randomInt(0,1+1));// 开放旗舰版（1：开放；0：不开放）
		// params.add("moduleOn",RandomUtil.randomInt(0,1+1));// 服务发布状态（1：发布 ；0：未发布）

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/module-infos").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		ModuleInfo tenantInfo = ModuleInfo.builder()//
				.id(TestCaseUtil.id())// 
				.moduleName(TestCaseUtil.name())// 服务模块名称
				.openTarget(RandomUtil.randomInt(0,1000+1))// 开放对象（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
				.runEnv(RandomUtil.randomInt(0,1000+1))// 运行环境（1：PC端；2：移动端；3：微信端；4：支付宝端；5：API接口；6：自助终端）
				.relyModuleId(RandomUtil.randomString(4))// 所依赖的模块ID列表
				.billingMode(RandomUtil.randomInt(0,1000+1))// 计费模式（1：默认开通；2：免费；3：按量付费；4：固定价格；5：阶梯价格）
				.billingCycle(RandomUtil.randomInt(0,1000+1))// 计费周期（0：不计费；1：实时；2：按天；3：按月；4：按年）
				.basicEditionOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				.advanceEditionOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				.ultimateEditionOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				.moduleOn(RandomUtil.randomInt(0,1+1))// 服务发布状态（1：发布 ；0：未发布）
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
		String id = "";

		ModuleInfo tenantInfo = ModuleInfo.builder()//
				//.id(TestCaseUtil.id())// 
				.moduleName(TestCaseUtil.name())// 服务模块名称
				.openTarget(RandomUtil.randomInt(0,1000+1))// 开放对象（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
				.runEnv(RandomUtil.randomInt(0,1000+1))// 运行环境（1：PC端；2：移动端；3：微信端；4：支付宝端；5：API接口；6：自助终端）
				.relyModuleId(RandomUtil.randomString(4))// 所依赖的模块ID列表
				.billingMode(RandomUtil.randomInt(0,1000+1))// 计费模式（1：默认开通；2：免费；3：按量付费；4：固定价格；5：阶梯价格）
				.billingCycle(RandomUtil.randomInt(0,1000+1))// 计费周期（0：不计费；1：实时；2：按天；3：按月；4：按年）
				.basicEditionOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				.advanceEditionOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				.ultimateEditionOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				.moduleOn(RandomUtil.randomInt(0,1+1))// 服务发布状态（1：发布 ；0：未发布）
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
		String id = "";

		ModuleInfo tenantInfo = ModuleInfo.builder()//
				//.moduleName(TestCaseUtil.name())// 服务模块名称
				//.openTarget(RandomUtil.randomInt(0,1000+1))// 开放对象（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
				//.runEnv(RandomUtil.randomInt(0,1000+1))// 运行环境（1：PC端；2：移动端；3：微信端；4：支付宝端；5：API接口；6：自助终端）
				//.relyModuleId(RandomUtil.randomString(4))// 所依赖的模块ID列表
				//.billingMode(RandomUtil.randomInt(0,1000+1))// 计费模式（1：默认开通；2：免费；3：按量付费；4：固定价格；5：阶梯价格）
				//.billingCycle(RandomUtil.randomInt(0,1000+1))// 计费周期（0：不计费；1：实时；2：按天；3：按月；4：按年）
				//.basicEditionOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				//.advanceEditionOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				//.ultimateEditionOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				//.moduleOn(RandomUtil.randomInt(0,1+1))// 服务发布状态（1：发布 ；0：未发布）
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
		String id = "";

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/module-infos" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
