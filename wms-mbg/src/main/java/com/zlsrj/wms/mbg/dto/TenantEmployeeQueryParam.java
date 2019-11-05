package com.zlsrj.wms.mbg.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantEmployee查询参数", description = "租户员工")
public class TenantEmployeeQueryParam implements Serializable {

	private static final long serialVersionUID = 1121211772129111014L;

	@ApiModelProperty(value = "系统ID")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "员工名称")
	private String empName;

	@ApiModelProperty(value = "登录密码")
	private String empPassword;

	@ApiModelProperty(value = "员工部门")
	private Long deptId;

	@ApiModelProperty(value = "可登录系统（1可登录，0不能登录）")
	private Integer loginOn;

	@ApiModelProperty(value = "员工状态（在职/离职/禁用）")
	private Integer empStatus;

	@ApiModelProperty(value = "员工手机号")
	private String empMobile;

	@ApiModelProperty(value = "员工邮箱")
	private String empEmail;

	@ApiModelProperty(value = "员工个人微信号")
	private String empPersonalWx;

	@ApiModelProperty(value = "员工企业微信号")
	private String empEnterpriceWx;

}

