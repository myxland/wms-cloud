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
import com.zlsrj.wms.api.entity.ModuleMenu;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ModuleMenuRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/module-menus" + "/" + id))
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
		// params.add("moduleId",RandomUtil.randomLong());// 服务模块ID
		// params.add("menuName",TestCaseUtil.name());// 菜单名称
		// params.add("menuOrder",RandomUtil.randomInt(0,1000+1));// 菜单排序
		// params.add("menuIcon",RandomUtil.randomString(4));// 菜单图标
		// params.add("menuParentId",RandomUtil.randomLong());// 父菜单ID
		// params.add("basicEditionOn",RandomUtil.randomInt(0,1+1));// 开放基础版（1：开放；0：不开放）
		// params.add("advanceEditionOn",RandomUtil.randomInt(0,1+1));// 开放高级版（1：开放；0：不开放）
		// params.add("ultimateEditionOn",RandomUtil.randomInt(0,1+1));// 开放旗舰版（1：开放；0：不开放）
		// params.add("basicUrl",RandomUtil.randomString(4));// 基础版链接地址
		// params.add("advanceUrl",RandomUtil.randomString(4));// 高级版链接地址
		// params.add("ultimateUrl",RandomUtil.randomString(4));// 旗舰版链接地址

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/module-menus").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		ModuleMenu tenantInfo = ModuleMenu.builder()//
				.id(TestCaseUtil.id())// 
				.moduleId(RandomUtil.randomLong())// 服务模块ID
				.menuName(TestCaseUtil.name())// 菜单名称
				.menuOrder(RandomUtil.randomInt(0,1000+1))// 菜单排序
				.menuIcon(RandomUtil.randomString(4))// 菜单图标
				.menuParentId(RandomUtil.randomLong())// 父菜单ID
				.basicEditionOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				.advanceEditionOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				.ultimateEditionOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				.basicUrl(RandomUtil.randomString(4))// 基础版链接地址
				.advanceUrl(RandomUtil.randomString(4))// 高级版链接地址
				.ultimateUrl(RandomUtil.randomString(4))// 旗舰版链接地址
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/module-menus").content(JSON.toJSONString(tenantInfo)) //
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

		ModuleMenu tenantInfo = ModuleMenu.builder()//
				//.id(TestCaseUtil.id())// 
				.moduleId(RandomUtil.randomLong())// 服务模块ID
				.menuName(TestCaseUtil.name())// 菜单名称
				.menuOrder(RandomUtil.randomInt(0,1000+1))// 菜单排序
				.menuIcon(RandomUtil.randomString(4))// 菜单图标
				.menuParentId(RandomUtil.randomLong())// 父菜单ID
				.basicEditionOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				.advanceEditionOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				.ultimateEditionOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				.basicUrl(RandomUtil.randomString(4))// 基础版链接地址
				.advanceUrl(RandomUtil.randomString(4))// 高级版链接地址
				.ultimateUrl(RandomUtil.randomString(4))// 旗舰版链接地址
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/module-menus" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		ModuleMenu tenantInfo = ModuleMenu.builder()//
				//.moduleId(RandomUtil.randomLong())// 服务模块ID
				//.menuName(TestCaseUtil.name())// 菜单名称
				//.menuOrder(RandomUtil.randomInt(0,1000+1))// 菜单排序
				//.menuIcon(RandomUtil.randomString(4))// 菜单图标
				//.menuParentId(RandomUtil.randomLong())// 父菜单ID
				//.basicEditionOn(RandomUtil.randomInt(0,1+1))// 开放基础版（1：开放；0：不开放）
				//.advanceEditionOn(RandomUtil.randomInt(0,1+1))// 开放高级版（1：开放；0：不开放）
				//.ultimateEditionOn(RandomUtil.randomInt(0,1+1))// 开放旗舰版（1：开放；0：不开放）
				//.basicUrl(RandomUtil.randomString(4))// 基础版链接地址
				//.advanceUrl(RandomUtil.randomString(4))// 高级版链接地址
				//.ultimateUrl(RandomUtil.randomString(4))// 旗舰版链接地址
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/module-menus" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/module-menus" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
