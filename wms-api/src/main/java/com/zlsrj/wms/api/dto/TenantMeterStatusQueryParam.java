package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterStatus查询参数", description = "水表状况")
public class TenantMeterStatusQueryParam implements Serializable {

	private static final long serialVersionUID = 7103121541415246137L;

	@ApiModelProperty(value = "表况ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "表况名称")
	private String meterStatusName;

	@ApiModelProperty(value = "水量计算方式（1：自动计算；2：手工输入）")
	private Integer usenumCalcType;

	@ApiModelProperty(value = "生成工单类型（0：不生成；1：故障换表；3：周期换表）")
	private Integer workBillType;

	@ApiModelProperty(value = "创建方式（1：平台创建；2：客户自建）")
	private Integer createType;

}

