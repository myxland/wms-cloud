package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@TableName("tenant_meter_price")
@ApiModel(value = "TenantMeterPrice对象", description = "水表计费")
public class TenantMeterPrice implements Serializable {

	private static final long serialVersionUID = 1015127021211371421L;

	@ApiModelProperty(value = "水表计费ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "水表ID")
	@TableField("meter_id")
	private String meterId;

	@ApiModelProperty(value = "水表编号")
	@TableField("meter_code")
	private String meterCode;

	@ApiModelProperty(value = "排序")
	@TableField("price_order")
	private Integer priceOrder;

	@ApiModelProperty(value = "水价列表ID")
	@TableField("price_id")
	private String priceId;

	@ApiModelProperty(value = "计费类别（1：定量；0：定比）")
	@TableField("price_type")
	private Integer priceType;

	@ApiModelProperty(value = "系数")
	@TableField("price_scale")
	private BigDecimal priceScale;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}