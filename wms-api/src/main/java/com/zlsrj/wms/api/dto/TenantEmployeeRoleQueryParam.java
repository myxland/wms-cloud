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

	private static final long serialVersionUID = 2464644152131313511L;

	@ApiModelProperty(value = "ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "员工ID")
	private String employeeId;

	@ApiModelProperty(value = "角色ID")
	private String roleId;

}

