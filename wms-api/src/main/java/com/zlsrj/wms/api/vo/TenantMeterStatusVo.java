package com.zlsrj.wms.api.vo;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterStatus对象", description = "水表状况")
public class TenantMeterStatusVo implements Serializable {

	private static final long serialVersionUID = 8101129108555151002L;

	@ApiModelProperty(value = "表况ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "表况名称")
	private String meterStatusName;

	@ApiModelProperty(value = "水量计算方式（1：自动计算；2：手工输入）")
	private Integer usenumCalcType;

	@ApiModelProperty(value = "生成工单类型（0：不生成；1：故障换表；3：周期换表）")
	private Integer workBillType;

	@ApiModelProperty(value = "创建方式（1：平台创建；2：客户自建）")
	private Integer createType;

}
