package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterIndustry更新参数", description = "行业分类")
public class TenantMeterIndustryUpdateParam implements Serializable {

	private static final long serialVersionUID = 3714510261575511946L;

	@ApiModelProperty(value = "行业分类ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "名称")
	private String meterIndustryName;

	@ApiModelProperty(value = "父级ID")
	private String meterIndustryParentId;

	@ApiModelProperty(value = "结构化数据")
	private String meterIndustryData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

