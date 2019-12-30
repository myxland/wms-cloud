package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("tenant_consumption_bill")
@ApiModel(value = "TenantConsumptionBill对象", description = "租户账单")
public class TenantConsumptionBill implements Serializable {

	private static final long serialVersionUID = 1401241211514881391L;

	@ApiModelProperty(value = "租户账单ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "账单类型（1：充值；2：消费）")
	@TableField("consumption_bill_type")
	private Integer consumptionBillType;

	@ApiModelProperty(value = "账单时间")
	@TableField("consumption_bill_time")
	private Date consumptionBillTime;

	@ApiModelProperty(value = "账单名称[账户充值/短信平台/...]")
	@TableField("consumption_bill_name")
	private String consumptionBillName;

	@ApiModelProperty(value = "账单金额")
	@TableField("consumption_bill_money")
	private BigDecimal consumptionBillMoney;

	@ApiModelProperty(value = "租户账户余额")
	@TableField("tenant_balance")
	private BigDecimal tenantBalance;

	@ApiModelProperty(value = "备注")
	@TableField("consumption_bill_remark")
	private String consumptionBillRemark;


}
