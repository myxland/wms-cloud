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
@ApiModel(value = "TenantCustomerType对象", description = "用户分类")
public class TenantCustomerTypeVo implements Serializable {

	private static final long serialVersionUID = 1111151104131010035L;

	@ApiModelProperty(value = "用户类别ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "用户类别名称")
	private String customerTypeName;

	@ApiModelProperty(value = "上级用户类别ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long customerTypeParentId;

	@ApiModelProperty(value = "子级用户分类列表")
	private List<TenantCustomerTypeVo> children;
	
	@ApiModelProperty(value = "是否包含子级用户分类")
	private boolean hasChildren;
	
}
