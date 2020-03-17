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
@ApiModel(value = "TenantMeterIndustry对象", description = "行业分类")
public class TenantMeterIndustryVo implements Serializable {

	private static final long serialVersionUID = 1613751310311836591L;

	@ApiModelProperty(value = "行业分类ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

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

	@ApiModelProperty(value = "子级行业分类列表")
	private List<TenantMeterIndustryVo> children;
	
	@ApiModelProperty(value = "是否包含子级行业分类")
	private boolean hasChildren;
	
}
