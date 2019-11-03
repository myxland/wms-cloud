package com.zlsrj.wms.mbg.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantRole查询参数", description = "租户角色")
public class TenantRoleQueryParam implements Serializable {

	private static final long serialVersionUID = 2464644152131313511L;

	@ApiModelProperty(value = "系统ID")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "角色名称")
	private String roleName;

	@ApiModelProperty(value = "角色说明")
	private String roleRemark;

}

