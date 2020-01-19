package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantReceivableDetail查询参数", description = "应收明细账")
public class TenantReceivableDetailQueryParam implements Serializable {

	private static final long serialVersionUID = 1587121201310415321L;

	@ApiModelProperty(value = "应收明细账ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "应收总账ID")
	private Long receivableId;

	@ApiModelProperty(value = "价格阶梯ID")
	private Long priceStepId;

	@ApiModelProperty(value = "应收水量")
	private BigDecimal receivableWaters;

	@ApiModelProperty(value = "欠费水量")
	private BigDecimal arrearsWaters;

	@ApiModelProperty(value = "费用项目ID")
	private Long priceItemId;

	@ApiModelProperty(value = "价格")
	private BigDecimal detailPrice;

	@ApiModelProperty(value = "应收金额")
	private BigDecimal receivableMoney;

	@ApiModelProperty(value = "欠费金额")
	private BigDecimal arrearsMoney;

}

