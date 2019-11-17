package com.zlsrj.wms.mbg.rest;

import java.math.BigDecimal;
import java.util.Date;

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
import com.zlsrj.wms.api.entity.TenantSystem;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantSystemRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-systems" + "/" + id))
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
		// params.add("sysDispName",TestCaseUtil.name());// 模块显示名称
		// params.add("sysOrder",RandomUtil.randomInt(0,1000+1));// 模块排序
		// params.add("sysEdition",RandomUtil.randomInt(0,1000+1));// 开通版本（1：基础版；2：高级版；3：旗舰版）
		// params.add("sysStatus",RandomUtil.randomInt(0,1+1));// 模块状态（1：开通；0：未开通）
		// params.add("sysPriceType",RandomUtil.randomInt(0,1+1));// 价格体系（1：标准价格；2：指定价格）
		// params.add("sysOpenDate",new Date());// 开通时间
		// params.add("sysEndDate",new Date());// 到期时间
		// params.add("sysStartChargeDate",new Date());// 开始计费日期
		// params.add("sysArrears",new BigDecimal(0));// 欠费金额
		// params.add("sysOverdraft",new BigDecimal(0));// 透支额度

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-systems").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantSystem tenantInfo = TenantSystem.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysDispName(TestCaseUtil.name())// 模块显示名称
				.sysOrder(RandomUtil.randomInt(0,1000+1))// 模块排序
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（1：基础版；2：高级版；3：旗舰版）
				.sysStatus(RandomUtil.randomInt(0,1+1))// 模块状态（1：开通；0：未开通）
				.sysPriceType(RandomUtil.randomInt(0,1+1))// 价格体系（1：标准价格；2：指定价格）
				.sysOpenDate(new Date())// 开通时间
				.sysEndDate(new Date())// 到期时间
				.sysStartChargeDate(new Date())// 开始计费日期
				.sysArrears(new BigDecimal(0))// 欠费金额
				.sysOverdraft(new BigDecimal(0))// 透支额度
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-systems").content(JSON.toJSONString(tenantInfo)) //
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

		TenantSystem tenantInfo = TenantSystem.builder()//
				//.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysDispName(TestCaseUtil.name())// 模块显示名称
				.sysOrder(RandomUtil.randomInt(0,1000+1))// 模块排序
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（1：基础版；2：高级版；3：旗舰版）
				.sysStatus(RandomUtil.randomInt(0,1+1))// 模块状态（1：开通；0：未开通）
				.sysPriceType(RandomUtil.randomInt(0,1+1))// 价格体系（1：标准价格；2：指定价格）
				.sysOpenDate(new Date())// 开通时间
				.sysEndDate(new Date())// 到期时间
				.sysStartChargeDate(new Date())// 开始计费日期
				.sysArrears(new BigDecimal(0))// 欠费金额
				.sysOverdraft(new BigDecimal(0))// 透支额度
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-systems" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantSystem tenantInfo = TenantSystem.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.sysId(RandomUtil.randomLong())// 模块编号
				//.sysDispName(TestCaseUtil.name())// 模块显示名称
				//.sysOrder(RandomUtil.randomInt(0,1000+1))// 模块排序
				//.sysEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（1：基础版；2：高级版；3：旗舰版）
				//.sysStatus(RandomUtil.randomInt(0,1+1))// 模块状态（1：开通；0：未开通）
				//.sysPriceType(RandomUtil.randomInt(0,1+1))// 价格体系（1：标准价格；2：指定价格）
				//.sysOpenDate(new Date())// 开通时间
				//.sysEndDate(new Date())// 到期时间
				//.sysStartChargeDate(new Date())// 开始计费日期
				//.sysArrears(new BigDecimal(0))// 欠费金额
				//.sysOverdraft(new BigDecimal(0))// 透支额度
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-systems" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-systems" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
