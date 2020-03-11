package com.zlsrj.wms.api.vo;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantInvoice对象", description = "租户发票配置")
public class TenantInvoiceVo implements Serializable {

	private static final long serialVersionUID = 2131019151315131054L;

	@ApiModelProperty(value = "租户编号")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

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
