package com.zlsrj.wms.api.vo;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ModuleMenu对象", description = "模块菜单")
public class ModuleMenuDataVo implements Serializable {

	private static final long serialVersionUID = 7147504511413971029L;

	@ApiModelProperty(value = "ID")
	private String id;

	@ApiModelProperty(value = "菜单名称")
	private String menuName;

	@ApiModelProperty(value = "菜单排序")
	private Integer menuOrder;

	@ApiModelProperty(value = "菜单图标")
	private String menuIcon;

	@ApiModelProperty(value = "父菜单ID")
	private String menuParentId;

	@ApiModelProperty(value = "菜单链接地址")
	private String menuUrl;

}
