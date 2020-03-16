package com.zlsrj.wms.api.vo;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantRole对象", description = "角色信息")
public class TenantRoleDataVo implements Serializable {


	private static final long serialVersionUID = 336163178223263334L;

	@ApiModelProperty(value = "工作岗位ID")
	private String id;

	@ApiModelProperty(value = "工作岗位名称")
	private String roleName;

	@ApiModelProperty(value = "是否包括该工作岗位（1：包含，2：不包含）")
	private Integer issel;

}
