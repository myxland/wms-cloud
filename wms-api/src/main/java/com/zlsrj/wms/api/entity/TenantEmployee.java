package com.zlsrj.wms.api.entity;

import java.io.Serializable;

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
@TableName("tenant_employee")
@ApiModel(value = "TenantEmployee对象", description = "租户员工")
public class TenantEmployee implements Serializable {

	private static final long serialVersionUID = 1451230121015853219L;

	@ApiModelProperty(value = "员工ID")
	@TableId(value = "id", type = IdType.AUTO)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "员工名称")
	@TableField("employee_name")
	private String employeeName;

	@ApiModelProperty(value = "登录密码")
	@TableField("employee_password")
	private String employeePassword;

	@ApiModelProperty(value = "员工所属部门ID")
	@TableField("employee_department_id")
	private String employeeDepartmentId;

	@ApiModelProperty(value = "可登录系统（1：可登录；0：不能登录）")
	@TableField("employee_login_on")
	private Integer employeeLoginOn;

	@ApiModelProperty(value = "员工状态（1：在职；2：离职；3：禁用）")
	@TableField("employee_status")
	private Integer employeeStatus;

	@ApiModelProperty(value = "员工手机号")
	@TableField("employee_mobile")
	private String employeeMobile;

	@ApiModelProperty(value = "员工邮箱")
	@TableField("employee_email")
	private String employeeEmail;

	@ApiModelProperty(value = "员工个人微信号")
	@TableField("employee_personal_wx")
	private String employeePersonalWx;

	@ApiModelProperty(value = "员工企业微信号")
	@TableField("employee_enterprice_wx")
	private String employeeEnterpriceWx;

	@ApiModelProperty(value = "钉钉号")
	@TableField("employee_dingding")
	private String employeeDingding;

	@ApiModelProperty(value = "操作员创建类型（1：平台默认创建；2：租户自建）")
	@TableField("employee_create_type")
	private Integer employeeCreateType;


}
