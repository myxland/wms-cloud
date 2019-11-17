package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "SystemPrice查询参数", description = "模块各版本价格定义表")
public class SystemPriceQueryParam implements Serializable {

	private static final long serialVersionUID = 5714205221521010136L;

	@ApiModelProperty(value = "系统ID")
	private Long id;

	@ApiModelProperty(value = "模块编号")
	private Long sysId;

	@ApiModelProperty(value = "模块版本（1：基础版；2：高级版；3：旗舰版）")
	private Integer sysEdition;

	@ApiModelProperty(value = "起始量")
	private Integer startNum;

	@ApiModelProperty(value = "终止量")
	private Integer endNum;

	@ApiModelProperty(value = "价格")
	private BigDecimal price;

}

