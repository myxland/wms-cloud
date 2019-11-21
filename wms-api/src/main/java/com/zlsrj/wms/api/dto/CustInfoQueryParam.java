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
@ApiModel(value = "CustInfo查询参数", description = "用户信息")
public class CustInfoQueryParam implements Serializable {

	private static final long serialVersionUID = 5122231540851003136L;

	@ApiModelProperty(value = "系统编号")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "用户编号")
	private String custNo;

	@ApiModelProperty(value = "用户名称")
	private String custName;

	@ApiModelProperty(value = "用户地址")
	private String custAddress;

	@ApiModelProperty(value = "用户类别编号")
	private Long custTypeId;

	@ApiModelProperty(value = "立户日期")
	private Date custRegistDate;

	@ApiModelProperty(value = "立户日期开始")
	private Date custRegistDateStart;

	@ApiModelProperty(value = "立户日期结束")
	private Date custRegistDateEnd;

	@ApiModelProperty(value = "用户状态（1：正常；2：暂停；3：消户）")
	private Integer custStatus;

	@ApiModelProperty(value = "收费方式（1：坐收；2：走收；3：代扣；4：托收）")
	private Integer payType;

	@ApiModelProperty(value = "开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	private Integer billType;

	@ApiModelProperty(value = "开票名称")
	private String billName;

	@ApiModelProperty(value = "税号")
	private String billTaxnum;

	@ApiModelProperty(value = "开票地址")
	private String billAddress;

	@ApiModelProperty(value = "开票电话")
	private String billTel;

	@ApiModelProperty(value = "银行名称")
	private String billBank;

	@ApiModelProperty(value = "开户行名称")
	private String billBankName;

	@ApiModelProperty(value = "开户行账号")
	private String billBankAccount;

	@ApiModelProperty(value = "开户行号")
	private String billBankId;

	@ApiModelProperty(value = "预存余额")
	private BigDecimal saveMoney;

	@ApiModelProperty(value = "欠费金额")
	private BigDecimal dueMoney;

}

