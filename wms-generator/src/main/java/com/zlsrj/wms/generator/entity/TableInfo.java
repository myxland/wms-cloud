package com.zlsrj.wms.generator.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TableInfo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 5973596925991735103L;

	private String tableName;
	private String tableComment;

	private String tablePrefix;// * 表t_op_tenant_info中t_op_ 为表前缀 */

	private boolean viewable = true;/* 是否可以查看 */ 
	private boolean addable = true;/* 是否可以新增 */
	private boolean updatable = true;/* 是否可以编辑 */
	private boolean deletable = true;/* 是否可以删除 */
	private boolean selectable = false; /* 是否作为下拉列表数据 */

	// private String entityName;
	// private String entityShortName;/* t_op_tenant_info --> TenantInfo */
	// private String entityModuleName;/* t_op_tenant_info --> Op */

	// * t_op_tenant_info --> tenant_info */
	public String getTableShortName() {
		if (StringUtils.isNotEmpty(tablePrefix)) {
			return tableName.replaceFirst(tablePrefix, "");
		}

		return tableName;
	}

	// * t_op_tenant_info --> tenant_info --> TenantInfo */
	public String getEntityName() {
		StringBuilder builder = new StringBuilder();
		Arrays.asList(getTableShortName().split("_")).forEach(c -> {
			builder.append(c.substring(0, 1).toUpperCase()); // 首字母大写
			if (c.length() > 1) {
				builder.append(c.substring(1).toLowerCase());// 其余字母小写
			}
		});

		return builder.toString();
	}

	// * t_op_tenant_info --> tenant_info --> tenant-info */
	// RESTful API 应具备良好的可读性，当url中某一个片段（segment）由多个单词组成时，建议使用 - 来隔断单词，而不是使用 _
	public String getRestSegment() {
		return getTableShortName().replaceAll("_", "-");
	}

	private List<TableField> columnList = new ArrayList<TableField>();

	@JSONField(serialize = false)
	public List<TableField> getPrimaryKeyList() {
		return columnList.stream().filter(column -> "PRI".equals(column.getColumnKey())).collect(Collectors.toList());
	}

	@JSONField(serialize = false)
	public boolean isIncludeBigDecimal() {
		return columnList.stream().filter(column -> "decimal".equals(column.getDataType())).count() > 0;
	}

	@JSONField(serialize = false)
	public boolean isIncludeDate() {
		return columnList.stream().filter(column -> Arrays
				.asList(new String[] { "datetime", "timestamp", "time", "date" }).contains(column.getDataType()))
				.count() > 0;
	}

	@JSONField(serialize = false)
	public boolean isIncludeStatus() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("_status")).count() > 0;
	}

	@JSONField(serialize = false)
	public boolean isIncludeCompanyShortName() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("display_name")
				|| column.getColumnName().endsWith("tenant_name")).count() > 0;
	}

	@JSONField(serialize = false)
	public boolean isIncludeTenantId() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("tenant_id")).count() > 0;
	}
	
	/**
	 * 是否跟tenantInfo实体一对一关系
	 */
	@JSONField(serialize = false)
	public boolean isIncludeTenantOne2One() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("tenant_id")//
				&& "UNI".equals(column.getColumnKey())//
		).count() > 0;
	}

	/**
	 * 是否跟tenantInfo实体一对多关系
	 */
	@JSONField(serialize = false)
	public boolean isIncludeTenantOne2Many() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("tenant_id")//
				&& ("UNI".equals(column.getColumnKey()) == false)//
		).count() > 0;
	}

	@JSONField(serialize = false)
	public boolean isIncludeSysId() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("sys_id")).count() > 0;
	}
	
	/**
	 * 是否跟systemDesign实体一对一关系
	 */
	@JSONField(serialize = false)
	public boolean isIncludeSysOne2One() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("sys_id")//
				&& "UNI".equals(column.getColumnKey())//
		).count() > 0;
	}

	/**
	 * 是否跟systemDesign实体一对多关系
	 */
	@JSONField(serialize = false)
	public boolean isIncludeSysOne2Many() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("sys_id")//
				&& ("UNI".equals(column.getColumnKey()) == false)//
		).count() > 0;
	}
	
	@JSONField(serialize = false)
	public boolean isIncludeModuleId() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("module_id")).count() > 0;
	}
	
	/**
	 * 是否跟tenantModule实体一对一关系
	 */
	@JSONField(serialize = false)
	public boolean isIncludeModuleOne2One() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("module_id")//
				&& "UNI".equals(column.getColumnKey())//
		).count() > 0;
	}

	/**
	 * 是否跟tenantModule实体一对多关系
	 */
	@JSONField(serialize = false)
	public boolean isIncludeModuleOne2Many() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("module_id")//
				&& ("UNI".equals(column.getColumnKey()) == false)//
		).count() > 0;
	}

	@JSONField(serialize = false)
	public List<TableField> getSelectColumnList() {
		return columnList.stream().filter(
				// column -> column.getColumnName().endsWith("_type") ||
				// column.getColumnName().endsWith("_status"))
				column -> column.isSelectable()).collect(Collectors.toList());
	}

	@JSONField(serialize = false)
	public boolean isIncludeSingleUpdatable() {
		return columnList.stream().filter(column -> column.isSingleUpdatable()).count() > 0;
	}

	@JSONField(serialize = false)
	public List<TableField> getSingleUpdatableColumnList() {
		return columnList.stream().filter(column -> column.isSingleUpdatable()).collect(Collectors.toList());
	}

	@JSONField(serialize = false)
	public boolean isIncludeBatchUpdatable() {
		return columnList.stream().filter(column -> column.isBatchUpdatable()).count() > 0;
	}

	@JSONField(serialize = false)
	public List<TableField> getBatchUpdatableColumnList() {
		return columnList.stream().filter(column -> column.isBatchUpdatable()).collect(Collectors.toList());
	}

	@JSONField(serialize = false)
	public boolean isIncludeNotNullabe() {
		return columnList.stream()
				.filter(column -> "PRI".equals(column.getColumnKey()) == false
						&& StringUtils.isEmpty(column.getDefaultAddValue()) && "NO".equals(column.getIsNullable()))
				.count() > 0;
	}

	@JSONField(serialize = false)
	public List<TableField> getNotNullabeColumnList() {
		return columnList.stream()
				.filter(column -> "PRI".equals(column.getColumnKey()) == false
						&& StringUtils.isEmpty(column.getDefaultAddValue()) && "NO".equals(column.getIsNullable()))
				.collect(Collectors.toList());
	}
	
	@JSONField(serialize = false)
	public boolean isIncludeParentId() {
		return columnList.stream().filter(column -> column.getColumnName().endsWith("parent_id")).count() > 0;
	}
	
	@JSONField(serialize = false)
	public boolean isIncludeAggregation() {
		return columnList.stream().filter(column -> ("BigDecimal".equals(column.getPropertyType()))
				|| ("Integer".equals(column.getPropertyType()) && (column.getPropertyOptionList().size()==0))).count() > 0;
	}
	
	@JSONField(serialize = false)
	public List<TableField> getAggregationColumnList() {
		return columnList.stream()
				.filter(column -> ("BigDecimal".equals(column.getPropertyType()))
						|| ("Integer".equals(column.getPropertyType()) && (column.getPropertyOptionList().size()==0)))
				.collect(Collectors.toList());
	}
}
