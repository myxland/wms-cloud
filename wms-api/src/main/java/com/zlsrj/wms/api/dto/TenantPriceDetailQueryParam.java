package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceDetail查询参数", description = "水价明细")
public class TenantPriceDetailQueryParam implements Serializable {

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
	private String priceRuleId;

	@ApiModelProperty(value = "单价")
	private BigDecimal detailPrice;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据新增时间开始")
	private Date addTimeStart;

	@ApiModelProperty(value = "数据新增时间结束")
	private Date addTimeEnd;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "数据修改时间开始")
	private Date updateTimeStart;

	@ApiModelProperty(value = "数据修改时间结束")
	private Date updateTimeEnd;

}

