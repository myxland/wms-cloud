package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ModuleMenu查询参数", description = "模块菜单")
public class ModuleMenuQueryParam implements Serializable {

	private static final long serialVersionUID = 8170611224391310108L;

	@ApiModelProperty(value = "系统ID")
	private Long id;

	@ApiModelProperty(value = "父菜单编号")
	private Long parentMenuId;

	@ApiModelProperty(value = "模块编号")
	private Long moduleId;

	@ApiModelProperty(value = "菜单名称")
	private String menuName;

	@ApiModelProperty(value = "菜单名称")
	private String menuNameLike;

	@ApiModelProperty(value = "菜单排序")
	private Integer menuOrder;

	@ApiModelProperty(value = "菜单图标")
	private String menuIcon;

	@ApiModelProperty(value = "开放基础版（1：开放；0：不开放）")
	private Integer basicOn;

	@ApiModelProperty(value = "开放高级版（1：开放；0：不开放）")
	private Integer advanceOn;

	@ApiModelProperty(value = "开放旗舰版（1：开放；0：不开放）")
	private Integer ultimateOn;

	@ApiModelProperty(value = "基础版链接地址")
	private String basicUrl;

	@ApiModelProperty(value = "高级版链接地址")
	private String advanceUrl;

	@ApiModelProperty(value = "旗舰版链接地址")
	private String ultimateUrl;

	@ApiModelProperty(value = "授权")
	private String perms;

	@ApiModelProperty(value = "菜单类型（0：目录；1：菜单；2：按钮）")
	private Integer menuType;

}

