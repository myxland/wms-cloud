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
@TableName("tenant_meter_marketing_area")
@ApiModel(value = "TenantMeterMarketingArea对象", description = "营销机构")
public class TenantMeterMarketingArea implements Serializable {

	private static final long serialVersionUID = 1271211132101312231L;

	@ApiModelProperty(value = "营销机构ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "区域类型（0：内部机构；1：代收机构）")
	@TableField("marketing_area_type")
	private Integer marketingAreaType;

	@ApiModelProperty(value = "名称")
	@TableField("marketing_area_name")
	private String marketingAreaName;

	@ApiModelProperty(value = "父级ID")
	@TableField("marketing_area_parent_id")
	private String marketingAreaParentId;

	@ApiModelProperty(value = "结构化数据")
	@TableField("marketing_area_data")
	private String marketingAreaData;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}