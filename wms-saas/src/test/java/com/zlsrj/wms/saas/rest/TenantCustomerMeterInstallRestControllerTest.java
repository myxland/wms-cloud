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
import com.zlsrj.wms.api.entity.TenantCustomerMeterInstall;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerMeterInstallRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-meter-installs" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 水表立户ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("meterId",RandomUtil.randomLong());// 已经立户的水表ID
		// params.add("meterCode",RandomUtil.randomString(4));// 已经立户的水表代码
		// params.add("custName",TestCaseUtil.name());// 用户名称
		// params.add("meterAddress",TestCaseUtil.address());// 水表地址
		// params.add("meterMachineCode",RandomUtil.randomString(4));// 表身号码[钢印号等]
		// params.add("manufactorId",RandomUtil.randomLong());// 厂商ID
		// params.add("meterType",RandomUtil.randomInt(0,1+1));// 水表类型（1：机械表；2：远传表；3：IC卡表）
		// params.add("caliberId",RandomUtil.randomLong());// 水表口径ID
		// params.add("waterTypeId",RandomUtil.randomLong());// 用水分类ID
		// params.add("priceTypeId",RandomUtil.randomLong());// 价格分类ID
		// params.add("meterIotCode",RandomUtil.randomString(4));// 采集系统编号
		// params.add("meterInstallDate",new Date());// 水表安装日期
		// params.add("meterLastSettleTime",new Date());// 最后一次结算时间
		// params.add("meterLastSettlePointer",new BigDecimal(0));// 最后一次结算指针
		// params.add("linkmanMobile",TestCaseUtil.mobile());// 联系人手机号码
		// params.add("custmerIdNo",RandomUtil.randomString(4));// 用户身份证编号
		// params.add("oldCode",RandomUtil.randomString(4));// 老系统编号
		// params.add("inputType",RandomUtil.randomInt(0,1+1));// 录入方式（1：手工录入；2：文件导入）
		// params.add("inputTime",new Date());// 录入日期
		// params.add("createOn",RandomUtil.randomInt(0,1+1));// 是否已经立户（1：是；0：否）

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-meter-installs").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantCustomerMeterInstall tenantInfo = TenantCustomerMeterInstall.builder()//
				.id(TestCaseUtil.id())// 水表立户ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.meterId(RandomUtil.randomString(32))// 已经立户的水表ID
				.meterCode(RandomUtil.randomString(4))// 已经立户的水表代码
				.custName(TestCaseUtil.name())// 用户名称
				.meterAddress(TestCaseUtil.address())// 水表地址
				.meterMachineCode(RandomUtil.randomString(4))// 表身号码[钢印号等]
				.manufactorId(RandomUtil.randomString(32))// 厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomString(32))// 水表口径ID
				.waterTypeId(RandomUtil.randomString(32))// 用水分类ID
				.priceTypeId(RandomUtil.randomString(32))// 价格分类ID
				.meterIotCode(RandomUtil.randomString(4))// 采集系统编号
				.meterInstallDate(new Date())// 水表安装日期
				.meterLastSettleTime(new Date())// 最后一次结算时间
				.meterLastSettlePointer(new BigDecimal(0))// 最后一次结算指针
				.linkmanMobile(TestCaseUtil.mobile())// 联系人手机号码
				.custmerIdNo(RandomUtil.randomString(4))// 用户身份证编号
				.oldCode(RandomUtil.randomString(4))// 老系统编号
				.inputType(RandomUtil.randomInt(0,1+1))// 录入方式（1：手工录入；2：文件导入）
				.inputTime(new Date())// 录入日期
				.createOn(RandomUtil.randomInt(0,1+1))// 是否已经立户（1：是；0：否）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-customer-meter-installs").content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerMeterInstall tenantInfo = TenantCustomerMeterInstall.builder()//
				//.id(TestCaseUtil.id())// 水表立户ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.meterId(RandomUtil.randomString(32))// 已经立户的水表ID
				.meterCode(RandomUtil.randomString(4))// 已经立户的水表代码
				.custName(TestCaseUtil.name())// 用户名称
				.meterAddress(TestCaseUtil.address())// 水表地址
				.meterMachineCode(RandomUtil.randomString(4))// 表身号码[钢印号等]
				.manufactorId(RandomUtil.randomString(32))// 厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomString(32))// 水表口径ID
				.waterTypeId(RandomUtil.randomString(32))// 用水分类ID
				.priceTypeId(RandomUtil.randomString(32))// 价格分类ID
				.meterIotCode(RandomUtil.randomString(4))// 采集系统编号
				.meterInstallDate(new Date())// 水表安装日期
				.meterLastSettleTime(new Date())// 最后一次结算时间
				.meterLastSettlePointer(new BigDecimal(0))// 最后一次结算指针
				.linkmanMobile(TestCaseUtil.mobile())// 联系人手机号码
				.custmerIdNo(RandomUtil.randomString(4))// 用户身份证编号
				.oldCode(RandomUtil.randomString(4))// 老系统编号
				.inputType(RandomUtil.randomInt(0,1+1))// 录入方式（1：手工录入；2：文件导入）
				.inputTime(new Date())// 录入日期
				.createOn(RandomUtil.randomInt(0,1+1))// 是否已经立户（1：是；0：否）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-customer-meter-installs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerMeterInstall tenantInfo = TenantCustomerMeterInstall.builder()//
				//.tenantId(RandomUtil.randomString(32))// 租户ID
				//.meterId(RandomUtil.randomString(32))// 已经立户的水表ID
				//.meterCode(RandomUtil.randomString(4))// 已经立户的水表代码
				//.custName(TestCaseUtil.name())// 用户名称
				//.meterAddress(TestCaseUtil.address())// 水表地址
				//.meterMachineCode(RandomUtil.randomString(4))// 表身号码[钢印号等]
				//.manufactorId(RandomUtil.randomString(32))// 厂商ID
				//.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				//.caliberId(RandomUtil.randomString(32))// 水表口径ID
				//.waterTypeId(RandomUtil.randomString(32))// 用水分类ID
				//.priceTypeId(RandomUtil.randomString(32))// 价格分类ID
				//.meterIotCode(RandomUtil.randomString(4))// 采集系统编号
				//.meterInstallDate(new Date())// 水表安装日期
				//.meterLastSettleTime(new Date())// 最后一次结算时间
				//.meterLastSettlePointer(new BigDecimal(0))// 最后一次结算指针
				//.linkmanMobile(TestCaseUtil.mobile())// 联系人手机号码
				//.custmerIdNo(RandomUtil.randomString(4))// 用户身份证编号
				//.oldCode(RandomUtil.randomString(4))// 老系统编号
				//.inputType(RandomUtil.randomInt(0,1+1))// 录入方式（1：手工录入；2：文件导入）
				//.inputTime(new Date())// 录入日期
				//.createOn(RandomUtil.randomInt(0,1+1))// 是否已经立户（1：是；0：否）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-customer-meter-installs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-customer-meter-installs" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
