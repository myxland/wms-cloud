package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantConfig查询参数", description = "租户基础配置")
public class TenantConfigQueryParam implements Serializable {

	private static final long serialVersionUID = 2151086113661436515L;

	@ApiModelProperty(value = "租户编号")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "是否启用部分缴费（1：启用；0：不启用）")
	private Integer partChargeOn;

	@ApiModelProperty(value = "是否启用违约金（1：启用；0：不启用）")
	private Integer overDuefineOn;

	@ApiModelProperty(value = "违约金宽限天数")
	private Integer overDuefineDay;

	@ApiModelProperty(value = "违约金每天收取比例")
	private BigDecimal overDuefineRatio;

	@ApiModelProperty(value = "违约金封顶比例（与欠费金额相比）")
	private BigDecimal overDuefineTopRatio;

	@ApiModelProperty(value = "预存抵扣方式（1：抄表后即时抵扣；2：人工发起抵扣）")
	private Integer ycdkType;

}

