package ${domainName}.${projectName}.service.impl;
<#if table.selectable>
import java.io.Serializable;
</#if>
<#if table.includeTenantOne2Many || table.includeModuleOne2Many>
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
</#if>

<#if table.includeTenantOne2One || table.includeTenantOne2Many || table.includeModuleOne2One || table.includeModuleOne2Many>
<#if table.includeBigDecimal>
import java.math.BigDecimal;

</#if>
</#if>
<#if table.selectable || table.includeTenantOne2One|| table.includeTenantOne2Many || table.includeModuleOne2One || table.includeModuleOne2Many>
import javax.annotation.Resource;

</#if>
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

<#if table.selectable>
import com.alibaba.fastjson.JSON;
</#if>
<#if table.selectable || table.includeAggregation>
import com.baomidou.mybatisplus.core.conditions.Wrapper;
</#if>
<#if table.selectable>
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
</#if>
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${domainName}.${projectNameApi}.dto.${table.entityName}AddParam;
import ${domainName}.${projectNameApi}.dto.${table.entityName}UpdateParam;
import ${domainName}.${projectNameApi}.entity.${table.entityName};
import ${domainName}.common.util.TranslateUtil;
import ${domainName}.${projectName}.mapper.${table.entityName}Mapper;
import ${domainName}.${projectName}.service.I${table.entityName}Service;
import ${domainName}.${projectName}.service.IIdService;
<#if table.selectable>
import ${domainName}.${projectName}.service.RedisService;
</#if>
<#if table.selectable || table.includeTenantOne2Many || table.includeModuleOne2Many>

import lombok.extern.slf4j.Slf4j;
</#if>

@Service
<#if table.selectable || table.includeTenantOne2Many || table.includeModuleOne2Many>
@Slf4j
</#if>
public class ${table.entityName}ServiceImpl extends ServiceImpl<${table.entityName}Mapper, ${table.entityName}> implements I${table.entityName}Service {
	@Resource
	private IIdService idService;
	<#if table.selectable>
	@Resource
	private RedisService<String, String> redisService;
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
	<#if table.includeAggregation>
	@Override
	public ${table.entityName} getAggregation(Wrapper<${table.entityName}> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
	</#if>
	@Override
	public String save(${table.entityName}AddParam ${table.entityName?uncap_first}AddParam) {
		${table.entityName} ${table.entityName?uncap_first} = TranslateUtil.translate(${table.entityName?uncap_first}AddParam,
				${table.entityName}.class);
		if (${table.entityName?uncap_first} != null && StringUtils.isBlank(${table.entityName?uncap_first}.getId())) {
			${table.entityName?uncap_first}.setId(idService.selectId());
		}
		this.save(${table.entityName?uncap_first});

		return ${table.entityName?uncap_first}.getId();
	}

	@Override
	public boolean updateById(${table.entityName}UpdateParam ${table.entityName?uncap_first}UpdateParam) {
		${table.entityName} ${table.entityName?uncap_first} = TranslateUtil.translate(${table.entityName?uncap_first}UpdateParam,
				${table.entityName}.class);

		return this.updateById(${table.entityName?uncap_first});
	}
	
}
