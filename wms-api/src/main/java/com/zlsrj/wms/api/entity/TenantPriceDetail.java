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
@TableName("t_op_tenant_price_detail")
@ApiModel(value = "TenantPriceDetail对象", description = "")
public class TenantPriceDetail implements Serializable {

	private static final long serialVersionUID = 4946512119311115151L;

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

	@ApiModelProperty(value = "计算方法（固定单价/固定金额/阶梯价格）")
	@TableField("calc_type")
	private Integer calcType;

	@ApiModelProperty(value = "指定价格（金额）")
	@TableField("price")
	private BigDecimal price;

}
