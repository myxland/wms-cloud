package com.zlsrj.wms.mbg.rest;

import java.math.BigDecimal;

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
import com.zlsrj.wms.api.entity.TenantSystemPrice;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantSystemPriceRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-system-prices" + "/" + id))
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
		// params.add("tenantId",RandomUtil.randomLong());// 租户编号
		// params.add("sysId",RandomUtil.randomLong());// 模块编号
		// params.add("sysEdition",RandomUtil.randomInt(0,1000+1));// 模块版本（0：基础版；1：高级版；2：旗舰版）
		// params.add("startNum",RandomUtil.randomInt(0,1000+1));// 起始量
		// params.add("endNum",RandomUtil.randomInt(0,1000+1));// 终止量
		// params.add("price",new BigDecimal(0));// 价格

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-system-prices").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantSystemPrice tenantInfo = TenantSystemPrice.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（0：基础版；1：高级版；2：旗舰版）
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.price(new BigDecimal(0))// 价格
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-system-prices").content(JSON.toJSONString(tenantInfo)) //
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

		TenantSystemPrice tenantInfo = TenantSystemPrice.builder()//
				//.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（0：基础版；1：高级版；2：旗舰版）
				.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				.price(new BigDecimal(0))// 价格
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-system-prices" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantSystemPrice tenantInfo = TenantSystemPrice.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.sysId(RandomUtil.randomLong())// 模块编号
				//.sysEdition(RandomUtil.randomInt(0,1000+1))// 模块版本（0：基础版；1：高级版；2：旗舰版）
				//.startNum(RandomUtil.randomInt(0,1000+1))// 起始量
				//.endNum(RandomUtil.randomInt(0,1000+1))// 终止量
				//.price(new BigDecimal(0))// 价格
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-system-prices" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-system-prices" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
