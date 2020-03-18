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
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceStepRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-price-steps" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 阶梯明细ID
		// params.add("tenantId",RandomUtil.randomString(4));// 租户ID
		// params.add("priceId",RandomUtil.randomString(4));// 水价列表ID
		// params.add("priceItemId",RandomUtil.randomString(4));// 费用项目ID
		// params.add("stepClass",RandomUtil.randomInt(0,1000+1));// 阶梯级次
		// params.add("startCode",new BigDecimal(0));// 阶梯起始量
		// params.add("endCode",new BigDecimal(0));// 阶梯终止量
		// params.add("stepPrice",new BigDecimal(0));// 单价
		// params.add("stepUsers",RandomUtil.randomInt(0,1000+1));// 标准用水人数
		// params.add("stepUsersAdd",TestCaseUtil.address());// 超人数增补量
		// params.add("addTime",new Date());// 数据新增时间
		// params.add("updateTime",new Date());// 数据修改时间

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-price-steps").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantPriceStep tenantInfo = TenantPriceStep.builder()//
				.id(TestCaseUtil.id())// 阶梯明细ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.priceId(RandomUtil.randomString(4))// 水价列表ID
				.priceItemId(RandomUtil.randomString(4))// 费用项目ID
				.stepClass(RandomUtil.randomInt(0,1000+1))// 阶梯级次
				.startCode(new BigDecimal(0))// 阶梯起始量
				.endCode(new BigDecimal(0))// 阶梯终止量
				.stepPrice(new BigDecimal(0))// 单价
				.stepUsers(RandomUtil.randomInt(0,1000+1))// 标准用水人数
				.stepUsersAdd(RandomUtil.randomBigDecimal(new BigDecimal(0), new BigDecimal(1000)))// 超人数增补量
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-price-steps").content(JSON.toJSONString(tenantInfo)) //
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

		TenantPriceStep tenantInfo = TenantPriceStep.builder()//
				//.id(TestCaseUtil.id())// 阶梯明细ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.priceId(RandomUtil.randomString(4))// 水价列表ID
				.priceItemId(RandomUtil.randomString(4))// 费用项目ID
				.stepClass(RandomUtil.randomInt(0,1000+1))// 阶梯级次
				.startCode(new BigDecimal(0))// 阶梯起始量
				.endCode(new BigDecimal(0))// 阶梯终止量
				.stepPrice(new BigDecimal(0))// 单价
				.stepUsers(RandomUtil.randomInt(0,1000+1))// 标准用水人数
				.stepUsersAdd(RandomUtil.randomBigDecimal(new BigDecimal(0), new BigDecimal(1000)))// 超人数增补量
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-price-steps" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantPriceStep tenantInfo = TenantPriceStep.builder()//
				//.tenantId(RandomUtil.randomString(4))// 租户ID
				//.priceId(RandomUtil.randomString(4))// 水价列表ID
				//.priceItemId(RandomUtil.randomString(4))// 费用项目ID
				//.stepClass(RandomUtil.randomInt(0,1000+1))// 阶梯级次
				//.startCode(new BigDecimal(0))// 阶梯起始量
				//.endCode(new BigDecimal(0))// 阶梯终止量
				//.stepPrice(new BigDecimal(0))// 单价
				//.stepUsers(RandomUtil.randomInt(0,1000+1))// 标准用水人数
				//.stepUsersAdd(TestCaseUtil.address())// 超人数增补量
				//.addTime(new Date())// 数据新增时间
				//.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-price-steps" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-price-steps" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
