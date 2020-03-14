package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantEmployee更新参数", description = "租户员工")
public class TenantEmployeeUpdateParam implements Serializable {

	private static final long serialVersionUID = 1121211772129111014L;

	@ApiModelProperty(value = "租户ID")
	@JSONField(name="tenant_id")
	private String tenantId;
	
	@ApiModelProperty(value = "员工名称")
	@JSONField(name="employee_name")
	private String employeeName;
	
	@ApiModelProperty(value = "员工所属部门ID")
	@JSONField(name="employee_department_id")
	private String employeeDepartmentId;
	
	@ApiModelProperty(value = "可登录系统（1：可登录；0：不能登录）")
	@JSONField(name="employee_login_on")
	private Integer employeeLoginOn;
	
	@ApiModelProperty(value = "员工状态（1：在职；2：离职；3：禁用）")
	@JSONField(name="employee_status")
	private Integer employeeStatus;
	
	@ApiModelProperty(value = "员工手机号")
	@JSONField(name="employee_mobile")
	private String employeeMobile;

	@ApiModelProperty(value = "员工邮箱")
	@JSONField(name="employee_email")
	private String employeeEmail;
	
	@ApiModelProperty(value = "员工个人微信号")
	@JSONField(name="employee_personal_wx")
	private String employeePersonalWx;

	@ApiModelProperty(value = "员工企业微信号")
	@JSONField(name="employee_enterprice_wx")
	private String employeeEnterpriceWx;

	@ApiModelProperty(value = "钉钉号")
	@JSONField(name="employee_dingding")
	private String employeeDingding;

	@ApiModelProperty(value = "操作员创建类型（1：平台默认创建；2：租户自建）")
	@JSONField(name="employee_create_type")
	private Integer employeeCreateType;
	
	@ApiModelProperty(value = "工作岗位")
	@JSONField(name="tenant_role_list")
	private List<TenantRoleUpdateParam> tenantRoleList;
	
}

