package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterType更新参数", description = "水表类型")
public class TenantMeterTypeUpdateParam implements Serializable {

	private static final long serialVersionUID = 3142111109614471196L;

	@ApiModelProperty(value = "水表类型ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "名称")
	private String meterTypeName;

	@ApiModelProperty(value = "结构化数据")
	private String meterTypeData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

