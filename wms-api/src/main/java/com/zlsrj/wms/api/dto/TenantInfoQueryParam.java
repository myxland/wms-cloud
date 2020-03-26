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
	private String id;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "账户余额")
	private BigDecimal tenantBalance;

	@ApiModelProperty(value = "透支额度")
	private BigDecimal tenantOverdraw;

	@ApiModelProperty(value = "租户显示名称")
	private String tenantDisplayName;

	@ApiModelProperty(value = "省")
	private String tenantProvince;

	@ApiModelProperty(value = "市")
	private String tenantCity;

	@ApiModelProperty(value = "区/县")
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

	@ApiModelProperty(value = "租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）")
	private Integer tenantType;

	@ApiModelProperty(value = "注册时间")
	private Date tenantRegisterTime;

	@ApiModelProperty(value = "注册时间开始")
	private Date tenantRegisterTimeStart;

	@ApiModelProperty(value = "注册时间结束")
	private Date tenantRegisterTimeEnd;

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

	@ApiModelProperty(value = "开户账号")
	private String invoiceBankAccountNo;

	@ApiModelProperty(value = "租户KEY")
	private String tenantAccesskey;
	
	@ApiModelProperty(value = "阶梯水价")
	private Integer priceStepOn;
	
	@ApiModelProperty(value = "营销机构类型（1：扁平型；2：复杂型）")
	private Integer marketingAreaType;

	@ApiModelProperty(value = "查询字段")
	private String queryCol;
	
	@ApiModelProperty(value = "查询条件 like = 等")
	private String queryType;
	
	@ApiModelProperty(value = "查询值")
	private String queryValue;
}

