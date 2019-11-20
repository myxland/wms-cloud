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
@ApiModel(value = "TenantRoleSystem对象", description = "角色模块")
public class TenantRoleSystemVo implements Serializable {

	private static final long serialVersionUID = 1301310877131139112L;

	@ApiModelProperty(value = "系统ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "角色编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long roleId;

	@ApiModelProperty(value = "模块编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long sysId;

	@ApiModelProperty(value = "模块名称")
	private String moduleName;

	@ApiModelProperty(value = "开放（1：开放；0：不开放）")
	private Integer roleSysOn;

}
