use ${databaseSchema};

CREATE TABLE `${table.tableName}` (
  <#list table.columnList as column>
  `${column.columnName}` <#if column.dataType=="bigint">bigint(20)<#elseif column.dataType=="int">int(11)<#elseif column.dataType=="varchar">${column.dataType}(${column.characterMaximumLength?c})<#elseif column.dataType=="datetime" || column.dataType=="date">${column.dataType}<#else>${column.dataType}</#if> <#if column.isNullable=="YES">DEFAULT NULL<#else>NOT NULL</#if> COMMENT '${column.columnComment}',
  </#list>
  PRIMARY KEY (<#list table.primaryKeyList as column>`${column.columnName}`<#if column_has_next>, </#if></#list>)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='${table.tableComment}';