package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantRole新增参数", description = "角色信息")
public class TenantRoleAddParam implements Serializable {

	private static final long serialVersionUID = 2464644152131313511L;

	@ApiModelProperty(value = "工作岗位ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "工作岗位名称")
	private String roleName;

	@ApiModelProperty(value = "工作岗位说明")
	private String roleRemark;

	@ApiModelProperty(value = "创建类型（1：平台默认创建；2：租户自建）")
	private Integer createType;
	
	@ApiModelProperty(value = "角色分配菜单")
	private List<Map<String,String>> moduleMenuList;
	
	@ApiModelProperty(value = "角色分配员工")
	private List<Map<String,String>> tenantEmployeeList;

}

