package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantAccount对象", description = "租户账户")
public class TenantAccountVo implements Serializable {

	private static final long serialVersionUID = 1045127991569099101L;

	@ApiModelProperty(value = "编号ID")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "账户余额")
	private BigDecimal accountBalance;

}
