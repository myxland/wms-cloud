package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantWaterType查询参数", description = "用水类型")
public class TenantWaterTypeQueryParam implements Serializable {

	private static final long serialVersionUID = 1361410124141212701L;

	@ApiModelProperty(value = "系统ID")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "用水类别名称")
	private String waterTypeName;

}

