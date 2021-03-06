package com.zlsrj.wms.api.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "SystemMenuDesign对象", description = "模块菜单")
public class SystemMenuDesignVo implements Serializable {

	private static final long serialVersionUID = 6161211931281270315L;

	@ApiModelProperty(value = "系统ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "父菜单编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long parentMenuId;

	@ApiModelProperty(value = "模块编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long sysId;

	@ApiModelProperty(value = "模块名称")
	private String moduleName;

	@ApiModelProperty(value = "菜单名称")
	private String menuName;

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

}
