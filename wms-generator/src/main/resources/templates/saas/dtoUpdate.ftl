package ${domainName}.${projectNameApi}.dto;

import java.io.Serializable;
<#if table.includeBigDecimal>
import java.math.BigDecimal;
</#if>
<#if table.includeDate>
import java.util.Date;
</#if>

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "${table.entityName}更新参数", description = "${table.tableComment}")
public class ${table.entityName}UpdateParam implements Serializable {

	private static final long serialVersionUID = ${serialVersionUIDUpdateParam}L;

	<#list table.columnList as column>
	<#if "tenant_id" == column.columnName>
	<#else>
	@ApiModelProperty(value = "${column.columnComment}")
	private ${column.propertyType} ${column.propertyName};

	</#if>
	</#list>
}

