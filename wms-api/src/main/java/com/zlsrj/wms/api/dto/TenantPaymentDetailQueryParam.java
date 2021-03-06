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
@ApiModel(value = "TenantPaymentDetail查询参数", description = "实收明细账，记录本次所销账的欠费明细情况")
public class TenantPaymentDetailQueryParam implements Serializable {

	private static final long serialVersionUID = 1011151154210891078L;

	@ApiModelProperty(value = "实收明细账ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "实收总账ID")
	private Long paymentId;

	@ApiModelProperty(value = "对应的应收账时间")
	private Date receivableTime;

	@ApiModelProperty(value = "对应的应收账时间开始")
	private Date receivableTimeStart;

	@ApiModelProperty(value = "对应的应收账时间结束")
	private Date receivableTimeEnd;

	@ApiModelProperty(value = "应收总账ID")
	private Long receivableId;

	@ApiModelProperty(value = "应收明细账ID")
	private Long receivableDetailId;

	@ApiModelProperty(value = "阶梯号")
	private Integer stepNo;

	@ApiModelProperty(value = "收费水量")
	private BigDecimal payWaters;

	@ApiModelProperty(value = "价格分类ID")
	private Long priceTypeId;

	@ApiModelProperty(value = "费用项目ID")
	private Long priceItemId;

	@ApiModelProperty(value = "价格")
	private BigDecimal payPrice;

	@ApiModelProperty(value = "收费金额")
	private BigDecimal payMoney;

}

