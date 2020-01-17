package com.zlsrj.wms.saas.rest;

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
import com.zlsrj.wms.api.entity.TenantBooklet;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantBookletRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-booklets" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 表册ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("bookletDepartmentId",RandomUtil.randomLong());// 所属部门ID
		// params.add("bookletWaterAreaId",RandomUtil.randomLong());// 所属供水区域ID
		// params.add("bookletCode",RandomUtil.randomString(4));// 表册代码
		// params.add("bookletName",TestCaseUtil.name());// 表册名称
		// params.add("bookletReadEmployeeId",RandomUtil.randomLong());// 抄表员ID
		// params.add("bookletChargeEmployeeId",RandomUtil.randomLong());// 收费员ID
		// params.add("bookletSettleCycleInterval",RandomUtil.randomInt(0,1000+1));// 结算间隔周期[月]
		// params.add("bookletLastSettleMonth",new Date());// 最后一次结算月份
		// params.add("bookletNextSettleMonth",new Date());// 下次计划结算月份
		// params.add("bookletStatus",RandomUtil.randomInt(0,1+1));// 表册状态（1：抄表进行中；2：抄表截止）
		// params.add("bookletOn",RandomUtil.randomInt(0,1+1));// 表册可用状态（1：可用；0：禁用）

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-booklets").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantBooklet tenantInfo = TenantBooklet.builder()//
				.id(TestCaseUtil.id())// 表册ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.bookletDepartmentId(RandomUtil.randomLong())// 所属部门ID
				.bookletWaterAreaId(RandomUtil.randomLong())// 所属供水区域ID
				.bookletCode(RandomUtil.randomString(4))// 表册代码
				.bookletName(TestCaseUtil.name())// 表册名称
				.bookletReadEmployeeId(RandomUtil.randomLong())// 抄表员ID
				.bookletChargeEmployeeId(RandomUtil.randomLong())// 收费员ID
				.bookletSettleCycleInterval(RandomUtil.randomInt(0,1000+1))// 结算间隔周期[月]
				.bookletLastSettleMonth(new Date())// 最后一次结算月份
				.bookletNextSettleMonth(new Date())// 下次计划结算月份
				.bookletStatus(RandomUtil.randomInt(0,1+1))// 表册状态（1：抄表进行中；2：抄表截止）
				.bookletOn(RandomUtil.randomInt(0,1+1))// 表册可用状态（1：可用；0：禁用）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-booklets").content(JSON.toJSONString(tenantInfo)) //
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

		TenantBooklet tenantInfo = TenantBooklet.builder()//
				//.id(TestCaseUtil.id())// 表册ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.bookletDepartmentId(RandomUtil.randomLong())// 所属部门ID
				.bookletWaterAreaId(RandomUtil.randomLong())// 所属供水区域ID
				.bookletCode(RandomUtil.randomString(4))// 表册代码
				.bookletName(TestCaseUtil.name())// 表册名称
				.bookletReadEmployeeId(RandomUtil.randomLong())// 抄表员ID
				.bookletChargeEmployeeId(RandomUtil.randomLong())// 收费员ID
				.bookletSettleCycleInterval(RandomUtil.randomInt(0,1000+1))// 结算间隔周期[月]
				.bookletLastSettleMonth(new Date())// 最后一次结算月份
				.bookletNextSettleMonth(new Date())// 下次计划结算月份
				.bookletStatus(RandomUtil.randomInt(0,1+1))// 表册状态（1：抄表进行中；2：抄表截止）
				.bookletOn(RandomUtil.randomInt(0,1+1))// 表册可用状态（1：可用；0：禁用）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-booklets" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantBooklet tenantInfo = TenantBooklet.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户ID
				//.bookletDepartmentId(RandomUtil.randomLong())// 所属部门ID
				//.bookletWaterAreaId(RandomUtil.randomLong())// 所属供水区域ID
				//.bookletCode(RandomUtil.randomString(4))// 表册代码
				//.bookletName(TestCaseUtil.name())// 表册名称
				//.bookletReadEmployeeId(RandomUtil.randomLong())// 抄表员ID
				//.bookletChargeEmployeeId(RandomUtil.randomLong())// 收费员ID
				//.bookletSettleCycleInterval(RandomUtil.randomInt(0,1000+1))// 结算间隔周期[月]
				//.bookletLastSettleMonth(new Date())// 最后一次结算月份
				//.bookletNextSettleMonth(new Date())// 下次计划结算月份
				//.bookletStatus(RandomUtil.randomInt(0,1+1))// 表册状态（1：抄表进行中；2：抄表截止）
				//.bookletOn(RandomUtil.randomInt(0,1+1))// 表册可用状态（1：可用；0：禁用）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-booklets" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-booklets" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
