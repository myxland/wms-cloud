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
@ApiModel(value = "TenantMeterSupplyArea对象", description = "供水区域")
public class TenantMeterSupplyAreaVo implements Serializable {

	private static final long serialVersionUID = 1511358115578031111L;

	@ApiModelProperty(value = "供水区域ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "名称")
	private String supplyAreaName;

	@ApiModelProperty(value = "父级ID")
	private String supplyAreaParentId;

	@ApiModelProperty(value = "结构化数据")
	private String supplyAreaData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "子级供水区域列表")
	private List<TenantMeterSupplyAreaVo> children;
	
	@ApiModelProperty(value = "是否包含子级供水区域")
	private boolean hasChildren;
	
}
