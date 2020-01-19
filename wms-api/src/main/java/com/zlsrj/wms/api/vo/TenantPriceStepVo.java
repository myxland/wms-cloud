package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceStep对象", description = "水价阶梯")
public class TenantPriceStepVo implements Serializable {

	private static final long serialVersionUID = 1471110365931151053L;

	@ApiModelProperty(value = "价格阶梯ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "价格类别ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeId;

	@ApiModelProperty(value = "费用项目ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceItemId;

	@ApiModelProperty(value = "阶梯号")
	private Integer stepNo;

	@ApiModelProperty(value = "起始量")
	private BigDecimal startWaters;

	@ApiModelProperty(value = "终止量")
	private BigDecimal endWaters;

	@ApiModelProperty(value = "价格")
	private BigDecimal stepPrice;

}
