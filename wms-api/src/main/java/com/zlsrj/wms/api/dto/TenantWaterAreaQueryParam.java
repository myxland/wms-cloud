package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantWaterArea查询参数", description = "供水区域")
public class TenantWaterAreaQueryParam implements Serializable {

	private static final long serialVersionUID = 1006121171211151480L;

	@ApiModelProperty(value = "供水区域ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "供水区域名称")
	private String waterAreaName;

	@ApiModelProperty(value = "上级供水区域ID")
	private String waterAreaParentId;

	@ApiModelProperty(value = "父级ID")
	private String parentId;
	
}

