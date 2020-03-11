package com.zlsrj.wms.api.entity;

import java.io.Serializable;

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
@TableName("tenant_water_area")
@ApiModel(value = "TenantWaterArea对象", description = "供水区域")
public class TenantWaterArea implements Serializable {

	private static final long serialVersionUID = 1389972413109411232L;

	@ApiModelProperty(value = "供水区域ID")
	@TableId(value = "id", type = IdType.AUTO)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "供水区域名称")
	@TableField("water_area_name")
	private String waterAreaName;

	@ApiModelProperty(value = "上级供水区域ID")
	@TableField("water_area_parent_id")
	private String waterAreaParentId;


}
