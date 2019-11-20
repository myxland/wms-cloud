package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceDetail查询参数", description = "价格明细")
public class TenantPriceDetailQueryParam implements Serializable {

	private static final long serialVersionUID = 1911109532111161510L;

	@ApiModelProperty(value = "系统ID")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "价格类别")
	private Long priceTypeId;

	@ApiModelProperty(value = "费用项目")
	private Long priceItemId;

	@ApiModelProperty(value = "计算方法（1：固定单价；2：固定金额；3：阶梯价格）")
	private Integer calcType;

	@ApiModelProperty(value = "指定价格（金额）")
	private BigDecimal price;

}

