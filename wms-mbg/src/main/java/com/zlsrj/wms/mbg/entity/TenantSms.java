package com.zlsrj.wms.mbg.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("t_op_tenant_sms")
@ApiModel(value = "TenantSms对象", description = "租户短信配置")
public class TenantSms implements Serializable {

	private static final long serialVersionUID = 8791571412111111615L;

	@ApiModelProperty(value = "编号ID")
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	@ApiModelProperty(value = "租房编号")
	@TableField("tenant_id")
	private Long tenantId;

	@ApiModelProperty(value = "短信签名")
	@TableField("sms_signature")
	private String smsSignature;

	@ApiModelProperty(value = "短信SP服务商")
	@TableField("sms_sp_service")
	private String smsSpService;

	@ApiModelProperty(value = "是否启用抄表账单通知短信（启用/不启用）")
	@TableField("sms_read_send_on")
	private Integer smsReadSendOn;

	@ApiModelProperty(value = "是否启用缴费成功通知短信（启用/不启用）")
	@TableField("sms_charge_send_on")
	private Integer smsChargeSendOn;

	@ApiModelProperty(value = "是否启用欠费通知短信（启用/不启用）")
	@TableField("sms_qf_send_on")
	private Integer smsQfSendOn;

	@ApiModelProperty(value = "欠费通知短信发送间隔天数（欠费多少天后，催费多少天后仍然未缴）")
	@TableField("sms_qf_send_after_days")
	private Integer smsQfSendAfterDays;

}
