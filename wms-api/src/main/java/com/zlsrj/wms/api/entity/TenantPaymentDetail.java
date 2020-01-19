package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("tenant_payment_detail")
@ApiModel(value = "TenantPaymentDetail对象", description = "实收明细账，记录本次所销账的欠费明细情况")
public class TenantPaymentDetail implements Serializable {

	private static final long serialVersionUID = 1381406321281100111L;

	@ApiModelProperty(value = "实收明细账ID")
	@TableId(value = "id", type = IdType.AUTO)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "实收总账ID")
	@TableField("payment_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long paymentId;

	@ApiModelProperty(value = "对应的应收账时间")
	@TableField("receivable_time")
	private Date receivableTime;

	@ApiModelProperty(value = "应收总账ID")
	@TableField("receivable_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long receivableId;

	@ApiModelProperty(value = "应收明细账ID")
	@TableField("receivable_detail_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long receivableDetailId;

	@ApiModelProperty(value = "阶梯号")
	@TableField("step_no")
	private Integer stepNo;

	@ApiModelProperty(value = "收费水量")
	@TableField("pay_waters")
	private BigDecimal payWaters;

	@ApiModelProperty(value = "价格分类ID")
	@TableField("price_type_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeId;

	@ApiModelProperty(value = "费用项目ID")
	@TableField("price_item_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceItemId;

	@ApiModelProperty(value = "价格")
	@TableField("pay_price")
	private BigDecimal payPrice;

	@ApiModelProperty(value = "收费金额")
	@TableField("pay_money")
	private BigDecimal payMoney;


}
