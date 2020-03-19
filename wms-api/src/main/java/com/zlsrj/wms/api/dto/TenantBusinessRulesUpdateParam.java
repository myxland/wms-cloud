package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantBusinessRules更新参数", description = "业务规则")
public class TenantBusinessRulesUpdateParam implements Serializable {

	private static final long serialVersionUID = 8204422104210151215L;

	@ApiModelProperty(value = "业务规则ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "业务规则类型")
	private String rulesType;

	@ApiModelProperty(value = "结构化数据")
	private String rulesData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

