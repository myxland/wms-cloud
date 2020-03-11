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
@TableName("tenant_payment")
@ApiModel(value = "TenantPayment对象", description = "实收总账，记录每次缴费的总信息")
public class TenantPayment implements Serializable {

	private static final long serialVersionUID = 1015661416123132711L;

	@ApiModelProperty(value = "实收账ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "内部生成的订单号")
	@TableField("out_transno")
	private String outTransno;

	@ApiModelProperty(value = "外部如微信支付宝传入的订单号")
	@TableField("in_transno")
	private String inTransno;

	@ApiModelProperty(value = "付款时间")
	@TableField("pay_time")
	private Date payTime;

	@ApiModelProperty(value = "实收账状态（1：正常；2：被退款；3：退款记录）")
	@TableField("payment_status")
	private Integer paymentStatus;

	@ApiModelProperty(value = "用户ID")
	@TableField("customer_id")
	private String customerId;

	@ApiModelProperty(value = "收款部门ID")
	@TableField("charge_department_id")
	private String chargeDepartmentId;

	@ApiModelProperty(value = "收费员ID")
	@TableField("charge_employee_id")
	private String chargeEmployeeId;

	@ApiModelProperty(value = "付款途径（1：柜台；2：银行；3：线上；4：走收；5：系统处理）")
	@TableField("pay_channels")
	private Integer payChannels;

	@ApiModelProperty(value = "付款方式（0：预存抵扣；1：现金；2：支票；3：刷卡；4：电汇；5：代扣；6：托收；7：微信生活缴费；8：支付宝生活缴费；9：微信公众号；10：微信扫码[用户被扫]；11：支付宝扫码[用户被扫]；12：微信扫码[用户主扫]；13：支付宝扫码[用户主扫]）")
	@TableField("pay_method")
	private Integer payMethod;

	@ApiModelProperty(value = "用户上期预存余额")
	@TableField("customer_balance_money_before")
	private BigDecimal customerBalanceMoneyBefore;

	@ApiModelProperty(value = "用户付款金额")
	@TableField("customer_pay_money")
	private BigDecimal customerPayMoney;

	@ApiModelProperty(value = "用户预存发生值")
	@TableField("customer_balance_money_happen")
	private BigDecimal customerBalanceMoneyHappen;

	@ApiModelProperty(value = "所缴欠费金额")
	@TableField("pay_the_arrears_money")
	private BigDecimal payTheArrearsMoney;

	@ApiModelProperty(value = "所缴违约金金额")
	@TableField("pay_the_late_fee_money")
	private BigDecimal payTheLateFeeMoney;

	@ApiModelProperty(value = "用户本期预存余额")
	@TableField("customer_balance_money_after")
	private BigDecimal customerBalanceMoneyAfter;


}
