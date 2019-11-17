package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantSystemPrice查询参数", description = "租户模块价格")
public class TenantSystemPriceQueryParam implements Serializable {

	private static final long serialVersionUID = 1310371468121331461L;

	@ApiModelProperty(value = "系统ID")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "模块编号")
	private Long sysId;

	@ApiModelProperty(value = "模块版本（0：基础版；1：高级版；2：旗舰版）")
	private Integer sysEdition;

	@ApiModelProperty(value = "起始量")
	private Integer startNum;

	@ApiModelProperty(value = "终止量")
	private Integer endNum;

	@ApiModelProperty(value = "价格")
	private BigDecimal price;

}

