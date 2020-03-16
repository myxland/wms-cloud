package com.zlsrj.wms.api.vo;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantRole对象", description = "角色信息")
public class TenantRoleVo implements Serializable {

	private static final long serialVersionUID = 1081181431513799315L;

	@ApiModelProperty(value = "工作岗位ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "工作岗位名称")
	private String roleName;

	@ApiModelProperty(value = "工作岗位说明")
	private String roleRemark;

	@ApiModelProperty(value = "创建类型（1：平台默认创建；2：租户自建）")
	private Integer createType;
	
}
