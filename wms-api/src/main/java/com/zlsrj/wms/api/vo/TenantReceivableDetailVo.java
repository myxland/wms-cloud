package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantReceivableDetail对象", description = "应收明细账")
public class TenantReceivableDetailVo implements Serializable {

	private static final long serialVersionUID = 1312391281121312013L;

	@ApiModelProperty(value = "应收明细账ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "应收总账ID")
	private String receivableId;

	@ApiModelProperty(value = "价格阶梯ID")
	private String priceStepId;

	@ApiModelProperty(value = "应收水量")
	private BigDecimal receivableWaters;

	@ApiModelProperty(value = "欠费水量")
	private BigDecimal arrearsWaters;

	@ApiModelProperty(value = "费用项目ID")
	private String priceItemId;

	@ApiModelProperty(value = "价格")
	private BigDecimal detailPrice;

	@ApiModelProperty(value = "应收金额")
	private BigDecimal receivableMoney;

	@ApiModelProperty(value = "欠费金额")
	private BigDecimal arrearsMoney;

}
