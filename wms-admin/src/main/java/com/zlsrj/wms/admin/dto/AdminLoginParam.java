package com.zlsrj.wms.admin.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdminLoginParam {
	@ApiModelProperty(value = "用户名", required = true)
	@NotEmpty(message = "用户名不能为空")
	private String username;
	@ApiModelProperty(value = "密码", required = true)
	@NotEmpty(message = "密码不能为空")
	private String password;
}
