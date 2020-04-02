package com.zlsrj.wms.admin.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
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
import com.zlsrj.wms.api.dto.TenantMeterAddParam;
import com.zlsrj.wms.api.dto.TenantMeterBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantMeterUpdateParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterControllerTest {
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
	public void createTest() throws Exception {
		TenantMeterAddParam tenantMeterAddParam = new TenantMeterAddParam();
		tenantMeterAddParam.setTenantId(RandomUtil.randomString(4));// 租户ID
		tenantMeterAddParam.setCustomerId(RandomUtil.randomString(4));// 用户IＤ
		tenantMeterAddParam.setCustomerCode(RandomUtil.randomString(4));// 用户编号
		tenantMeterAddParam.setMeterCode(RandomUtil.randomString(4));// 水表编号
		tenantMeterAddParam.setMeterAddress(TestCaseUtil.address());// 水表地址
		tenantMeterAddParam.setMeterBookId(RandomUtil.randomString(4));// 表册ID
		tenantMeterAddParam.setMeterPeoples(RandomUtil.randomInt(0,1000+1));// 用水人数
		tenantMeterAddParam.setMeterStatus(RandomUtil.randomInt(0,1+1));// 水表状态（1：正常；2：暂停；3：拆表）
		tenantMeterAddParam.setMeterBrandId(RandomUtil.randomString(4));// 水表厂家
		tenantMeterAddParam.setMeterCaliberId(RandomUtil.randomString(4));// 水表口径
		tenantMeterAddParam.setMeterTypeId(RandomUtil.randomString(4));// 水表类型
		tenantMeterAddParam.setMeterModelId(RandomUtil.randomString(4));// 水表型号
		tenantMeterAddParam.setMeterMarketingAreaId(RandomUtil.randomString(4));// 营销区域
		tenantMeterAddParam.setMeterSupplyAreaId(RandomUtil.randomString(4));// 供水区域
		tenantMeterAddParam.setMeterIndustryId(RandomUtil.randomString(4));// 行业分类
		tenantMeterAddParam.setMeterUseType(RandomUtil.randomInt(0,1+1));// 水表用途（1：计量计费；2：计量不计费；3：考核表计量）
		tenantMeterAddParam.setMeterSaveWater(RandomUtil.randomInt(0,1000+1));// 节水标志（1：节水；0：无）
		tenantMeterAddParam.setMeterNewFlag(RandomUtil.randomInt(0,1000+1));// 新表标志（1：新表；0：旧表）
		tenantMeterAddParam.setMeterGpsX(RandomUtil.randomString(4));// gps x坐标
		tenantMeterAddParam.setMeterGpsY(RandomUtil.randomString(4));// gps y坐标
		tenantMeterAddParam.setMeterMachineCode(RandomUtil.randomString(4));// 表身码
		tenantMeterAddParam.setMeterRemoteCode(RandomUtil.randomString(4));// 远传表号
		tenantMeterAddParam.setMeterInstallDate(new Date());// 水表安装日期
		tenantMeterAddParam.setMeterRegisterTime(new Date());// 建档时间
		tenantMeterAddParam.setMeterInstallPer(RandomUtil.randomString(4));// 装表人员
		tenantMeterAddParam.setMeterReadOrder(RandomUtil.randomInt(0,1000+1));// 抄表顺序
		tenantMeterAddParam.setMeterReadCode(RandomUtil.randomInt(0,1000+1));// 最近抄码
		tenantMeterAddParam.setMeterReadDate(new Date());// 最近抄表日期
		tenantMeterAddParam.setMeterSettleCode(RandomUtil.randomInt(0,1000+1));// 最近计费抄码
		tenantMeterAddParam.setMeterSettleDate(new Date());// 最近计费日期
		tenantMeterAddParam.setMeterOweAmt(new BigDecimal(0));// 欠费金额
		tenantMeterAddParam.setMeterPenaltyAmt(new BigDecimal(0));// 违约金
		tenantMeterAddParam.setMeterYearTotalWaters(new BigDecimal(0));// 年用水总量
		tenantMeterAddParam.setMeterTotalWaters(new BigDecimal(0));// 历史用水总量
		tenantMeterAddParam.setMeterPriceStepDate(new Date());// 阶梯起算日
		tenantMeterAddParam.setMeterPriceStepWaters(new BigDecimal(0));// 阶梯使用量
		tenantMeterAddParam.setMeterMemo(RandomUtil.randomString(4));// 备注
		
		log.info(JSON.toJSONString(tenantMeterAddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeter/create")//
						.content(JSON.toJSONString(tenantMeterAddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeter/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		String id = "";
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeter/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
		String tenantId = "";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeter/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void pageTest() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", "e1ddb601b6cc48b79f989d710712f6d0");

		params.add("page", "1");
		params.add("rows", "10");
		// params.add("sort", "id");
		// params.add("order", "desc");

		params.add("queryCol", "id");
		params.add("queryType", "=");
		params.add("queryValue", "e7e469182a4a4f6abb551b23ccf038e8");

		params.add("queryCol", "meter_address");
		params.add("queryType", "like");
		params.add("queryValue", "号");

		log.info(JSON.toJSONString(params));

		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeter/page")//
						.params(params).accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void countTest() throws Exception {
		String tenantId = "";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("tenantId", tenantId);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantMeter/count")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		TenantMeterUpdateParam tenantMeterUpdateParam = new TenantMeterUpdateParam();
		tenantMeterUpdateParam.setCustomerId(RandomUtil.randomString(4));// 用户IＤ
		tenantMeterUpdateParam.setCustomerCode(RandomUtil.randomString(4));// 用户编号
		tenantMeterUpdateParam.setMeterCode(RandomUtil.randomString(4));// 水表编号
		tenantMeterUpdateParam.setMeterAddress(TestCaseUtil.address());// 水表地址
		tenantMeterUpdateParam.setMeterBookId(RandomUtil.randomString(4));// 表册ID
		tenantMeterUpdateParam.setMeterPeoples(RandomUtil.randomInt(0,1000+1));// 用水人数
		tenantMeterUpdateParam.setMeterStatus(RandomUtil.randomInt(0,1+1));// 水表状态（1：正常；2：暂停；3：拆表）
		tenantMeterUpdateParam.setMeterBrandId(RandomUtil.randomString(4));// 水表厂家
		tenantMeterUpdateParam.setMeterCaliberId(RandomUtil.randomString(4));// 水表口径
		tenantMeterUpdateParam.setMeterTypeId(RandomUtil.randomString(4));// 水表类型
		tenantMeterUpdateParam.setMeterModelId(RandomUtil.randomString(4));// 水表型号
		tenantMeterUpdateParam.setMeterMarketingAreaId(RandomUtil.randomString(4));// 营销区域
		tenantMeterUpdateParam.setMeterSupplyAreaId(RandomUtil.randomString(4));// 供水区域
		tenantMeterUpdateParam.setMeterIndustryId(RandomUtil.randomString(4));// 行业分类
		tenantMeterUpdateParam.setMeterUseType(RandomUtil.randomInt(0,1+1));// 水表用途（1：计量计费；2：计量不计费；3：考核表计量）
		tenantMeterUpdateParam.setMeterSaveWater(RandomUtil.randomInt(0,1000+1));// 节水标志（1：节水；0：无）
		tenantMeterUpdateParam.setMeterNewFlag(RandomUtil.randomInt(0,1000+1));// 新表标志（1：新表；0：旧表）
		tenantMeterUpdateParam.setMeterGpsX(RandomUtil.randomString(4));// gps x坐标
		tenantMeterUpdateParam.setMeterGpsY(RandomUtil.randomString(4));// gps y坐标
		tenantMeterUpdateParam.setMeterMachineCode(RandomUtil.randomString(4));// 表身码
		tenantMeterUpdateParam.setMeterRemoteCode(RandomUtil.randomString(4));// 远传表号
		tenantMeterUpdateParam.setMeterInstallDate(new Date());// 水表安装日期
		tenantMeterUpdateParam.setMeterRegisterTime(new Date());// 建档时间
		tenantMeterUpdateParam.setMeterInstallPer(RandomUtil.randomString(4));// 装表人员
		tenantMeterUpdateParam.setMeterReadOrder(RandomUtil.randomInt(0,1000+1));// 抄表顺序
		tenantMeterUpdateParam.setMeterReadCode(RandomUtil.randomInt(0,1000+1));// 最近抄码
		tenantMeterUpdateParam.setMeterReadDate(new Date());// 最近抄表日期
		tenantMeterUpdateParam.setMeterSettleCode(RandomUtil.randomInt(0,1000+1));// 最近计费抄码
		tenantMeterUpdateParam.setMeterSettleDate(new Date());// 最近计费日期
		tenantMeterUpdateParam.setMeterOweAmt(new BigDecimal(0));// 欠费金额
		tenantMeterUpdateParam.setMeterPenaltyAmt(new BigDecimal(0));// 违约金
		tenantMeterUpdateParam.setMeterYearTotalWaters(new BigDecimal(0));// 年用水总量
		tenantMeterUpdateParam.setMeterTotalWaters(new BigDecimal(0));// 历史用水总量
		tenantMeterUpdateParam.setMeterPriceStepDate(new Date());// 阶梯起算日
		tenantMeterUpdateParam.setMeterPriceStepWaters(new BigDecimal(0));// 阶梯使用量
		tenantMeterUpdateParam.setMeterMemo(RandomUtil.randomString(4));// 备注
		
		log.info(JSON.toJSONString(tenantMeterUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeter/update/"+id)//
						.content(JSON.toJSONString(tenantMeterUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void batchUpdateTest() throws Exception {
		String[] ids = new String[] {"109d82f66604463faf3a09d249ec6b7f","10f86976533e46378d746a05b97817c3"};
		log.info("ids={}",StringUtils.join(ids, ","));
		
		TenantMeterBatchUpdateParam tenantMeterBatchUpdateParam = new TenantMeterBatchUpdateParam();
		tenantMeterBatchUpdateParam.setMeterBookId(new String[] {"61928b54f5f0475c9fb5bfef28e5131b","61928b54f5f0475c9fb5bfef28e5131b"});// 表册ID
		tenantMeterBatchUpdateParam.setMeterReadOrder(new Integer[] {0,1});// 抄表顺序
		
		log.info(JSON.toJSONString(tenantMeterBatchUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantMeter/update/batch/"+StringUtils.join(ids, ","))//
						.content(JSON.toJSONString(tenantMeterBatchUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
}
