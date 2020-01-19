package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

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
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "实收总账ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long paymentId;

	@ApiModelProperty(value = "对应的应收账时间")
	private Date receivableTime;

	@ApiModelProperty(value = "应收总账ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long receivableId;

	@ApiModelProperty(value = "应收明细账ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long receivableDetailId;

	@ApiModelProperty(value = "阶梯号")
	private Integer stepNo;

	@ApiModelProperty(value = "收费水量")
	private BigDecimal payWaters;

	@ApiModelProperty(value = "价格分类ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeId;

	@ApiModelProperty(value = "费用项目ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceItemId;

	@ApiModelProperty(value = "价格")
	private BigDecimal payPrice;

	@ApiModelProperty(value = "收费金额")
	private BigDecimal payMoney;

}
