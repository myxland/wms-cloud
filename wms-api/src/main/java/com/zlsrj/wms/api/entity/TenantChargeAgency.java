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
@TableName("tenant_charge_agency")
@ApiModel(value = "TenantChargeAgency对象", description = "代收机构")
public class TenantChargeAgency implements Serializable {

	private static final long serialVersionUID = 1938714915150991451L;

	@ApiModelProperty(value = "代收机构ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "代收机构名称")
	@TableField("charge_agency_name")
	private String chargeAgencyName;

	@ApiModelProperty(value = "结构化数据")
	@TableField("charge_agency_data")
	private String chargeAgencyData;

	@ApiModelProperty(value = "创建类型（1：平台默认创建；2：租户自建）")
	@TableField("create_type")
	private Integer createType;

	@ApiModelProperty(value = "是否开放API（1：开放；0：不开放）")
	@TableField("api_on")
	private Integer apiOn;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}