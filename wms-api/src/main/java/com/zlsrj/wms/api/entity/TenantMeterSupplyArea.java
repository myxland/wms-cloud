package com.zlsrj.wms.api.entity;

import java.io.Serializable;
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
@TableName("tenant_meter_supply_area")
@ApiModel(value = "TenantMeterSupplyArea对象", description = "供水区域")
public class TenantMeterSupplyArea implements Serializable {

	private static final long serialVersionUID = 1206149131020105571L;

	@ApiModelProperty(value = "供水区域ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "名称")
	@TableField("supply_area_name")
	private String supplyAreaName;

	@ApiModelProperty(value = "父级ID")
	@TableField("supply_area_parent_id")
	private String supplyAreaParentId;

	@ApiModelProperty(value = "结构化数据")
	@TableField("supply_area_data")
	private String supplyAreaData;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}