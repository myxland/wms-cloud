package com.zlsrj.wms.mbg.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "SystemMenuDesign查询参数", description = "模块功能菜单定义表（定义所有模块的功能菜单）")
public class SystemMenuDesignQueryParam implements Serializable {

	private static final long serialVersionUID = 4714715231510115861L;

	@ApiModelProperty(value = "系统ID")
	private Long id;

	@ApiModelProperty(value = "父菜单编号")
	private Long parentMenuId;

	@ApiModelProperty(value = "模块编号")
	private Long sysId;

	@ApiModelProperty(value = "菜单名称")
	private String menuName;

	@ApiModelProperty(value = "菜单排序")
	private Integer menuOrder;

	@ApiModelProperty(value = "菜单图标")
	private String menuIcon;

	@ApiModelProperty(value = "开放基础版（1开放0不开放）")
	private Integer basicOn;

	@ApiModelProperty(value = "开放高级版（1开放0不开放）")
	private Integer advanceOn;

	@ApiModelProperty(value = "开放旗舰版（1开放0不开放）")
	private Integer ultimateOn;

	@ApiModelProperty(value = "基础版链接地址")
	private String basicUrl;

	@ApiModelProperty(value = "高级版链接地址")
	private String advanceUrl;

	@ApiModelProperty(value = "旗舰版链接地址")
	private String ultimateUrl;

}

