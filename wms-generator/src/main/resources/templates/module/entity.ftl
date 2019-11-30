package ${domainName}.${projectNameApi}.entity;

import java.io.Serializable;
<#if table.includeBigDecimal>
import java.math.BigDecimal;
</#if>
<#if table.includeDate>
import java.util.Date;
</#if>

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("${table.tableName}")
@ApiModel(value = "${table.entityName}对象", description = "${table.tableComment}")
public class ${table.entityName} implements Serializable {

	private static final long serialVersionUID = ${serialVersionUID}L;

	<#list table.columnList as column>
	@ApiModelProperty(value = "${column.columnComment}")
	<#if "PRI"==column.columnKey>
	<#if "auto_increment"==column.extra>
	@TableId(value = "${column.columnName}", type = IdType.AUTO)
	<#else>
	@TableId(value = "${column.columnName}", type = IdType.INPUT)
	</#if>
	<#else>
	@TableField("${column.columnName}")
	</#if>
	<#if "bigint"==column.dataType>
	@JSONField(serializeUsing = ToStringSerializer.class)
	</#if>
	private ${column.propertyType} ${column.propertyName};

	</#list>

}
