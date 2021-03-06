package ${domainName}.${projectNameApi}.vo;

import java.io.Serializable;
<#if table.includeBigDecimal>
import java.math.BigDecimal;
</#if>
<#if table.includeDate>
import java.util.Date;
</#if>

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "${table.entityName}对象", description = "${table.tableComment}")
public class ${table.entityName}Vo implements Serializable {

	private static final long serialVersionUID = ${serialVersionUIDVO}L;

	<#list table.columnList as column>
	@ApiModelProperty(value = "${column.columnComment}")
	<#if "bigint"==column.dataType>
	@JSONField(serializeUsing = ToStringSerializer.class)
	</#if>
	private ${column.propertyType} ${column.propertyName};

	<#if column.columnName?ends_with("tenant_id")>
	@ApiModelProperty(value = "${column.columnComment?replace("编号","名称")}")
	private String ${column.propertyName?replace("Id","Name")};

	</#if>
	<#if column.columnName?ends_with("sys_id")>
	@ApiModelProperty(value = "${column.columnComment?replace("编号","名称")}")
	private String ${column.propertyName?replace("sysId","moduleName")?replace("SysId","ModuleName")};

	</#if>
	</#list>
}
