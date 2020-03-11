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
@TableName("t_cust_dev")
@ApiModel(value = "CustDev对象", description = "用户表具")
public class CustDev implements Serializable {

	private static final long serialVersionUID = 1413154711129351515L;

	@ApiModelProperty(value = "系统编号")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "表具编号")
	@TableField("dev_id")
	private String devId;

	@ApiModelProperty(value = "价格类别编号")
	@TableField("price_type_id")
	private String priceTypeId;

	@ApiModelProperty(value = "用水类别编号")
	@TableField("water_type_id")
	private String waterTypeId;

	@ApiModelProperty(value = "排序")
	@TableField("dev_order")
	private Integer devOrder;

	@ApiModelProperty(value = "混合类型（1：定量；2：比例）")
	@TableField("water_mix_type")
	private Integer waterMixType;

	@ApiModelProperty(value = "本价格用水量占比或定量")
	@TableField("water_scale")
	private BigDecimal waterScale;

	@ApiModelProperty(value = "水量计算方法（1：向上取整；2：向下取整；3：保留两位小数）")
	@TableField("water_calc_type")
	private Integer waterCalcType;


}
