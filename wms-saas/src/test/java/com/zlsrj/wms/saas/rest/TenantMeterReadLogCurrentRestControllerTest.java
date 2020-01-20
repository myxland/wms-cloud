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
import com.zlsrj.wms.api.entity.TenantMeterReadLogCurrent;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterReadLogCurrentRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-meter-read-log-currents" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 抄表计划
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("readMonth",new Date());// 结算月份
		// params.add("customerId",RandomUtil.randomLong());// 用户ID
		// params.add("meterId",RandomUtil.randomLong());// 水表ID
		// params.add("meterYearTotalWatersBefore",new BigDecimal(0));// 结算前水表当年累计水量
		// params.add("settleStartTime",new Date());// 结算开始时间
		// params.add("settleStartPointer",new BigDecimal(0));// 结算开始指针
		// params.add("currentReadTime",new Date());// 本次抄表时间
		// params.add("currentReadPointer",new BigDecimal(0));// 本次抄表指针
		// params.add("readEmployeeId",RandomUtil.randomLong());// 抄表员ID
		// params.add("meterStatusId",RandomUtil.randomLong());// 表次抄表状况
		// params.add("settleWaters",new BigDecimal(0));// 应结算水量
		// params.add("receivableWaters",new BigDecimal(0));// 应收水量
		// params.add("readSource",RandomUtil.randomInt(0,1000+1));// 抄表来源（1：移动抄表；2：人工入账；3：远传表导入；4：远传表接口）
		// params.add("readStatus",RandomUtil.randomInt(0,1+1));// 抄表状态（0：未抄；1：已抄）
		// params.add("checkResult",RandomUtil.randomInt(0,1000+1));// 检查结果（0：正常；1：异常）
		// params.add("processReault",RandomUtil.randomInt(0,1000+1));// 处理结果（1：已处理；2：未处理）
		// params.add("processEmployeeId",RandomUtil.randomLong());// 处理人
		// params.add("processTime",new Date());// 处理时间
		// params.add("processType",RandomUtil.randomInt(0,1+1));// 处理方式（1：重新抄表；2：通过）

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-meter-read-log-currents").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantMeterReadLogCurrent tenantInfo = TenantMeterReadLogCurrent.builder()//
				.id(TestCaseUtil.id())// 抄表计划
				.tenantId(RandomUtil.randomLong())// 租户ID
				.readMonth(new Date())// 结算月份
				.customerId(RandomUtil.randomLong())// 用户ID
				.meterId(RandomUtil.randomLong())// 水表ID
				.meterYearTotalWatersBefore(new BigDecimal(0))// 结算前水表当年累计水量
				.settleStartTime(new Date())// 结算开始时间
				.settleStartPointer(new BigDecimal(0))// 结算开始指针
				.currentReadTime(new Date())// 本次抄表时间
				.currentReadPointer(new BigDecimal(0))// 本次抄表指针
				.readEmployeeId(RandomUtil.randomLong())// 抄表员ID
				.meterStatusId(RandomUtil.randomLong())// 表次抄表状况
				.settleWaters(new BigDecimal(0))// 应结算水量
				.receivableWaters(new BigDecimal(0))// 应收水量
				.readSource(RandomUtil.randomInt(0,1000+1))// 抄表来源（1：移动抄表；2：人工入账；3：远传表导入；4：远传表接口）
				.readStatus(RandomUtil.randomInt(0,1+1))// 抄表状态（0：未抄；1：已抄）
				.checkResult(RandomUtil.randomInt(0,1000+1))// 检查结果（0：正常；1：异常）
				.processReault(RandomUtil.randomInt(0,1000+1))// 处理结果（1：已处理；2：未处理）
				.processEmployeeId(RandomUtil.randomLong())// 处理人
				.processTime(new Date())// 处理时间
				.processType(RandomUtil.randomInt(0,1+1))// 处理方式（1：重新抄表；2：通过）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-meter-read-log-currents").content(JSON.toJSONString(tenantInfo)) //
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

		TenantMeterReadLogCurrent tenantInfo = TenantMeterReadLogCurrent.builder()//
				//.id(TestCaseUtil.id())// 抄表计划
				.tenantId(RandomUtil.randomLong())// 租户ID
				.readMonth(new Date())// 结算月份
				.customerId(RandomUtil.randomLong())// 用户ID
				.meterId(RandomUtil.randomLong())// 水表ID
				.meterYearTotalWatersBefore(new BigDecimal(0))// 结算前水表当年累计水量
				.settleStartTime(new Date())// 结算开始时间
				.settleStartPointer(new BigDecimal(0))// 结算开始指针
				.currentReadTime(new Date())// 本次抄表时间
				.currentReadPointer(new BigDecimal(0))// 本次抄表指针
				.readEmployeeId(RandomUtil.randomLong())// 抄表员ID
				.meterStatusId(RandomUtil.randomLong())// 表次抄表状况
				.settleWaters(new BigDecimal(0))// 应结算水量
				.receivableWaters(new BigDecimal(0))// 应收水量
				.readSource(RandomUtil.randomInt(0,1000+1))// 抄表来源（1：移动抄表；2：人工入账；3：远传表导入；4：远传表接口）
				.readStatus(RandomUtil.randomInt(0,1+1))// 抄表状态（0：未抄；1：已抄）
				.checkResult(RandomUtil.randomInt(0,1000+1))// 检查结果（0：正常；1：异常）
				.processReault(RandomUtil.randomInt(0,1000+1))// 处理结果（1：已处理；2：未处理）
				.processEmployeeId(RandomUtil.randomLong())// 处理人
				.processTime(new Date())// 处理时间
				.processType(RandomUtil.randomInt(0,1+1))// 处理方式（1：重新抄表；2：通过）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-meter-read-log-currents" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantMeterReadLogCurrent tenantInfo = TenantMeterReadLogCurrent.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户ID
				//.readMonth(new Date())// 结算月份
				//.customerId(RandomUtil.randomLong())// 用户ID
				//.meterId(RandomUtil.randomLong())// 水表ID
				//.meterYearTotalWatersBefore(new BigDecimal(0))// 结算前水表当年累计水量
				//.settleStartTime(new Date())// 结算开始时间
				//.settleStartPointer(new BigDecimal(0))// 结算开始指针
				//.currentReadTime(new Date())// 本次抄表时间
				//.currentReadPointer(new BigDecimal(0))// 本次抄表指针
				//.readEmployeeId(RandomUtil.randomLong())// 抄表员ID
				//.meterStatusId(RandomUtil.randomLong())// 表次抄表状况
				//.settleWaters(new BigDecimal(0))// 应结算水量
				//.receivableWaters(new BigDecimal(0))// 应收水量
				//.readSource(RandomUtil.randomInt(0,1000+1))// 抄表来源（1：移动抄表；2：人工入账；3：远传表导入；4：远传表接口）
				//.readStatus(RandomUtil.randomInt(0,1+1))// 抄表状态（0：未抄；1：已抄）
				//.checkResult(RandomUtil.randomInt(0,1000+1))// 检查结果（0：正常；1：异常）
				//.processReault(RandomUtil.randomInt(0,1000+1))// 处理结果（1：已处理；2：未处理）
				//.processEmployeeId(RandomUtil.randomLong())// 处理人
				//.processTime(new Date())// 处理时间
				//.processType(RandomUtil.randomInt(0,1+1))// 处理方式（1：重新抄表；2：通过）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-meter-read-log-currents" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-meter-read-log-currents" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
