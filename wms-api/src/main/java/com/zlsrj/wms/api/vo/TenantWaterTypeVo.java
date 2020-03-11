package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.List;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantWaterType对象", description = "用水分类")
public class TenantWaterTypeVo implements Serializable {

	private static final long serialVersionUID = 6011721018121484133L;

	@ApiModelProperty(value = "用水类别ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "用水类别名称")
	private String waterTypeName;

	@ApiModelProperty(value = "上级用水类别编号")
	private String waterTypeParentId;

	@ApiModelProperty(value = "默认价格分类ID")
	private String defaultPriceTypeId;

	@ApiModelProperty(value = "子级用水分类列表")
	private List<TenantWaterTypeVo> children;
	
	@ApiModelProperty(value = "是否包含子级用水分类")
	private boolean hasChildren;
	
}
