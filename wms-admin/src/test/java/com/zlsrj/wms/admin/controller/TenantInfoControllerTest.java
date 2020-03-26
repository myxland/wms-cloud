package com.zlsrj.wms.admin.controller;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.dto.TenantInfoAddParam;
import com.zlsrj.wms.api.dto.TenantInfoModuleInfoUpdateParam;
import com.zlsrj.wms.api.dto.TenantInfoRechargeParam;
import com.zlsrj.wms.api.dto.TenantInfoUpdateParam;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantInfoControllerTest {
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
		for(int i=0;i<4;i++) {
			TenantInfoAddParam tenantInfoAddParam = new TenantInfoAddParam();
			
			String companyShortName = TestCaseUtil.companyShortName();
			
			tenantInfoAddParam.setId(TestCaseUtil.id());// 租户ID
			tenantInfoAddParam.setTenantName("租户名称"+"-"+"新增用例"+"-"+i+"-"+RandomUtil.randomNumbers(4));// 租户名称
			tenantInfoAddParam.setTenantBalance(new BigDecimal(0));// 账户余额
			tenantInfoAddParam.setTenantOverdraw(new BigDecimal(0));// 透支额度
			tenantInfoAddParam.setTenantDisplayName(companyShortName);// 租户显示名称
			tenantInfoAddParam.setTenantProvince(TestCaseUtil.province());// 省
			tenantInfoAddParam.setTenantCity(TestCaseUtil.city());// 市
			tenantInfoAddParam.setTenantCountyTown(RandomUtil.randomString(4));// 区/县
			tenantInfoAddParam.setTenantLinkAddress(TestCaseUtil.address());// 联系地址
			tenantInfoAddParam.setTenantLinkman(TestCaseUtil.name());// 联系人
			tenantInfoAddParam.setTenantLinkmanMobile(TestCaseUtil.mobile());// 手机号码
			tenantInfoAddParam.setTenantLinkmanTel(TestCaseUtil.tel());// 单位电话
			tenantInfoAddParam.setTenantLinkmanEmail(TestCaseUtil.email(null));// 邮箱
			tenantInfoAddParam.setTenantLinkmanQq(TestCaseUtil.qq());// QQ号码
			tenantInfoAddParam.setTenantType(RandomUtil.randomInt(0,1+1));// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
			tenantInfoAddParam.setTenantRegisterTime(new Date());// 注册时间
			tenantInfoAddParam.setInvoiceType(RandomUtil.randomInt(0,1+1));// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
			tenantInfoAddParam.setInvoiceName(TestCaseUtil.name());// 开票名称
			tenantInfoAddParam.setInvoiceTaxNo(RandomUtil.randomString(4));// 税号
			tenantInfoAddParam.setInvoiceAddress(TestCaseUtil.address());// 开票地址
			tenantInfoAddParam.setInvoiceTel(TestCaseUtil.tel());// 开票电话
			tenantInfoAddParam.setInvoiceBankCode(RandomUtil.randomString(4));// 开户行行号
			tenantInfoAddParam.setInvoiceBankName(TestCaseUtil.bankName());// 开户行名称
			tenantInfoAddParam.setInvoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户账号
			tenantInfoAddParam.setTenantAccesskey(RandomUtil.randomString(4));// 租户KEY
			tenantInfoAddParam.setPriceStepOn(RandomUtil.randomInt(0,1+1));
			tenantInfoAddParam.setMarketingAreaType(RandomUtil.randomInt(1,2+1));
			
			log.info(JSON.toJSONString(tenantInfoAddParam));
			
			String responseString = mockMvc.perform(//
					MockMvcRequestBuilders.post("/tenantInfo/create")//
							.content(JSON.toJSONString(tenantInfoAddParam))//
							.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
							.accept(MediaType.APPLICATION_JSON_UTF8)//
			).andReturn().getResponse().getContentAsString();
			log.info(responseString);
		}
		
	}
	
	@Test
	public void createSwaggerTest() throws Exception {
		TenantInfoAddParam tenantInfoAddParam = new TenantInfoAddParam();
		
		String companyShortName = TestCaseUtil.companyShortName();
		
		tenantInfoAddParam.setId(TestCaseUtil.id());// 租户ID
		tenantInfoAddParam.setTenantName("租户名称"+"-"+"新增用例"+"-"+"8"+"-"+RandomUtil.randomNumbers(4));// 租户名称
		tenantInfoAddParam.setTenantBalance(new BigDecimal(0));// 账户余额
		tenantInfoAddParam.setTenantOverdraw(new BigDecimal(0));// 透支额度
		tenantInfoAddParam.setTenantDisplayName(companyShortName);// 租户显示名称
		tenantInfoAddParam.setTenantProvince(TestCaseUtil.province());// 省
		tenantInfoAddParam.setTenantCity(TestCaseUtil.city());// 市
		tenantInfoAddParam.setTenantCountyTown(RandomUtil.randomString(4));// 区/县
		tenantInfoAddParam.setTenantLinkAddress(TestCaseUtil.address());// 联系地址
		tenantInfoAddParam.setTenantLinkman(TestCaseUtil.name());// 联系人
		tenantInfoAddParam.setTenantLinkmanMobile(TestCaseUtil.mobile());// 手机号码
		tenantInfoAddParam.setTenantLinkmanTel(TestCaseUtil.tel());// 单位电话
		tenantInfoAddParam.setTenantLinkmanEmail(TestCaseUtil.email(null));// 邮箱
		tenantInfoAddParam.setTenantLinkmanQq(TestCaseUtil.qq());// QQ号码
		tenantInfoAddParam.setTenantType(RandomUtil.randomInt(0,1+1));// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
		tenantInfoAddParam.setTenantRegisterTime(new Date());// 注册时间
		tenantInfoAddParam.setInvoiceType(RandomUtil.randomInt(0,1+1));// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
		tenantInfoAddParam.setInvoiceName(TestCaseUtil.name());// 开票名称
		tenantInfoAddParam.setInvoiceTaxNo(RandomUtil.randomString(4));// 税号
		tenantInfoAddParam.setInvoiceAddress(TestCaseUtil.address());// 开票地址
		tenantInfoAddParam.setInvoiceTel(TestCaseUtil.tel());// 开票电话
		tenantInfoAddParam.setInvoiceBankCode(RandomUtil.randomString(4));// 开户行行号
		tenantInfoAddParam.setInvoiceBankName(TestCaseUtil.bankName());// 开户行名称
		tenantInfoAddParam.setInvoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户账号
		tenantInfoAddParam.setTenantAccesskey(RandomUtil.randomString(4));// 租户KEY
		tenantInfoAddParam.setPriceStepOn(RandomUtil.randomInt(0,1+1));
		tenantInfoAddParam.setMarketingAreaType(RandomUtil.randomInt(1,2+1));
		
		log.info(JSON.toJSONString(tenantInfoAddParam));
		
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "dd119da46cb44455be061425da1dd9ed";
		log.info("id={}",id);
		
		TenantInfoUpdateParam tenantInfoUpdateParam = new TenantInfoUpdateParam();
		tenantInfoUpdateParam.setTenantDisplayName("欣茂"+"更新测试"+RandomUtil.randomString(4));
		
		log.info(JSON.toJSONString(tenantInfoUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantInfo/update/"+id)//
						.content(JSON.toJSONString(tenantInfoUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "5da621db1ee643eaa28649d89204eaed";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantInfo/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void rechargeTest() throws Exception {
		String id = "5776fb77e26e45209d56e39f9ceb7308";
		log.info("id={}",id);
		
		TenantInfoRechargeParam tenantInfoRechargeParam = new TenantInfoRechargeParam();
		tenantInfoRechargeParam.setId(id);
		tenantInfoRechargeParam.setRechargeMoney(new BigDecimal(RandomUtil.randomInt(1, 10)));
		
		log.info(JSON.toJSONString(tenantInfoRechargeParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantInfo/recharge/"+id)//
						.content(JSON.toJSONString(tenantInfoRechargeParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listTest() throws Exception {
//		String queryCol = "tenant_name";
//		String queryType = "=";
//		String queryValue = "租户名称-新增用例-0-1897";
		
//		String queryCol = "tenant_name";
//		String queryType = "like";
//		String queryValue = "1-8358";
		
		String queryCol = "tenant_type";
		String queryType = "=";
		String queryValue = "1";
		
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("queryCol", queryCol);
		params.add("queryType", queryType);
		params.add("queryValue", queryValue);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantInfo/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		String id = "5776fb77e26e45209d56e39f9ceb7308";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantInfo/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateModeulTest() throws Exception {
		TenantInfoModuleInfoUpdateParam tenantInfoModuleInfoUpdateParam = new TenantInfoModuleInfoUpdateParam();
		tenantInfoModuleInfoUpdateParam.setTenantId("23a60db88e184a3fa82d21dd4b0055c4");
		tenantInfoModuleInfoUpdateParam.setModuleId("1DC71E22720A45ADA9C0EA4B8A28C0C3");
		tenantInfoModuleInfoUpdateParam.setModuleEdition(1);
		tenantInfoModuleInfoUpdateParam.setModuleOnOff(0);
		tenantInfoModuleInfoUpdateParam.setModuleOnOff(1);
		log.info(JSON.toJSONString(tenantInfoModuleInfoUpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/tenantInfo/update/module")//
						.content(JSON.toJSONString(tenantInfoModuleInfoUpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void listModuleTest() throws Exception {
		String id = "23a60db88e184a3fa82d21dd4b0055c4";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/tenantInfo/list/module/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
}
