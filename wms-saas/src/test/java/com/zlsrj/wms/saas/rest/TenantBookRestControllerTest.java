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
import com.zlsrj.wms.api.entity.TenantBook;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantBookRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-books" + "/" + id))
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
		// params.add("tenantId",RandomUtil.randomString(4));// 租户ID
		// params.add("bookCode",RandomUtil.randomString(4));// 表册编号
		// params.add("bookName",TestCaseUtil.name());// 表册名称
		// params.add("bookReaderEmployeeId",RandomUtil.randomString(4));// 抄表员
		// params.add("bookChargeEmployeeId",RandomUtil.randomString(4));// 收费员
		// params.add("bookMarketingAreaId",RandomUtil.randomString(4));// 营销区域
		// params.add("bookReadCycle",RandomUtil.randomInt(0,1000+1));// 抄表周期
		// params.add("bookLastMonth",RandomUtil.randomString(4));// 最后一次抄表月份
		// params.add("bookReadMonth",RandomUtil.randomString(4));// 下次抄表月份
		// params.add("bookSettleCycle",RandomUtil.randomInt(0,1000+1));// 结算周期
		// params.add("bookSettleLastMonth",RandomUtil.randomString(4));// 最后一次结算月份
		// params.add("bookSettleMonth",RandomUtil.randomString(4));// 下次结算月份
		// params.add("bookStatus",RandomUtil.randomInt(0,1+1));// 有效状态（1：可用；0：禁用）
		// params.add("bookReadStatus",RandomUtil.randomInt(0,1+1));// 表册状态（1：抄表进行中；2：抄表截止）
		// params.add("priceClass",RandomUtil.randomInt(0,1000+1));// 级次
		// params.add("priceMemo",RandomUtil.randomString(4));// 备注
		// params.add("addTime",new Date());// 数据新增时间
		// params.add("updateTime",new Date());// 数据修改时间

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-books").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantBook tenantInfo = TenantBook.builder()//
				.id(TestCaseUtil.id())// 表册ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.bookCode(RandomUtil.randomString(4))// 表册编号
				.bookName(TestCaseUtil.name())// 表册名称
				.bookReaderEmployeeId(RandomUtil.randomString(4))// 抄表员
				.bookChargeEmployeeId(RandomUtil.randomString(4))// 收费员
				.bookMarketingAreaId(RandomUtil.randomString(4))// 营销区域
				.bookReadCycle(RandomUtil.randomInt(0,1000+1))// 抄表周期
				.bookLastMonth(RandomUtil.randomString(4))// 最后一次抄表月份
				.bookReadMonth(RandomUtil.randomString(4))// 下次抄表月份
				.bookSettleCycle(RandomUtil.randomInt(0,1000+1))// 结算周期
				.bookSettleLastMonth(RandomUtil.randomString(4))// 最后一次结算月份
				.bookSettleMonth(RandomUtil.randomString(4))// 下次结算月份
				.bookStatus(RandomUtil.randomInt(0,1+1))// 有效状态（1：可用；0：禁用）
				.bookReadStatus(RandomUtil.randomInt(0,1+1))// 表册状态（1：抄表进行中；2：抄表截止）
				.bookMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-books").content(JSON.toJSONString(tenantInfo)) //
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

		TenantBook tenantInfo = TenantBook.builder()//
				//.id(TestCaseUtil.id())// 表册ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.bookCode(RandomUtil.randomString(4))// 表册编号
				.bookName(TestCaseUtil.name())// 表册名称
				.bookReaderEmployeeId(RandomUtil.randomString(4))// 抄表员
				.bookChargeEmployeeId(RandomUtil.randomString(4))// 收费员
				.bookMarketingAreaId(RandomUtil.randomString(4))// 营销区域
				.bookReadCycle(RandomUtil.randomInt(0,1000+1))// 抄表周期
				.bookLastMonth(RandomUtil.randomString(4))// 最后一次抄表月份
				.bookReadMonth(RandomUtil.randomString(4))// 下次抄表月份
				.bookSettleCycle(RandomUtil.randomInt(0,1000+1))// 结算周期
				.bookSettleLastMonth(RandomUtil.randomString(4))// 最后一次结算月份
				.bookSettleMonth(RandomUtil.randomString(4))// 下次结算月份
				.bookStatus(RandomUtil.randomInt(0,1+1))// 有效状态（1：可用；0：禁用）
				.bookReadStatus(RandomUtil.randomInt(0,1+1))// 表册状态（1：抄表进行中；2：抄表截止）
				.bookMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-books" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantBook tenantInfo = TenantBook.builder()//
				//.tenantId(RandomUtil.randomString(4))// 租户ID
				//.bookCode(RandomUtil.randomString(4))// 表册编号
				//.bookName(TestCaseUtil.name())// 表册名称
				//.bookReaderEmployeeId(RandomUtil.randomString(4))// 抄表员
				//.bookChargeEmployeeId(RandomUtil.randomString(4))// 收费员
				//.bookMarketingAreaId(RandomUtil.randomString(4))// 营销区域
				//.bookReadCycle(RandomUtil.randomInt(0,1000+1))// 抄表周期
				//.bookLastMonth(RandomUtil.randomString(4))// 最后一次抄表月份
				//.bookReadMonth(RandomUtil.randomString(4))// 下次抄表月份
				//.bookSettleCycle(RandomUtil.randomInt(0,1000+1))// 结算周期
				//.bookSettleLastMonth(RandomUtil.randomString(4))// 最后一次结算月份
				//.bookSettleMonth(RandomUtil.randomString(4))// 下次结算月份
				//.bookStatus(RandomUtil.randomInt(0,1+1))// 有效状态（1：可用；0：禁用）
				//.bookReadStatus(RandomUtil.randomInt(0,1+1))// 表册状态（1：抄表进行中；2：抄表截止）
				//.priceClass(RandomUtil.randomInt(0,1000+1))// 级次
				//.priceMemo(RandomUtil.randomString(4))// 备注
				//.addTime(new Date())// 数据新增时间
				//.updateTime(new Date())// 数据修改时间
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-books" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-books" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
