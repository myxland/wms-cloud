package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("tenant_customer_invoice")
@ApiModel(value = "TenantCustomerInvoice对象", description = "用户开票信息")
public class TenantCustomerInvoice implements Serializable {

	private static final long serialVersionUID = 1085510140751512231L;

	@ApiModelProperty(value = "用户开票ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "用户ID")
	@TableField("customer_id")
	private String customerId;

	@ApiModelProperty(value = "用户号")
	@TableField("customer_code")
	private String customerCode;

	@ApiModelProperty(value = "开票类型（1：增值税普通纸质发票；2：增值税普通电子发票；3：增值税专用纸质发票）")
	@TableField("invoice_type")
	private Integer invoiceType;

	@ApiModelProperty(value = "开票名称")
	@TableField("invoice_name")
	private String invoiceName;

	@ApiModelProperty(value = "开票税号")
	@TableField("invoice_tax_no")
	private String invoiceTaxNo;

	@ApiModelProperty(value = "开票地址")
	@TableField("invoice_address")
	private String invoiceAddress;

	@ApiModelProperty(value = "开票电话")
	@TableField("invoice_tel")
	private String invoiceTel;

	@ApiModelProperty(value = "开户银行")
	@TableField("invoice_bank")
	private String invoiceBank;

	@ApiModelProperty(value = "开户账号")
	@TableField("invoice_account_no")
	private String invoiceAccountNo;

	@ApiModelProperty(value = "备注")
	@TableField("invoice_memo")
	private String invoiceMemo;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}