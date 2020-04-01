package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCustomerContacts新增参数", description = "用户联系方式")
public class TenantCustomerContactsAddParam implements Serializable {

	private static final long serialVersionUID = 1001198131011221294L;

	@ApiModelProperty(value = "用户联系ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "用户ID")
	private String customerId;

	@ApiModelProperty(value = "用户号")
	private String customerCode;

	@ApiModelProperty(value = "联系人姓名")
	private String contactsName;

	@ApiModelProperty(value = "联系人地址")
	private String contactsAddress;

	@ApiModelProperty(value = "主联系人（1：是；0：否）")
	private Integer contactsMain;

	@ApiModelProperty(value = "性别（1：男；0：女）")
	private Integer contactsSex;

	@ApiModelProperty(value = "出生日期")
	private Date contactsBirthday;

	@ApiModelProperty(value = "手机号码")
	private String contactsMobile;

	@ApiModelProperty(value = "固定电话")
	private String contactsTel;

	@ApiModelProperty(value = "邮箱地址")
	private String contactsEmail;

	@ApiModelProperty(value = "微信号")
	private String contactsWx;

	@ApiModelProperty(value = "QQ号")
	private String contactsQq;

	@ApiModelProperty(value = "备注")
	private String contactsMemo;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

