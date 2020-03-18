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
@ApiModel(value = "TenantPriceItem查询参数", description = "费用项目")
public class TenantPriceItemQueryParam implements Serializable {

	private static final long serialVersionUID = 2133415811411181314L;

	@ApiModelProperty(value = "")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "费用项目编码")
	private Integer priceItemCode;

	@ApiModelProperty(value = "费用项目名称")
	private String priceItemName;

	@ApiModelProperty(value = "税率")
	private BigDecimal priceItemTaxRate;

	@ApiModelProperty(value = "税收分类编码")
	private String priceItemTaxCode;

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

