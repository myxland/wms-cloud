package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeter批量更新参数", description = "水表信息")
public class TenantMeterBatchUpdateParam implements Serializable {

	private static final long serialVersionUID = -3414488740821495791L;

	@ApiModelProperty(value = "水表ID")
	private String[] id;

	@ApiModelProperty(value = "表册ID")
	private String[] meterBookId;

	@ApiModelProperty(value = "抄表顺序")
	private Integer[] meterReadOrder;

}

