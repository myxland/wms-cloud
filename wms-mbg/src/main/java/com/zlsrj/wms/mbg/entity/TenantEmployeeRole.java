package com.zlsrj.wms.mbg.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("t_op_tenant_employee_role")
@ApiModel(value = "TenantEmployeeRole对象", description = "员工角色")
public class TenantEmployeeRole implements Serializable {

	private static final long serialVersionUID = 1487131351102141063L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private Long tenantId;

	@ApiModelProperty(value = "员工编号")
	@TableField("emp_id")
	private Long empId;

	@ApiModelProperty(value = "角色编号")
	@TableField("role_id")
	private Long roleId;

	@ApiModelProperty(value = "开放（1开放，0不开放）")
	@TableField("emp_role_on")
	private Integer empRoleOn;

}
