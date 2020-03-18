package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceRule新增参数", description = "计费规则")
public class TenantPriceRuleAddParam implements Serializable {

	private static final long serialVersionUID = 1311291113410118141L;

	@ApiModelProperty(value = "")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "计费方法名称")
	private String ruleName;

	@ApiModelProperty(value = "计费方法说明")
	private String ruleExplain;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

