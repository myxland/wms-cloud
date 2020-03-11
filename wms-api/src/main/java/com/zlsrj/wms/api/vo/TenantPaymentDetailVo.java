package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPaymentDetail对象", description = "实收明细账，记录本次所销账的欠费明细情况")
public class TenantPaymentDetailVo implements Serializable {

	private static final long serialVersionUID = 1013151310147161614L;

	@ApiModelProperty(value = "实收明细账ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "实收总账ID")
	private String paymentId;

	@ApiModelProperty(value = "对应的应收账时间")
	private Date receivableTime;

	@ApiModelProperty(value = "应收总账ID")
	private String receivableId;

	@ApiModelProperty(value = "应收明细账ID")
	private String receivableDetailId;

	@ApiModelProperty(value = "阶梯号")
	private Integer stepNo;

	@ApiModelProperty(value = "收费水量")
	private BigDecimal payWaters;

	@ApiModelProperty(value = "价格分类ID")
	private String priceTypeId;

	@ApiModelProperty(value = "费用项目ID")
	private String priceItemId;

	@ApiModelProperty(value = "价格")
	private BigDecimal payPrice;

	@ApiModelProperty(value = "收费金额")
	private BigDecimal payMoney;

}
