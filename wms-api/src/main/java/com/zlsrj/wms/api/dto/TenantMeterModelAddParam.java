package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterModel新增参数", description = "水表型号")
public class TenantMeterModelAddParam implements Serializable {

	private static final long serialVersionUID = 1911415657661514014L;

	@ApiModelProperty(value = "水表型号ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "名称")
	private String meterModelName;

	@ApiModelProperty(value = "结构化数据")
	private String meterModelData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

