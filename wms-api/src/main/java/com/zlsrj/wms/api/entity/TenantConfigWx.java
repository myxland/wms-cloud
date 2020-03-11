package com.zlsrj.wms.api.entity;

import java.io.Serializable;
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
@TableName("tenant_config_wx")
@ApiModel(value = "TenantConfigWx对象", description = "微信参数设置")
public class TenantConfigWx implements Serializable {

	private static final long serialVersionUID = 1810430061515895212L;

	@ApiModelProperty(value = "微信配置ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "微信公众号APPID")
	@TableField("wx_appid")
	private String wxAppid;

	@ApiModelProperty(value = "微信公众号AppSecret")
	@TableField("wx_appsecret")
	private String wxAppsecret;

	@ApiModelProperty(value = "微信商户ID")
	@TableField("wx_account_id")
	private String wxAccountId;

	@ApiModelProperty(value = "微信商户API密钥")
	@TableField("wx_account_api_key")
	private String wxAccountApiKey;

	@ApiModelProperty(value = "微信公众号首页显示图片名称")
	@TableField("wx_title_picture_file_name")
	private String wxTitlePictureFileName;

	@ApiModelProperty(value = "微信公众号显示服务电话")
	@TableField("wx_service_tel")
	private String wxServiceTel;

	@ApiModelProperty(value = "开启出账微信通知（1：开启；0：不开启）")
	@TableField("wx_receivable_notice_on")
	private Integer wxReceivableNoticeOn;

	@ApiModelProperty(value = "开启付款微信通知（1：开启；0：不开启）")
	@TableField("wx_payment_notice_on")
	private Integer wxPaymentNoticeOn;

	@ApiModelProperty(value = "开启用户预存款变动微信通知（1：开启；0：不开启）")
	@TableField("wx_balance_money_change_notice_on")
	private Integer wxBalanceMoneyChangeNoticeOn;

	@ApiModelProperty(value = "开启欠费催缴微信通知（1：开启；0：不开启）")
	@TableField("wx_arrears_notice_on")
	private Integer wxArrearsNoticeOn;

	@ApiModelProperty(value = "出账微信通知模板")
	@TableField("wx_receivable_notice_template")
	private String wxReceivableNoticeTemplate;

	@ApiModelProperty(value = "付款微信通知模板")
	@TableField("wx_payment_notice_template")
	private String wxPaymentNoticeTemplate;

	@ApiModelProperty(value = "用户预存款变动微信通知模板")
	@TableField("wx_balance_money_change_notice_template")
	private String wxBalanceMoneyChangeNoticeTemplate;

	@ApiModelProperty(value = "欠费催缴微信通知模板")
	@TableField("wx_arrears_notice_template")
	private String wxArrearsNoticeTemplate;

	@ApiModelProperty(value = "进入催缴的欠费天数")
	@TableField("wx_arrears_days")
	private Integer wxArrearsDays;

	@ApiModelProperty(value = "每月多少号进行欠费催缴")
	@TableField("wx_arrears_notice_day")
	private Integer wxArrearsNoticeDay;

	@ApiModelProperty(value = "欠费催缴开始时间")
	@TableField("wx_arrears_notice_start_time")
	private Date wxArrearsNoticeStartTime;

	@ApiModelProperty(value = "欠费催缴结束时间")
	@TableField("wx_arrears_notice_end_time")
	private Date wxArrearsNoticeEndTime;


}
