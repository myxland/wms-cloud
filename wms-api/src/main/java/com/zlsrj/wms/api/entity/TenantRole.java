package com.zlsrj.wms.api.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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
@TableName("tenant_role")
@ApiModel(value = "TenantRole对象", description = "角色信息")
public class TenantRole implements Serializable {

	private static final long serialVersionUID = 1114108415611111574L;

	@ApiModelProperty(value = "工作岗位ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "工作岗位名称")
	@TableField("role_name")
	private String roleName;

	@ApiModelProperty(value = "工作岗位说明")
	@TableField("role_remark")
	private String roleRemark;

	@ApiModelProperty(value = "创建类型（1：平台默认创建；2：租户自建）")
	@TableField("create_type")
	private Integer createType;


}
