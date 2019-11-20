package com.zlsrj.wms.api.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceType对象", description = "价格类别")
public class TenantPriceTypeVo implements Serializable {

	private static final long serialVersionUID = 3147913149142125141L;

	@ApiModelProperty(value = "系统ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "价格类别名称")
	private String priceTypeName;

	@ApiModelProperty(value = "启用保底水量（1：启用；0：不启用）")
	private Integer bottomOn;

	@ApiModelProperty(value = "保底水量")
	private Integer bottomNum;

	@ApiModelProperty(value = "启用封顶水量（1：启用；0：不启用）")
	private Integer topOn;

	@ApiModelProperty(value = "封顶水量")
	private Integer topNum;

	@ApiModelProperty(value = "启用固定减免（1：启用；0：不启用）")
	private Integer reduceOn;

	@ApiModelProperty(value = "固定减免水量")
	private Integer reduceNum;

	@ApiModelProperty(value = "减免起始水量")
	private Integer reduceLowerLimit;

	@ApiModelProperty(value = "启用固定水量征收（1：启用；0：不启用）")
	private Integer fixedOn;

	@ApiModelProperty(value = "固定征收水量")
	private Integer fixedNum;

}
