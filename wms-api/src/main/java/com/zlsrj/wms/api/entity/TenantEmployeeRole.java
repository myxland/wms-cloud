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
@TableName("t_op_tenant_employee_role")
@ApiModel(value = "TenantEmployeeRole对象", description = "员工角色")
public class TenantEmployeeRole implements Serializable {

	private static final long serialVersionUID = 1487131351102141063L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "员工编号")
	@TableField("emp_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long empId;

	@ApiModelProperty(value = "角色编号")
	@TableField("role_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long roleId;

	@ApiModelProperty(value = "开放（1：开放；0：不开放）")
	@TableField("emp_role_on")
	private Integer empRoleOn;


}
