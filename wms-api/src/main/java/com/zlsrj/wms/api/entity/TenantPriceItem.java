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
@TableName("tenant_price_item")
@ApiModel(value = "TenantPriceItem对象", description = "费用项目")
public class TenantPriceItem implements Serializable {

	private static final long serialVersionUID = 1261572142661071112L;

	@ApiModelProperty(value = "费用项目ID")
	@TableId(value = "id", type = IdType.AUTO)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "费用项目名称")
	@TableField("price_item_name")
	private String priceItemName;

	@ApiModelProperty(value = "税率")
	@TableField("price_item_tax_rate")
	private BigDecimal priceItemTaxRate;

	@ApiModelProperty(value = "对应税控项目编号")
	@TableField("price_item_tax_id")
	private String priceItemTaxId;


}
