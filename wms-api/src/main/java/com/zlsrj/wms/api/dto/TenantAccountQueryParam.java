package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantAccount查询参数", description = "租户账户")
public class TenantAccountQueryParam implements Serializable {

	private static final long serialVersionUID = 1413852148013633147L;

	@ApiModelProperty(value = "编号ID")
	private Long id;

	@ApiModelProperty(value = "租房编号")
	private Long tenantId;

	@ApiModelProperty(value = "账户余额")
	private BigDecimal accountBalance;

}

