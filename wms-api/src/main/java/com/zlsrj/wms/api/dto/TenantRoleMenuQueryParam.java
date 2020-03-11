package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantRoleMenu查询参数", description = "角色菜单")
public class TenantRoleMenuQueryParam implements Serializable {

	private static final long serialVersionUID = 1421115110184410581L;

	@ApiModelProperty(value = "系统ID")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "角色编号")
	private Long roleId;

	@ApiModelProperty(value = "模块编号")
	private String moduleId;

	@ApiModelProperty(value = "菜单编号")
	private Long menuId;

	@ApiModelProperty(value = "开放（1：开放；0：不开放）")
	private Integer roleMenuOn;

}

