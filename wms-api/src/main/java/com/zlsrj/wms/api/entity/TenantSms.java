package com.zlsrj.wms.api.entity;

import java.io.Serializable;

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
@TableName("t_op_tenant_sms")
@ApiModel(value = "TenantSms对象", description = "租户短信配置")
public class TenantSms implements Serializable {

	private static final long serialVersionUID = 8791571412111111615L;

	@ApiModelProperty(value = "编号ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "短信签名")
	@TableField("sms_signature")
	private String smsSignature;

	@ApiModelProperty(value = "短信SP服务商")
	@TableField("sms_sp_service")
	private String smsSpService;

	@ApiModelProperty(value = "是否启用抄表账单通知短信（1：启用；0：不启用）")
	@TableField("sms_read_send_on")
	private Integer smsReadSendOn;

	@ApiModelProperty(value = "是否启用缴费成功通知短信（1：启用；0：不启用）")
	@TableField("sms_charge_send_on")
	private Integer smsChargeSendOn;

	@ApiModelProperty(value = "是否启用欠费通知短信（1：启用；0：不启用）")
	@TableField("sms_qf_send_on")
	private Integer smsQfSendOn;

	@ApiModelProperty(value = "欠费通知短信发送间隔天数")
	@TableField("sms_qf_send_after_days")
	private Integer smsQfSendAfterDays;


}
