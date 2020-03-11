package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "CustContact查询参数", description = "用户联系人")
public class CustContactQueryParam implements Serializable {

	private static final long serialVersionUID = 4681406131301212444L;

	@ApiModelProperty(value = "系统编号")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "用户编号")
	private String custId;

	@ApiModelProperty(value = "联系人姓名")
	private String contactName;

	@ApiModelProperty(value = "主联系人（1：是；0：否）")
	private Integer mainOn;

	@ApiModelProperty(value = "性别（1：男；2：女）")
	private Integer gender;

	@ApiModelProperty(value = "生日")
	private Date birthday;

	@ApiModelProperty(value = "生日开始")
	private Date birthdayStart;

	@ApiModelProperty(value = "生日结束")
	private Date birthdayEnd;

	@ApiModelProperty(value = "手机号码")
	private String mobile;

	@ApiModelProperty(value = "固定电话号码")
	private String tel;

	@ApiModelProperty(value = "邮箱地址")
	private String email;

	@ApiModelProperty(value = "微信号")
	private String personalWx;

	@ApiModelProperty(value = "QQ号")
	private String qq;

	@ApiModelProperty(value = "备注")
	private String remark;

}

