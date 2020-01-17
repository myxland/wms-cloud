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
@ApiModel(value = "TenantWaterType对象", description = "用水分类")
public class TenantWaterTypeVo implements Serializable {

	private static final long serialVersionUID = 6011721018121484133L;

	@ApiModelProperty(value = "用水类别ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "用水类别名称")
	private String waterTypeName;

	@ApiModelProperty(value = "上级用水类别编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long waterTypeParentId;

	@ApiModelProperty(value = "默认价格分类ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long defaultPriceTypeId;

	@ApiModelProperty(value = "子级用水分类列表")
	private List<TenantWaterTypeVo> children;
	
	@ApiModelProperty(value = "是否包含子级用水分类")
	private boolean hasChildren;
	
}
