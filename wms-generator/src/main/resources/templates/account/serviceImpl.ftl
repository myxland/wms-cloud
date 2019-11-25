package ${domainName}.${projectName}.service.impl;
<#if table.selectable>
import java.io.Serializable;

</#if>
<#if table.selectable>
import org.springframework.beans.factory.annotation.Autowired;
</#if>
import org.springframework.stereotype.Service;

<#if table.selectable>
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
</#if>
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${domainName}.${projectNameApi}.entity.${table.entityName};
import ${domainName}.${projectName}.mapper.${table.entityName}Mapper;
import ${domainName}.${projectName}.service.I${table.entityName}Service;
<#if table.selectable>
import ${domainName}.${projectName}.service.RedisService;
</#if>
<#if table.selectable>

import lombok.extern.slf4j.Slf4j;
</#if>

@Service
<#if table.selectable>
@Slf4j
</#if>
public class ${table.entityName}ServiceImpl extends ServiceImpl<${table.entityName}Mapper, ${table.entityName}> implements I${table.entityName}Service {
	<#if table.selectable>

	@Autowired
	private RedisService<String, String> redisService;

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
}
