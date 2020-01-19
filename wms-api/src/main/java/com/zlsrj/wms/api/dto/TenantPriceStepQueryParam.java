package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceStep查询参数", description = "水价阶梯")
public class TenantPriceStepQueryParam implements Serializable {

	private static final long serialVersionUID = 2544711154145231311L;

	@ApiModelProperty(value = "价格阶梯ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "价格类别ID")
	private Long priceTypeId;

	@ApiModelProperty(value = "费用项目ID")
	private Long priceItemId;

	@ApiModelProperty(value = "阶梯号")
	private Integer stepNo;

	@ApiModelProperty(value = "起始量")
	private BigDecimal startWaters;

	@ApiModelProperty(value = "终止量")
	private BigDecimal endWaters;

	@ApiModelProperty(value = "价格")
	private BigDecimal stepPrice;

}

