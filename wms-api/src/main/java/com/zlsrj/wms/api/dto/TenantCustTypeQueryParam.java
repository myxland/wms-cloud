package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCustType查询参数", description = "")
public class TenantCustTypeQueryParam implements Serializable {

	private static final long serialVersionUID = 4214114101312131615L;

	@ApiModelProperty(value = "用户类型")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "用户类别名称")
	private String custTypeName;

}

