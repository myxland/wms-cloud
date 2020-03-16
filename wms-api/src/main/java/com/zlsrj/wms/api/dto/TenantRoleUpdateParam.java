package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantRole更新参数", description = "角色信息")
public class TenantRoleUpdateParam implements Serializable {

	private static final long serialVersionUID = -6351616099914618162L;

	@ApiModelProperty(value = "工作岗位ID")
	@JSONField(name="id")
	private String id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(name="tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "工作岗位名称")
	@JSONField(name="role_name")
	private String roleName;

	@ApiModelProperty(value = "工作岗位说明")
	@JSONField(name="role_remark")
	private String roleRemark;

	@ApiModelProperty(value = "创建类型（1：平台默认创建；2：租户自建）")
	@JSONField(name="create_type")
	private Integer createType;
	
	@ApiModelProperty(value = "角色分配菜单")
	@JSONField(name="module_menu_list")
	private List<Map<String,String>> moduleMenuList;
	
	@ApiModelProperty(value = "角色分配员工")
	@JSONField(name="tenant_employee_list")
	private List<Map<String,String>> tenantEmployeeList;

}

