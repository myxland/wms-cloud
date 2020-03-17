package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterCaliber新增参数", description = "水表口径字典表")
public class TenantMeterCaliberAddParam implements Serializable {

	private static final long serialVersionUID = 7310877512107101451L;

	@ApiModelProperty(value = "")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "名称")
	private String meterCaliberName;

	@ApiModelProperty(value = "结构化数据")
	private String meterCaliberData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

