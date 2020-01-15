package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantRole查询参数", description = "角色信息")
public class TenantRoleQueryParam implements Serializable {

	private static final long serialVersionUID = 2464644152131313511L;

	@ApiModelProperty(value = "工作岗位ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "工作岗位名称")
	private String roleName;

	@ApiModelProperty(value = "工作岗位说明")
	private String roleRemark;

	@ApiModelProperty(value = "创建类型（1：平台默认创建；2：租户自建）")
	private Integer createType;

}

