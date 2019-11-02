package com.zlsrj.wms.mbg.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;
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

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.mbg.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
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
	public void getByIdTest() throws Exception {
		Long id = 1L;
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantInfo/select" + "/" + id)).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void listTest() throws Exception {
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantInfo/list")).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void pageTest() throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("tenantType", Integer.toString(5));

		params.add("pageNum", Integer.toString(1));
		params.add("pageSize", Integer.toString(10));

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/tenantInfo/page").params(params))
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void insertTest() throws Exception {
		String companyShortName = TestCaseUtil.companyShortName();

		TenantInfo tenantInfo = TenantInfo.builder()//
				.id(TestCaseUtil.id())// 租户编号
				.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
				.displayName(companyShortName)// 显示名称
				.tenantProvince(TestCaseUtil.province())// 省
				.tenantCity(TestCaseUtil.city())// 市
				.tenantArea(TestCaseUtil.area())// 区县
				.tenantAddress(TestCaseUtil.address())// 联系地址
				.tenantLinkman(TestCaseUtil.name())// 联系人
				.tenantMobile(TestCaseUtil.mobile())// 手机号码
				.tenantTel(TestCaseUtil.tel())// 单位电话
				.tenantEmail(TestCaseUtil.email(null))// 邮箱
				.tenantQq(TestCaseUtil.qq())// QQ号码
				.tenantType(RandomUtil.randomInt(0,1+1))// 租户类型（1：使用单位；2：供应单位；3：内部运营）
				.tenantStatus(RandomUtil.randomInt(0,1+1))// 租户状态（正式/试用）
				.regTime(new Date())// 注册时间
				.endDate(new Date())// 到期日期
				.creditNumber(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.bankNo(TestCaseUtil.bankNo())// 开户行行号
				.bankName(TestCaseUtil.bankName())// 开户行名称
				.accountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.partChargeOn(RandomUtil.randomInt(0,1+1))// 是否启用部分缴费（启用/不启用）
				.overDuefineOn(RandomUtil.randomInt(0,1+1))// 是否启用违约金（启用/不启用）
				.overDuefineDay(RandomUtil.randomInt(0,1000+1))// 违约金宽限天数（从计费之日开始）
				.overDuefineRatio(new BigDecimal(0))// 违约金每天收取比例
				.overDuefineTopRatio(new BigDecimal(0))// 违约金封顶比例（与欠费金额相比）
				.ycdkType(RandomUtil.randomInt(0,1+1))// 预存抵扣方式（抄表后即时抵扣/人工发起抵扣）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/tenantInfo/insert").content(JSONUtil.toJsonStr(tenantInfo)) // 请求的url,请求的方法是get
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updateTest() throws Exception {
		Long id = 1L;
		String companyShortName = TestCaseUtil.companyShortName();

		TenantInfo tenantInfo = TenantInfo.builder()//
				.id(id)// 
				.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
				.displayName(companyShortName)// 显示名称
				.tenantProvince(TestCaseUtil.province())// 省
				.tenantCity(TestCaseUtil.city())// 市
				.tenantArea(TestCaseUtil.area())// 区县
				.tenantAddress(TestCaseUtil.address())// 联系地址
				.tenantLinkman(TestCaseUtil.name())// 联系人
				.tenantMobile(TestCaseUtil.mobile())// 手机号码
				.tenantTel(TestCaseUtil.tel())// 单位电话
				.tenantEmail(TestCaseUtil.email(null))// 邮箱
				.tenantQq(TestCaseUtil.qq())// QQ号码
				.tenantType(RandomUtil.randomInt(0,1+1))// 租户类型（1：使用单位；2：供应单位；3：内部运营）
				.tenantStatus(RandomUtil.randomInt(0,1+1))// 租户状态（正式/试用）
				.regTime(new Date())// 注册时间
				.endDate(new Date())// 到期日期
				.creditNumber(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.bankNo(TestCaseUtil.bankNo())// 开户行行号
				.bankName(TestCaseUtil.bankName())// 开户行名称
				.accountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.partChargeOn(RandomUtil.randomInt(0,1+1))// 是否启用部分缴费（启用/不启用）
				.overDuefineOn(RandomUtil.randomInt(0,1+1))// 是否启用违约金（启用/不启用）
				.overDuefineDay(RandomUtil.randomInt(0,1000+1))// 违约金宽限天数（从计费之日开始）
				.overDuefineRatio(new BigDecimal(0))// 违约金每天收取比例
				.overDuefineTopRatio(new BigDecimal(0))// 违约金封顶比例（与欠费金额相比）
				.ycdkType(RandomUtil.randomInt(0,1+1))// 预存抵扣方式（抄表后即时抵扣/人工发起抵扣）
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenantInfo/update").content(JSONUtil.toJsonStr(tenantInfo)) // 请求的url,请求的方法是get
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updateStatusTest() throws Exception {
		Long id = 1L;
		Integer status = 0;

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/tenantInfo/update" + "/" + id + "/status" + "/" + status)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void deleteTest() throws Exception {
		Long id = 1L;

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.delete("/tenantInfo/delete" + "/" + id)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void deleteByIdsTest() throws Exception {
		Long[] ids = new Long[] { 1L, 2L, 3L, 4L, 5L };
		List<String> list = new ArrayList<String>();
		Arrays.asList(ids).forEach(id -> list.add(Long.toString(id)));

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.delete("/tenantInfo/delete/ids" + "/" + StringUtils.join(list, ','))
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

}
