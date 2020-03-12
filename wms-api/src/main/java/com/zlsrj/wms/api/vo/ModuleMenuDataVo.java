package com.zlsrj.wms.api.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

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
	@JSONField(name="id")
	private String id;

	@ApiModelProperty(value = "菜单名称")
	@JSONField(name="menu_name")
	private String menuName;

	@ApiModelProperty(value = "菜单排序")
	@JSONField(name="menu_order")
	private Integer menuOrder;

	@ApiModelProperty(value = "菜单图标")
	@JSONField(name="menu_icon")
	private String menuIcon;

	@ApiModelProperty(value = "父菜单ID")
	@JSONField(name="menu_parent_id")
	private String menuParentId;

	@ApiModelProperty(value = "菜单链接地址")
	@JSONField(name="menu_url")
	private String menuUrl;

}
