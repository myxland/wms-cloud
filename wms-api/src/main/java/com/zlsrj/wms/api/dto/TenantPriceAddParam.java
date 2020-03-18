package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPrice新增参数", description = "水价列表")
public class TenantPriceAddParam implements Serializable {

	private static final long serialVersionUID = 5714135142018413213L;

	@ApiModelProperty(value = "")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "排序")
	private Integer priceOrder;

	@ApiModelProperty(value = "水价名称")
	private String priceName;

	@ApiModelProperty(value = "父级ID")
	private String priceParentId;

	@ApiModelProperty(value = "水价版本")
	private String priceVersion;

	@ApiModelProperty(value = "版本说明")
	private String priceVersionMemo;

	@ApiModelProperty(value = "营销区域")
	private String marketingAreaId;

	@ApiModelProperty(value = "备注")
	private String priceMemo;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

