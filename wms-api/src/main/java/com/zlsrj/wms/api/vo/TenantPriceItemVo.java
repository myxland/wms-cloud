package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

	@ApiModelProperty(value = "")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

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

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}
