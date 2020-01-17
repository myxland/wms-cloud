package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("tenant_customer_meter_change_log")
@ApiModel(value = "TenantCustomerMeterChangeLog对象", description = "信息变更")
public class TenantCustomerMeterChangeLog implements Serializable {

	private static final long serialVersionUID = 4132651114789123134L;

	@ApiModelProperty(value = "变更日志ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "用户ID")
	@TableField("customer_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long customerId;

	@ApiModelProperty(value = "新用户ID")
	@TableField("csutomer_id_new")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long csutomerIdNew;

	@ApiModelProperty(value = "用户名称")
	@TableField("customer_name")
	private String customerName;

	@ApiModelProperty(value = "新用户名称")
	@TableField("customer_name_new")
	private String customerNameNew;

	@ApiModelProperty(value = "用户地址")
	@TableField("customer_address")
	private String customerAddress;

	@ApiModelProperty(value = "新用户地址")
	@TableField("customer_address_new")
	private String customerAddressNew;

	@ApiModelProperty(value = "用户类别ID")
	@TableField("customer_type_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long customerTypeId;

	@ApiModelProperty(value = "新用户类别ID")
	@TableField("customer_type_id_new")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long customerTypeIdNew;

	@ApiModelProperty(value = "用户状态（1：正常；2：暂停；3：消户）")
	@TableField("customer_status")
	private Integer customerStatus;

	@ApiModelProperty(value = "新用户状态（1：正常；2：暂停；3：消户）")
	@TableField("customer_status_new")
	private Integer customerStatusNew;

	@ApiModelProperty(value = "收费方式（1：坐收；2：走收；3：代扣；4：托收）")
	@TableField("customer_payment_method")
	private Integer customerPaymentMethod;

	@ApiModelProperty(value = "新收费方式（1：坐收；2：走收；3：代扣；4：托收）")
	@TableField("customer_payment_method_new")
	private Integer customerPaymentMethodNew;

	@ApiModelProperty(value = "开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	@TableField("invoice_type")
	private Integer invoiceType;

	@ApiModelProperty(value = "新开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	@TableField("invoice_type_new")
	private Integer invoiceTypeNew;

	@ApiModelProperty(value = "开票名称")
	@TableField("invoice_name")
	private String invoiceName;

	@ApiModelProperty(value = "新开票名称")
	@TableField("invoice_name_new")
	private String invoiceNameNew;

	@ApiModelProperty(value = "税号")
	@TableField("invoice_tax_no")
	private String invoiceTaxNo;

	@ApiModelProperty(value = "新税号")
	@TableField("invoice_tax_no_new")
	private String invoiceTaxNoNew;

	@ApiModelProperty(value = "开票地址")
	@TableField("invoice_address")
	private String invoiceAddress;

	@ApiModelProperty(value = "新开票地址")
	@TableField("invoice_address_new")
	private String invoiceAddressNew;

	@ApiModelProperty(value = "开票电话")
	@TableField("invoice_tel")
	private String invoiceTel;

	@ApiModelProperty(value = "新开票电话")
	@TableField("invoice_tel_new")
	private String invoiceTelNew;

	@ApiModelProperty(value = "开户行行号")
	@TableField("invoice_bank_code")
	private String invoiceBankCode;

	@ApiModelProperty(value = "新开户行行号")
	@TableField("invoice_bank_code_new")
	private String invoiceBankCodeNew;

	@ApiModelProperty(value = "开户行名称")
	@TableField("invoice_bank_name")
	private String invoiceBankName;

	@ApiModelProperty(value = "新开户行名称")
	@TableField("invoice_bank_name_new")
	private String invoiceBankNameNew;

	@ApiModelProperty(value = "开户行账号")
	@TableField("invoice_bank_account_no")
	private String invoiceBankAccountNo;

	@ApiModelProperty(value = "新开户行账号")
	@TableField("invoice_bank_account_no_new")
	private String invoiceBankAccountNoNew;

	@ApiModelProperty(value = "水表ID")
	@TableField("meter_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterId;

	@ApiModelProperty(value = "价格分类ID")
	@TableField("price_type_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeId;

	@ApiModelProperty(value = "新价格分类ID")
	@TableField("price_type_id_new")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeIdNew;

	@ApiModelProperty(value = "水表止码")
	@TableField("meter_last_settle_pointer")
	private BigDecimal meterLastSettlePointer;

	@ApiModelProperty(value = "新水表止码")
	@TableField("meter_last_settle_pointer_new")
	private BigDecimal meterLastSettlePointerNew;

	@ApiModelProperty(value = "水表厂商ID")
	@TableField("manufactor_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long manufactorId;

	@ApiModelProperty(value = "新水表厂商ID")
	@TableField("manufactor_id_new")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long manufactorIdNew;

	@ApiModelProperty(value = "水表类型（1：机械表；2：远传表；3：IC卡表）")
	@TableField("meter_type")
	private Integer meterType;

	@ApiModelProperty(value = "新水表类型（1：机械表；2：远传表；3：IC卡表）")
	@TableField("meter_type_new")
	private Integer meterTypeNew;

	@ApiModelProperty(value = "水表口径ID")
	@TableField("caliber_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long caliberId;

	@ApiModelProperty(value = "新水表口径ID")
	@TableField("caliber_id_new")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long caliberIdNew;

	@ApiModelProperty(value = "水表表身码")
	@TableField("meter_machine_code")
	private String meterMachineCode;

	@ApiModelProperty(value = "新水表表身码")
	@TableField("meter_machine_code_new")
	private String meterMachineCodeNew;

	@ApiModelProperty(value = "远传系统编号")
	@TableField("meter_iot_code")
	private String meterIotCode;

	@ApiModelProperty(value = "新远传系统编号")
	@TableField("meter_iot_code_new")
	private String meterIotCodeNew;

	@ApiModelProperty(value = "人口数")
	@TableField("meter_peoples")
	private Integer meterPeoples;

	@ApiModelProperty(value = "新人口数")
	@TableField("meter_peoples_new")
	private Integer meterPeoplesNew;


}
