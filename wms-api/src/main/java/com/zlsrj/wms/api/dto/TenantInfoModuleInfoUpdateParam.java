package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantInfoModuleInfo更新参数", description = "租户表")
public class TenantInfoModuleInfoUpdateParam implements Serializable {

	private static final long serialVersionUID = -5414979804072125894L;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "模块ID")
	private String moduleId;

	@ApiModelProperty(value = "开通版本（1基础版/2高级版/3旗舰版）")
	private Integer moduleEdition;

	@ApiModelProperty(value = "开通或关闭（1：开通，0：关闭）")
	private Integer moduleOnOff;
}

