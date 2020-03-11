package com.zlsrj.wms.api.vo;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantDept对象", description = "租户部门")
public class TenantDeptVo implements Serializable {

	private static final long serialVersionUID = 8312479813624475138L;

	@ApiModelProperty(value = "系统ID")
	private String id;

	@ApiModelProperty(value = "上级部门")
	private Long parentDeptId;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "部门名称")
	private String deptName;

}
