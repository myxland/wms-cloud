package com.zlsrj.wms.api.dto;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantDepartment新增参数", description = "租户部门")
public class TenantDepartmentAddParam implements Serializable {

	private static final long serialVersionUID = 1611121823631115110L;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "部门名称")
	private String departmentName;

	@ApiModelProperty(value = "上级部门ID")
	private String departmentParentId;

}

