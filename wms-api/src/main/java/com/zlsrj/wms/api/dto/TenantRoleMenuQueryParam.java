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
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "角色编号")
	private Long roleId;

	@ApiModelProperty(value = "模块编号")
	private Long sysId;

	@ApiModelProperty(value = "菜单编号")
	private Long menuId;

	@ApiModelProperty(value = "开放（1开放，0不开放）")
	private Integer roleMenuOn;

}

