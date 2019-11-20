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
@ApiModel(value = "TenantEmployeeRole对象", description = "员工角色")
public class TenantEmployeeRoleVo implements Serializable {

	private static final long serialVersionUID = 1222112151152314124L;

	@ApiModelProperty(value = "系统ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "员工编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long empId;

	@ApiModelProperty(value = "角色编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long roleId;

	@ApiModelProperty(value = "开放（1：开放；0：不开放）")
	private Integer empRoleOn;

}
