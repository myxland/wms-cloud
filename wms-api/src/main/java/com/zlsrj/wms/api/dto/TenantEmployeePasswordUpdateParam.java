package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantEmployee密码更新参数", description = "租户员工")
public class TenantEmployeePasswordUpdateParam implements Serializable {

	private static final long serialVersionUID = -850035936037216762L;

	@ApiModelProperty(value = "旧密码")
	private String oldPassword;
	
	@ApiModelProperty(value = "新密码")
	private String newPassword;
	
	@ApiModelProperty(value = "确认密码")
	private String confirmPassword;
	
}

