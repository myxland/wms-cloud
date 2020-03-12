package com.zlsrj.wms.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "登录请求参数", description = "登录请求参数")
public class AccountLoginParam {
	
	@ApiModelProperty(value = "登录方式（1账号密码，2微信扫码）")
	private String logintype;
	
	@ApiModelProperty(value = "登录名（账号、手机号、邮箱地址、个人微信号）")
	private String loginname;
	
	@ApiModelProperty(value = "登录密码")
	private String loginpassword;
}
