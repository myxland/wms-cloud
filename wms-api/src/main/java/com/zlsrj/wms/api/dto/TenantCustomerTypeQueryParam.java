package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCustomerType查询参数", description = "用户分类")
public class TenantCustomerTypeQueryParam implements Serializable {

	private static final long serialVersionUID = 1515912158891441514L;

	@ApiModelProperty(value = "用户类别ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "用户类别名称")
	private String customerTypeName;

	@ApiModelProperty(value = "上级用户类别ID")
	private String customerTypeParentId;

	@ApiModelProperty(value = "父级ID")
	private String parentId;
	
}

