package com.zlsrj.wms.tenant.rest;

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
		Long id = 1L;
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

		// params.add("id",TestCaseUtil.id());// 租户编号
		// params.add("tenantName",TestCaseUtil.companyName(companyShortName));// 租户名称
		// params.add("displayName",companyShortName);// 显示名称
		// params.add("tenantProvince",TestCaseUtil.province());// 省
		// params.add("tenantCity",TestCaseUtil.city());// 市
		// params.add("tenantArea",TestCaseUtil.area());// 区县
		// params.add("tenantAddress",TestCaseUtil.address());// 联系地址
		// params.add("tenantLinkman",TestCaseUtil.name());// 联系人
		// params.add("tenantMobile",TestCaseUtil.mobile());// 手机号码
		// params.add("tenantTel",TestCaseUtil.tel());// 单位电话
		// params.add("tenantEmail",TestCaseUtil.email(null));// 邮箱
		// params.add("tenantQq",TestCaseUtil.qq());// QQ号码
		// params.add("tenantType",RandomUtil.randomInt(0,1+1));// 租户类型（1：使用单位；2：供应单位；3：内部运营）
		// params.add("tenantStatus",RandomUtil.randomInt(0,1+1));// 租户状态（1：正式；2：试用）
		// params.add("regTime",new Date());// 注册时间
		// params.add("endDate",new Date());// 到期日期

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
				.id(TestCaseUtil.id())// 租户编号
				.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
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
		Long id = 1L;
		String companyShortName = TestCaseUtil.companyShortName();


		TenantInfo tenantInfo = TenantInfo.builder()//
				//.id(TestCaseUtil.id())// 租户编号
				.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
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
		Long id = 1L;
		// String companyShortName = TestCaseUtil.companyShortName();


		TenantInfo tenantInfo = TenantInfo.builder()//
				//.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
				//.displayName(companyShortName)// 显示名称
				//.tenantProvince(TestCaseUtil.province())// 省
				//.tenantCity(TestCaseUtil.city())// 市
				//.tenantArea(TestCaseUtil.area())// 区县
				//.tenantAddress(TestCaseUtil.address())// 联系地址
				//.tenantLinkman(TestCaseUtil.name())// 联系人
				//.tenantMobile(TestCaseUtil.mobile())// 手机号码
				//.tenantTel(TestCaseUtil.tel())// 单位电话
				//.tenantEmail(TestCaseUtil.email(null))// 邮箱
				//.tenantQq(TestCaseUtil.qq())// QQ号码
				//.tenantType(RandomUtil.randomInt(0,1+1))// 租户类型（1：使用单位；2：供应单位；3：内部运营）
				//.tenantStatus(RandomUtil.randomInt(0,1+1))// 租户状态（1：正式；2：试用）
				//.regTime(new Date())// 注册时间
				//.endDate(new Date())// 到期日期
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
		Long id = 1L;

		String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/tenant-infos" + "/" + id)) //
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}
}
