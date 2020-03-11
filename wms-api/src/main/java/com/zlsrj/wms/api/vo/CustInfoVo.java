package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "CustInfo对象", description = "用户信息")
public class CustInfoVo implements Serializable {

	private static final long serialVersionUID = 1512356159005103207L;

	@ApiModelProperty(value = "系统编号")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "用户编号")
	private String custNo;

	@ApiModelProperty(value = "用户名称")
	private String custName;

	@ApiModelProperty(value = "用户地址")
	private String custAddress;

	@ApiModelProperty(value = "用户类别编号")
	private String custTypeId;
	
	@ApiModelProperty(value = "用户类别名称")
	private String custTypeName;

	@ApiModelProperty(value = "立户日期")
	private Date custRegistDate;

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
