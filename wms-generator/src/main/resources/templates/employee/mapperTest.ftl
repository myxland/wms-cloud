package ${domainName}.${projectName}.mapper;

<#if table.includeBigDecimal>
import java.math.BigDecimal;
</#if>
<#if table.includeDate>
import java.util.Date;
</#if>
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ${domainName}.common.test.TestCaseUtil;
import ${domainName}.${projectNameApi}.entity.${table.entityName};

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ${table.entityName}MapperTest {

	@Resource
	private ${table.entityName}Mapper ${table.entityName?uncap_first}Mapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		${table.entityName} ${table.entityName?uncap_first} = ${table.entityName?uncap_first}Mapper.selectById(id);
		log.info(${table.entityName?uncap_first}.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<${table.entityName}> queryWrapper = new QueryWrapper<${table.entityName}>();
		List<${table.entityName}> ${table.entityName?uncap_first}List = ${table.entityName?uncap_first}Mapper.selectList(queryWrapper);
		${table.entityName?uncap_first}List.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
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
				
		int count = ${table.entityName?uncap_first}Mapper.insert(${table.entityName?uncap_first});
		log.info("count={}",count);
		log.info("${table.entityName?uncap_first}={}",${table.entityName?uncap_first});
	}
	
}
