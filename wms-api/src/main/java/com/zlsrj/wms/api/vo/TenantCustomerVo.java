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
@ApiModel(value = "TenantCustomer对象", description = "用户信息")
public class TenantCustomerVo implements Serializable {

	private static final long serialVersionUID = 1400131412151141400L;

	@ApiModelProperty(value = "")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "用户代码")
	private String customerCode;

	@ApiModelProperty(value = "用户名称")
	private String customerName;

	@ApiModelProperty(value = "用户地址")
	private String customerAddress;

	@ApiModelProperty(value = "用户类别ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long customerTypeId;

	@ApiModelProperty(value = "建档时间")
	private Date customerRegisterTime;

	@ApiModelProperty(value = "用户状态（1：正常；2：暂停；3：消户）")
	private Integer customerStatus;

	@ApiModelProperty(value = "用户缴费方式（1：坐收；2：走收；3：代扣；4：托收）")
	private Integer customerPaymentMethod;

	@ApiModelProperty(value = "开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	private Integer invoiceType;

	@ApiModelProperty(value = "开票名称")
	private String invoiceName;

	@ApiModelProperty(value = "税号")
	private String invoiceTaxNo;

	@ApiModelProperty(value = "开票地址")
	private String invoiceAddress;

	@ApiModelProperty(value = "开票电话")
	private String invoiceTel;

	@ApiModelProperty(value = "开户行行号")
	private String invoiceBankCode;

	@ApiModelProperty(value = "开户行名称")
	private String invoiceBankName;

	@ApiModelProperty(value = "开户行账号")
	private String invoiceBankAccountNo;

	@ApiModelProperty(value = "用户预存余额")
	private BigDecimal customerBalanceMoney;

	@ApiModelProperty(value = "用户欠费金额")
	private BigDecimal customerArrearsMoney;

}
