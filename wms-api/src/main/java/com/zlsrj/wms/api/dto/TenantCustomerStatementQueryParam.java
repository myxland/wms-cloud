package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCustomerStatement查询参数", description = "用户结算信息")
public class TenantCustomerStatementQueryParam implements Serializable {

	private static final long serialVersionUID = 5155122151014759154L;

	@ApiModelProperty(value = "用户结算ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "用户ID")
	private String customerId;

	@ApiModelProperty(value = "用户号")
	private String customerCode;

	@ApiModelProperty(value = "结算方式（1：坐收；2：托收；3：代扣；4：走收）")
	private Integer statementMethod;

	@ApiModelProperty(value = "付款银行")
	private String statementBankId;

	@ApiModelProperty(value = "委托授权号")
	private String entrustAgreementNo;

	@ApiModelProperty(value = "托收号")
	private String entrustCode;

	@ApiModelProperty(value = "开户银行")
	private Long statementAccountBankId;

	@ApiModelProperty(value = "开户名称")
	private String statementAccountName;

	@ApiModelProperty(value = "开户账号")
	private String statementAccountNo;

	@ApiModelProperty(value = "签约日期")
	private Date statementRegisterDate;

	@ApiModelProperty(value = "签约日期开始")
	private Date statementRegisterDateStart;

	@ApiModelProperty(value = "签约日期结束")
	private Date statementRegisterDateEnd;

	@ApiModelProperty(value = "备注")
	private String statementMemo;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据新增时间开始")
	private Date addTimeStart;

	@ApiModelProperty(value = "数据新增时间结束")
	private Date addTimeEnd;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "数据修改时间开始")
	private Date updateTimeStart;

	@ApiModelProperty(value = "数据修改时间结束")
	private Date updateTimeEnd;

	@ApiModelProperty(value = "查询字段")
	private String[] queryCol;
	
	@ApiModelProperty(value = "查询条件")
	private String[] queryType;
	
	@ApiModelProperty(value = "查询值")
	private String[] queryValue;
}

