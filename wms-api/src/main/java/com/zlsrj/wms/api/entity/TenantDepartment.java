package com.zlsrj.wms.api.entity;

import java.io.Serializable;

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
@TableName("tenant_department")
@ApiModel(value = "TenantDepartment对象", description = "租户部门")
public class TenantDepartment implements Serializable {

	private static final long serialVersionUID = 1110411514104661089L;

	@ApiModelProperty(value = "部门ID")
	@TableId(value = "id", type = IdType.AUTO)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "部门名称")
	@TableField("department_name")
	private String departmentName;

	@ApiModelProperty(value = "上级部门ID")
	@TableField("department_parent_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long departmentParentId;


}
