package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantConfigSms对象", description = "短信参数设置")
public class TenantConfigSmsVo implements Serializable {

	private static final long serialVersionUID = 1412077871314792101L;

	@ApiModelProperty(value = "短信配置ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "短信签名")
	private String smsSignature;

	@ApiModelProperty(value = "开启出账短信通知（1：开启；0：不开启）")
	private Integer smsReceivableNoticeOn;

	@ApiModelProperty(value = "开启付款短信通知（1：开启；0：不开启）")
	private Integer smsPaymentNoticeOn;

	@ApiModelProperty(value = "开启用户预存款变动短信通知（1：开启；0：不开启）")
	private Integer smsBalanceMoneyChangeNoticeOn;

	@ApiModelProperty(value = "开启欠费催缴短信通知（1：开启；0：不开启）")
	private Integer smsArrearsNoticeOn;

	@ApiModelProperty(value = "出账短信通知模板")
	private String smsReceivableNoticeTemplate;

	@ApiModelProperty(value = "付款短信通知模板")
	private String smsPaymentNoticeTemplate;

	@ApiModelProperty(value = "用户预存款变动短信通知模板")
	private String smsBalanceMoneyChangeNoticeTemplate;

	@ApiModelProperty(value = "欠费催缴短信通知模板")
	private String smsArrearsNoticeTemplate;

	@ApiModelProperty(value = "进入催缴的欠费天数")
	private Integer smsArrearsDays;

	@ApiModelProperty(value = "每月多少号进行欠费催缴")
	private Integer smsArrearsNoticeDay;

	@ApiModelProperty(value = "欠费催缴开始时间")
	private Date smsArrearsNoticeStartTime;

	@ApiModelProperty(value = "欠费催缴结束时间")
	private Date smsArrearsNoticeEndTime;

}
