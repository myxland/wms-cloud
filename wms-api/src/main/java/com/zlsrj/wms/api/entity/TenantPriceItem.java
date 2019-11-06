package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("t_op_tenant_price_item")
@ApiModel(value = "TenantPriceItem对象", description = "")
public class TenantPriceItem implements Serializable {

	private static final long serialVersionUID = 1261572142661071112L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private Long tenantId;

	@ApiModelProperty(value = "费用项目名称")
	@TableField("price_item_name")
	private String priceItemName;

	@ApiModelProperty(value = "税率")
	@TableField("tax_rate")
	private BigDecimal taxRate;

	@ApiModelProperty(value = "对应税控项目编号")
	@TableField("tax_id")
	private String taxId;

}
