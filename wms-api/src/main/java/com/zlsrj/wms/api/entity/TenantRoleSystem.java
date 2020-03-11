package com.zlsrj.wms.api.entity;

import java.io.Serializable;

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
@TableName("t_op_tenant_role_system")
@ApiModel(value = "TenantRoleSystem对象", description = "角色模块")
public class TenantRoleSystem implements Serializable {

	private static final long serialVersionUID = 4815153854610113109L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "角色编号")
	@TableField("role_id")
	private Long roleId;

	@ApiModelProperty(value = "模块编号")
	@TableField("sys_id")
	private Long sysId;

	@ApiModelProperty(value = "开放（1：开放；0：不开放）")
	@TableField("role_sys_on")
	private Integer roleSysOn;


}
