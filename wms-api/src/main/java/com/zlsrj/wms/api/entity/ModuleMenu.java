package com.zlsrj.wms.api.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("module_menu")
@ApiModel(value = "ModuleMenu对象", description = "模块菜单")
public class ModuleMenu implements Serializable {

	private static final long serialVersionUID = 1013136170121288761L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "服务模块ID")
	@TableField("module_id")
	private String moduleId;

	@ApiModelProperty(value = "菜单名称")
	@TableField("menu_name")
	private String menuName;

	@ApiModelProperty(value = "菜单排序")
	@TableField("menu_order")
	private Integer menuOrder;

	@ApiModelProperty(value = "菜单图标")
	@TableField("menu_icon")
	private String menuIcon;

	@ApiModelProperty(value = "父菜单ID")
	@TableField("menu_parent_id")
	private String menuParentId;

	@ApiModelProperty(value = "开放基础版（1：开放；0：不开放）")
	@TableField("basic_edition_on")
	private Integer basicEditionOn;

	@ApiModelProperty(value = "开放高级版（1：开放；0：不开放）")
	@TableField("advance_edition_on")
	private Integer advanceEditionOn;

	@ApiModelProperty(value = "开放旗舰版（1：开放；0：不开放）")
	@TableField("ultimate_edition_on")
	private Integer ultimateEditionOn;

	@ApiModelProperty(value = "基础版链接地址")
	@TableField("basic_url")
	private String basicUrl;

	@ApiModelProperty(value = "高级版链接地址")
	@TableField("advance_url")
	private String advanceUrl;

	@ApiModelProperty(value = "旗舰版链接地址")
	@TableField("ultimate_url")
	private String ultimateUrl;
	
	@ApiModelProperty(value = "菜单链接地址")
	@TableField(value="menu_url",exist = false)
	private String menuUrl;

}
