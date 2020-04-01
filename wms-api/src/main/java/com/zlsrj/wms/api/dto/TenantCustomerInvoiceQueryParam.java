package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCustomerInvoice查询参数", description = "用户开票信息")
public class TenantCustomerInvoiceQueryParam implements Serializable {

	private static final long serialVersionUID = 3813811114573135569L;

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

	@ApiModelProperty(value = "数据新增时间开始")
	private Date addTimeStart;

	@ApiModelProperty(value = "数据新增时间结束")
	private Date addTimeEnd;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "数据修改时间开始")
	private Date updateTimeStart;

	@ApiModelProperty(value = "数据修改时间结束")
	private Date updateTimeEnd;
	
	@ApiModelProperty(value = "查询字段")
	private String[] queryCol;
	
	@ApiModelProperty(value = "查询条件")
	private String[] queryType;
	
	@ApiModelProperty(value = "查询值")
	private String[] queryValue;

}

