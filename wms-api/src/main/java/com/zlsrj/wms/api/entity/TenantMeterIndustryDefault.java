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
@TableName("tenant_meter_industry_default")
@ApiModel(value = "TenantMeterIndustryDefault对象", description = "行业分类")
public class TenantMeterIndustryDefault implements Serializable {

	private static final long serialVersionUID = 5614102419120010151L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "名称")
	@TableField("meter_industry_name")
	private String meterIndustryName;

	@ApiModelProperty(value = "父级ID")
	@TableField("meter_industry_parent_id")
	private String meterIndustryParentId;

	@ApiModelProperty(value = "结构化数据")
	@TableField("meter_industry_data")
	private String meterIndustryData;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}