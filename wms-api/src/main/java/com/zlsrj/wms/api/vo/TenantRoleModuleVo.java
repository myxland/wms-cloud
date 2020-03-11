package com.zlsrj.wms.api.vo;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantRoleModule对象", description = "角色模块")
public class TenantRoleModuleVo implements Serializable {

	private static final long serialVersionUID = 7851313106512854122L;

	@ApiModelProperty(value = "系统ID")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "角色编号")
	private Long roleId;

	@ApiModelProperty(value = "模块编号")
	private String moduleId;

	@ApiModelProperty(value = "模块名称")
	private String moduleName;

	@ApiModelProperty(value = "开放（1：开放；0：不开放）")
	private Integer roleModuleOn;

}
