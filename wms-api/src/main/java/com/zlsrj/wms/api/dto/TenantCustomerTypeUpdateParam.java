package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCustomerType更新参数", description = "用户类别")
public class TenantCustomerTypeUpdateParam implements Serializable {

	private static final long serialVersionUID = 1515912158891441514L;

	@ApiModelProperty(value = "")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "用户分类名称")
	private String customerTypeName;

	@ApiModelProperty(value = "结构化数据")
	private String customerTypeData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

