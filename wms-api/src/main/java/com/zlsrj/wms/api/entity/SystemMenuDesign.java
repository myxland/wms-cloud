package com.zlsrj.wms.api.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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
@TableName("t_op_system_menu_design")
@ApiModel(value = "SystemMenuDesign对象", description = "模块菜单")
public class SystemMenuDesign implements Serializable {

	private static final long serialVersionUID = 2211770124104129501L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "父菜单编号")
	@TableField("parent_menu_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long parentMenuId;

	@ApiModelProperty(value = "模块编号")
	@TableField("sys_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long sysId;

	@ApiModelProperty(value = "菜单名称")
	@TableField("menu_name")
	private String menuName;

	@ApiModelProperty(value = "菜单排序")
	@TableField("menu_order")
	private Integer menuOrder;

	@ApiModelProperty(value = "菜单图标")
	@TableField("menu_icon")
	private String menuIcon;

	@ApiModelProperty(value = "开放基础版（1：开放；0：不开放）")
	@TableField("basic_on")
	private Integer basicOn;

	@ApiModelProperty(value = "开放高级版（1：开放；0：不开放）")
	@TableField("advance_on")
	private Integer advanceOn;

	@ApiModelProperty(value = "开放旗舰版（1：开放；0：不开放）")
	@TableField("ultimate_on")
	private Integer ultimateOn;

	@ApiModelProperty(value = "基础版链接地址")
	@TableField("basic_url")
	private String basicUrl;

	@ApiModelProperty(value = "高级版链接地址")
	@TableField("advance_url")
	private String advanceUrl;

	@ApiModelProperty(value = "旗舰版链接地址")
	@TableField("ultimate_url")
	private String ultimateUrl;


}
