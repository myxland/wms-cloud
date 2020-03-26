package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantInfo充值参数", description = "租户表")
public class TenantInfoRechargeParam implements Serializable {

	private static final long serialVersionUID = 5784121514767265731L;

	@ApiModelProperty(value = "租户ID")
	private String id;

	@ApiModelProperty(value = "充值余额")
	private BigDecimal rechargeMoney;

}

