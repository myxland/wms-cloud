package com.zlsrj.wms.api.entity;

import java.io.Serializable;

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
@TableName("t_op_tenant_price_type")
@ApiModel(value = "TenantPriceType对象", description = "价格类别")
public class TenantPriceType implements Serializable {

	private static final long serialVersionUID = 1081813151589313257L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "价格类别名称")
	@TableField("price_type_name")
	private String priceTypeName;

	@ApiModelProperty(value = "启用保底水量（1：启用；0：不启用）")
	@TableField("bottom_on")
	private Integer bottomOn;

	@ApiModelProperty(value = "保底水量")
	@TableField("bottom_num")
	private Integer bottomNum;

	@ApiModelProperty(value = "启用封顶水量（1：启用；0：不启用）")
	@TableField("top_on")
	private Integer topOn;

	@ApiModelProperty(value = "封顶水量")
	@TableField("top_num")
	private Integer topNum;

	@ApiModelProperty(value = "启用固定减免（1：启用；0：不启用）")
	@TableField("reduce_on")
	private Integer reduceOn;

	@ApiModelProperty(value = "固定减免水量")
	@TableField("reduce_num")
	private Integer reduceNum;

	@ApiModelProperty(value = "减免起始水量")
	@TableField("reduce_lower_limit")
	private Integer reduceLowerLimit;

	@ApiModelProperty(value = "启用固定水量征收（1：启用；0：不启用）")
	@TableField("fixed_on")
	private Integer fixedOn;

	@ApiModelProperty(value = "固定征收水量")
	@TableField("fixed_num")
	private Integer fixedNum;


}
