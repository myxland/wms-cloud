package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterIndustry查询参数", description = "行业分类")
public class TenantMeterIndustryQueryParam implements Serializable {

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

	@ApiModelProperty(value = "数据新增时间开始")
	private Date addTimeStart;

	@ApiModelProperty(value = "数据新增时间结束")
	private Date addTimeEnd;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "数据修改时间开始")
	private Date updateTimeStart;

	@ApiModelProperty(value = "数据修改时间结束")
	private Date updateTimeEnd;

	@ApiModelProperty(value = "父级ID")
	private String parentId;
	
}

