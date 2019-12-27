package ${domainName}.${projectName}.service;

<#if table.includeBigDecimal>
import java.math.BigDecimal;
</#if>
<#if table.includeDate>
import java.util.Date;
</#if>

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ${domainName}.common.test.TestCaseUtil;
import ${domainName}.${projectNameApi}.entity.${table.entityName};

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class I${table.entityName}ServiceTest {

	@Autowired
	private I${table.entityName}Service ${table.entityName?uncap_first}Service;

	@Test
	public void insertTest() {
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

		log.info(ToStringBuilder.reflectionToString(${table.entityName?uncap_first}, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = ${table.entityName?uncap_first}Service.save(${table.entityName?uncap_first});

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		Long id = 1L;

		<#if table.includeCompanyShortName>
		String companyShortName = TestCaseUtil.companyShortName();

		</#if>
		${table.entityName} ${table.entityName?uncap_first} = ${table.entityName}.builder()//
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
		${table.entityName?uncap_first}.setId(id);

		log.info(ToStringBuilder.reflectionToString(${table.entityName?uncap_first}, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = ${table.entityName?uncap_first}Service.updateById(${table.entityName?uncap_first});

		log.info(Boolean.toString(success));
	}
}
