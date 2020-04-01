package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCustomerInvoice新增参数", description = "用户开票信息")
public class TenantCustomerInvoiceAddParam implements Serializable {

	private static final long serialVersionUID = 3110101010461514611L;

	@ApiModelProperty(value = "用户开票ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "用户ID")
	private String customerId;

	@ApiModelProperty(value = "用户号")
	private String customerCode;

	@ApiModelProperty(value = "开票类型（1：增值税普通纸质发票；2：增值税普通电子发票；3：增值税专用纸质发票）")
	private Integer invoiceType;

	@ApiModelProperty(value = "开票名称")
	private String invoiceName;

	@ApiModelProperty(value = "开票税号")
	private String invoiceTaxNo;

	@ApiModelProperty(value = "开票地址")
	private String invoiceAddress;

	@ApiModelProperty(value = "开票电话")
	private String invoiceTel;

	@ApiModelProperty(value = "开户银行")
	private String invoiceBank;

	@ApiModelProperty(value = "开户账号")
	private String invoiceAccountNo;

	@ApiModelProperty(value = "备注")
	private String invoiceMemo;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

