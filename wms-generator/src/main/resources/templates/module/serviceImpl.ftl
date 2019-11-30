package ${domainName}.${projectName}.service.impl;
<#if table.selectable>
import java.io.Serializable;
</#if>
<#if table.includeTenantOne2Many>
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
</#if>

<#if table.includeTenantOne2One || table.includeTenantOne2Many>
<#if table.includeBigDecimal>
import java.math.BigDecimal;

</#if>
</#if>
<#if table.selectable || table.includeTenantOne2Many>
import javax.annotation.Resource;

</#if>
<#if table.includeTenantOne2One>
import javax.annotation.Resource;

</#if>
import org.springframework.stereotype.Service;

<#if table.selectable>
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
</#if>
<#if table.includeTenantOne2Many>
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
</#if>
<#if table.selectable>
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
</#if>
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${domainName}.${projectNameApi}.entity.${table.entityName};
<#if table.includeTenantOne2One || table.includeTenantOne2Many>
import ${domainName}.${projectNameApi}.entity.TenantInfo;
</#if>
<#if table.includeTenantOne2One || table.includeTenantOne2Many>
import ${domainName}.${projectNameApi}.enums.${table.entityName?replace("Tenant","")}Enum;
</#if>
import ${domainName}.${projectName}.mapper.${table.entityName}Mapper;
<#if table.includeTenantOne2One  || table.includeTenantOne2Many>
import ${domainName}.${projectName}.service.IIdService;
</#if>
import ${domainName}.${projectName}.service.I${table.entityName}Service;
<#if table.selectable>
import ${domainName}.${projectName}.service.RedisService;
</#if>
<#if table.selectable || table.includeTenantOne2Many>

import lombok.extern.slf4j.Slf4j;
</#if>

@Service
<#if table.selectable || table.includeTenantOne2Many>
@Slf4j
</#if>
public class ${table.entityName}ServiceImpl extends ServiceImpl<${table.entityName}Mapper, ${table.entityName}> implements I${table.entityName}Service {
	<#if table.selectable>
	@Resource
	private RedisService<String, String> redisService;
	</#if>
	<#if table.includeTenantOne2One || table.includeTenantOne2Many>
	@Resource
	private IIdService idService;
	</#if>

	<#if table.selectable>
	@Override
	public ${table.entityName} getById(Serializable id) {
		try {
			String jsonString = redisService.getValue(id.toString());
			if (StringUtils.isNotEmpty(jsonString)) {
				${table.entityName} ${table.entityName?uncap_first} = JSON.parseObject(jsonString, ${table.entityName}.class);
				return ${table.entityName?uncap_first};
			}
		} catch (Exception e) {
			// ex.printStackTrace();
			log.error("redis get value error", e);
		}

		${table.entityName} ${table.entityName?uncap_first} = baseMapper.selectById(id);
		if (${table.entityName?uncap_first} != null) {
			redisService.setValue(id.toString(), JSON.toJSONString(${table.entityName?uncap_first}));
		}

		return ${table.entityName?uncap_first};
	}

	@Override
	public boolean update(${table.entityName} entity, Wrapper<${table.entityName}> updateWrapper) {
		boolean success = super.update(entity, updateWrapper);
		if (success) {
			try {
				Long id = updateWrapper.getEntity().getId();
				redisService.remove(Long.toString(id));
			} catch(Exception e) {
				//ex.printStackTrace();
				log.error("redis remove error", e);
			}
		}
		return success;
	}

	@Override
	public boolean removeById(Serializable id) {
		boolean success = super.removeById(id);
		if (success) {
			try {
				redisService.remove(id.toString());
			} catch(Exception e) {
				//ex.printStackTrace();
				log.error("redis remove error", e);
			}
		}
		return success;
	}
	
	</#if>
	<#if table.includeTenantOne2One>
	@Override
	public boolean saveByTenantInfo(TenantInfo tenantInfo) {
		${table.entityName} ${table.entityName?uncap_first} = ${table.entityName}.builder()//
				<#list table.columnList as column>
				<#if "id" == column.columnName>
				.${column.propertyName}(idService.selectId())// ${column.columnComment}
				<#elseif column.columnName?ends_with("tenant_id")>
				.${column.propertyName}(tenantInfo.getId())// ${column.columnComment}
				<#elseif column.propertyType=="BigDecimal">
				.${column.propertyName}(BigDecimal.ZERO)// ${column.columnComment}
				<#elseif column.defaultAddValue?default("")?trim?length gt 1>
				<#if column.dataType=="date">
				.${column.propertyName}(DateUtil.beginOfDay(new Date()));
				<#elseif column.dataType=="datetime" || column.dataType=="timestamp" || column.dataType=="time">
				.${column.propertyName}(new Date());
				<#elseif column.propertyType=="Integer"|| column.propertyType=="Long" || column.propertyType=="BigDecimal" || column.propertyType=="Double" || column.propertyType=="Float">
				.${column.propertyName}(${column.defaultAddValue});
				<#else>
				.${column.propertyName}("${column.defaultAddValue}");
				</#if>
				<#else>
				.${column.propertyName}(null)// ${column.columnComment}
				</#if>
				</#list>
				.build();

		return super.save(${table.entityName?uncap_first});
	}
	
	</#if>
	<#if table.includeTenantOne2Many>
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<${table.entityName}> queryWrapper${table.entityName} = new QueryWrapper<${table.entityName}>();
		queryWrapper${table.entityName}.lambda()//
				.eq(${table.entityName}::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapper${table.entityName});
		if (count > 0) {
			log.error("根据租户信息初始化${table.tableComment}失败，${table.tableComment}已存在。");
			return false;
		}

		List<${table.entityName}> ${table.entityName?uncap_first}List = new ArrayList<${table.entityName}>();
		for (${table.entityName?replace("Tenant","")}Enum ${table.entityName?replace("Tenant","")?uncap_first}Enum : ${table.entityName?replace("Tenant","")}Enum.values()) {
			${table.entityName} ${table.entityName?uncap_first} = ${table.entityName}.builder()//
					<#list table.columnList as column>
					<#if "id" == column.columnName>
					.${column.propertyName}(idService.selectId())// ${column.columnComment}
					<#elseif column.columnName?ends_with("tenant_id")>
					.${column.propertyName}(tenantInfo.getId())// ${column.columnComment}
					<#elseif column.columnName?ends_with("_name")>
					.${column.propertyName}(${table.entityName?replace("Tenant","")?uncap_first}Enum.getText())// ${column.columnComment}
					<#elseif column.propertyType=="BigDecimal">
					.${column.propertyName}(BigDecimal.ZERO)// ${column.columnComment}
					<#elseif column.defaultAddValue?default("")?trim?length gt 1>
					<#if column.dataType=="date">
					.${column.propertyName}(DateUtil.beginOfDay(new Date()));
					<#elseif column.dataType=="datetime" || column.dataType=="timestamp" || column.dataType=="time">
					.${column.propertyName}(new Date());
					<#elseif column.propertyType=="Integer"|| column.propertyType=="Long" || column.propertyType=="BigDecimal" || column.propertyType=="Double" || column.propertyType=="Float">
					.${column.propertyName}(${column.defaultAddValue});
					<#else>
					.${column.propertyName}("${column.defaultAddValue}");
					</#if>
					<#else>
					.${column.propertyName}(null)// ${column.columnComment}
					</#if>
					</#list>
					.build();
			log.info(${table.entityName?uncap_first}.toString());
			${table.entityName?uncap_first}List.add(${table.entityName?uncap_first});
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info(${table.entityName?uncap_first}List.toString());
		return super.saveBatch(${table.entityName?uncap_first}List);
	}

	</#if>
}
