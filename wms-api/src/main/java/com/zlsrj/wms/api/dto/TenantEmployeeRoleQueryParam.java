package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantEmployeeRole查询参数", description = "员工角色")
public class TenantEmployeeRoleQueryParam implements Serializable {

	private static final long serialVersionUID = 1999714010106215320L;

	@ApiModelProperty(value = "系统ID")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "员工编号")
	private Long empId;

	@ApiModelProperty(value = "角色编号")
	private Long roleId;

	@ApiModelProperty(value = "开放（1：开放；0：不开放）")
	private Integer empRoleOn;

}

