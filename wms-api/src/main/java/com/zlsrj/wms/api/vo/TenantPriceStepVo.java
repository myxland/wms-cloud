package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceStep对象", description = "水价阶梯")
public class TenantPriceStepVo implements Serializable {

	private static final long serialVersionUID = 1471110365931151053L;

	@ApiModelProperty(value = "价格阶梯ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "价格类别ID")
	private String priceTypeId;

	@ApiModelProperty(value = "费用项目ID")
	private String priceItemId;

	@ApiModelProperty(value = "阶梯号")
	private Integer stepNo;

	@ApiModelProperty(value = "起始量")
	private BigDecimal startWaters;

	@ApiModelProperty(value = "终止量")
	private BigDecimal endWaters;

	@ApiModelProperty(value = "价格")
	private BigDecimal stepPrice;

}
