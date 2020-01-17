package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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
@TableName("tenant_customer")
@ApiModel(value = "TenantCustomer对象", description = "用户信息")
public class TenantCustomer implements Serializable {

	private static final long serialVersionUID = 1121065111214301181L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "用户代码")
	@TableField("customer_code")
	private String customerCode;

	@ApiModelProperty(value = "用户名称")
	@TableField("customer_name")
	private String customerName;

	@ApiModelProperty(value = "用户地址")
	@TableField("customer_address")
	private String customerAddress;

	@ApiModelProperty(value = "用户类别ID")
	@TableField("customer_type_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long customerTypeId;

	@ApiModelProperty(value = "建档时间")
	@TableField("customer_register_time")
	private Date customerRegisterTime;

	@ApiModelProperty(value = "用户状态（1：正常；2：暂停；3：消户）")
	@TableField("customer_status")
	private Integer customerStatus;

	@ApiModelProperty(value = "用户缴费方式（1：坐收；2：走收；3：代扣；4：托收）")
	@TableField("customer_payment_method")
	private Integer customerPaymentMethod;

	@ApiModelProperty(value = "开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	@TableField("invoice_type")
	private Integer invoiceType;

	@ApiModelProperty(value = "开票名称")
	@TableField("invoice_name")
	private String invoiceName;

	@ApiModelProperty(value = "税号")
	@TableField("invoice_tax_no")
	private String invoiceTaxNo;

	@ApiModelProperty(value = "开票地址")
	@TableField("invoice_address")
	private String invoiceAddress;

	@ApiModelProperty(value = "开票电话")
	@TableField("invoice_tel")
	private String invoiceTel;

	@ApiModelProperty(value = "开户行行号")
	@TableField("invoice_bank_code")
	private String invoiceBankCode;

	@ApiModelProperty(value = "开户行名称")
	@TableField("invoice_bank_name")
	private String invoiceBankName;

	@ApiModelProperty(value = "开户行账号")
	@TableField("invoice_bank_account_no")
	private String invoiceBankAccountNo;

	@ApiModelProperty(value = "用户预存余额")
	@TableField("customer_balance_money")
	private BigDecimal customerBalanceMoney;

	@ApiModelProperty(value = "用户欠费金额")
	@TableField("customer_arrears_money")
	private BigDecimal customerArrearsMoney;


}
