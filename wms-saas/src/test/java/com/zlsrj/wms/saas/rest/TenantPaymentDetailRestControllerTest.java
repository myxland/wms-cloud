package com.zlsrj.wms.saas.rest;

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
import com.zlsrj.wms.api.entity.TenantPaymentDetail;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPaymentDetailRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-payment-details" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 实收明细账ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("paymentId",RandomUtil.randomLong());// 实收总账ID
		// params.add("receivableTime",new Date());// 对应的应收账时间
		// params.add("receivableId",RandomUtil.randomLong());// 应收总账ID
		// params.add("receivableDetailId",RandomUtil.randomLong());// 应收明细账ID
		// params.add("stepNo",RandomUtil.randomInt(0,1000+1));// 阶梯号
		// params.add("payWaters",new BigDecimal(0));// 收费水量
		// params.add("priceTypeId",RandomUtil.randomLong());// 价格分类ID
		// params.add("priceItemId",RandomUtil.randomLong());// 费用项目ID
		// params.add("payPrice",new BigDecimal(0));// 价格
		// params.add("payMoney",new BigDecimal(0));// 收费金额

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-payment-details").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantPaymentDetail tenantInfo = TenantPaymentDetail.builder()//
				.id(TestCaseUtil.id())// 实收明细账ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.paymentId(RandomUtil.randomLong())// 实收总账ID
				.receivableTime(new Date())// 对应的应收账时间
				.receivableId(RandomUtil.randomLong())// 应收总账ID
				.receivableDetailId(RandomUtil.randomLong())// 应收明细账ID
				.stepNo(RandomUtil.randomInt(0,1000+1))// 阶梯号
				.payWaters(new BigDecimal(0))// 收费水量
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.priceItemId(RandomUtil.randomLong())// 费用项目ID
				.payPrice(new BigDecimal(0))// 价格
				.payMoney(new BigDecimal(0))// 收费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-payment-details").content(JSON.toJSONString(tenantInfo)) //
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

		TenantPaymentDetail tenantInfo = TenantPaymentDetail.builder()//
				//.id(TestCaseUtil.id())// 实收明细账ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.paymentId(RandomUtil.randomLong())// 实收总账ID
				.receivableTime(new Date())// 对应的应收账时间
				.receivableId(RandomUtil.randomLong())// 应收总账ID
				.receivableDetailId(RandomUtil.randomLong())// 应收明细账ID
				.stepNo(RandomUtil.randomInt(0,1000+1))// 阶梯号
				.payWaters(new BigDecimal(0))// 收费水量
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.priceItemId(RandomUtil.randomLong())// 费用项目ID
				.payPrice(new BigDecimal(0))// 价格
				.payMoney(new BigDecimal(0))// 收费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-payment-details" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantPaymentDetail tenantInfo = TenantPaymentDetail.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户ID
				//.paymentId(RandomUtil.randomLong())// 实收总账ID
				//.receivableTime(new Date())// 对应的应收账时间
				//.receivableId(RandomUtil.randomLong())// 应收总账ID
				//.receivableDetailId(RandomUtil.randomLong())// 应收明细账ID
				//.stepNo(RandomUtil.randomInt(0,1000+1))// 阶梯号
				//.payWaters(new BigDecimal(0))// 收费水量
				//.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				//.priceItemId(RandomUtil.randomLong())// 费用项目ID
				//.payPrice(new BigDecimal(0))// 价格
				//.payMoney(new BigDecimal(0))// 收费金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-payment-details" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-payment-details" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
