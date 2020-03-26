package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantInfo充值返回对象", description = "租户表")
public class TenantInfoRechargeVo implements Serializable {

	private static final long serialVersionUID = 8631864761322896998L;

	@ApiModelProperty(value = "租户ID")
	private String id;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "账户余额")
	private BigDecimal tenantBalance;

	@ApiModelProperty(value = "手机号码")
	private String tenantLinkmanMobile;

}
