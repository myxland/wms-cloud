package ${domainName}.${projectName}.service;

<#if table.includeAggregation>
import com.baomidou.mybatisplus.core.conditions.Wrapper;
</#if>
import com.baomidou.mybatisplus.extension.service.IService;
import ${domainName}.${projectNameApi}.dto.${table.entityName}AddParam;
import ${domainName}.${projectNameApi}.dto.${table.entityName}UpdateParam;
import ${domainName}.${projectNameApi}.entity.${table.entityName};

public interface I${table.entityName}Service extends IService<${table.entityName}> {
	<#if table.includeAggregation>
	${table.entityName} getAggregation(Wrapper<${table.entityName}> wrapper);
	
	</#if>
	String save(${table.entityName}AddParam ${table.entityName?uncap_first}AddParam);

	boolean updateById(${table.entityName}UpdateParam ${table.entityName?uncap_first}UpdateParam);

}