package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantInvoice查询参数", description = "租户发票配置")
public class TenantInvoiceQueryParam implements Serializable {

	private static final long serialVersionUID = 6815231150799151113L;

	@ApiModelProperty(value = "租户编号")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "税号")
	private String creditNumber;

	@ApiModelProperty(value = "开票地址")
	private String invoiceAddress;

	@ApiModelProperty(value = "开户行行号")
	private String bankNo;

	@ApiModelProperty(value = "开户行名称")
	private String bankName;

	@ApiModelProperty(value = "开户账号")
	private String accountNo;

}

