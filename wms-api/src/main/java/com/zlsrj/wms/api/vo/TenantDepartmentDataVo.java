package com.zlsrj.wms.api.vo;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantDepartment对象", description = "租户部门")
public class TenantDepartmentDataVo implements Serializable {

	private static final long serialVersionUID = 1151213112571588529L;

	@ApiModelProperty(value = "部门ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "部门名称")
	private String departmentName;

	@ApiModelProperty(value = "上级部门ID")
	private String departmentParentId;

	@ApiModelProperty(value = "部门路径")
	private String departmentPath;
}
