package com.zlsrj.wms.mbg.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("t_op_tenant_employee")
@ApiModel(value = "TenantEmployee对象", description = "租户员工")
public class TenantEmployee implements Serializable {

	private static final long serialVersionUID = 1451230121015853219L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private Integer tenantId;

	@ApiModelProperty(value = "员工名称")
	@TableField("emp_name")
	private String empName;

	@ApiModelProperty(value = "登录密码")
	@TableField("emp_password")
	private String empPassword;

	@ApiModelProperty(value = "员工部门")
	@TableField("dept_id")
	private Long deptId;

	@ApiModelProperty(value = "可登录系统（1可登录，0不能登录）")
	@TableField("login_on")
	private Integer loginOn;

	@ApiModelProperty(value = "员工状态（在职/离职/禁用）")
	@TableField("emp_status")
	private Integer empStatus;

	@ApiModelProperty(value = "员工手机号")
	@TableField("emp_mobile")
	private String empMobile;

	@ApiModelProperty(value = "员工邮箱")
	@TableField("emp_email")
	private String empEmail;

	@ApiModelProperty(value = "员工个人微信号")
	@TableField("emp_personal_wx")
	private String empPersonalWx;

	@ApiModelProperty(value = "员工企业微信号")
	@TableField("emp_enterprice_wx")
	private String empEnterpriceWx;

}
