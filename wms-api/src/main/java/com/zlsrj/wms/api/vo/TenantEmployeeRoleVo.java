package com.zlsrj.wms.api.vo;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantEmployeeRole对象", description = "用户角色")
public class TenantEmployeeRoleVo implements Serializable {

	private static final long serialVersionUID = 1441291036320431020L;

	@ApiModelProperty(value = "工作岗位ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "员工ID")
	private String employeeId;

	@ApiModelProperty(value = "角色ID")
	private String roleId;

}
