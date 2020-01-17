package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCaliber查询参数", description = "水表口径")
public class TenantCaliberQueryParam implements Serializable {

	private static final long serialVersionUID = 1314941414101367378L;

	@ApiModelProperty(value = "口径ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "口径名称")
	private String caliberName;

}

