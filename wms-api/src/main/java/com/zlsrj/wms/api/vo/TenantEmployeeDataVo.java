package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "登录成功返回对象", description = "租户员工")
public class TenantEmployeeDataVo implements Serializable {

	private static final long serialVersionUID = 5310707155104911241L;

	@ApiModelProperty(value = "员工ID")
	@JSONField(name="id")
	private String id;
	
	@ApiModelProperty(value = "员工名称")
	@JSONField(name="employee_name")
	private String employeeName;
	
	@ApiModelProperty(value = "员工所属部门ID")
	@JSONField(name="employee_department_id")
	private String employeeDepartmentId;
	
	@ApiModelProperty(value = "员工所属部门")
	@JSONField(name="employee_department_name")
	private String employeeDepartmentName;

	@ApiModelProperty(value = "租户ID")
	@JSONField(name="tenant_id")
	private String tenantId;
	
	@ApiModelProperty(value = "租户信息")
	@JSONField(name="tenant_info")
	private TenantInfoDataVo tenantInfo;

	@ApiModelProperty(value = "可登录系统（1：可登录；0：不能登录）")
	@JSONField(name="employee_login_on")
	private Integer employeeLoginOn;

	@ApiModelProperty(value = "员工状态（1：在职；2：离职；3：禁用）")
	@JSONField(name="employee_status")
	private Integer employeeStatus;
	
	@ApiModelProperty(value = "操作员创建类型（1：平台默认创建；2：租户自建）")
	@JSONField(name="employee_create_type")
	private Integer employeeCreateType;

	@ApiModelProperty(value = "菜单信息")
	@JSONField(name="module_menu_list")
	private List<ModuleMenuDataVo> moduleMenuList;
	
	@ApiModelProperty(value = "是否选中")
	@JSONField(name="issel")
	private Integer issel;
}
