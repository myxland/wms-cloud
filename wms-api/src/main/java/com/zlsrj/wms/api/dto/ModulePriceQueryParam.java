package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ModulePrice查询参数", description = "模块价格")
public class ModulePriceQueryParam implements Serializable {

	private static final long serialVersionUID = 1118125131211921200L;

	@ApiModelProperty(value = "模块价格ID")
	private Long id;

	@ApiModelProperty(value = "模块ID")
	private Long moduleId;

	@ApiModelProperty(value = "模块版本（1：基础版；2：高级版；3：旗舰版）")
	private Integer moduleEdition;

	@ApiModelProperty(value = "起始量")
	private Integer startNum;

	@ApiModelProperty(value = "终止量")
	private Integer endNum;

	@ApiModelProperty(value = "价格")
	private BigDecimal priceMoney;

}

