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
@TableName("tenant_customer")
@ApiModel(value = "TenantCustomer对象", description = "用户信息")
public class TenantCustomer implements Serializable {

	private static final long serialVersionUID = 1121065111214301181L;

	@ApiModelProperty(value = "用户ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "用户号")
	@TableField("customer_code")
	private String customerCode;

	@ApiModelProperty(value = "用户名称")
	@TableField("customer_name")
	private String customerName;

	@ApiModelProperty(value = "用户地址")
	@TableField("customer_address")
	private String customerAddress;

	@ApiModelProperty(value = "用户状态（1：正常；2：暂停；3：销户）")
	@TableField("customer_status")
	private Integer customerStatus;

	@ApiModelProperty(value = "用户类别")
	@TableField("customer_type")
	private String customerType;

	@ApiModelProperty(value = "建档时间")
	@TableField("customer_register_time")
	private Date customerRegisterTime;

	@ApiModelProperty(value = "立户日期")
	@TableField("customer_register_date")
	private Date customerRegisterDate;

	@ApiModelProperty(value = "信用等级")
	@TableField("customer_credit_rating")
	private Integer customerCreditRating;

	@ApiModelProperty(value = "最近评估日期")
	@TableField("customer_rating_date")
	private Date customerRatingDate;

	@ApiModelProperty(value = "预存余额")
	@TableField("customer_balance_amt")
	private BigDecimal customerBalanceAmt;

	@ApiModelProperty(value = "预存冻结金额")
	@TableField("customer_freeze_balance")
	private BigDecimal customerFreezeBalance;

	@ApiModelProperty(value = "欠费金额")
	@TableField("customer_owe_amt")
	private BigDecimal customerOweAmt;

	@ApiModelProperty(value = "违约金")
	@TableField("customer_penalty_amt")
	private BigDecimal customerPenaltyAmt;

	@ApiModelProperty(value = "用户备注")
	@TableField("customer_memo")
	private String customerMemo;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}