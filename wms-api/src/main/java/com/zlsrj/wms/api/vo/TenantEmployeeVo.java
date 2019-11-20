package com.zlsrj.wms.api.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantEmployee对象", description = "租户员工")
public class TenantEmployeeVo implements Serializable {

	private static final long serialVersionUID = 5310707155104911241L;

	@ApiModelProperty(value = "系统ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "员工名称")
	private String empName;

	@ApiModelProperty(value = "登录密码")
	private String empPassword;

	@ApiModelProperty(value = "员工部门")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long deptId;

	@ApiModelProperty(value = "可登录系统（1：可登录；0：不能登录）")
	private Integer loginOn;

	@ApiModelProperty(value = "员工状态（1：在职；2：离职；3：禁用）")
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
