package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterMarketingArea查询参数", description = "营销机构")
public class TenantMeterMarketingAreaQueryParam implements Serializable {

	private static final long serialVersionUID = 7158126132470212522L;

	@ApiModelProperty(value = "营销机构ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "区域类型（0：内部机构；1：代收机构）")
	private Integer marketingAreaType;

	@ApiModelProperty(value = "名称")
	private String marketingAreaName;

	@ApiModelProperty(value = "父级ID")
	private String marketingAreaParentId;

	@ApiModelProperty(value = "结构化数据")
	private String marketingAreaData;

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

