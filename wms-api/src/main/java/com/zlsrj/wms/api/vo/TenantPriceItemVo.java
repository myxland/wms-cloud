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
@ApiModel(value = "TenantPriceItem对象", description = "费用项目")
public class TenantPriceItemVo implements Serializable {

	private static final long serialVersionUID = 3461291971261406271L;

	@ApiModelProperty(value = "系统ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "费用项目名称")
	private String priceItemName;

	@ApiModelProperty(value = "税率")
	private BigDecimal taxRate;

	@ApiModelProperty(value = "对应税控项目编号")
	private String taxId;

}
