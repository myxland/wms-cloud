package ${domainName}.${projectName}.controller;

import java.util.ArrayList;
import java.util.Arrays;
<#if table.includeBigDecimal>
import java.math.BigDecimal;
</#if>
<#if table.includeDate>
import java.util.Date;
</#if>
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

import ${domainName}.common.test.TestCaseUtil;
import ${domainName}.${projectNameApi}.entity.${table.entityName};

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ${table.entityName}ControllerTest {
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
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/${table.entityName?uncap_first}/select" + "/" + id)).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void listTest() throws Exception {
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/${table.entityName?uncap_first}/list")).andReturn()
				.getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void pageTest() throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("tenantType", Integer.toString(5));

		params.add("pageNum", Integer.toString(1));
		params.add("pageSize", Integer.toString(10));

		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/${table.entityName?uncap_first}/page").params(params))
				.andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}

	@Test
	public void insertTest() throws Exception {
		<#if table.includeCompanyShortName>
		String companyShortName = TestCaseUtil.companyShortName();

		</#if>
		${table.entityName} ${table.entityName?uncap_first} = ${table.entityName}.builder()//
				<#list table.columnList as column>
				<#if "id" == column.columnName>
				.${column.propertyName}(TestCaseUtil.id())// ${column.columnComment}
				<#elseif column.columnName?ends_with("tenant_name")>
				.${column.propertyName}(TestCaseUtil.companyName(companyShortName))// ${column.columnComment}
				<#elseif column.columnName?ends_with("display_name")>
				.${column.propertyName}(companyShortName)// ${column.columnComment}
				<#elseif column.columnName?ends_with("bank_name")>
				.${column.propertyName}(TestCaseUtil.bankName())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_name") || column.columnName?ends_with("_linkman")>
				.${column.propertyName}(TestCaseUtil.name())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_province")>
				.${column.propertyName}(TestCaseUtil.province())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_city")>
				.${column.propertyName}(TestCaseUtil.city())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_area")>
				.${column.propertyName}(TestCaseUtil.area())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_address") || column.columnName?ends_with("_add")>
				.${column.propertyName}(TestCaseUtil.address())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_mobile")>
				.${column.propertyName}(TestCaseUtil.mobile())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_tel")>
				.${column.propertyName}(TestCaseUtil.tel())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_email")>
				.${column.propertyName}(TestCaseUtil.email(null))// ${column.columnComment}
				<#elseif column.columnName?ends_with("_qq")>
				.${column.propertyName}(TestCaseUtil.qq())// ${column.columnComment}
				<#elseif column.columnName?ends_with("bank_no")>
				.${column.propertyName}(TestCaseUtil.bankNo())// ${column.columnComment}
				<#elseif column.columnName?ends_with("account_no")>
				.${column.propertyName}(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// ${column.columnComment}
				<#elseif column.columnName?ends_with("_type") || column.columnName?ends_with("_status") || column.columnName?ends_with("_on")>
				.${column.propertyName}(RandomUtil.randomInt(0,1+1))// ${column.columnComment}
				<#elseif column.propertyType=="Date">
				.${column.propertyName}(new Date())// ${column.columnComment}
				<#elseif column.propertyType=="BigDecimal">
				.${column.propertyName}(new BigDecimal(0))// ${column.columnComment}
				<#elseif column.propertyType=="Long">
				.${column.propertyName}(RandomUtil.randomLong())// ${column.columnComment}
				<#elseif column.propertyType=="Integer">
				.${column.propertyName}(RandomUtil.randomInt(0,1000+1))// ${column.columnComment}
				<#else>
				.${column.propertyName}(RandomUtil.randomString(4))// ${column.columnComment}
				</#if>
				</#list>
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/${table.entityName?uncap_first}/insert").content(JSONUtil.toJsonStr(${table.entityName?uncap_first})) // 请求的url,请求的方法是get
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updateTest() throws Exception {
		String id = "";
		<#if table.includeCompanyShortName>
		String companyShortName = TestCaseUtil.companyShortName();

		</#if>
		${table.entityName} ${table.entityName?uncap_first} = ${table.entityName}.builder()//
				.id(id)// 
				<#list table.columnList as column>
				<#if column.columnKey != "PRI">
				<#if "id" == column.columnName>
				.${column.propertyName}(TestCaseUtil.id())// ${column.columnComment}
				<#elseif column.columnName?ends_with("tenant_name")>
				.${column.propertyName}(TestCaseUtil.companyName(companyShortName))// ${column.columnComment}
				<#elseif column.columnName?ends_with("display_name")>
				.${column.propertyName}(companyShortName)// ${column.columnComment}
				<#elseif column.columnName?ends_with("bank_name")>
				.${column.propertyName}(TestCaseUtil.bankName())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_name") || column.columnName?ends_with("_linkman")>
				.${column.propertyName}(TestCaseUtil.name())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_province")>
				.${column.propertyName}(TestCaseUtil.province())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_city")>
				.${column.propertyName}(TestCaseUtil.city())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_area")>
				.${column.propertyName}(TestCaseUtil.area())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_address") || column.columnName?ends_with("_add")>
				.${column.propertyName}(TestCaseUtil.address())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_mobile")>
				.${column.propertyName}(TestCaseUtil.mobile())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_tel")>
				.${column.propertyName}(TestCaseUtil.tel())// ${column.columnComment}
				<#elseif column.columnName?ends_with("_email")>
				.${column.propertyName}(TestCaseUtil.email(null))// ${column.columnComment}
				<#elseif column.columnName?ends_with("_qq")>
				.${column.propertyName}(TestCaseUtil.qq())// ${column.columnComment}
				<#elseif column.columnName?ends_with("bank_no")>
				.${column.propertyName}(TestCaseUtil.bankNo())// ${column.columnComment}
				<#elseif column.columnName?ends_with("account_no")>
				.${column.propertyName}(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// ${column.columnComment}
				<#elseif column.columnName?ends_with("_type") || column.columnName?ends_with("_status") || column.columnName?ends_with("_on")>
				.${column.propertyName}(RandomUtil.randomInt(0,1+1))// ${column.columnComment}
				<#elseif column.propertyType=="Date">
				.${column.propertyName}(new Date())// ${column.columnComment}
				<#elseif column.propertyType=="BigDecimal">
				.${column.propertyName}(new BigDecimal(0))// ${column.columnComment}
				<#elseif column.propertyType=="Long">
				.${column.propertyName}(RandomUtil.randomLong())// ${column.columnComment}
				<#elseif column.propertyType=="Integer">
				.${column.propertyName}(RandomUtil.randomInt(0,1000+1))// ${column.columnComment}
				<#else>
				.${column.propertyName}(RandomUtil.randomString(4))// ${column.columnComment}
				</#if>
				</#if>
				</#list>
				.build();

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/${table.entityName?uncap_first}/update").content(JSONUtil.toJsonStr(${table.entityName?uncap_first})) // 请求的url,请求的方法是get
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void updateStatusTest() throws Exception {
		String id = "";
		Integer status = 0;

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.put("/${table.entityName?uncap_first}/update" + "/" + id + "/status" + "/" + status)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

	@Test
	public void deleteTest() throws Exception {
		String id = "";

		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.delete("/${table.entityName?uncap_first}/delete" + "/" + id)
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
				.perform(MockMvcRequestBuilders.delete("/${table.entityName?uncap_first}/delete/ids" + "/" + StringUtils.join(list, ','))
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符
		log.info(responseString);
	}

}
