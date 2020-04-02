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
@ApiModel(value = "TenantMeterPrice查询参数", description = "水表计费")
public class TenantMeterPriceQueryParam implements Serializable {

	private static final long serialVersionUID = 8610104152210813938L;

	@ApiModelProperty(value = "水表计费ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "水表ID")
	private String meterId;

	@ApiModelProperty(value = "水表编号")
	private String meterCode;

	@ApiModelProperty(value = "排序")
	private Integer priceOrder;

	@ApiModelProperty(value = "水价列表ID")
	private String priceId;

	@ApiModelProperty(value = "计费类别（1：定量；0：定比）")
	private Integer priceType;

	@ApiModelProperty(value = "系数")
	private BigDecimal priceScale;

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

