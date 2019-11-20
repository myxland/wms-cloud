package com.zlsrj.wms.api.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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
@TableName("t_op_tenant_employee")
@ApiModel(value = "TenantEmployee对象", description = "租户员工")
public class TenantEmployee implements Serializable {

	private static final long serialVersionUID = 1451230121015853219L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "员工名称")
	@TableField("emp_name")
	private String empName;

	@ApiModelProperty(value = "登录密码")
	@TableField("emp_password")
	private String empPassword;

	@ApiModelProperty(value = "员工部门")
	@TableField("dept_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long deptId;

	@ApiModelProperty(value = "可登录系统（1：可登录；0：不能登录）")
	@TableField("login_on")
	private Integer loginOn;

	@ApiModelProperty(value = "员工状态（1：在职；2：离职；3：禁用）")
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
