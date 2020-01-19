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
<#if table.includeTenantId>
import ${domainName}.${projectNameApi}.entity.TenantInfo;
<#list table.columnList as column>
<#if column.refEntityName?default("")?trim?length gt 1>
import ${domainName}.${projectNameApi}.entity.${column.refEntityName};
</#if>
</#list>
</#if>

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ${table.entityName}MapperTest {

	@Resource
	private ${table.entityName}Mapper ${table.entityName?uncap_first}Mapper;
	<#if table.includeTenantId>
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	<#list table.columnList as column>
	<#if column.refEntityName?default("")?trim?length gt 1>
	@Resource
	private ${column.refEntityName}Mapper ${column.refEntityName?uncap_first}Mapper;
	</#if>
	</#list>
	</#if>
	
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
		<#if table.includeTenantId>
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		</#if>
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			<#if table.includeTenantId>
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			<#list table.columnList as column>
			<#if column.refEntityName?default("")?trim?length gt 1>
			${column.refEntityName} ${column.refEntityName?uncap_first} = null;
			List<${column.refEntityName}> ${column.refEntityName?uncap_first}List = ${column.refEntityName?uncap_first}Mapper.selectList(new QueryWrapper<${column.refEntityName}>().lambda().eq(${column.refEntityName}::getTenantId, tenantInfo.getId()));
			if(${column.refEntityName?uncap_first}List!=null && ${column.refEntityName?uncap_first}List.size()>0) {
				${column.refEntityName?uncap_first} = ${column.refEntityName?uncap_first}List.get(RandomUtil.randomInt(${column.refEntityName?uncap_first}List.size()));
			}
			
			</#if>
			</#list>
			</#if>
			<#if table.includeCompanyShortName>
			String companyShortName = TestCaseUtil.companyShortName();
	
			</#if>
			${table.entityName} ${table.entityName?uncap_first} = ${table.entityName}.builder()//
					<#list table.columnList as column>
					<#if "id" == column.columnName>
					.${column.propertyName}(TestCaseUtil.id())// ${column.columnComment}
					<#elseif column.columnName?ends_with("tenant_id")>
					.${column.propertyName}(tenantInfo.getId())// ${column.columnComment}
					<#elseif column.columnName?ends_with("_id")>
					<#if column.refEntityName?default("")?trim?length gt 1>
					.${column.propertyName}(${column.refEntityName?uncap_first}!=null?${column.refEntityName?uncap_first}.getId():null)// ${column.columnComment}
					<#else>
					.${column.propertyName}(RandomUtil.randomLong())// ${column.columnComment}
					</#if>
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
					<#elseif column.columnName?ends_with("_type") || column.columnName?ends_with("_status") || column.columnName?ends_with("_on") || column.columnName?ends_with("_channels") || column.columnName?ends_with("_method")>
					.${column.propertyName}(RandomUtil.randomInt(${column.propertyOptionList[0].value},${column.propertyOptionList[column.propertyOptionList?size-1].value}+1))// ${column.columnComment}
					<#elseif column.propertyType=="Date">
					.${column.propertyName}(TestCaseUtil.dateBefore())// ${column.columnComment}
					<#elseif column.propertyType=="BigDecimal">
					<#if column.columnName?ends_with("_money") || column.columnName?index_of("_money") gt 0 || column.columnName?ends_with("_price")>
					.${column.propertyName}(TestCaseUtil.money())// ${column.columnComment}
					<#elseif column.columnName?ends_with("_rate") || column.columnName?ends_with("_percent")>
					.${column.propertyName}(TestCaseUtil.rate())// ${column.columnComment}
					<#elseif column.columnName?ends_with("_water") || column.columnName?ends_with("_waters") || column.columnName?ends_with("_code")>
					.${column.propertyName}(TestCaseUtil.water())// ${column.columnComment}
					<#else>
					.${column.propertyName}(new BigDecimal(0))// ${column.columnComment}
					</#if>
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
	
}
