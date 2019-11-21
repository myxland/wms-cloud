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
@ApiModel(value = "CustDev对象", description = "用户表具")
public class CustDevVo implements Serializable {

	private static final long serialVersionUID = 6234812091313121375L;

	@ApiModelProperty(value = "系统编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "表具编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long devId;

	@ApiModelProperty(value = "价格类别编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeId;
	
	@ApiModelProperty(value = "价格类别名称")
	private String priceTypeName;

	@ApiModelProperty(value = "用水类别编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long waterTypeId;
	
	@ApiModelProperty(value = "用水类别名称")
	private String waterTypeName;

	@ApiModelProperty(value = "排序")
	private Integer devOrder;

	@ApiModelProperty(value = "混合类型（1：定量；2：比例）")
	private Integer waterMixType;

	@ApiModelProperty(value = "本价格用水量占比或定量")
	private BigDecimal waterScale;

	@ApiModelProperty(value = "水量计算方法（1：向上取整；2：向下取整；3：保留两位小数）")
	private Integer waterCalcType;

}
