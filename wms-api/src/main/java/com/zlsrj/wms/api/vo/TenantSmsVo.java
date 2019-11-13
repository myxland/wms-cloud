package com.zlsrj.wms.api.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantSms对象", description = "租户短信配置")
public class TenantSmsVo implements Serializable {

	private static final long serialVersionUID = 5103410621067130796L;

	@ApiModelProperty(value = "编号ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "签名")
	private String smsSignature;

	@ApiModelProperty(value = "SP服务商")
	private String smsSpService;

	@ApiModelProperty(value = "抄表账单通知")
	private Integer smsReadSendOn;

	@ApiModelProperty(value = "缴费成功通知")
	private Integer smsChargeSendOn;

	@ApiModelProperty(value = "是否启用欠费通知短信（1：启用；0：不启用）")
	private Integer smsQfSendOn;

	@ApiModelProperty(value = "欠费通知短信发送间隔天数")
	private Integer smsQfSendAfterDays;

}
