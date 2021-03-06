package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantWaterArea对象", description = "供水区域")
public class TenantWaterAreaVo implements Serializable {

	private static final long serialVersionUID = 1233539401337715511L;

	@ApiModelProperty(value = "供水区域ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "供水区域名称")
	private String waterAreaName;

	@ApiModelProperty(value = "上级供水区域ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long waterAreaParentId;

	@ApiModelProperty(value = "子级供水区域列表")
	private List<TenantWaterAreaVo> children;
	
	@ApiModelProperty(value = "是否包含子级供水区域")
	private boolean hasChildren;
	
}
