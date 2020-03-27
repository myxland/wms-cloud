package com.zlsrj.wms.admin.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.dto.ModuleInfoAddParam;
import com.zlsrj.wms.api.dto.ModuleInfoUpdateParam;
import com.zlsrj.wms.api.entity.ModulePrice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ModuleInfoControllerTest {
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
		String id = "a19ea998ecb4424c94d365039c2e85db";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/moduleInfo/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/moduleInfo/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "206d0c7c8ab34e3e8211ed4051b962c8";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/moduleInfo/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "a19ea998ecb4424c94d365039c2e85db";
		log.info("id={}",id);
		
		ModuleInfoUpdateParam moduleInfoUpdateParam = new ModuleInfoUpdateParam();
		moduleInfoUpdateParam.setModuleName("服务模块名称" +"-"+"更新用例"+"-"+RandomUtil.randomString(4));// 服务模块名称
		moduleInfoUpdateParam.setOpenTarget(RandomUtil.randomInt(1,5+1));// 开放对象（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
		moduleInfoUpdateParam.setRunEnv(RandomUtil.randomInt(1,6+1));// 运行环境（1：PC端；2：移动端；3：微信端；4：支付宝端；5：API接口；6：自助终端）
		moduleInfoUpdateParam.setRelyModuleId(null);// 所依赖的模块ID列表
		moduleInfoUpdateParam.setBillingMode(RandomUtil.randomInt(1,5+1));// 计费模式（1：默认开通；2：免费；3：按量付费；4：固定价格；5：阶梯价格）
		moduleInfoUpdateParam.setBillingCycle(RandomUtil.randomInt(0,4+1));// 计费周期（0：不计费；1：实时；2：按天；3：按月；4：按年）
		moduleInfoUpdateParam.setBasicEditionOn(RandomUtil.randomInt(0,1+1));// 开放基础版（1：开放；0：不开放）
		moduleInfoUpdateParam.setAdvanceEditionOn(RandomUtil.randomInt(0,1+1));// 开放高级版（1：开放；0：不开放）
		moduleInfoUpdateParam.setUltimateEditionOn(RandomUtil.randomInt(0,1+1));// 开放旗舰版（1：开放；0：不开放）
		moduleInfoUpdateParam.setModuleOn(RandomUtil.randomInt(0,1+1));// 服务发布状态（1：发布 ；0：未发布）
		
		if(moduleInfoUpdateParam.getBasicEditionOn() ==1) {
			List<ModulePrice> basicModulePriceList = new ArrayList<ModulePrice>();
			ModulePrice modulePrice = ModulePrice.builder()//
					.moduleEdition(1)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(1)// 起始量
					.endNum(100)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(0, 100)))// 价格
					.build();
			basicModulePriceList.add(modulePrice);
			
			modulePrice = ModulePrice.builder()//
					.moduleEdition(1)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(101)// 起始量
					.endNum(10000)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(101, 1000)))// 价格
					.build();
			basicModulePriceList.add(modulePrice);
			
			moduleInfoUpdateParam.setBasicModulePriceList(basicModulePriceList);
		}
		
		if(moduleInfoUpdateParam.getAdvanceEditionOn() ==1) {
			List<ModulePrice> advanceModulePriceList = new ArrayList<ModulePrice>();
			ModulePrice modulePrice = ModulePrice.builder()//
					.moduleEdition(2)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(1)// 起始量
					.endNum(100)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(0, 100)))// 价格
					.build();
			advanceModulePriceList.add(modulePrice);
			
			modulePrice = ModulePrice.builder()//
					.moduleEdition(2)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(101)// 起始量
					.endNum(10000)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(101, 1000)))// 价格
					.build();
			advanceModulePriceList.add(modulePrice);
			
			moduleInfoUpdateParam.setAdvanceModulePriceList(advanceModulePriceList);
		}
		
		if(moduleInfoUpdateParam.getUltimateEditionOn() ==1) {
			List<ModulePrice> ultimateModulePriceList = new ArrayList<ModulePrice>();
			ModulePrice modulePrice = ModulePrice.builder()//
					.moduleEdition(3)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(1)// 起始量
					.endNum(100)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(0, 100)))// 价格
					.build();
			ultimateModulePriceList.add(modulePrice);
			
			modulePrice = ModulePrice.builder()//
					.moduleEdition(3)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(101)// 起始量
					.endNum(10000)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(101, 1000)))// 价格
					.build();
			ultimateModulePriceList.add(modulePrice);
			
			moduleInfoUpdateParam.setUltimateModulePriceList(ultimateModulePriceList);
		}
		
		log.info(JSON.toJSONString(moduleInfoUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/moduleInfo/update/"+id)//
						.content(JSON.toJSONString(moduleInfoUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		ModuleInfoAddParam moduleInfoAddParam = new ModuleInfoAddParam();
		moduleInfoAddParam.setModuleName("服务模块名称" +"-"+"新增用例"+"-"+RandomUtil.randomString(4));// 服务模块名称
		moduleInfoAddParam.setOpenTarget(RandomUtil.randomInt(1,5+1));// 开放对象（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
		moduleInfoAddParam.setRunEnv(RandomUtil.randomInt(1,6+1));// 运行环境（1：PC端；2：移动端；3：微信端；4：支付宝端；5：API接口；6：自助终端）
		moduleInfoAddParam.setRelyModuleId(null);// 所依赖的模块ID列表
		moduleInfoAddParam.setBillingMode(RandomUtil.randomInt(1,5+1));// 计费模式（1：默认开通；2：免费；3：按量付费；4：固定价格；5：阶梯价格）
		moduleInfoAddParam.setBillingCycle(RandomUtil.randomInt(0,4+1));// 计费周期（0：不计费；1：实时；2：按天；3：按月；4：按年）
		moduleInfoAddParam.setBasicEditionOn(RandomUtil.randomInt(0,1+1));// 开放基础版（1：开放；0：不开放）
		moduleInfoAddParam.setAdvanceEditionOn(RandomUtil.randomInt(0,1+1));// 开放高级版（1：开放；0：不开放）
		moduleInfoAddParam.setUltimateEditionOn(RandomUtil.randomInt(0,1+1));// 开放旗舰版（1：开放；0：不开放）
		moduleInfoAddParam.setModuleOn(RandomUtil.randomInt(0,1+1));// 服务发布状态（1：发布 ；0：未发布）
		
		if(moduleInfoAddParam.getBasicEditionOn() ==1) {
			List<ModulePrice> basicModulePriceList = new ArrayList<ModulePrice>();
			ModulePrice modulePrice = ModulePrice.builder()//
					.moduleEdition(1)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(1)// 起始量
					.endNum(100)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(0, 100)))// 价格
					.build();
			basicModulePriceList.add(modulePrice);
			
			modulePrice = ModulePrice.builder()//
					.moduleEdition(1)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(101)// 起始量
					.endNum(10000)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(101, 1000)))// 价格
					.build();
			basicModulePriceList.add(modulePrice);
			
			moduleInfoAddParam.setBasicModulePriceList(basicModulePriceList);
		}
		
		if(moduleInfoAddParam.getAdvanceEditionOn() ==1) {
			List<ModulePrice> advanceModulePriceList = new ArrayList<ModulePrice>();
			ModulePrice modulePrice = ModulePrice.builder()//
					.moduleEdition(2)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(1)// 起始量
					.endNum(100)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(0, 100)))// 价格
					.build();
			advanceModulePriceList.add(modulePrice);
			
			modulePrice = ModulePrice.builder()//
					.moduleEdition(2)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(101)// 起始量
					.endNum(10000)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(101, 1000)))// 价格
					.build();
			advanceModulePriceList.add(modulePrice);
			
			moduleInfoAddParam.setAdvanceModulePriceList(advanceModulePriceList);
		}
		
		if(moduleInfoAddParam.getUltimateEditionOn() ==1) {
			List<ModulePrice> ultimateModulePriceList = new ArrayList<ModulePrice>();
			ModulePrice modulePrice = ModulePrice.builder()//
					.moduleEdition(3)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(1)// 起始量
					.endNum(100)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(0, 100)))// 价格
					.build();
			ultimateModulePriceList.add(modulePrice);
			
			modulePrice = ModulePrice.builder()//
					.moduleEdition(3)// 模块版本（1：基础版；2：高级版；3：旗舰版）
					.startNum(101)// 起始量
					.endNum(10000)// 终止量
					.priceMoney(new BigDecimal(RandomUtil.randomInt(101, 1000)))// 价格
					.build();
			ultimateModulePriceList.add(modulePrice);
			
			moduleInfoAddParam.setUltimateModulePriceList(ultimateModulePriceList);
		}
		
		log.info(JSON.toJSONString(moduleInfoAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/moduleInfo/create")//
						.content(JSON.toJSONString(moduleInfoAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
