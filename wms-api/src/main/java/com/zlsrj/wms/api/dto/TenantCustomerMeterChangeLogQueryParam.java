package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCustomerMeterChangeLog查询参数", description = "信息变更")
public class TenantCustomerMeterChangeLogQueryParam implements Serializable {

	private static final long serialVersionUID = 1101476116251114210L;

	@ApiModelProperty(value = "变更日志ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "用户ID")
	private Long customerId;

	@ApiModelProperty(value = "新用户ID")
	private Long csutomerIdNew;

	@ApiModelProperty(value = "用户名称")
	private String customerName;

	@ApiModelProperty(value = "新用户名称")
	private String customerNameNew;

	@ApiModelProperty(value = "用户地址")
	private String customerAddress;

	@ApiModelProperty(value = "新用户地址")
	private String customerAddressNew;

	@ApiModelProperty(value = "用户类别ID")
	private Long customerTypeId;

	@ApiModelProperty(value = "新用户类别ID")
	private Long customerTypeIdNew;

	@ApiModelProperty(value = "用户状态（1：正常；2：暂停；3：消户）")
	private Integer customerStatus;

	@ApiModelProperty(value = "新用户状态（1：正常；2：暂停；3：消户）")
	private Integer customerStatusNew;

	@ApiModelProperty(value = "收费方式（1：坐收；2：走收；3：代扣；4：托收）")
	private Integer customerPaymentMethod;

	@ApiModelProperty(value = "新收费方式（1：坐收；2：走收；3：代扣；4：托收）")
	private Integer customerPaymentMethodNew;

	@ApiModelProperty(value = "开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	private Integer invoiceType;

	@ApiModelProperty(value = "新开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	private Integer invoiceTypeNew;

	@ApiModelProperty(value = "开票名称")
	private String invoiceName;

	@ApiModelProperty(value = "新开票名称")
	private String invoiceNameNew;

	@ApiModelProperty(value = "税号")
	private String invoiceTaxNo;

	@ApiModelProperty(value = "新税号")
	private String invoiceTaxNoNew;

	@ApiModelProperty(value = "开票地址")
	private String invoiceAddress;

	@ApiModelProperty(value = "新开票地址")
	private String invoiceAddressNew;

	@ApiModelProperty(value = "开票电话")
	private String invoiceTel;

	@ApiModelProperty(value = "新开票电话")
	private String invoiceTelNew;

	@ApiModelProperty(value = "开户行行号")
	private String invoiceBankCode;

	@ApiModelProperty(value = "新开户行行号")
	private String invoiceBankCodeNew;

	@ApiModelProperty(value = "开户行名称")
	private String invoiceBankName;

	@ApiModelProperty(value = "新开户行名称")
	private String invoiceBankNameNew;

	@ApiModelProperty(value = "开户行账号")
	private String invoiceBankAccountNo;

	@ApiModelProperty(value = "新开户行账号")
	private String invoiceBankAccountNoNew;

	@ApiModelProperty(value = "水表ID")
	private Long meterId;

	@ApiModelProperty(value = "价格分类ID")
	private Long priceTypeId;

	@ApiModelProperty(value = "新价格分类ID")
	private Long priceTypeIdNew;

	@ApiModelProperty(value = "水表止码")
	private BigDecimal meterLastSettlePointer;

	@ApiModelProperty(value = "新水表止码")
	private BigDecimal meterLastSettlePointerNew;

	@ApiModelProperty(value = "水表厂商ID")
	private Long manufactorId;

	@ApiModelProperty(value = "新水表厂商ID")
	private Long manufactorIdNew;

	@ApiModelProperty(value = "水表类型（1：机械表；2：远传表；3：IC卡表）")
	private Integer meterType;

	@ApiModelProperty(value = "新水表类型（1：机械表；2：远传表；3：IC卡表）")
	private Integer meterTypeNew;

	@ApiModelProperty(value = "水表口径ID")
	private Long caliberId;

	@ApiModelProperty(value = "新水表口径ID")
	private Long caliberIdNew;

	@ApiModelProperty(value = "水表表身码")
	private String meterMachineCode;

	@ApiModelProperty(value = "新水表表身码")
	private String meterMachineCodeNew;

	@ApiModelProperty(value = "远传系统编号")
	private String meterIotCode;

	@ApiModelProperty(value = "新远传系统编号")
	private String meterIotCodeNew;

	@ApiModelProperty(value = "人口数")
	private Integer meterPeoples;

	@ApiModelProperty(value = "新人口数")
	private Integer meterPeoplesNew;

}

