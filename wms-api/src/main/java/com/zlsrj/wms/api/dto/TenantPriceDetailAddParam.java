package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceDetail新增参数", description = "水价明细")
public class TenantPriceDetailAddParam implements Serializable {

	private static final long serialVersionUID = 1911109532111161510L;

	@ApiModelProperty(value = "水价明细ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "水表列表ID")
	private String priceId;

	@ApiModelProperty(value = "费用项目")
	private String priceItemId;

	@ApiModelProperty(value = "计费规则")
	private Integer priceRule;

	@ApiModelProperty(value = "单价")
	private BigDecimal detailPrice;

	@ApiModelProperty(value = "阶梯明细")
	private List<TenantPriceStepAddParam> tenantPriceStepList;

}

