package com.zlsrj.wms.saas.rest;

import java.math.BigDecimal;

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
import com.zlsrj.wms.api.entity.TenantCustomerMeterChangeLog;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerMeterChangeLogRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-meter-change-logs" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 变更日志ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("customerId",RandomUtil.randomLong());// 用户ID
		// params.add("csutomerIdNew",RandomUtil.randomLong());// 新用户ID
		// params.add("customerName",TestCaseUtil.name());// 用户名称
		// params.add("customerNameNew",RandomUtil.randomString(4));// 新用户名称
		// params.add("customerAddress",TestCaseUtil.address());// 用户地址
		// params.add("customerAddressNew",RandomUtil.randomString(4));// 新用户地址
		// params.add("customerTypeId",RandomUtil.randomLong());// 用户类别ID
		// params.add("customerTypeIdNew",RandomUtil.randomLong());// 新用户类别ID
		// params.add("customerStatus",RandomUtil.randomInt(0,1+1));// 用户状态（1：正常；2：暂停；3：消户）
		// params.add("customerStatusNew",RandomUtil.randomInt(0,1000+1));// 新用户状态（1：正常；2：暂停；3：消户）
		// params.add("customerPaymentMethod",RandomUtil.randomInt(0,1000+1));// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
		// params.add("customerPaymentMethodNew",RandomUtil.randomInt(0,1000+1));// 新收费方式（1：坐收；2：走收；3：代扣；4：托收）
		// params.add("invoiceType",RandomUtil.randomInt(0,1+1));// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
		// params.add("invoiceTypeNew",RandomUtil.randomInt(0,1000+1));// 新开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
		// params.add("invoiceName",TestCaseUtil.name());// 开票名称
		// params.add("invoiceNameNew",RandomUtil.randomString(4));// 新开票名称
		// params.add("invoiceTaxNo",RandomUtil.randomString(4));// 税号
		// params.add("invoiceTaxNoNew",RandomUtil.randomString(4));// 新税号
		// params.add("invoiceAddress",TestCaseUtil.address());// 开票地址
		// params.add("invoiceAddressNew",RandomUtil.randomString(4));// 新开票地址
		// params.add("invoiceTel",TestCaseUtil.tel());// 开票电话
		// params.add("invoiceTelNew",RandomUtil.randomString(4));// 新开票电话
		// params.add("invoiceBankCode",RandomUtil.randomString(4));// 开户行行号
		// params.add("invoiceBankCodeNew",RandomUtil.randomString(4));// 新开户行行号
		// params.add("invoiceBankName",TestCaseUtil.bankName());// 开户行名称
		// params.add("invoiceBankNameNew",RandomUtil.randomString(4));// 新开户行名称
		// params.add("invoiceBankAccountNo",TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// 开户行账号
		// params.add("invoiceBankAccountNoNew",RandomUtil.randomString(4));// 新开户行账号
		// params.add("meterId",RandomUtil.randomLong());// 水表ID
		// params.add("priceTypeId",RandomUtil.randomLong());// 价格分类ID
		// params.add("priceTypeIdNew",RandomUtil.randomLong());// 新价格分类ID
		// params.add("meterLastSettlePointer",new BigDecimal(0));// 水表止码
		// params.add("meterLastSettlePointerNew",new BigDecimal(0));// 新水表止码
		// params.add("manufactorId",RandomUtil.randomLong());// 水表厂商ID
		// params.add("manufactorIdNew",RandomUtil.randomLong());// 新水表厂商ID
		// params.add("meterType",RandomUtil.randomInt(0,1+1));// 水表类型（1：机械表；2：远传表；3：IC卡表）
		// params.add("meterTypeNew",RandomUtil.randomInt(0,1000+1));// 新水表类型（1：机械表；2：远传表；3：IC卡表）
		// params.add("caliberId",RandomUtil.randomLong());// 水表口径ID
		// params.add("caliberIdNew",RandomUtil.randomLong());// 新水表口径ID
		// params.add("meterMachineCode",RandomUtil.randomString(4));// 水表表身码
		// params.add("meterMachineCodeNew",RandomUtil.randomString(4));// 新水表表身码
		// params.add("meterIotCode",RandomUtil.randomString(4));// 远传系统编号
		// params.add("meterIotCodeNew",RandomUtil.randomString(4));// 新远传系统编号
		// params.add("meterPeoples",RandomUtil.randomInt(0,1000+1));// 人口数
		// params.add("meterPeoplesNew",RandomUtil.randomInt(0,1000+1));// 新人口数

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-customer-meter-change-logs").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantCustomerMeterChangeLog tenantInfo = TenantCustomerMeterChangeLog.builder()//
				.id(TestCaseUtil.id())// 变更日志ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.customerId(RandomUtil.randomLong())// 用户ID
				.csutomerIdNew(RandomUtil.randomLong())// 新用户ID
				.customerName(TestCaseUtil.name())// 用户名称
				.customerNameNew(RandomUtil.randomString(4))// 新用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.customerAddressNew(RandomUtil.randomString(4))// 新用户地址
				.customerTypeId(RandomUtil.randomLong())// 用户类别ID
				.customerTypeIdNew(RandomUtil.randomLong())// 新用户类别ID
				.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.customerStatusNew(RandomUtil.randomInt(0,1000+1))// 新用户状态（1：正常；2：暂停；3：消户）
				.customerPaymentMethod(RandomUtil.randomInt(0,1000+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.customerPaymentMethodNew(RandomUtil.randomInt(0,1000+1))// 新收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceTypeNew(RandomUtil.randomInt(0,1000+1))// 新开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceNameNew(RandomUtil.randomString(4))// 新开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceTaxNoNew(RandomUtil.randomString(4))// 新税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceAddressNew(RandomUtil.randomString(4))// 新开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceTelNew(RandomUtil.randomString(4))// 新开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankCodeNew(RandomUtil.randomString(4))// 新开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankNameNew(RandomUtil.randomString(4))// 新开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
				.invoiceBankAccountNoNew(RandomUtil.randomString(4))// 新开户行账号
				.meterId(RandomUtil.randomLong())// 水表ID
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.priceTypeIdNew(RandomUtil.randomLong())// 新价格分类ID
				.meterLastSettlePointer(new BigDecimal(0))// 水表止码
				.meterLastSettlePointerNew(new BigDecimal(0))// 新水表止码
				.manufactorId(RandomUtil.randomLong())// 水表厂商ID
				.manufactorIdNew(RandomUtil.randomLong())// 新水表厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.meterTypeNew(RandomUtil.randomInt(0,1000+1))// 新水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomLong())// 水表口径ID
				.caliberIdNew(RandomUtil.randomLong())// 新水表口径ID
				.meterMachineCode(RandomUtil.randomString(4))// 水表表身码
				.meterMachineCodeNew(RandomUtil.randomString(4))// 新水表表身码
				.meterIotCode(RandomUtil.randomString(4))// 远传系统编号
				.meterIotCodeNew(RandomUtil.randomString(4))// 新远传系统编号
				.meterPeoples(RandomUtil.randomInt(0,1000+1))// 人口数
				.meterPeoplesNew(RandomUtil.randomInt(0,1000+1))// 新人口数
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-customer-meter-change-logs").content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerMeterChangeLog tenantInfo = TenantCustomerMeterChangeLog.builder()//
				//.id(TestCaseUtil.id())// 变更日志ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.customerId(RandomUtil.randomLong())// 用户ID
				.csutomerIdNew(RandomUtil.randomLong())// 新用户ID
				.customerName(TestCaseUtil.name())// 用户名称
				.customerNameNew(RandomUtil.randomString(4))// 新用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.customerAddressNew(RandomUtil.randomString(4))// 新用户地址
				.customerTypeId(RandomUtil.randomLong())// 用户类别ID
				.customerTypeIdNew(RandomUtil.randomLong())// 新用户类别ID
				.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.customerStatusNew(RandomUtil.randomInt(0,1000+1))// 新用户状态（1：正常；2：暂停；3：消户）
				.customerPaymentMethod(RandomUtil.randomInt(0,1000+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.customerPaymentMethodNew(RandomUtil.randomInt(0,1000+1))// 新收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceTypeNew(RandomUtil.randomInt(0,1000+1))// 新开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceNameNew(RandomUtil.randomString(4))// 新开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceTaxNoNew(RandomUtil.randomString(4))// 新税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceAddressNew(RandomUtil.randomString(4))// 新开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceTelNew(RandomUtil.randomString(4))// 新开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankCodeNew(RandomUtil.randomString(4))// 新开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankNameNew(RandomUtil.randomString(4))// 新开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
				.invoiceBankAccountNoNew(RandomUtil.randomString(4))// 新开户行账号
				.meterId(RandomUtil.randomLong())// 水表ID
				.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				.priceTypeIdNew(RandomUtil.randomLong())// 新价格分类ID
				.meterLastSettlePointer(new BigDecimal(0))// 水表止码
				.meterLastSettlePointerNew(new BigDecimal(0))// 新水表止码
				.manufactorId(RandomUtil.randomLong())// 水表厂商ID
				.manufactorIdNew(RandomUtil.randomLong())// 新水表厂商ID
				.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				.meterTypeNew(RandomUtil.randomInt(0,1000+1))// 新水表类型（1：机械表；2：远传表；3：IC卡表）
				.caliberId(RandomUtil.randomLong())// 水表口径ID
				.caliberIdNew(RandomUtil.randomLong())// 新水表口径ID
				.meterMachineCode(RandomUtil.randomString(4))// 水表表身码
				.meterMachineCodeNew(RandomUtil.randomString(4))// 新水表表身码
				.meterIotCode(RandomUtil.randomString(4))// 远传系统编号
				.meterIotCodeNew(RandomUtil.randomString(4))// 新远传系统编号
				.meterPeoples(RandomUtil.randomInt(0,1000+1))// 人口数
				.meterPeoplesNew(RandomUtil.randomInt(0,1000+1))// 新人口数
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-customer-meter-change-logs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantCustomerMeterChangeLog tenantInfo = TenantCustomerMeterChangeLog.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户ID
				//.customerId(RandomUtil.randomLong())// 用户ID
				//.csutomerIdNew(RandomUtil.randomLong())// 新用户ID
				//.customerName(TestCaseUtil.name())// 用户名称
				//.customerNameNew(RandomUtil.randomString(4))// 新用户名称
				//.customerAddress(TestCaseUtil.address())// 用户地址
				//.customerAddressNew(RandomUtil.randomString(4))// 新用户地址
				//.customerTypeId(RandomUtil.randomLong())// 用户类别ID
				//.customerTypeIdNew(RandomUtil.randomLong())// 新用户类别ID
				//.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				//.customerStatusNew(RandomUtil.randomInt(0,1000+1))// 新用户状态（1：正常；2：暂停；3：消户）
				//.customerPaymentMethod(RandomUtil.randomInt(0,1000+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				//.customerPaymentMethodNew(RandomUtil.randomInt(0,1000+1))// 新收费方式（1：坐收；2：走收；3：代扣；4：托收）
				//.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				//.invoiceTypeNew(RandomUtil.randomInt(0,1000+1))// 新开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				//.invoiceName(TestCaseUtil.name())// 开票名称
				//.invoiceNameNew(RandomUtil.randomString(4))// 新开票名称
				//.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				//.invoiceTaxNoNew(RandomUtil.randomString(4))// 新税号
				//.invoiceAddress(TestCaseUtil.address())// 开票地址
				//.invoiceAddressNew(RandomUtil.randomString(4))// 新开票地址
				//.invoiceTel(TestCaseUtil.tel())// 开票电话
				//.invoiceTelNew(RandomUtil.randomString(4))// 新开票电话
				//.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				//.invoiceBankCodeNew(RandomUtil.randomString(4))// 新开户行行号
				//.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				//.invoiceBankNameNew(RandomUtil.randomString(4))// 新开户行名称
				//.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
				//.invoiceBankAccountNoNew(RandomUtil.randomString(4))// 新开户行账号
				//.meterId(RandomUtil.randomLong())// 水表ID
				//.priceTypeId(RandomUtil.randomLong())// 价格分类ID
				//.priceTypeIdNew(RandomUtil.randomLong())// 新价格分类ID
				//.meterLastSettlePointer(new BigDecimal(0))// 水表止码
				//.meterLastSettlePointerNew(new BigDecimal(0))// 新水表止码
				//.manufactorId(RandomUtil.randomLong())// 水表厂商ID
				//.manufactorIdNew(RandomUtil.randomLong())// 新水表厂商ID
				//.meterType(RandomUtil.randomInt(0,1+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
				//.meterTypeNew(RandomUtil.randomInt(0,1000+1))// 新水表类型（1：机械表；2：远传表；3：IC卡表）
				//.caliberId(RandomUtil.randomLong())// 水表口径ID
				//.caliberIdNew(RandomUtil.randomLong())// 新水表口径ID
				//.meterMachineCode(RandomUtil.randomString(4))// 水表表身码
				//.meterMachineCodeNew(RandomUtil.randomString(4))// 新水表表身码
				//.meterIotCode(RandomUtil.randomString(4))// 远传系统编号
				//.meterIotCodeNew(RandomUtil.randomString(4))// 新远传系统编号
				//.meterPeoples(RandomUtil.randomInt(0,1000+1))// 人口数
				//.meterPeoplesNew(RandomUtil.randomInt(0,1000+1))// 新人口数
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-customer-meter-change-logs" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-customer-meter-change-logs" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
