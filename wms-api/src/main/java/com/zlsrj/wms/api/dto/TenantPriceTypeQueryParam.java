package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceType查询参数", description = "")
public class TenantPriceTypeQueryParam implements Serializable {

	private static final long serialVersionUID = 1311230281513941413L;

	@ApiModelProperty(value = "系统ID")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "价格类别名称")
	private String priceTypeName;

	@ApiModelProperty(value = "启用保底水量（启用/不启用）")
	private Integer bottomOn;

	@ApiModelProperty(value = "保底水量")
	private Integer bottomNum;

	@ApiModelProperty(value = "启用封顶水量（启用/不启用）")
	private Integer topOn;

	@ApiModelProperty(value = "封顶水量")
	private Integer topNum;

	@ApiModelProperty(value = "启用固定减免（启用/不启用）")
	private Integer reduceOn;

	@ApiModelProperty(value = "固定减免水量")
	private Integer reduceNum;

	@ApiModelProperty(value = "减免起始水量（当月多少吨以上才可以减免）")
	private Integer reduceLowerLimit;

	@ApiModelProperty(value = "启用固定水量征收（启用/不启用）")
	private Integer fixedOn;

	@ApiModelProperty(value = "固定征收水量")
	private Integer fixedNum;

}

