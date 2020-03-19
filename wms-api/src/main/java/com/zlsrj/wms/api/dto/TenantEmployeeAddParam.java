package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.List;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantEmployee新增参数", description = "租户员工")
public class TenantEmployeeAddParam implements Serializable {

	private static final long serialVersionUID = 1121211772129111014L;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;
	
	@ApiModelProperty(value = "员工账号")
	private String employeeLoginid;

	@ApiModelProperty(value = "员工名称")
	private String employeeName;
	
	@ApiModelProperty(value = "登录密码",hidden=true)
	private String employeePassword;
	
	@ApiModelProperty(value = "员工所属部门ID")
	private String employeeDepartmentId;
	
	@ApiModelProperty(value = "可登录系统（1：可登录；0：不能登录）")
	private Integer employeeLoginOn;
	
	@ApiModelProperty(value = "员工状态（1：在职；2：离职；3：禁用）")
	private Integer employeeStatus;
	
	@ApiModelProperty(value = "员工手机号")
	private String employeeMobile;

	@ApiModelProperty(value = "员工邮箱")
	private String employeeEmail;
	
	@ApiModelProperty(value = "员工个人微信号")
	private String employeePersonalWx;

	@ApiModelProperty(value = "员工企业微信号")
	private String employeeEnterpriceWx;

	@ApiModelProperty(value = "钉钉号")
	private String employeeDingding;

	@ApiModelProperty(value = "操作员创建类型（1：平台默认创建；2：租户自建）")
	private Integer employeeCreateType;
	
	@ApiModelProperty(value = "工作岗位")
	private List<TenantRoleAddParam> tenantRoleList;
	
}

