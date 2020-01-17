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
@TableName("tenant_config_sms")
@ApiModel(value = "TenantConfigSms对象", description = "短信参数设置")
public class TenantConfigSms implements Serializable {

	private static final long serialVersionUID = 1513315108713314105L;

	@ApiModelProperty(value = "短信配置ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "短信签名")
	@TableField("sms_signature")
	private String smsSignature;

	@ApiModelProperty(value = "开启出账短信通知（1：开启；0：不开启）")
	@TableField("sms_receivable_notice_on")
	private Integer smsReceivableNoticeOn;

	@ApiModelProperty(value = "开启付款短信通知（1：开启；0：不开启）")
	@TableField("sms_payment_notice_on")
	private Integer smsPaymentNoticeOn;

	@ApiModelProperty(value = "开启用户预存款变动短信通知（1：开启；0：不开启）")
	@TableField("sms_balance_money_change_notice_on")
	private Integer smsBalanceMoneyChangeNoticeOn;

	@ApiModelProperty(value = "开启欠费催缴短信通知（1：开启；0：不开启）")
	@TableField("sms_arrears_notice_on")
	private Integer smsArrearsNoticeOn;

	@ApiModelProperty(value = "出账短信通知模板")
	@TableField("sms_receivable_notice_template")
	private String smsReceivableNoticeTemplate;

	@ApiModelProperty(value = "付款短信通知模板")
	@TableField("sms_payment_notice_template")
	private String smsPaymentNoticeTemplate;

	@ApiModelProperty(value = "用户预存款变动短信通知模板")
	@TableField("sms_balance_money_change_notice_template")
	private String smsBalanceMoneyChangeNoticeTemplate;

	@ApiModelProperty(value = "欠费催缴短信通知模板")
	@TableField("sms_arrears_notice_template")
	private String smsArrearsNoticeTemplate;

	@ApiModelProperty(value = "进入催缴的欠费天数")
	@TableField("sms_arrears_days")
	private Integer smsArrearsDays;

	@ApiModelProperty(value = "每月多少号进行欠费催缴")
	@TableField("sms_arrears_notice_day")
	private Integer smsArrearsNoticeDay;

	@ApiModelProperty(value = "欠费催缴开始时间")
	@TableField("sms_arrears_notice_start_time")
	private Date smsArrearsNoticeStartTime;

	@ApiModelProperty(value = "欠费催缴结束时间")
	@TableField("sms_arrears_notice_end_time")
	private Date smsArrearsNoticeEndTime;


}
