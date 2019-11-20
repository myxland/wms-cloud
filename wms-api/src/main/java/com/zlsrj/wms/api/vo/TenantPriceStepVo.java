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
@ApiModel(value = "TenantPriceStep对象", description = "价格阶梯")
public class TenantPriceStepVo implements Serializable {

	private static final long serialVersionUID = 1471110365931151053L;

	@ApiModelProperty(value = "系统ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "价格类别")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeId;
	
	@ApiModelProperty(value = "价格类别名称")
	private String priceTypeName;

	@ApiModelProperty(value = "费用项目")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceItemId;
	
	@ApiModelProperty(value = "价格类别名称")
	private String priceItemName;

	@ApiModelProperty(value = "阶梯号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long stepId;

	@ApiModelProperty(value = "起始量")
	private Integer startNum;

	@ApiModelProperty(value = "终止量")
	private Integer endNum;

	@ApiModelProperty(value = "价格")
	private BigDecimal price;

}
