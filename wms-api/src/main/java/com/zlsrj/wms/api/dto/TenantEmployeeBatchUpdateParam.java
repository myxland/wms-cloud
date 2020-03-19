package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantEmployee批量更新参数", description = "租户员工")
public class TenantEmployeeBatchUpdateParam implements Serializable {

	private static final long serialVersionUID = 6606837648172160671L;

	@ApiModelProperty(value = "员工所属部门ID")
	private String employeeDepartmentId;
	
}

