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
@TableName("tenant_customer_type")
@ApiModel(value = "TenantCustomerType对象", description = "用户分类")
public class TenantCustomerType implements Serializable {

	private static final long serialVersionUID = 4315913410913901181L;

	@ApiModelProperty(value = "用户类别ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "用户类别名称")
	@TableField("customer_type_name")
	private String customerTypeName;

	@ApiModelProperty(value = "上级用户类别ID")
	@TableField("customer_type_parent_id")
	private String customerTypeParentId;


}
