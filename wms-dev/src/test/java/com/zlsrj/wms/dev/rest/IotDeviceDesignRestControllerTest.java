package com.zlsrj.wms.dev.rest;

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
import com.zlsrj.wms.api.entity.IotDeviceDesign;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IotDeviceDesignRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/iot-device-designs" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 系统编号
		// params.add("parentId",RandomUtil.randomLong());// 父级系统编号
		// params.add("devType",RandomUtil.randomInt(0,1+1));// 表具用途（1：贸易结算；2：数据监测）
		// params.add("devVerId",RandomUtil.randomInt(0,1000+1));// 产品型号编号
		// params.add("devMacId",RandomUtil.randomString(4));// 设备出厂编号
		// params.add("imei",RandomUtil.randomString(4));// 模组编号
		// params.add("imsi",RandomUtil.randomString(4));// 通讯卡号
		// params.add("areaId",RandomUtil.randomInt(0,1000+1));// 设备所属区域编号
		// params.add("custId",RandomUtil.randomLong());// 用户编号
		// params.add("instAddress",TestCaseUtil.address());// 安装地址
		// params.add("mapX",RandomUtil.randomString(4));// 地图X坐标
		// params.add("mapY",RandomUtil.randomString(4));// 地图Y坐标
		// params.add("mapIcon",RandomUtil.randomString(4));// 地图显示图标
		// params.add("factId",RandomUtil.randomLong());// 供应商编号（租户编号）
		// params.add("userId",RandomUtil.randomLong());// 使用方编号（租户编号）
		// params.add("devStatus",RandomUtil.randomInt(0,1+1));// 设备当前状态
		// params.add("devRegTime",new Date());// 注册时间
		// params.add("devRegister",RandomUtil.randomLong());// 注册人
		// params.add("devSegWay",RandomUtil.randomInt(0,1000+1));// 注册数据来源
		// params.add("devSendoutTime",new Date());// 发货时间
		// params.add("devSendouter",RandomUtil.randomLong());// 发货人
		// params.add("devSendoutBatch",RandomUtil.randomLong());// 发货批次
		// params.add("devSendoutWay",RandomUtil.randomInt(0,1000+1));// 发货数据来源
		// params.add("devPutinTime",new Date());// 入库时间
		// params.add("devPutiner",RandomUtil.randomLong());// 入库人
		// params.add("devPutinWay",RandomUtil.randomInt(0,1000+1));// 入库数据来源
		// params.add("devInstTime",new Date());// 安装时间
		// params.add("devInster",RandomUtil.randomLong());// 安装人
		// params.add("devInstWay",RandomUtil.randomInt(0,1000+1));// 安装数据来源
		// params.add("devLastUpTime",new Date());// 最后一次上传时间
		// params.add("devLastUpId",RandomUtil.randomLong());// 最后一次上传ID
		// params.add("bookletId",RandomUtil.randomLong());// 表册编号
		// params.add("lastCalcDate",new Date());// 最后一次计费日期
		// params.add("yearusenum",new BigDecimal(0));// 年累计用水量
		// params.add("lastCalcCode",new BigDecimal(0));// 最后一次计费止码
		// params.add("devNo",RandomUtil.randomString(4));// 设备编号
		// params.add("channelType",RandomUtil.randomInt(0,1+1));// 上传渠道类型（1：移动物联平台）

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/iot-device-designs").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		IotDeviceDesign tenantInfo = IotDeviceDesign.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.parentId(RandomUtil.randomLong())// 父级系统编号
				.devType(RandomUtil.randomInt(0,1+1))// 表具用途（1：贸易结算；2：数据监测）
				.devVerId(RandomUtil.randomInt(0,1000+1))// 产品型号编号
				.devMacId(RandomUtil.randomString(4))// 设备出厂编号
				.imei(RandomUtil.randomString(4))// 模组编号
				.imsi(RandomUtil.randomString(4))// 通讯卡号
				.areaId(RandomUtil.randomInt(0,1000+1))// 设备所属区域编号
				.custId(RandomUtil.randomLong())// 用户编号
				.instAddress(TestCaseUtil.address())// 安装地址
				.mapX(null)// 地图X坐标
				.mapY(null)// 地图Y坐标
				.mapIcon(RandomUtil.randomString(4))// 地图显示图标
				.factId(RandomUtil.randomLong())// 供应商编号（租户编号）
				.userId(RandomUtil.randomLong())// 使用方编号（租户编号）
				.devStatus(RandomUtil.randomInt(0,1+1))// 设备当前状态
				.devRegTime(new Date())// 注册时间
				.devRegister(RandomUtil.randomLong())// 注册人
				.devSegWay(RandomUtil.randomInt(0,1000+1))// 注册数据来源
				.devSendoutTime(new Date())// 发货时间
				.devSendouter(RandomUtil.randomLong())// 发货人
				.devSendoutBatch(RandomUtil.randomLong())// 发货批次
				.devSendoutWay(RandomUtil.randomInt(0,1000+1))// 发货数据来源
				.devPutinTime(new Date())// 入库时间
				.devPutiner(RandomUtil.randomLong())// 入库人
				.devPutinWay(RandomUtil.randomInt(0,1000+1))// 入库数据来源
				.devInstTime(new Date())// 安装时间
				.devInster(RandomUtil.randomLong())// 安装人
				.devInstWay(RandomUtil.randomInt(0,1000+1))// 安装数据来源
				.devLastUpTime(new Date())// 最后一次上传时间
				.devLastUpId(RandomUtil.randomLong())// 最后一次上传ID
				.bookletId(RandomUtil.randomLong())// 表册编号
				.lastCalcDate(new Date())// 最后一次计费日期
				.yearusenum(new BigDecimal(0))// 年累计用水量
				.lastCalcCode(new BigDecimal(0))// 最后一次计费止码
				.devNo(RandomUtil.randomString(4))// 设备编号
				.channelType(RandomUtil.randomInt(0,1+1))// 上传渠道类型（1：移动物联平台）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/iot-device-designs").content(JSON.toJSONString(tenantInfo)) //
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

		IotDeviceDesign tenantInfo = IotDeviceDesign.builder()//
				//.id(TestCaseUtil.id())// 系统编号
				.parentId(RandomUtil.randomLong())// 父级系统编号
				.devType(RandomUtil.randomInt(0,1+1))// 表具用途（1：贸易结算；2：数据监测）
				.devVerId(RandomUtil.randomInt(0,1000+1))// 产品型号编号
				.devMacId(RandomUtil.randomString(4))// 设备出厂编号
				.imei(RandomUtil.randomString(4))// 模组编号
				.imsi(RandomUtil.randomString(4))// 通讯卡号
				.areaId(RandomUtil.randomInt(0,1000+1))// 设备所属区域编号
				.custId(RandomUtil.randomLong())// 用户编号
				.instAddress(TestCaseUtil.address())// 安装地址
				.mapX(null)// 地图X坐标
				.mapY(null)// 地图Y坐标
				.mapIcon(RandomUtil.randomString(4))// 地图显示图标
				.factId(RandomUtil.randomLong())// 供应商编号（租户编号）
				.userId(RandomUtil.randomLong())// 使用方编号（租户编号）
				.devStatus(RandomUtil.randomInt(0,1+1))// 设备当前状态
				.devRegTime(new Date())// 注册时间
				.devRegister(RandomUtil.randomLong())// 注册人
				.devSegWay(RandomUtil.randomInt(0,1000+1))// 注册数据来源
				.devSendoutTime(new Date())// 发货时间
				.devSendouter(RandomUtil.randomLong())// 发货人
				.devSendoutBatch(RandomUtil.randomLong())// 发货批次
				.devSendoutWay(RandomUtil.randomInt(0,1000+1))// 发货数据来源
				.devPutinTime(new Date())// 入库时间
				.devPutiner(RandomUtil.randomLong())// 入库人
				.devPutinWay(RandomUtil.randomInt(0,1000+1))// 入库数据来源
				.devInstTime(new Date())// 安装时间
				.devInster(RandomUtil.randomLong())// 安装人
				.devInstWay(RandomUtil.randomInt(0,1000+1))// 安装数据来源
				.devLastUpTime(new Date())// 最后一次上传时间
				.devLastUpId(RandomUtil.randomLong())// 最后一次上传ID
				.bookletId(RandomUtil.randomLong())// 表册编号
				.lastCalcDate(new Date())// 最后一次计费日期
				.yearusenum(new BigDecimal(0))// 年累计用水量
				.lastCalcCode(new BigDecimal(0))// 最后一次计费止码
				.devNo(RandomUtil.randomString(4))// 设备编号
				.channelType(RandomUtil.randomInt(0,1+1))// 上传渠道类型（1：移动物联平台）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/iot-device-designs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		IotDeviceDesign tenantInfo = IotDeviceDesign.builder()//
				//.parentId(RandomUtil.randomLong())// 父级系统编号
				//.devType(RandomUtil.randomInt(0,1+1))// 表具用途（1：贸易结算；2：数据监测）
				//.devVerId(RandomUtil.randomInt(0,1000+1))// 产品型号编号
				//.devMacId(RandomUtil.randomString(4))// 设备出厂编号
				//.imei(RandomUtil.randomString(4))// 模组编号
				//.imsi(RandomUtil.randomString(4))// 通讯卡号
				//.areaId(RandomUtil.randomInt(0,1000+1))// 设备所属区域编号
				//.custId(RandomUtil.randomLong())// 用户编号
				//.instAddress(TestCaseUtil.address())// 安装地址
				//.mapX(RandomUtil.randomString(4))// 地图X坐标
				//.mapY(RandomUtil.randomString(4))// 地图Y坐标
				//.mapIcon(RandomUtil.randomString(4))// 地图显示图标
				//.factId(RandomUtil.randomLong())// 供应商编号（租户编号）
				//.userId(RandomUtil.randomLong())// 使用方编号（租户编号）
				//.devStatus(RandomUtil.randomInt(0,1+1))// 设备当前状态
				//.devRegTime(new Date())// 注册时间
				//.devRegister(RandomUtil.randomLong())// 注册人
				//.devSegWay(RandomUtil.randomInt(0,1000+1))// 注册数据来源
				//.devSendoutTime(new Date())// 发货时间
				//.devSendouter(RandomUtil.randomLong())// 发货人
				//.devSendoutBatch(RandomUtil.randomLong())// 发货批次
				//.devSendoutWay(RandomUtil.randomInt(0,1000+1))// 发货数据来源
				//.devPutinTime(new Date())// 入库时间
				//.devPutiner(RandomUtil.randomLong())// 入库人
				//.devPutinWay(RandomUtil.randomInt(0,1000+1))// 入库数据来源
				//.devInstTime(new Date())// 安装时间
				//.devInster(RandomUtil.randomLong())// 安装人
				//.devInstWay(RandomUtil.randomInt(0,1000+1))// 安装数据来源
				//.devLastUpTime(new Date())// 最后一次上传时间
				//.devLastUpId(RandomUtil.randomLong())// 最后一次上传ID
				//.bookletId(RandomUtil.randomLong())// 表册编号
				//.lastCalcDate(new Date())// 最后一次计费日期
				//.yearusenum(new BigDecimal(0))// 年累计用水量
				//.lastCalcCode(new BigDecimal(0))// 最后一次计费止码
				//.devNo(RandomUtil.randomString(4))// 设备编号
				//.channelType(RandomUtil.randomInt(0,1+1))// 上传渠道类型（1：移动物联平台）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/iot-device-designs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/iot-device-designs" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
