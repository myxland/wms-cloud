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
import com.zlsrj.wms.api.entity.TenantPayment;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPaymentRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-payments" + "/" + id))
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
		
		// params.add("id",TestCaseUtil.id());// 实收账ID
		// params.add("tenantId",RandomUtil.randomLong());// 租户ID
		// params.add("outTransno",RandomUtil.randomString(4));// 内部生成的订单号
		// params.add("inTransno",RandomUtil.randomString(4));// 外部如微信支付宝传入的订单号
		// params.add("payTime",new Date());// 付款时间
		// params.add("paymentStatus",RandomUtil.randomInt(0,1+1));// 实收账状态（1：正常；2：被退款；3：退款记录）
		// params.add("customerId",RandomUtil.randomLong());// 用户ID
		// params.add("chargeDepartmentId",RandomUtil.randomLong());// 收款部门ID
		// params.add("chargeEmployeeId",RandomUtil.randomLong());// 收费员ID
		// params.add("payChannels",RandomUtil.randomInt(0,1000+1));// 付款途径（1：柜台；2：银行；3：线上；4：走收；5：系统处理）
		// params.add("payMethod",RandomUtil.randomInt(0,1000+1));// 付款方式（1：现金；2：支票；3：刷卡；4：电汇；5：代扣；6：托收；7：微信生活缴费；8：支付宝生活缴费；9：微信公众号；10：微信扫码[用户被扫]；11：支付宝扫码[用户被扫]；12：微信扫码[用户主扫]；13：支付宝扫码[用户主扫]；0：预存抵扣）
		// params.add("customerBalanceMoneyBefore",new BigDecimal(0));// 用户上期预存余额
		// params.add("customerPayMoney",new BigDecimal(0));// 用户付款金额
		// params.add("customerBalanceMoneyHappen",new BigDecimal(0));// 用户预存发生值
		// params.add("payTheArrearsMoney",new BigDecimal(0));// 所缴欠费金额
		// params.add("payTheLateFeeMoney",new BigDecimal(0));// 所缴违约金金额
		// params.add("customerBalanceMoneyAfter",new BigDecimal(0));// 用户本期预存余额

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenant-payments").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		TenantPayment tenantInfo = TenantPayment.builder()//
				.id(TestCaseUtil.id())// 实收账ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.outTransno(RandomUtil.randomString(4))// 内部生成的订单号
				.inTransno(RandomUtil.randomString(4))// 外部如微信支付宝传入的订单号
				.payTime(new Date())// 付款时间
				.paymentStatus(RandomUtil.randomInt(0,1+1))// 实收账状态（1：正常；2：被退款；3：退款记录）
				.customerId(RandomUtil.randomLong())// 用户ID
				.chargeDepartmentId(RandomUtil.randomLong())// 收款部门ID
				.chargeEmployeeId(RandomUtil.randomLong())// 收费员ID
				.payChannels(RandomUtil.randomInt(0,1000+1))// 付款途径（1：柜台；2：银行；3：线上；4：走收；5：系统处理）
				.payMethod(RandomUtil.randomInt(0,1000+1))// 付款方式（1：现金；2：支票；3：刷卡；4：电汇；5：代扣；6：托收；7：微信生活缴费；8：支付宝生活缴费；9：微信公众号；10：微信扫码[用户被扫]；11：支付宝扫码[用户被扫]；12：微信扫码[用户主扫]；13：支付宝扫码[用户主扫]；0：预存抵扣）
				.customerBalanceMoneyBefore(new BigDecimal(0))// 用户上期预存余额
				.customerPayMoney(new BigDecimal(0))// 用户付款金额
				.customerBalanceMoneyHappen(new BigDecimal(0))// 用户预存发生值
				.payTheArrearsMoney(new BigDecimal(0))// 所缴欠费金额
				.payTheLateFeeMoney(new BigDecimal(0))// 所缴违约金金额
				.customerBalanceMoneyAfter(new BigDecimal(0))// 用户本期预存余额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenant-payments").content(JSON.toJSONString(tenantInfo)) //
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

		TenantPayment tenantInfo = TenantPayment.builder()//
				//.id(TestCaseUtil.id())// 实收账ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.outTransno(RandomUtil.randomString(4))// 内部生成的订单号
				.inTransno(RandomUtil.randomString(4))// 外部如微信支付宝传入的订单号
				.payTime(new Date())// 付款时间
				.paymentStatus(RandomUtil.randomInt(0,1+1))// 实收账状态（1：正常；2：被退款；3：退款记录）
				.customerId(RandomUtil.randomLong())// 用户ID
				.chargeDepartmentId(RandomUtil.randomLong())// 收款部门ID
				.chargeEmployeeId(RandomUtil.randomLong())// 收费员ID
				.payChannels(RandomUtil.randomInt(0,1000+1))// 付款途径（1：柜台；2：银行；3：线上；4：走收；5：系统处理）
				.payMethod(RandomUtil.randomInt(0,1000+1))// 付款方式（1：现金；2：支票；3：刷卡；4：电汇；5：代扣；6：托收；7：微信生活缴费；8：支付宝生活缴费；9：微信公众号；10：微信扫码[用户被扫]；11：支付宝扫码[用户被扫]；12：微信扫码[用户主扫]；13：支付宝扫码[用户主扫]；0：预存抵扣）
				.customerBalanceMoneyBefore(new BigDecimal(0))// 用户上期预存余额
				.customerPayMoney(new BigDecimal(0))// 用户付款金额
				.customerBalanceMoneyHappen(new BigDecimal(0))// 用户预存发生值
				.payTheArrearsMoney(new BigDecimal(0))// 所缴欠费金额
				.payTheLateFeeMoney(new BigDecimal(0))// 所缴违约金金额
				.customerBalanceMoneyAfter(new BigDecimal(0))// 用户本期预存余额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenant-payments" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		TenantPayment tenantInfo = TenantPayment.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户ID
				//.outTransno(RandomUtil.randomString(4))// 内部生成的订单号
				//.inTransno(RandomUtil.randomString(4))// 外部如微信支付宝传入的订单号
				//.payTime(new Date())// 付款时间
				//.paymentStatus(RandomUtil.randomInt(0,1+1))// 实收账状态（1：正常；2：被退款；3：退款记录）
				//.customerId(RandomUtil.randomLong())// 用户ID
				//.chargeDepartmentId(RandomUtil.randomLong())// 收款部门ID
				//.chargeEmployeeId(RandomUtil.randomLong())// 收费员ID
				//.payChannels(RandomUtil.randomInt(0,1000+1))// 付款途径（1：柜台；2：银行；3：线上；4：走收；5：系统处理）
				//.payMethod(RandomUtil.randomInt(0,1000+1))// 付款方式（1：现金；2：支票；3：刷卡；4：电汇；5：代扣；6：托收；7：微信生活缴费；8：支付宝生活缴费；9：微信公众号；10：微信扫码[用户被扫]；11：支付宝扫码[用户被扫]；12：微信扫码[用户主扫]；13：支付宝扫码[用户主扫]；0：预存抵扣）
				//.customerBalanceMoneyBefore(new BigDecimal(0))// 用户上期预存余额
				//.customerPayMoney(new BigDecimal(0))// 用户付款金额
				//.customerBalanceMoneyHappen(new BigDecimal(0))// 用户预存发生值
				//.payTheArrearsMoney(new BigDecimal(0))// 所缴欠费金额
				//.payTheLateFeeMoney(new BigDecimal(0))// 所缴违约金金额
				//.customerBalanceMoneyAfter(new BigDecimal(0))// 用户本期预存余额
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/tenant-payments" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-payments" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
