package com.zlsrj.wms.api.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("tenant_water_type")
@ApiModel(value = "TenantWaterType对象", description = "用水分类")
public class TenantWaterType implements Serializable {

	private static final long serialVersionUID = 6741896111511451214L;

	@ApiModelProperty(value = "用水类别ID")
	@TableId(value = "id")
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "用水类别名称")
	@TableField("water_type_name")
	private String waterTypeName;

	@ApiModelProperty(value = "上级用水类别编号")
	@TableField("water_type_parent_id")
	private String waterTypeParentId;

	@ApiModelProperty(value = "默认价格分类ID")
	@TableField("default_price_type_id")
	private String defaultPriceTypeId;


}
