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
@ApiModel(value = "TenantPriceDetail对象", description = "价格明细")
public class TenantPriceDetailVo implements Serializable {

	private static final long serialVersionUID = 1471181314102121211L;

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
	
	@ApiModelProperty(value = "费用项目名称")
	private String priceItemName;


	@ApiModelProperty(value = "计算方法（1：固定单价；2：固定金额；3：阶梯价格）")
	private Integer calcType;

	@ApiModelProperty(value = "指定价格（金额）")
	private BigDecimal price;

}
