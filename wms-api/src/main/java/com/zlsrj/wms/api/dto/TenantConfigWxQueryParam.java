package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantConfigWx查询参数", description = "微信参数设置")
public class TenantConfigWxQueryParam implements Serializable {

	private static final long serialVersionUID = 5914814441412014131L;

	@ApiModelProperty(value = "微信配置ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "微信公众号APPID")
	private String wxAppid;

	@ApiModelProperty(value = "微信公众号AppSecret")
	private String wxAppsecret;

	@ApiModelProperty(value = "微信商户ID")
	private String wxAccountId;

	@ApiModelProperty(value = "微信商户API密钥")
	private String wxAccountApiKey;

	@ApiModelProperty(value = "微信公众号首页显示图片名称")
	private String wxTitlePictureFileName;

	@ApiModelProperty(value = "微信公众号显示服务电话")
	private String wxServiceTel;

	@ApiModelProperty(value = "开启出账微信通知（1：开启；0：不开启）")
	private Integer wxReceivableNoticeOn;

	@ApiModelProperty(value = "开启付款微信通知（1：开启；0：不开启）")
	private Integer wxPaymentNoticeOn;

	@ApiModelProperty(value = "开启用户预存款变动微信通知（1：开启；0：不开启）")
	private Integer wxBalanceMoneyChangeNoticeOn;

	@ApiModelProperty(value = "开启欠费催缴微信通知（1：开启；0：不开启）")
	private Integer wxArrearsNoticeOn;

	@ApiModelProperty(value = "出账微信通知模板")
	private String wxReceivableNoticeTemplate;

	@ApiModelProperty(value = "付款微信通知模板")
	private String wxPaymentNoticeTemplate;

	@ApiModelProperty(value = "用户预存款变动微信通知模板")
	private String wxBalanceMoneyChangeNoticeTemplate;

	@ApiModelProperty(value = "欠费催缴微信通知模板")
	private String wxArrearsNoticeTemplate;

	@ApiModelProperty(value = "进入催缴的欠费天数")
	private Integer wxArrearsDays;

	@ApiModelProperty(value = "每月多少号进行欠费催缴")
	private Integer wxArrearsNoticeDay;

	@ApiModelProperty(value = "欠费催缴开始时间")
	private Date wxArrearsNoticeStartTime;

	@ApiModelProperty(value = "欠费催缴开始时间开始")
	private Date wxArrearsNoticeStartTimeStart;

	@ApiModelProperty(value = "欠费催缴开始时间结束")
	private Date wxArrearsNoticeStartTimeEnd;

	@ApiModelProperty(value = "欠费催缴结束时间")
	private Date wxArrearsNoticeEndTime;

	@ApiModelProperty(value = "欠费催缴结束时间开始")
	private Date wxArrearsNoticeEndTimeStart;

	@ApiModelProperty(value = "欠费催缴结束时间结束")
	private Date wxArrearsNoticeEndTimeEnd;

}

