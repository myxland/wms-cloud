package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceType对象", description = "水价分类")
public class TenantPriceTypeVo implements Serializable {

	private static final long serialVersionUID = 3147913149142125141L;

	@ApiModelProperty(value = "价格类别ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "价格类别名称")
	private String priceTypeName;

	@ApiModelProperty(value = "启用保底水量（1：启用；0：不启用）")
	private Integer bottomOn;

	@ApiModelProperty(value = "保底水量")
	private BigDecimal bottomWaters;

	@ApiModelProperty(value = "启用封顶水量（1：启用；0：不启用）")
	private Integer topOn;

	@ApiModelProperty(value = "封顶水量")
	private BigDecimal topWaters;

	@ApiModelProperty(value = "启用固定减免（1：启用；0：不启用）")
	private Integer reduceOn;

	@ApiModelProperty(value = "固定减免水量")
	private BigDecimal reduceWaters;

	@ApiModelProperty(value = "固定减免水量下限")
	private BigDecimal reduceLowerlimit;

	@ApiModelProperty(value = "启用固定水量征收（1：启用；0：不启用）")
	private Integer fixedOn;

	@ApiModelProperty(value = "固定征收水量")
	private BigDecimal fixedWaters;

}
