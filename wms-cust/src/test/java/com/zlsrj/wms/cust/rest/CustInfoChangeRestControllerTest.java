package com.zlsrj.wms.cust.rest;

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
import com.zlsrj.wms.api.entity.CustInfoChange;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustInfoChangeRestControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/cust-info-changes" + "/" + id))
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
		// params.add("tenantId",RandomUtil.randomLong());// 租户编号
		// params.add("custId",RandomUtil.randomString(4));// 用户编号
		// params.add("changeDate",new Date());// 变更日期
		// params.add("changer",RandomUtil.randomLong());// 变更人
		// params.add("baseChange",RandomUtil.randomInt(0,1000+1));// 用户信息变更（1：是；0：否）
		// params.add("billChange",RandomUtil.randomInt(0,1000+1));// 开票信息变更（1：是；0：否）
		// params.add("statusChange",RandomUtil.randomInt(0,1000+1));// 用户状态变更（1：是；0：否）
		// params.add("custName",TestCaseUtil.name());// 用户名称
		// params.add("custNameHis",RandomUtil.randomString(4));// 变更前用户名称
		// params.add("custAddress",TestCaseUtil.address());// 用户地址
		// params.add("custAddressHis",RandomUtil.randomString(4));// 变更前用户地址
		// params.add("custTypeId",RandomUtil.randomLong());// 用户类别编号
		// params.add("custTypeIdHis",RandomUtil.randomLong());// 变更前用户类别编号
		// params.add("custRegistDate",new Date());// 立户日期
		// params.add("custRegistDateHis",new Date());// 变更前立户日期
		// params.add("custStatus",RandomUtil.randomInt(0,1+1));// 用户状态（1：正常；2：暂停；3：消户）
		// params.add("custStatusHis",RandomUtil.randomInt(0,1000+1));// 变更前用户状态（1：正常；2：暂停；3：消户）
		// params.add("payType",RandomUtil.randomInt(0,1+1));// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
		// params.add("payTypeHis",RandomUtil.randomInt(0,1000+1));// 变更前收费方式（1：坐收；2：走收；3：代扣；4：托收）
		// params.add("billType",RandomUtil.randomInt(0,1+1));// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
		// params.add("billTypeHis",RandomUtil.randomInt(0,1000+1));// 变更前开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
		// params.add("billName",TestCaseUtil.name());// 开票名称
		// params.add("billNameHis",RandomUtil.randomString(4));// 变更前开票名称
		// params.add("billTaxnum",RandomUtil.randomString(4));// 税号
		// params.add("billTaxnumHis",RandomUtil.randomString(4));// 变更前税号
		// params.add("billAddress",TestCaseUtil.address());// 开票地址
		// params.add("billAddressHis",RandomUtil.randomString(4));// 变更前开票地址
		// params.add("billTel",TestCaseUtil.tel());// 开票电话
		// params.add("billTelHis",RandomUtil.randomString(4));// 变更前开票电话
		// params.add("billBank",RandomUtil.randomString(4));// 银行名称
		// params.add("billBankHis",RandomUtil.randomString(4));// 变更前银行名称
		// params.add("billBankId",RandomUtil.randomString(4));// 开户行行号
		// params.add("billBankIdHis",RandomUtil.randomString(4));// 变更前开户行行号
		// params.add("billBankName",TestCaseUtil.bankName());// 开户行名称
		// params.add("billBankNameHis",RandomUtil.randomString(4));// 变更前开户行名称
		// params.add("billBankAccount",RandomUtil.randomString(4));// 开户行账号
		// params.add("billBankAccountHis",RandomUtil.randomString(4));// 变更前开户行账号

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/cust-info-changes").params(params))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void saveTest() throws Exception {

		CustInfoChange tenantInfo = CustInfoChange.builder()//
				.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custId(RandomUtil.randomString(4))// 用户编号
				.changeDate(new Date())// 变更日期
				.changer(RandomUtil.randomLong())// 变更人
				.baseChange(RandomUtil.randomInt(0,1000+1))// 用户信息变更（1：是；0：否）
				.billChange(RandomUtil.randomInt(0,1000+1))// 开票信息变更（1：是；0：否）
				.statusChange(RandomUtil.randomInt(0,1000+1))// 用户状态变更（1：是；0：否）
				.custName(TestCaseUtil.name())// 用户名称
				.custNameHis(RandomUtil.randomString(4))// 变更前用户名称
				.custAddress(TestCaseUtil.address())// 用户地址
				.custAddressHis(RandomUtil.randomString(4))// 变更前用户地址
				.custTypeId(RandomUtil.randomLong())// 用户类别编号
				.custTypeIdHis(RandomUtil.randomLong())// 变更前用户类别编号
				.custRegistDate(new Date())// 立户日期
				.custRegistDateHis(new Date())// 变更前立户日期
				.custStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.custStatusHis(RandomUtil.randomInt(0,1000+1))// 变更前用户状态（1：正常；2：暂停；3：消户）
				.payType(RandomUtil.randomInt(0,1+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.payTypeHis(RandomUtil.randomInt(0,1000+1))// 变更前收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.billType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.billTypeHis(RandomUtil.randomInt(0,1000+1))// 变更前开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.billName(TestCaseUtil.name())// 开票名称
				.billNameHis(RandomUtil.randomString(4))// 变更前开票名称
				.billTaxnum(RandomUtil.randomString(4))// 税号
				.billTaxnumHis(RandomUtil.randomString(4))// 变更前税号
				.billAddress(TestCaseUtil.address())// 开票地址
				.billAddressHis(RandomUtil.randomString(4))// 变更前开票地址
				.billTel(TestCaseUtil.tel())// 开票电话
				.billTelHis(RandomUtil.randomString(4))// 变更前开票电话
				.billBank(RandomUtil.randomString(4))// 银行名称
				.billBankHis(RandomUtil.randomString(4))// 变更前银行名称
				.billBankId(RandomUtil.randomString(4))// 开户行行号
				.billBankIdHis(RandomUtil.randomString(4))// 变更前开户行行号
				.billBankName(TestCaseUtil.bankName())// 开户行名称
				.billBankNameHis(RandomUtil.randomString(4))// 变更前开户行名称
				.billBankAccount(RandomUtil.randomString(4))// 开户行账号
				.billBankAccountHis(RandomUtil.randomString(4))// 变更前开户行账号
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/cust-info-changes").content(JSON.toJSONString(tenantInfo)) //
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

		CustInfoChange tenantInfo = CustInfoChange.builder()//
				//.id(TestCaseUtil.id())// 系统编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.custId(RandomUtil.randomString(4))// 用户编号
				.changeDate(new Date())// 变更日期
				.changer(RandomUtil.randomLong())// 变更人
				.baseChange(RandomUtil.randomInt(0,1000+1))// 用户信息变更（1：是；0：否）
				.billChange(RandomUtil.randomInt(0,1000+1))// 开票信息变更（1：是；0：否）
				.statusChange(RandomUtil.randomInt(0,1000+1))// 用户状态变更（1：是；0：否）
				.custName(TestCaseUtil.name())// 用户名称
				.custNameHis(RandomUtil.randomString(4))// 变更前用户名称
				.custAddress(TestCaseUtil.address())// 用户地址
				.custAddressHis(RandomUtil.randomString(4))// 变更前用户地址
				.custTypeId(RandomUtil.randomLong())// 用户类别编号
				.custTypeIdHis(RandomUtil.randomLong())// 变更前用户类别编号
				.custRegistDate(new Date())// 立户日期
				.custRegistDateHis(new Date())// 变更前立户日期
				.custStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				.custStatusHis(RandomUtil.randomInt(0,1000+1))// 变更前用户状态（1：正常；2：暂停；3：消户）
				.payType(RandomUtil.randomInt(0,1+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.payTypeHis(RandomUtil.randomInt(0,1000+1))// 变更前收费方式（1：坐收；2：走收；3：代扣；4：托收）
				.billType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.billTypeHis(RandomUtil.randomInt(0,1000+1))// 变更前开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.billName(TestCaseUtil.name())// 开票名称
				.billNameHis(RandomUtil.randomString(4))// 变更前开票名称
				.billTaxnum(RandomUtil.randomString(4))// 税号
				.billTaxnumHis(RandomUtil.randomString(4))// 变更前税号
				.billAddress(TestCaseUtil.address())// 开票地址
				.billAddressHis(RandomUtil.randomString(4))// 变更前开票地址
				.billTel(TestCaseUtil.tel())// 开票电话
				.billTelHis(RandomUtil.randomString(4))// 变更前开票电话
				.billBank(RandomUtil.randomString(4))// 银行名称
				.billBankHis(RandomUtil.randomString(4))// 变更前银行名称
				.billBankId(RandomUtil.randomString(4))// 开户行行号
				.billBankIdHis(RandomUtil.randomString(4))// 变更前开户行行号
				.billBankName(TestCaseUtil.bankName())// 开户行名称
				.billBankNameHis(RandomUtil.randomString(4))// 变更前开户行名称
				.billBankAccount(RandomUtil.randomString(4))// 开户行账号
				.billBankAccountHis(RandomUtil.randomString(4))// 变更前开户行账号
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/cust-info-changes" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		CustInfoChange tenantInfo = CustInfoChange.builder()//
				//.tenantId(RandomUtil.randomLong())// 租户编号
				//.custId(RandomUtil.randomString(4))// 用户编号
				//.changeDate(new Date())// 变更日期
				//.changer(RandomUtil.randomLong())// 变更人
				//.baseChange(RandomUtil.randomInt(0,1000+1))// 用户信息变更（1：是；0：否）
				//.billChange(RandomUtil.randomInt(0,1000+1))// 开票信息变更（1：是；0：否）
				//.statusChange(RandomUtil.randomInt(0,1000+1))// 用户状态变更（1：是；0：否）
				//.custName(TestCaseUtil.name())// 用户名称
				//.custNameHis(RandomUtil.randomString(4))// 变更前用户名称
				//.custAddress(TestCaseUtil.address())// 用户地址
				//.custAddressHis(RandomUtil.randomString(4))// 变更前用户地址
				//.custTypeId(RandomUtil.randomLong())// 用户类别编号
				//.custTypeIdHis(RandomUtil.randomLong())// 变更前用户类别编号
				//.custRegistDate(new Date())// 立户日期
				//.custRegistDateHis(new Date())// 变更前立户日期
				//.custStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：消户）
				//.custStatusHis(RandomUtil.randomInt(0,1000+1))// 变更前用户状态（1：正常；2：暂停；3：消户）
				//.payType(RandomUtil.randomInt(0,1+1))// 收费方式（1：坐收；2：走收；3：代扣；4：托收）
				//.payTypeHis(RandomUtil.randomInt(0,1000+1))// 变更前收费方式（1：坐收；2：走收；3：代扣；4：托收）
				//.billType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				//.billTypeHis(RandomUtil.randomInt(0,1000+1))// 变更前开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				//.billName(TestCaseUtil.name())// 开票名称
				//.billNameHis(RandomUtil.randomString(4))// 变更前开票名称
				//.billTaxnum(RandomUtil.randomString(4))// 税号
				//.billTaxnumHis(RandomUtil.randomString(4))// 变更前税号
				//.billAddress(TestCaseUtil.address())// 开票地址
				//.billAddressHis(RandomUtil.randomString(4))// 变更前开票地址
				//.billTel(TestCaseUtil.tel())// 开票电话
				//.billTelHis(RandomUtil.randomString(4))// 变更前开票电话
				//.billBank(RandomUtil.randomString(4))// 银行名称
				//.billBankHis(RandomUtil.randomString(4))// 变更前银行名称
				//.billBankId(RandomUtil.randomString(4))// 开户行行号
				//.billBankIdHis(RandomUtil.randomString(4))// 变更前开户行行号
				//.billBankName(TestCaseUtil.bankName())// 开户行名称
				//.billBankNameHis(RandomUtil.randomString(4))// 变更前开户行名称
				//.billBankAccount(RandomUtil.randomString(4))// 开户行账号
				//.billBankAccountHis(RandomUtil.randomString(4))// 变更前开户行账号
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.patch("/cust-info-changes" + "/" + id).content(JSON.toJSONString(tenantInfo)) //
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

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/cust-info-changes" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
