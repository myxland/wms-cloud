package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("t_op_tenant_price_step")
@ApiModel(value = "TenantPriceStep对象", description = "价格阶梯")
public class TenantPriceStep implements Serializable {

	private static final long serialVersionUID = 1113721127569712549L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "价格类别")
	@TableField("price_type_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeId;

	@ApiModelProperty(value = "费用项目")
	@TableField("price_item_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceItemId;

	@ApiModelProperty(value = "阶梯号")
	@TableField("step_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
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
