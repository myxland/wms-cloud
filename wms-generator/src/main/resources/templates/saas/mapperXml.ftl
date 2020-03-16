<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${domainName}.${projectName}.mapper.${table.entityName}Mapper">
	<#if table.includeAggregation>
	<select id="selectAggregation" resultType="${domainName}.${projectNameApi}.entity.${table.entityName}">
		SELECT
			count(id) as id,
			<#list table.aggregationColumnList as column>
			sum(${column.columnName}) as ${column.columnName}<#if column_has_next>,</#if>
			</#list>
		FROM ${table.tableName}
		${"$"}{ew.customSqlSegment} 
	</select>
	</#if>
</mapper>