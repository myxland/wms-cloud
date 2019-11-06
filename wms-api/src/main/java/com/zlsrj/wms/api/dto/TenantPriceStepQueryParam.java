package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceStep查询参数", description = "")
public class TenantPriceStepQueryParam implements Serializable {

	private static final long serialVersionUID = 2544711154145231311L;

	@ApiModelProperty(value = "系统ID")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "价格类别")
	private Long priceTypeId;

	@ApiModelProperty(value = "费用项目")
	private Long priceItemId;

	@ApiModelProperty(value = "阶梯号")
	private Long stepId;

	@ApiModelProperty(value = "起始量")
	private Integer startNum;

	@ApiModelProperty(value = "终止量")
	private Integer endNum;

	@ApiModelProperty(value = "价格")
	private BigDecimal price;

}

