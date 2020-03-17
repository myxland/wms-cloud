package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterMarketingArea对象", description = "营销机构")
public class TenantMeterMarketingAreaVo implements Serializable {

	private static final long serialVersionUID = 4141311184418045111L;

	@ApiModelProperty(value = "营销机构ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

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

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "子级营销机构列表")
	private List<TenantMeterMarketingAreaVo> children;
	
	@ApiModelProperty(value = "是否包含子级营销机构")
	private boolean hasChildren;
	
}
