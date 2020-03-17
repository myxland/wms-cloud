package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterReadSituation新增参数", description = "抄表表况")
public class TenantMeterReadSituationAddParam implements Serializable {

	private static final long serialVersionUID = 1023345057151516312L;

	@ApiModelProperty(value = "抄表表况ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "名称")
	private String readSituationName;

	@ApiModelProperty(value = "结构化数据")
	private String readSituationData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

