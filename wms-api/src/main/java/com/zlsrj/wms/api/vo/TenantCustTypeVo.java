package com.zlsrj.wms.api.vo;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCustType对象", description = "用户类型")
public class TenantCustTypeVo implements Serializable {

	private static final long serialVersionUID = 8781410121201311111L;

	@ApiModelProperty(value = "用户类型")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "用户类别名称")
	private String custTypeName;

}
