package com.zlsrj.wms.mbg.entity;

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
@TableName("t_op_tenant_price_step")
@ApiModel(value = "TenantPriceStep对象", description = "")
public class TenantPriceStep implements Serializable {

	private static final long serialVersionUID = 1113721127569712549L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private Long tenantId;

	@ApiModelProperty(value = "价格类别")
	@TableField("price_type_id")
	private Long priceTypeId;

	@ApiModelProperty(value = "费用项目")
	@TableField("price_item_id")
	private Long priceItemId;

	@ApiModelProperty(value = "阶梯号")
	@TableField("step_id")
	private Long stepId;

	@ApiModelProperty(value = "起始量")
	@TableField("start_num")
	private Integer startNum;

	@ApiModelProperty(value = "终止量")
	@TableField("end_num")
	private Integer endNum;

	@ApiModelProperty(value = "价格")
	@TableField("price")
	private BigDecimal price;

}
