package ${domainName}.${projectName}.mapper;

<#if table.includeAggregation>
import org.apache.ibatis.annotations.Param;

</#if>
<#if table.includeAggregation>
import com.baomidou.mybatisplus.core.conditions.Wrapper;
</#if>
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
<#if table.includeAggregation>
import com.baomidou.mybatisplus.core.toolkit.Constants;
</#if>
import ${domainName}.${projectNameApi}.entity.${table.entityName};

public interface ${table.entityName}Mapper extends BaseMapper<${table.entityName}> {
	${table.entityName} selectAggregation (@Param(Constants.WRAPPER) Wrapper<${table.entityName}> wrapper);
}
