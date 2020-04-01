package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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
@TableName("tenant_customer_statement")
@ApiModel(value = "TenantCustomerStatement对象", description = "用户结算信息")
public class TenantCustomerStatement implements Serializable {

	private static final long serialVersionUID = 1111145525697522122L;

	@ApiModelProperty(value = "用户结算ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "用户ID")
	@TableField("customer_id")
	private String customerId;

	@ApiModelProperty(value = "用户号")
	@TableField("customer_code")
	private String customerCode;

	@ApiModelProperty(value = "结算方式（1：坐收；2：托收；3：代扣；4：走收）")
	@TableField("statement_method")
	private Integer statementMethod;

	@ApiModelProperty(value = "付款银行")
	@TableField("statement_bank_id")
	private String statementBankId;

	@ApiModelProperty(value = "委托授权号")
	@TableField("entrust_agreement_no")
	private String entrustAgreementNo;

	@ApiModelProperty(value = "托收号")
	@TableField("entrust_code")
	private String entrustCode;

	@ApiModelProperty(value = "开户银行")
	@TableField("statement_account_bank_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long statementAccountBankId;

	@ApiModelProperty(value = "开户名称")
	@TableField("statement_account_name")
	private String statementAccountName;

	@ApiModelProperty(value = "开户账号")
	@TableField("statement_account_no")
	private String statementAccountNo;

	@ApiModelProperty(value = "签约日期")
	@TableField("statement_register_date")
	private Date statementRegisterDate;

	@ApiModelProperty(value = "备注")
	@TableField("statement_memo")
	private String statementMemo;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}