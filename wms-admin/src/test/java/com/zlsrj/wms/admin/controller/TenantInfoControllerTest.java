package com.zlsrj.wms.admin.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.dto.TenantEmployeeUpdateParam;
import com.zlsrj.wms.api.dto.TenantInfoAddParam;
import com.zlsrj.wms.api.dto.TenantInfoUpdateParam;
import com.zlsrj.wms.api.dto.TenantRoleUpdateParam;
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
	
}
