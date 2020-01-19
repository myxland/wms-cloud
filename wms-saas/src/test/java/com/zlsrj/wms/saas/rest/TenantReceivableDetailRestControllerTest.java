package com.zlsrj.wms.saas.rest;

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
import com.zlsrj.wms.api.entity.TenantReceivableDetail;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantReceivableDetailRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-receivable-details" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 应收明细账ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("receivableId",RandomUtil.randomLong());// 应收总账ID
		// params.add("priceStepId",RandomUtil.randomLong());// 价格阶梯ID
		// params.add("receivableWaters",new BigDecimal(0));// 应收水量
		// params.add("arrearsWaters",new BigDecimal(0));// 欠费水量
		// params.add("priceItemId",RandomUtil.randomLong());// 费用项目ID
		// params.add("detailPrice",new BigDecimal(0));// 价格
		// params.add("receivableMoney",new BigDecimal(0));// 应收金额
		// params.add("arrearsMoney",new BigDecimal(0));// 欠费金额

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-receivable-details").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantReceivableDetail tenantInfo = TenantReceivableDetail.builder()//
				.id(TestCaseUtil.id())// 应收明细账ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.receivableId(RandomUtil.randomLong())// 应收总账ID
				.priceStepId(RandomUtil.randomLong())// 价格阶梯ID
				.receivableWaters(new BigDecimal(0))// 应收水量
				.arrearsWaters(new BigDecimal(0))// 欠费水量
				.priceItemId(RandomUtil.randomLong())// 费用项目ID
				.detailPrice(new BigDecimal(0))// 价格
				.receivableMoney(new BigDecimal(0))// 应收金额
				.arrearsMoney(new BigDecimal(0))// 欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-receivable-details").content(JSON.toJSONString(tenantInfo)) //
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

		TenantReceivableDetail tenantInfo = TenantReceivableDetail.builder()//
				//.id(TestCaseUtil.id())// 应收明细账ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.receivableId(RandomUtil.randomLong())// 应收总账ID
				.priceStepId(RandomUtil.randomLong())// 价格阶梯ID
				.receivableWaters(new BigDecimal(0))// 应收水量
				.arrearsWaters(new BigDecimal(0))// 欠费水量
				.priceItemId(RandomUtil.randomLong())// 费用项目ID
				.detailPrice(new BigDecimal(0))// 价格
				.receivableMoney(new BigDecimal(0))// 应收金额
				.arrearsMoney(new BigDecimal(0))// 欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-receivable-details" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantReceivableDetail tenantInfo = TenantReceivableDetail.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户ID
				//.receivableId(RandomUtil.randomLong())// 应收总账ID
				//.priceStepId(RandomUtil.randomLong())// 价格阶梯ID
				//.receivableWaters(new BigDecimal(0))// 应收水量
				//.arrearsWaters(new BigDecimal(0))// 欠费水量
				//.priceItemId(RandomUtil.randomLong())// 费用项目ID
				//.detailPrice(new BigDecimal(0))// 价格
				//.receivableMoney(new BigDecimal(0))// 应收金额
				//.arrearsMoney(new BigDecimal(0))// 欠费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-receivable-details" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-receivable-details" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
