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
@ApiModel(value = "ModuleMenu对象", description = "模块菜单")
public class ModuleMenuVo implements Serializable {

	private static final long serialVersionUID = 7147504511413971029L;

	@ApiModelProperty(value = "菜单ID")
	@JSONField(name="id")
	private String id;

	@ApiModelProperty(value = "服务模块ID")
	@JSONField(name="module_id")
	private String moduleId;

	@ApiModelProperty(value = "服务模块ID")
	@JSONField(name="module_name")
	private String moduleName;

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

	@ApiModelProperty(value = "开放基础版（1：开放；0：不开放）")
	@JSONField(name="basic_edition_on")
	private Integer basicEditionOn;

	@ApiModelProperty(value = "开放高级版（1：开放；0：不开放）")
	@JSONField(name="advance_edition_on")
	private Integer advanceEditionOn;

	@ApiModelProperty(value = "开放旗舰版（1：开放；0：不开放）")
	@JSONField(name="ultimate_edition_on")
	private Integer ultimateEditionOn;

	@ApiModelProperty(value = "基础版链接地址")
	@JSONField(name="basic_url")
	private String basicUrl;

	@ApiModelProperty(value = "高级版链接地址")
	@JSONField(name="advance_url")
	private String advanceUrl;

	@ApiModelProperty(value = "旗舰版链接地址")
	@JSONField(name="ultimate_url")
	private String ultimateUrl;

	@ApiModelProperty(value = "子级菜单列表")
	@JSONField(name="children")
	private List<ModuleMenuVo> children;
	
	@ApiModelProperty(value = "是否包含子级菜单")
	@JSONField(name="has_children")
	private boolean hasChildren;
	
	@ApiModelProperty(value = "菜单链接地址")
	@JSONField(name="menu_url")
	private String menuUrl;
}
