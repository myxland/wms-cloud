package ${domainName}.${projectNameWeb}.controller;

<#if table.includeBigDecimal>
import java.math.BigDecimal;
</#if>
<#if table.includeDate>
import java.util.Date;
</#if>

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
import ${domainName}.common.test.TestCaseUtil;
import ${domainName}.api.dto.${table.entityName}AddParam;
import ${domainName}.api.dto.${table.entityName}UpdateParam;

import cn.hutool.core.util.RandomUtil;
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
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/${table.entityName?uncap_first}/"+id)//
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
				MockMvcRequestBuilders.get("/${table.entityName?uncap_first}/list")//
						.params(params)
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void deleteTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.get("/${table.entityName?uncap_first}/delete/"+id)//
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void updateTest() throws Exception {
		String id = "";
		log.info("id={}",id);
		
		${table.entityName}UpdateParam ${table.entityName?uncap_first}UpdateParam = new ${table.entityName}UpdateParam();
		<#list table.columnList as column>
		<#if "id" == column.columnName>
		<#elseif column.columnName?ends_with("tenant_name")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.companyName(companyShortName));// ${column.columnComment}
		<#elseif column.columnName?ends_with("display_name")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(companyShortName);// ${column.columnComment}
		<#elseif column.columnName?ends_with("bank_name")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.bankName());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_name") || column.columnName?ends_with("_linkman")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.name());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_province")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.province());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_city")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.city());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_area")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.area());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_address") || column.columnName?ends_with("_add")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.address());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_mobile")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.mobile());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_tel")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.tel());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_email")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.email(null));// ${column.columnComment}
		<#elseif column.columnName?ends_with("_qq")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.qq());// ${column.columnComment}
		<#elseif column.columnName?ends_with("bank_no")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.bankNo());// ${column.columnComment}
		<#elseif column.columnName?ends_with("account_no")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// ${column.columnComment}
		<#elseif column.columnName?ends_with("_type") || column.columnName?ends_with("_status") || column.columnName?ends_with("_on")>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(RandomUtil.randomInt(0,1+1));// ${column.columnComment}
		<#elseif column.propertyType=="Date">
		<#if column.columnName?ends_with("add_time") || column.columnName?ends_with("update_time")>
		<#else>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(new Date());// ${column.columnComment}
		</#if>
		<#elseif column.propertyType=="BigDecimal">
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(new BigDecimal(0));// ${column.columnComment}
		<#elseif column.propertyType=="Long">
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(RandomUtil.randomLong());// ${column.columnComment}
		<#elseif column.propertyType=="Integer">
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(RandomUtil.randomInt(0,1000+1));// ${column.columnComment}
		<#else>
		${table.entityName?uncap_first}UpdateParam.set${column.propertyName?cap_first}(RandomUtil.randomString(4));// ${column.columnComment}
		</#if>
		</#list>
		
		log.info(JSON.toJSONString(${table.entityName?uncap_first}UpdateParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/${table.entityName?uncap_first}/update/"+id)//
						.content(JSON.toJSONString(${table.entityName?uncap_first}UpdateParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
	
	@Test
	public void createTest() throws Exception {
		${table.entityName}AddParam ${table.entityName?uncap_first}AddParam = new ${table.entityName}AddParam();
		<#list table.columnList as column>
		<#if "id" == column.columnName>
		<#elseif column.columnName?ends_with("tenant_name")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.companyName(companyShortName));// ${column.columnComment}
		<#elseif column.columnName?ends_with("display_name")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(companyShortName);// ${column.columnComment}
		<#elseif column.columnName?ends_with("bank_name")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.bankName());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_name") || column.columnName?ends_with("_linkman")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.name());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_province")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.province());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_city")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.city());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_area")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.area());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_address") || column.columnName?ends_with("_add")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.address());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_mobile")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.mobile());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_tel")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.tel());// ${column.columnComment}
		<#elseif column.columnName?ends_with("_email")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.email(null));// ${column.columnComment}
		<#elseif column.columnName?ends_with("_qq")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.qq());// ${column.columnComment}
		<#elseif column.columnName?ends_with("bank_no")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.bankNo());// ${column.columnComment}
		<#elseif column.columnName?ends_with("account_no")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(TestCaseUtil.bankCardNo(TestCaseUtil.bank()));// ${column.columnComment}
		<#elseif column.columnName?ends_with("_type") || column.columnName?ends_with("_status") || column.columnName?ends_with("_on")>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(RandomUtil.randomInt(0,1+1));// ${column.columnComment}
		<#elseif column.propertyType=="Date">
		<#if column.columnName?ends_with("add_time") || column.columnName?ends_with("update_time")>
		<#else>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(new Date());// ${column.columnComment}
		</#if>
		<#elseif column.propertyType=="BigDecimal">
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(new BigDecimal(0));// ${column.columnComment}
		<#elseif column.propertyType=="Long">
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(RandomUtil.randomLong());// ${column.columnComment}
		<#elseif column.propertyType=="Integer">
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(RandomUtil.randomInt(0,1000+1));// ${column.columnComment}
		<#else>
		${table.entityName?uncap_first}AddParam.set${column.propertyName?cap_first}(RandomUtil.randomString(4));// ${column.columnComment}
		</#if>
		</#list>
		
		log.info(JSON.toJSONString(${table.entityName?uncap_first}AddParam));
		
		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders.post("/${table.entityName?uncap_first}/create")//
						.content(JSON.toJSONString(${table.entityName?uncap_first}AddParam))//
						.contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的格式
						.accept(MediaType.APPLICATION_JSON_UTF8)//
		).andReturn().getResponse().getContentAsString();
		log.info(responseString);
	}
}
