package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantChargeAgency查询参数", description = "代收机构")
public class TenantChargeAgencyQueryParam implements Serializable {

	private static final long serialVersionUID = 1245101441570131063L;

	@ApiModelProperty(value = "代收机构ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "代收机构名称")
	private String chargeAgencyName;

	@ApiModelProperty(value = "结构化数据")
	private String chargeAgencyData;

	@ApiModelProperty(value = "创建类型（1：平台默认创建；2：租户自建）")
	private Integer createType;

	@ApiModelProperty(value = "是否开放API（1：开放；0：不开放）")
	private Integer apiOn;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据新增时间开始")
	private Date addTimeStart;

	@ApiModelProperty(value = "数据新增时间结束")
	private Date addTimeEnd;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "数据修改时间开始")
	private Date updateTimeStart;

	@ApiModelProperty(value = "数据修改时间结束")
	private Date updateTimeEnd;

}

