package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("tenant_receivable_detail")
@ApiModel(value = "TenantReceivableDetail对象", description = "应收明细账")
public class TenantReceivableDetail implements Serializable {

	private static final long serialVersionUID = 5414610141528101126L;

	@ApiModelProperty(value = "应收明细账ID")
	@TableId(value = "id", type = IdType.AUTO)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "应收总账ID")
	@TableField("receivable_id")
	private String receivableId;

	@ApiModelProperty(value = "价格阶梯ID")
	@TableField("price_step_id")
	private String priceStepId;

	@ApiModelProperty(value = "应收水量")
	@TableField("receivable_waters")
	private BigDecimal receivableWaters;

	@ApiModelProperty(value = "欠费水量")
	@TableField("arrears_waters")
	private BigDecimal arrearsWaters;

	@ApiModelProperty(value = "费用项目ID")
	@TableField("price_item_id")
	private String priceItemId;

	@ApiModelProperty(value = "价格")
	@TableField("detail_price")
	private BigDecimal detailPrice;

	@ApiModelProperty(value = "应收金额")
	@TableField("receivable_money")
	private BigDecimal receivableMoney;

	@ApiModelProperty(value = "欠费金额")
	@TableField("arrears_money")
	private BigDecimal arrearsMoney;


}
