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
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-meters" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 水表ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("customerId",RandomUtil.randomLong());// 用户ID
		// params.add("meterBookletId",RandomUtil.randomLong());// 水表表册ID
		// params.add("meterParentId",RandomUtil.randomLong());// 上级水表ID
		// params.add("meterCode",RandomUtil.randomString(4));// 水表编号
		// params.add("meterAddress",TestCaseUtil.address());// 水表地址
		// params.add("meterPeoples",RandomUtil.randomInt(0,1000+1));// 家庭人数
		// params.add("meterMachineCode",RandomUtil.randomString(4));// 表身号码[钢印号等]
		// params.add("meterUseType",RandomUtil.randomInt(0,1+1));// 用途（1：水费结算；2：水量考核）
		// params.add("meterManufactorId",RandomUtil.randomLong());// 厂商ID
		// params.add("meterType",RandomUtil.randomInt(0,1+1));// 水表类型（1：机械表；2：远传表；3：IC卡表）
		// params.add("caliberId",RandomUtil.randomLong());// 水表口径ID
		// params.add("meterWaterTypeId",RandomUtil.randomLong());// 用水分类ID
		// params.add("priceTypeId",RandomUtil.randomLong());// 价格分类ID
		// params.add("meterIotCode",RandomUtil.randomString(4));// 采集系统代码
		// params.add("meterInstallDate",new Date());// 水表安装日期
		// params.add("meterRegisterTime",new Date());// 水表建档日期
		// params.add("meterStatus",RandomUtil.randomInt(0,1+1));// 水表状态（1：正常；2：暂停；3：拆表）
		// params.add("meterYearTotalWaters",new BigDecimal(0));// 年累计用水量
		// params.add("meterLastSettleTime",new Date());// 最后一次结算日期
		// params.add("meterLastSettlePointer",new BigDecimal(0));// 最后一次结算指针
		// params.add("meterArrearsMoney",new BigDecimal(0));// 水表欠费总金额

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-meters").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantMeter tenantInfo = TenantMeter.builder()//
				.id(TestCaseUtil.id())// 水表ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.customerId(RandomUtil.randomLong())// 用户ID
				.meterBookletId(RandomUtil.randomLong())// 水表表册ID
				.meterParentId(RandomUtil.randomLong())// 上级水表ID
				.meterCode(RandomUtil.randomString(4))// 水表编号
				.meterAddress(TestCaseUtil.address())// 水表地址
				.meterPeoples(RandomUtil.randomInt(0,1000+1))// 家庭人数
				.meterMachineCode(RandomUtil.randomString(4))// 表身号码[钢印号等]
				.meterUseType(RandomUtil.randomInt(0,1+1))// 用途（1：水费结算；2：水量考核）
				.meterManufactorId(RandomUtil.randomLong())// 厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomLong())// 水表口径ID
				.meterWaterTypeId(RandomUtil.randomLong())// 用水分类ID
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.meterIotCode(RandomUtil.randomString(4))// 采集系统代码
				.meterInstallDate(new Date())// 水表安装日期
				.meterRegisterTime(new Date())// 水表建档日期
				.meterStatus(RandomUtil.randomInt(0,1+1))// 水表状态（1：正常；2：暂停；3：拆表）
				.meterYearTotalWaters(new BigDecimal(0))// 年累计用水量
				.meterLastSettleTime(new Date())// 最后一次结算日期
				.meterLastSettlePointer(new BigDecimal(0))// 最后一次结算指针
				.meterArrearsMoney(new BigDecimal(0))// 水表欠费总金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-meters").content(JSON.toJSONString(tenantInfo)) //
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

		TenantMeter tenantInfo = TenantMeter.builder()//
				//.id(TestCaseUtil.id())// 水表ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.customerId(RandomUtil.randomLong())// 用户ID
				.meterBookletId(RandomUtil.randomLong())// 水表表册ID
				.meterParentId(RandomUtil.randomLong())// 上级水表ID
				.meterCode(RandomUtil.randomString(4))// 水表编号
				.meterAddress(TestCaseUtil.address())// 水表地址
				.meterPeoples(RandomUtil.randomInt(0,1000+1))// 家庭人数
				.meterMachineCode(RandomUtil.randomString(4))// 表身号码[钢印号等]
				.meterUseType(RandomUtil.randomInt(0,1+1))// 用途（1：水费结算；2：水量考核）
				.meterManufactorId(RandomUtil.randomLong())// 厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomLong())// 水表口径ID
				.meterWaterTypeId(RandomUtil.randomLong())// 用水分类ID
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.meterIotCode(RandomUtil.randomString(4))// 采集系统代码
				.meterInstallDate(new Date())// 水表安装日期
				.meterRegisterTime(new Date())// 水表建档日期
				.meterStatus(RandomUtil.randomInt(0,1+1))// 水表状态（1：正常；2：暂停；3：拆表）
				.meterYearTotalWaters(new BigDecimal(0))// 年累计用水量
				.meterLastSettleTime(new Date())// 最后一次结算日期
				.meterLastSettlePointer(new BigDecimal(0))// 最后一次结算指针
				.meterArrearsMoney(new BigDecimal(0))// 水表欠费总金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-meters" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantMeter tenantInfo = TenantMeter.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户ID
				//.customerId(RandomUtil.randomLong())// 用户ID
				//.meterBookletId(RandomUtil.randomLong())// 水表表册ID
				//.meterParentId(RandomUtil.randomLong())// 上级水表ID
				//.meterCode(RandomUtil.randomString(4))// 水表编号
				//.meterAddress(TestCaseUtil.address())// 水表地址
				//.meterPeoples(RandomUtil.randomInt(0,1000+1))// 家庭人数
				//.meterMachineCode(RandomUtil.randomString(4))// 表身号码[钢印号等]
				//.meterUseType(RandomUtil.randomInt(0,1+1))// 用途（1：水费结算；2：水量考核）
				//.meterManufactorId(RandomUtil.randomLong())// 厂商ID
				//.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				//.caliberId(RandomUtil.randomLong())// 水表口径ID
				//.meterWaterTypeId(RandomUtil.randomLong())// 用水分类ID
				//.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				//.meterIotCode(RandomUtil.randomString(4))// 采集系统代码
				//.meterInstallDate(new Date())// 水表安装日期
				//.meterRegisterTime(new Date())// 水表建档日期
				//.meterStatus(RandomUtil.randomInt(0,1+1))// 水表状态（1：正常；2：暂停；3：拆表）
				//.meterYearTotalWaters(new BigDecimal(0))// 年累计用水量
				//.meterLastSettleTime(new Date())// 最后一次结算日期
				//.meterLastSettlePointer(new BigDecimal(0))// 最后一次结算指针
				//.meterArrearsMoney(new BigDecimal(0))// 水表欠费总金额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-meters" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-meters" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
