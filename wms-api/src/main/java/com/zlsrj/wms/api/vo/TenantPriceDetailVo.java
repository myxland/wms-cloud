package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceDetail对象", description = "水价明细")
public class TenantPriceDetailVo implements Serializable {

	private static final long serialVersionUID = 1471181314102121211L;

	@ApiModelProperty(value = "水价明细ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "水表列表ID")
	private String priceId;

	@ApiModelProperty(value = "费用项目")
	private String priceItemId;

	@ApiModelProperty(value = "计费规则")
	private Integer priceRule;

	@ApiModelProperty(value = "单价")
	private BigDecimal detailPrice;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "阶梯明细")
	private List<TenantPriceStepVo> tenantPriceStepList;
}
