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
		String id = "";
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
		// params.add("tenantId",RandomUtil.randomString(4));// 租户ID
		// params.add("customerId",RandomUtil.randomString(4));// 用户IＤ
		// params.add("customerCode",RandomUtil.randomString(4));// 用户编号
		// params.add("meterCode",RandomUtil.randomString(4));// 水表编号
		// params.add("meterAddress",TestCaseUtil.address());// 水表地址
		// params.add("meterBookId",RandomUtil.randomString(4));// 表册ID
		// params.add("meterPeoples",RandomUtil.randomInt(0,1000+1));// 用水人数
		// params.add("meterStatus",RandomUtil.randomInt(0,1+1));// 水表状态（1：正常；2：暂停；3：拆表）
		// params.add("meterBrandId",RandomUtil.randomString(4));// 水表厂家
		// params.add("meterCaliberId",RandomUtil.randomString(4));// 水表口径
		// params.add("meterTypeId",RandomUtil.randomString(4));// 水表类型
		// params.add("meterModelId",RandomUtil.randomString(4));// 水表型号
		// params.add("meterMarketingAreaId",RandomUtil.randomString(4));// 营销区域
		// params.add("meterSupplyAreaId",RandomUtil.randomString(4));// 供水区域
		// params.add("meterIndustryId",RandomUtil.randomString(4));// 行业分类
		// params.add("meterUseType",RandomUtil.randomInt(0,1+1));// 水表用途（1：计量计费；2：计量不计费；3：考核表计量）
		// params.add("meterSaveWater",RandomUtil.randomInt(0,1000+1));// 节水标志（1：节水；0：无）
		// params.add("meterNewFlag",RandomUtil.randomInt(0,1000+1));// 新表标志（1：新表；0：旧表）
		// params.add("meterGpsX",RandomUtil.randomString(4));// gps x坐标
		// params.add("meterGpsY",RandomUtil.randomString(4));// gps y坐标
		// params.add("meterMachineCode",RandomUtil.randomString(4));// 表身码
		// params.add("meterRemoteCode",RandomUtil.randomString(4));// 远传表号
		// params.add("meterInstallDate",new Date());// 水表安装日期
		// params.add("meterRegisterTime",new Date());// 建档时间
		// params.add("meterInstallPer",RandomUtil.randomString(4));// 装表人员
		// params.add("meterReadOrder",RandomUtil.randomInt(0,1000+1));// 抄表顺序
		// params.add("meterReadCode",RandomUtil.randomInt(0,1000+1));// 最近抄码
		// params.add("meterReadDate",new Date());// 最近抄表日期
		// params.add("meterSettleCode",RandomUtil.randomInt(0,1000+1));// 最近计费抄码
		// params.add("meterSettleDate",new Date());// 最近计费日期
		// params.add("meterOweAmt",new BigDecimal(0));// 欠费金额
		// params.add("meterPenaltyAmt",new BigDecimal(0));// 违约金
		// params.add("meterYearTotalWaters",new BigDecimal(0));// 年用水总量
		// params.add("meterTotalWaters",new BigDecimal(0));// 历史用水总量
		// params.add("meterPriceStepDate",new Date());// 阶梯起算日
		// params.add("meterPriceStepWaters",new BigDecimal(0));// 阶梯使用量
		// params.add("meterMemo",RandomUtil.randomString(4));// 备注
		// params.add("addTime",new Date());// 数据新增时间
		// params.add("updateTime",new Date());// 数据修改时间

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
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户IＤ
				.customerCode(RandomUtil.randomString(4))// 用户编号
				.meterCode(RandomUtil.randomString(4))// 水表编号
				.meterAddress(TestCaseUtil.address())// 水表地址
				.meterBookId(RandomUtil.randomString(4))// 表册ID
				.meterPeoples(RandomUtil.randomInt(0,1000+1))// 用水人数
				.meterStatus(RandomUtil.randomInt(0,1+1))// 水表状态（1：正常；2：暂停；3：拆表）
				.meterBrandId(RandomUtil.randomString(4))// 水表厂家
				.meterCaliberId(RandomUtil.randomString(4))// 水表口径
				.meterTypeId(RandomUtil.randomString(4))// 水表类型
				.meterModelId(RandomUtil.randomString(4))// 水表型号
				.meterMarketingAreaId(RandomUtil.randomString(4))// 营销区域
				.meterSupplyAreaId(RandomUtil.randomString(4))// 供水区域
				.meterIndustryId(RandomUtil.randomString(4))// 行业分类
				.meterUseType(RandomUtil.randomInt(0,1+1))// 水表用途（1：计量计费；2：计量不计费；3：考核表计量）
				.meterSaveWater(RandomUtil.randomInt(0,1000+1))// 节水标志（1：节水；0：无）
				.meterNewFlag(RandomUtil.randomInt(0,1000+1))// 新表标志（1：新表；0：旧表）
				.meterGpsX(RandomUtil.randomString(4))// gps x坐标
				.meterGpsY(RandomUtil.randomString(4))// gps y坐标
				.meterMachineCode(RandomUtil.randomString(4))// 表身码
				.meterRemoteCode(RandomUtil.randomString(4))// 远传表号
				.meterInstallDate(new Date())// 水表安装日期
				.meterRegisterTime(new Date())// 建档时间
				.meterInstallPer(RandomUtil.randomString(4))// 装表人员
				.meterReadOrder(RandomUtil.randomInt(0,1000+1))// 抄表顺序
				.meterReadCode(RandomUtil.randomInt(0,1000+1))// 最近抄码
				.meterReadDate(new Date())// 最近抄表日期
				.meterSettleCode(RandomUtil.randomInt(0,1000+1))// 最近计费抄码
				.meterSettleDate(new Date())// 最近计费日期
				.meterOweAmt(new BigDecimal(0))// 欠费金额
				.meterPenaltyAmt(new BigDecimal(0))// 违约金
				.meterYearTotalWaters(new BigDecimal(0))// 年用水总量
				.meterTotalWaters(new BigDecimal(0))// 历史用水总量
				.meterPriceStepDate(new Date())// 阶梯起算日
				.meterPriceStepWaters(new BigDecimal(0))// 阶梯使用量
				.meterMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
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
		String id = "";

		TenantMeter tenantInfo = TenantMeter.builder()//
				//.id(TestCaseUtil.id())// 水表ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户IＤ
				.customerCode(RandomUtil.randomString(4))// 用户编号
				.meterCode(RandomUtil.randomString(4))// 水表编号
				.meterAddress(TestCaseUtil.address())// 水表地址
				.meterBookId(RandomUtil.randomString(4))// 表册ID
				.meterPeoples(RandomUtil.randomInt(0,1000+1))// 用水人数
				.meterStatus(RandomUtil.randomInt(0,1+1))// 水表状态（1：正常；2：暂停；3：拆表）
				.meterBrandId(RandomUtil.randomString(4))// 水表厂家
				.meterCaliberId(RandomUtil.randomString(4))// 水表口径
				.meterTypeId(RandomUtil.randomString(4))// 水表类型
				.meterModelId(RandomUtil.randomString(4))// 水表型号
				.meterMarketingAreaId(RandomUtil.randomString(4))// 营销区域
				.meterSupplyAreaId(RandomUtil.randomString(4))// 供水区域
				.meterIndustryId(RandomUtil.randomString(4))// 行业分类
				.meterUseType(RandomUtil.randomInt(0,1+1))// 水表用途（1：计量计费；2：计量不计费；3：考核表计量）
				.meterSaveWater(RandomUtil.randomInt(0,1000+1))// 节水标志（1：节水；0：无）
				.meterNewFlag(RandomUtil.randomInt(0,1000+1))// 新表标志（1：新表；0：旧表）
				.meterGpsX(RandomUtil.randomString(4))// gps x坐标
				.meterGpsY(RandomUtil.randomString(4))// gps y坐标
				.meterMachineCode(RandomUtil.randomString(4))// 表身码
				.meterRemoteCode(RandomUtil.randomString(4))// 远传表号
				.meterInstallDate(new Date())// 水表安装日期
				.meterRegisterTime(new Date())// 建档时间
				.meterInstallPer(RandomUtil.randomString(4))// 装表人员
				.meterReadOrder(RandomUtil.randomInt(0,1000+1))// 抄表顺序
				.meterReadCode(RandomUtil.randomInt(0,1000+1))// 最近抄码
				.meterReadDate(new Date())// 最近抄表日期
				.meterSettleCode(RandomUtil.randomInt(0,1000+1))// 最近计费抄码
				.meterSettleDate(new Date())// 最近计费日期
				.meterOweAmt(new BigDecimal(0))// 欠费金额
				.meterPenaltyAmt(new BigDecimal(0))// 违约金
				.meterYearTotalWaters(new BigDecimal(0))// 年用水总量
				.meterTotalWaters(new BigDecimal(0))// 历史用水总量
				.meterPriceStepDate(new Date())// 阶梯起算日
				.meterPriceStepWaters(new BigDecimal(0))// 阶梯使用量
				.meterMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
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
		String id = "";

		TenantMeter tenantInfo = TenantMeter.builder()//
				//.tenantId(RandomUtil.randomString(4))// 租户ID
				//.customerId(RandomUtil.randomString(4))// 用户IＤ
				//.customerCode(RandomUtil.randomString(4))// 用户编号
				//.meterCode(RandomUtil.randomString(4))// 水表编号
				//.meterAddress(TestCaseUtil.address())// 水表地址
				//.meterBookId(RandomUtil.randomString(4))// 表册ID
				//.meterPeoples(RandomUtil.randomInt(0,1000+1))// 用水人数
				//.meterStatus(RandomUtil.randomInt(0,1+1))// 水表状态（1：正常；2：暂停；3：拆表）
				//.meterBrandId(RandomUtil.randomString(4))// 水表厂家
				//.meterCaliberId(RandomUtil.randomString(4))// 水表口径
				//.meterTypeId(RandomUtil.randomString(4))// 水表类型
				//.meterModelId(RandomUtil.randomString(4))// 水表型号
				//.meterMarketingAreaId(RandomUtil.randomString(4))// 营销区域
				//.meterSupplyAreaId(RandomUtil.randomString(4))// 供水区域
				//.meterIndustryId(RandomUtil.randomString(4))// 行业分类
				//.meterUseType(RandomUtil.randomInt(0,1+1))// 水表用途（1：计量计费；2：计量不计费；3：考核表计量）
				//.meterSaveWater(RandomUtil.randomInt(0,1000+1))// 节水标志（1：节水；0：无）
				//.meterNewFlag(RandomUtil.randomInt(0,1000+1))// 新表标志（1：新表；0：旧表）
				//.meterGpsX(RandomUtil.randomString(4))// gps x坐标
				//.meterGpsY(RandomUtil.randomString(4))// gps y坐标
				//.meterMachineCode(RandomUtil.randomString(4))// 表身码
				//.meterRemoteCode(RandomUtil.randomString(4))// 远传表号
				//.meterInstallDate(new Date())// 水表安装日期
				//.meterRegisterTime(new Date())// 建档时间
				//.meterInstallPer(RandomUtil.randomString(4))// 装表人员
				//.meterReadOrder(RandomUtil.randomInt(0,1000+1))// 抄表顺序
				//.meterReadCode(RandomUtil.randomInt(0,1000+1))// 最近抄码
				//.meterReadDate(new Date())// 最近抄表日期
				//.meterSettleCode(RandomUtil.randomInt(0,1000+1))// 最近计费抄码
				//.meterSettleDate(new Date())// 最近计费日期
				//.meterOweAmt(new BigDecimal(0))// 欠费金额
				//.meterPenaltyAmt(new BigDecimal(0))// 违约金
				//.meterYearTotalWaters(new BigDecimal(0))// 年用水总量
				//.meterTotalWaters(new BigDecimal(0))// 历史用水总量
				//.meterPriceStepDate(new Date())// 阶梯起算日
				//.meterPriceStepWaters(new BigDecimal(0))// 阶梯使用量
				//.meterMemo(RandomUtil.randomString(4))// 备注
				//.addTime(new Date())// 数据新增时间
				//.updateTime(new Date())// 数据修改时间
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
		String id = "";

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-meters" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
