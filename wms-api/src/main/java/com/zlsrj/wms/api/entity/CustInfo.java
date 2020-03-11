package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@TableName("t_cust_info")
@ApiModel(value = "CustInfo对象", description = "用户信息")
public class CustInfo implements Serializable {

	private static final long serialVersionUID = 1169521014114110641L;

	@ApiModelProperty(value = "系统编号")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "用户编号")
	@TableField("cust_no")
	private String custNo;

	@ApiModelProperty(value = "用户名称")
	@TableField("cust_name")
	private String custName;

	@ApiModelProperty(value = "用户地址")
	@TableField("cust_address")
	private String custAddress;

	@ApiModelProperty(value = "用户类别编号")
	@TableField("cust_type_id")
	private String custTypeId;

	@ApiModelProperty(value = "立户日期")
	@TableField("cust_regist_date")
	private Date custRegistDate;

	@ApiModelProperty(value = "用户状态（1：正常；2：暂停；3：消户）")
	@TableField("cust_status")
	private Integer custStatus;

	@ApiModelProperty(value = "收费方式（1：坐收；2：走收；3：代扣；4：托收）")
	@TableField("pay_type")
	private Integer payType;

	@ApiModelProperty(value = "开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	@TableField("bill_type")
	private Integer billType;

	@ApiModelProperty(value = "开票名称")
	@TableField("bill_name")
	private String billName;

	@ApiModelProperty(value = "税号")
	@TableField("bill_taxnum")
	private String billTaxnum;

	@ApiModelProperty(value = "开票地址")
	@TableField("bill_address")
	private String billAddress;

	@ApiModelProperty(value = "开票电话")
	@TableField("bill_tel")
	private String billTel;

	@ApiModelProperty(value = "银行名称")
	@TableField("bill_bank")
	private String billBank;

	@ApiModelProperty(value = "开户行名称")
	@TableField("bill_bank_name")
	private String billBankName;

	@ApiModelProperty(value = "开户行账号")
	@TableField("bill_bank_account")
	private String billBankAccount;

	@ApiModelProperty(value = "开户行号")
	@TableField("bill_bank_id")
	private String billBankId;

	@ApiModelProperty(value = "预存余额")
	@TableField("save_money")
	private BigDecimal saveMoney;

	@ApiModelProperty(value = "欠费金额")
	@TableField("due_money")
	private BigDecimal dueMoney;


}
