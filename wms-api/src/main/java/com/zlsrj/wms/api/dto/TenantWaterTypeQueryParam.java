package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantWaterType查询参数", description = "用水分类")
public class TenantWaterTypeQueryParam implements Serializable {

	private static final long serialVersionUID = 1361410124141212701L;

	@ApiModelProperty(value = "用水类别ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "用水类别名称")
	private String waterTypeName;

	@ApiModelProperty(value = "上级用水类别编号")
	private Long waterTypeParentId;

	@ApiModelProperty(value = "默认价格分类ID")
	private Long defaultPriceTypeId;

	@ApiModelProperty(value = "父级ID")
	private Long parentId;
	
}

