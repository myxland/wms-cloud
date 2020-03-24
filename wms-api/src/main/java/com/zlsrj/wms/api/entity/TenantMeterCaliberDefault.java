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
@TableName("tenant_meter_caliber_default")
@ApiModel(value = "TenantMeterCaliberDefault对象", description = "水表口径")
public class TenantMeterCaliberDefault implements Serializable {

	private static final long serialVersionUID = 1314341311111134127L;

	@ApiModelProperty(value = "名称")
	@TableField("meter_caliber_name")
	private String meterCaliberName;

	@ApiModelProperty(value = "结构化数据")
	@TableField("meter_caliber_data")
	private String meterCaliberData;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}