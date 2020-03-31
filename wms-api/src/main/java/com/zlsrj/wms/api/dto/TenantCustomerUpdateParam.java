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
@ApiModel(value = "TenantCustomer更新参数", description = "用户信息")
public class TenantCustomerUpdateParam implements Serializable {

	private static final long serialVersionUID = 1010081111511129111L;

	@ApiModelProperty(value = "用户ID")
	private String id;

	@ApiModelProperty(value = "用户号")
	private String customerCode;

	@ApiModelProperty(value = "用户名称")
	private String customerName;

	@ApiModelProperty(value = "用户地址")
	private String customerAddress;

	@ApiModelProperty(value = "用户状态（1：正常；2：暂停；3：销户）")
	private Integer customerStatus;

	@ApiModelProperty(value = "用户类别")
	private String customerType;

	@ApiModelProperty(value = "建档时间")
	private Date customerRegisterTime;

	@ApiModelProperty(value = "立户日期")
	private Date customerRegisterDate;

	@ApiModelProperty(value = "信用等级")
	private Integer customerCreditRating;

	@ApiModelProperty(value = "最近评估日期")
	private Date customerRatingDate;

	@ApiModelProperty(value = "预存余额")
	private BigDecimal customerBalanceAmt;

	@ApiModelProperty(value = "预存冻结金额")
	private BigDecimal customerFreezeBalance;

	@ApiModelProperty(value = "欠费金额")
	private BigDecimal customerOweAmt;

	@ApiModelProperty(value = "违约金")
	private BigDecimal customerPenaltyAmt;

	@ApiModelProperty(value = "用户备注")
	private String customerMemo;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

