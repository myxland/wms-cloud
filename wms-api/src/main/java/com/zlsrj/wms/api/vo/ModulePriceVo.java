package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ModulePrice对象", description = "模块价格")
public class ModulePriceVo implements Serializable {

	private static final long serialVersionUID = 5506315411847413561L;

	@ApiModelProperty(value = "模块价格ID")
	private String id;

	@ApiModelProperty(value = "模块ID")
	private String moduleId;

	@ApiModelProperty(value = "模块ID")
	private String moduleName;

	@ApiModelProperty(value = "模块版本（1：基础版；2：高级版；3：旗舰版）")
	private Integer moduleEdition;

	@ApiModelProperty(value = "起始量")
	private Integer startNum;

	@ApiModelProperty(value = "终止量")
	private Integer endNum;

	@ApiModelProperty(value = "价格")
	private BigDecimal priceMoney;

}
