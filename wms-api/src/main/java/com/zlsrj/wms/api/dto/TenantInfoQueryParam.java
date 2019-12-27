package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantInfo查询参数", description = "租户表")
public class TenantInfoQueryParam implements Serializable {

	private static final long serialVersionUID = 5784121514767265731L;

	@ApiModelProperty(value = "租户ID")
	private Long id;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "账户余额")
	private BigDecimal tenantBalance;

	@ApiModelProperty(value = "透支额度")
	private BigDecimal teanantOverdraw;

	@ApiModelProperty(value = "租户显示名称")
	private String tenantDisplayName;

	@ApiModelProperty(value = "省（下拉框）")
	private String tenantProvince;

	@ApiModelProperty(value = "市（下拉框）")
	private String tenantCity;

	@ApiModelProperty(value = "区县（下拉框）")
	private String tenantCountyTown;

	@ApiModelProperty(value = "联系地址")
	private String tenantLinkAddress;

	@ApiModelProperty(value = "联系人")
	private String tenantLinkman;

	@ApiModelProperty(value = "手机号码")
	private String tenantLinkmanMobile;

	@ApiModelProperty(value = "单位电话")
	private String tenantLinkmanTel;

	@ApiModelProperty(value = "邮箱")
	private String tenantLinkmanEmail;

	@ApiModelProperty(value = "QQ号码")
	private String tenantLinkmanQq;

	@ApiModelProperty(value = "租户类型（1使用单位/2水表厂商/3代收机构/4内部运营/5分销商）")
	private Integer tenantType;

	@ApiModelProperty(value = "注册时间")
	private Date tenantRegisterTime;

	@ApiModelProperty(value = "注册时间开始")
	private Date tenantRegisterTimeStart;

	@ApiModelProperty(value = "注册时间结束")
	private Date tenantRegisterTimeEnd;

	@ApiModelProperty(value = "开票类别（1普通纸制发票/2普通电子发票/3专用纸制发票）")
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

	@ApiModelProperty(value = "开户账号")
	private String invoiceBankAccountNo;

	@ApiModelProperty(value = "租户KEY")
	private String tenantAccesskey;

}

