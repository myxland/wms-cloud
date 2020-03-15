package com.zlsrj.wms.api.entity;

import java.io.Serializable;

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
@TableName("tenant_role_menu")
@ApiModel(value = "TenantRoleMenu对象", description = "角色菜单")
public class TenantRoleMenu implements Serializable {

	private static final long serialVersionUID = 3022094560428338638L;

	@ApiModelProperty(value = "ID")
	@TableId(value = "id")
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "角色ID")
	@TableField("role_id")
	private String roleId;

	@ApiModelProperty(value = "菜单ID")
	@TableField("menu_id")
	private String menuId;

}
