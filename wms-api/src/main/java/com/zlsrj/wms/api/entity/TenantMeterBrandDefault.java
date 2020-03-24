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
@TableName("tenant_meter_brand_default")
@ApiModel(value = "TenantMeterBrandDefault对象", description = "水表品牌")
public class TenantMeterBrandDefault implements Serializable {

	private static final long serialVersionUID = 1457925158861256121L;

	@ApiModelProperty(value = "名称")
	@TableField("meter_brand_name")
	private String meterBrandName;

	@ApiModelProperty(value = "结构化数据")
	@TableField("meter_brand_data")
	private String meterBrandData;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}