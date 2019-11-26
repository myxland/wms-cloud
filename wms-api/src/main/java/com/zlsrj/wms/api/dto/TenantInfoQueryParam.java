package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantInfo查询参数", description = "租户")
public class TenantInfoQueryParam implements Serializable {

	private static final long serialVersionUID = 5784121514767265731L;

	@ApiModelProperty(value = "租户编号")
	private Long id;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "租户名称")
	private String tenantNameLike;

	@ApiModelProperty(value = "显示名称")
	private String displayName;

	@ApiModelProperty(value = "显示名称")
	private String displayNameLike;

	@ApiModelProperty(value = "省")
	private String tenantProvince;

	@ApiModelProperty(value = "市")
	private String tenantCity;

	@ApiModelProperty(value = "区县")
	private String tenantArea;

	@ApiModelProperty(value = "联系地址")
	private String tenantAddress;

	@ApiModelProperty(value = "联系人")
	private String tenantContact;

	@ApiModelProperty(value = "手机号码")
	private String tenantMobile;

	@ApiModelProperty(value = "单位电话")
	private String tenantTel;

	@ApiModelProperty(value = "邮箱")
	private String tenantEmail;

	@ApiModelProperty(value = "QQ号码")
	private String tenantQq;

	@ApiModelProperty(value = "租户类型（1：使用单位；2：供应单位；3：内部运营）")
	private Integer tenantType;

	@ApiModelProperty(value = "租户状态（1：正式；2：试用）")
	private Integer tenantStatus;

	@ApiModelProperty(value = "注册时间")
	private Date regTime;

	@ApiModelProperty(value = "注册时间开始")
	private Date regTimeStart;

	@ApiModelProperty(value = "注册时间结束")
	private Date regTimeEnd;

	@ApiModelProperty(value = "到期日期")
	private Date endDate;

	@ApiModelProperty(value = "到期日期开始")
	private Date endDateStart;

	@ApiModelProperty(value = "到期日期结束")
	private Date endDateEnd;

}

