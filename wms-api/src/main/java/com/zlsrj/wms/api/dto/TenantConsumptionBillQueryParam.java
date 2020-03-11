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
@ApiModel(value = "TenantConsumptionBill查询参数", description = "租户账单")
public class TenantConsumptionBillQueryParam implements Serializable {

	private static final long serialVersionUID = 2261311341110798611L;

	@ApiModelProperty(value = "租户账单ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "账单类型（1：充值；2：消费）")
	private Integer consumptionBillType;

	@ApiModelProperty(value = "账单时间")
	private Date consumptionBillTime;

	@ApiModelProperty(value = "账单时间开始")
	private Date consumptionBillTimeStart;

	@ApiModelProperty(value = "账单时间结束")
	private Date consumptionBillTimeEnd;

	@ApiModelProperty(value = "账单名称[账户充值/短信平台/...]")
	private String consumptionBillName;

	@ApiModelProperty(value = "账单金额")
	private BigDecimal consumptionBillMoney;

	@ApiModelProperty(value = "租户账户余额")
	private BigDecimal tenantBalance;

	@ApiModelProperty(value = "备注")
	private String consumptionBillRemark;

}

