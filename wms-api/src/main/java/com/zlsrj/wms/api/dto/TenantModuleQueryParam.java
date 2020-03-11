package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantModule查询参数", description = "租户开通模块清单")
public class TenantModuleQueryParam implements Serializable {

	private static final long serialVersionUID = 1151599401221220122L;

	@ApiModelProperty(value = "租户模块ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "模块ID")
	private String moduleId;

	@ApiModelProperty(value = "开通版本（1：基础版；2：高级版；3：旗舰版）")
	private Integer moduleEdition;

	@ApiModelProperty(value = "开通时间")
	private Date moduleOpenTime;

	@ApiModelProperty(value = "开通时间开始")
	private Date moduleOpenTimeStart;

	@ApiModelProperty(value = "开通时间结束")
	private Date moduleOpenTimeEnd;

}

