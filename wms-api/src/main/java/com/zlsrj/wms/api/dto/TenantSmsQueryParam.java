package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantSms查询参数", description = "租户短信配置")
public class TenantSmsQueryParam implements Serializable {

	private static final long serialVersionUID = 5166613121591486436L;

	@ApiModelProperty(value = "编号ID")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "短信签名")
	private String smsSignature;

	@ApiModelProperty(value = "短信SP服务商")
	private String smsSpService;

	@ApiModelProperty(value = "是否启用抄表账单通知短信（启用/不启用）")
	private Integer smsReadSendOn;

	@ApiModelProperty(value = "是否启用缴费成功通知短信（启用/不启用）")
	private Integer smsChargeSendOn;

	@ApiModelProperty(value = "是否启用欠费通知短信（启用/不启用）")
	private Integer smsQfSendOn;

	@ApiModelProperty(value = "欠费通知短信发送间隔天数（欠费多少天后，催费多少天后仍然未缴）")
	private Integer smsQfSendAfterDays;

}

