package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.Date;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCustomerLinkman对象", description = "用户联系人")
public class TenantCustomerLinkmanVo implements Serializable {

	private static final long serialVersionUID = 1313141413814315121L;

	@ApiModelProperty(value = "联系人ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "客户ID")
	private String customerId;

	@ApiModelProperty(value = "联系人姓名")
	private String linkmanName;

	@ApiModelProperty(value = "联系人地址")
	private String linkmanAddress;

	@ApiModelProperty(value = "主联系人（1：是；0：否）")
	private Integer linkmanMainOn;

	@ApiModelProperty(value = "性别（1：男；2：女）")
	private Integer linkmanSex;

	@ApiModelProperty(value = "出生日期")
	private Date linkmanBirthday;

	@ApiModelProperty(value = "手机号码")
	private String linkmanMobile;

	@ApiModelProperty(value = "固定电话号码")
	private String linkmanTel;

	@ApiModelProperty(value = "邮箱地址")
	private String linkmanEmail;

	@ApiModelProperty(value = "微信号")
	private String linkmanPersonalWx;

	@ApiModelProperty(value = "QQ号")
	private String linkmanQq;

	@ApiModelProperty(value = "备注")
	private String linkmanRemark;

}
