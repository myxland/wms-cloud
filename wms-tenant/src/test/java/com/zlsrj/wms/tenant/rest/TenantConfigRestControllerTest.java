package com.zlsrj.wms.tenant.rest;

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
import com.zlsrj.wms.api.entity.TenantConfig;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantConfigRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-configs" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 租户编号
		// params.add("tenantId",RandomUtil.randomLong());// 租户编号
		// params.add("partChargeOn",RandomUtil.randomInt(0,1+1));// 是否启用部分缴费（1：启用；0：不启用）
		// params.add("overDuefineOn",RandomUtil.randomInt(0,1+1));// 是否启用违约金（1：启用；0：不启用）
		// params.add("overDuefineDay",RandomUtil.randomInt(0,1000+1));// 违约金宽限天数
		// params.add("overDuefineRatio",new BigDecimal(0));// 违约金每天收取比例
		// params.add("overDuefineTopRatio",new BigDecimal(0));// 违约金封顶比例（与欠费金额相比）
		// params.add("ycdkType",RandomUtil.randomInt(0,1+1));// 预存抵扣方式（1：抄表后即时抵扣；2：人工发起抵扣）

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-configs").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantConfig tenantInfo = TenantConfig.builder()//
				.id(TestCaseUtil.id())// 租户编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.partChargeOn(RandomUtil.randomInt(0,1+1))// 是否启用部分缴费（1：启用；0：不启用）
				.overDuefineOn(RandomUtil.randomInt(0,1+1))// 是否启用违约金（1：启用；0：不启用）
				.overDuefineDay(RandomUtil.randomInt(0,1000+1))// 违约金宽限天数
				.overDuefineRatio(new BigDecimal(0))// 违约金每天收取比例
				.overDuefineTopRatio(new BigDecimal(0))// 违约金封顶比例（与欠费金额相比）
				.ycdkType(RandomUtil.randomInt(0,1+1))// 预存抵扣方式（1：抄表后即时抵扣；2：人工发起抵扣）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-configs").content(JSON.toJSONString(tenantInfo)) //
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

		TenantConfig tenantInfo = TenantConfig.builder()//
				//.id(TestCaseUtil.id())// 租户编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.partChargeOn(RandomUtil.randomInt(0,1+1))// 是否启用部分缴费（1：启用；0：不启用）
				.overDuefineOn(RandomUtil.randomInt(0,1+1))// 是否启用违约金（1：启用；0：不启用）
				.overDuefineDay(RandomUtil.randomInt(0,1000+1))// 违约金宽限天数
				.overDuefineRatio(new BigDecimal(0))// 违约金每天收取比例
				.overDuefineTopRatio(new BigDecimal(0))// 违约金封顶比例（与欠费金额相比）
				.ycdkType(RandomUtil.randomInt(0,1+1))// 预存抵扣方式（1：抄表后即时抵扣；2：人工发起抵扣）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-configs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantConfig tenantInfo = TenantConfig.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.partChargeOn(RandomUtil.randomInt(0,1+1))// 是否启用部分缴费（1：启用；0：不启用）
				//.overDuefineOn(RandomUtil.randomInt(0,1+1))// 是否启用违约金（1：启用；0：不启用）
				//.overDuefineDay(RandomUtil.randomInt(0,1000+1))// 违约金宽限天数
				//.overDuefineRatio(new BigDecimal(0))// 违约金每天收取比例
				//.overDuefineTopRatio(new BigDecimal(0))// 违约金封顶比例（与欠费金额相比）
				//.ycdkType(RandomUtil.randomInt(0,1+1))// 预存抵扣方式（1：抄表后即时抵扣；2：人工发起抵扣）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-configs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-configs" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
