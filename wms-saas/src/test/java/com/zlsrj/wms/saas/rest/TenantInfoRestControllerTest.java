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
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantInfoRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-infos" + "/" + id))
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
		
		// String companyShortName = TestCaseUtil.companyShortName();

		// params.add("id",TestCaseUtil.id());// 租户ID
		// params.add("tenantName",TestCaseUtil.companyName(companyShortName));// 租户名称
		// params.add("tenantBalance",new BigDecimal(0));// 账户余额
		// params.add("teanantOverdraw",new BigDecimal(0));// 透支额度
		// params.add("tenantDisplayName",companyShortName);// 租户显示名称
		// params.add("tenantProvince",TestCaseUtil.province());// 省
		// params.add("tenantCity",TestCaseUtil.city());// 市
		// params.add("tenantCountyTown",RandomUtil.randomString(4));// 区/县
		// params.add("tenantLinkAddress",TestCaseUtil.address());// 联系地址
		// params.add("tenantLinkman",TestCaseUtil.name());// 联系人
		// params.add("tenantLinkmanMobile",TestCaseUtil.mobile());// 手机号码
		// params.add("tenantLinkmanTel",TestCaseUtil.tel());// 单位电话
		// params.add("tenantLinkmanEmail",TestCaseUtil.email(null));// 邮箱
		// params.add("tenantLinkmanQq",TestCaseUtil.qq());// QQ号码
		// params.add("tenantType",RandomUtil.randomInt(0,1+1));// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
		// params.add("tenantRegisterTime",new Date());// 注册时间
		// params.add("invoiceType",RandomUtil.randomInt(0,1+1));// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
		// params.add("invoiceName",TestCaseUtil.name());// 开票名称
		// params.add("invoiceTaxNo",RandomUtil.randomString(4));// 税号
		// params.add("invoiceAddress",TestCaseUtil.address());// 开票地址
		// params.add("invoiceTel",TestCaseUtil.tel());// 开票电话
		// params.add("invoiceBankCode",RandomUtil.randomString(4));// 开户行行号
		// params.add("invoiceBankName",TestCaseUtil.bankName());// 开户行名称
		// params.add("invoiceBankAccountNo",TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户账号
		// params.add("tenantAccesskey",RandomUtil.randomString(4));// 租户KEY

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-infos").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {
		String companyShortName = TestCaseUtil.companyShortName();


		TenantInfo tenantInfo = TenantInfo.builder()//
				.id(TestCaseUtil.id())// 租户ID
				.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
				.tenantBalance(new BigDecimal(0))// 账户余额
				.teanantOverdraw(new BigDecimal(0))// 透支额度
				.tenantDisplayName(companyShortName)// 租户显示名称
				.tenantProvince(TestCaseUtil.province())// 省
				.tenantCity(TestCaseUtil.city())// 市
				.tenantCountyTown(RandomUtil.randomString(4))// 区/县
				.tenantLinkAddress(TestCaseUtil.address())// 联系地址
				.tenantLinkman(TestCaseUtil.name())// 联系人
				.tenantLinkmanMobile(TestCaseUtil.mobile())// 手机号码
				.tenantLinkmanTel(TestCaseUtil.tel())// 单位电话
				.tenantLinkmanEmail(TestCaseUtil.email(null))// 邮箱
				.tenantLinkmanQq(TestCaseUtil.qq())// QQ号码
				.tenantType(RandomUtil.randomInt(0,1+1))// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
				.tenantRegisterTime(new Date())// 注册时间
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.tenantAccesskey(RandomUtil.randomString(4))// 租户KEY
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-infos").content(JSON.toJSONString(tenantInfo)) //
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
		String companyShortName = TestCaseUtil.companyShortName();


		TenantInfo tenantInfo = TenantInfo.builder()//
				//.id(TestCaseUtil.id())// 租户ID
				.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
				.tenantBalance(new BigDecimal(0))// 账户余额
				.teanantOverdraw(new BigDecimal(0))// 透支额度
				.tenantDisplayName(companyShortName)// 租户显示名称
				.tenantProvince(TestCaseUtil.province())// 省
				.tenantCity(TestCaseUtil.city())// 市
				.tenantCountyTown(RandomUtil.randomString(4))// 区/县
				.tenantLinkAddress(TestCaseUtil.address())// 联系地址
				.tenantLinkman(TestCaseUtil.name())// 联系人
				.tenantLinkmanMobile(TestCaseUtil.mobile())// 手机号码
				.tenantLinkmanTel(TestCaseUtil.tel())// 单位电话
				.tenantLinkmanEmail(TestCaseUtil.email(null))// 邮箱
				.tenantLinkmanQq(TestCaseUtil.qq())// QQ号码
				.tenantType(RandomUtil.randomInt(0,1+1))// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
				.tenantRegisterTime(new Date())// 注册时间
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.tenantAccesskey(RandomUtil.randomString(4))// 租户KEY
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-infos" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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
		// String companyShortName = TestCaseUtil.companyShortName();


		TenantInfo tenantInfo = TenantInfo.builder()//
				//.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
				//.tenantBalance(new BigDecimal(0))// 账户余额
				//.teanantOverdraw(new BigDecimal(0))// 透支额度
				//.tenantDisplayName(companyShortName)// 租户显示名称
				//.tenantProvince(TestCaseUtil.province())// 省
				//.tenantCity(TestCaseUtil.city())// 市
				//.tenantCountyTown(RandomUtil.randomString(4))// 区/县
				//.tenantLinkAddress(TestCaseUtil.address())// 联系地址
				//.tenantLinkman(TestCaseUtil.name())// 联系人
				//.tenantLinkmanMobile(TestCaseUtil.mobile())// 手机号码
				//.tenantLinkmanTel(TestCaseUtil.tel())// 单位电话
				//.tenantLinkmanEmail(TestCaseUtil.email(null))// 邮箱
				//.tenantLinkmanQq(TestCaseUtil.qq())// QQ号码
				//.tenantType(RandomUtil.randomInt(0,1+1))// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
				//.tenantRegisterTime(new Date())// 注册时间
				//.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				//.invoiceName(TestCaseUtil.name())// 开票名称
				//.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				//.invoiceAddress(TestCaseUtil.address())// 开票地址
				//.invoiceTel(TestCaseUtil.tel())// 开票电话
				//.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				//.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				//.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				//.tenantAccesskey(RandomUtil.randomString(4))// 租户KEY
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-infos" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-infos" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
