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
@ApiModel(value = "TenantPayment查询参数", description = "实收总账，记录每次缴费的总信息")
public class TenantPaymentQueryParam implements Serializable {

	private static final long serialVersionUID = 1911013213144111335L;

	@ApiModelProperty(value = "实收账ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "内部生成的订单号")
	private String outTransno;

	@ApiModelProperty(value = "外部如微信支付宝传入的订单号")
	private String inTransno;

	@ApiModelProperty(value = "付款时间")
	private Date payTime;

	@ApiModelProperty(value = "付款时间开始")
	private Date payTimeStart;

	@ApiModelProperty(value = "付款时间结束")
	private Date payTimeEnd;

	@ApiModelProperty(value = "实收账状态（1：正常；2：被退款；3：退款记录）")
	private Integer paymentStatus;

	@ApiModelProperty(value = "用户ID")
	private Long customerId;

	@ApiModelProperty(value = "收款部门ID")
	private Long chargeDepartmentId;

	@ApiModelProperty(value = "收费员ID")
	private Long chargeEmployeeId;

	@ApiModelProperty(value = "付款途径（1：柜台；2：银行；3：线上；4：走收；5：系统处理）")
	private Integer payChannels;

	@ApiModelProperty(value = "付款方式（0：预存抵扣；1：现金；2：支票；3：刷卡；4：电汇；5：代扣；6：托收；7：微信生活缴费；8：支付宝生活缴费；9：微信公众号；10：微信扫码[用户被扫]；11：支付宝扫码[用户被扫]；12：微信扫码[用户主扫]；13：支付宝扫码[用户主扫]）")
	private Integer payMethod;

	@ApiModelProperty(value = "用户上期预存余额")
	private BigDecimal customerBalanceMoneyBefore;

	@ApiModelProperty(value = "用户付款金额")
	private BigDecimal customerPayMoney;

	@ApiModelProperty(value = "用户预存发生值")
	private BigDecimal customerBalanceMoneyHappen;

	@ApiModelProperty(value = "所缴欠费金额")
	private BigDecimal payTheArrearsMoney;

	@ApiModelProperty(value = "所缴违约金金额")
	private BigDecimal payTheLateFeeMoney;

	@ApiModelProperty(value = "用户本期预存余额")
	private BigDecimal customerBalanceMoneyAfter;

}

