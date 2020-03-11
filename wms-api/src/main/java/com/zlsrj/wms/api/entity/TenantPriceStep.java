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
@TableName("tenant_price_step")
@ApiModel(value = "TenantPriceStep对象", description = "水价阶梯")
public class TenantPriceStep implements Serializable {

	private static final long serialVersionUID = 1113721127569712549L;

	@ApiModelProperty(value = "价格阶梯ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "价格类别ID")
	@TableField("price_type_id")
	private String priceTypeId;

	@ApiModelProperty(value = "费用项目ID")
	@TableField("price_item_id")
	private String priceItemId;

	@ApiModelProperty(value = "阶梯号")
	@TableField("step_no")
	private Integer stepNo;

	@ApiModelProperty(value = "起始量")
	@TableField("start_waters")
	private BigDecimal startWaters;

	@ApiModelProperty(value = "终止量")
	@TableField("end_waters")
	private BigDecimal endWaters;

	@ApiModelProperty(value = "价格")
	@TableField("step_price")
	private BigDecimal stepPrice;


}
