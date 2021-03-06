package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ModuleMenu对象", description = "模块菜单")
public class ModuleMenuVo implements Serializable {

	private static final long serialVersionUID = 7147504511413971029L;

	@ApiModelProperty(value = "")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "服务模块ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long moduleId;

	@ApiModelProperty(value = "服务模块ID")
	private String moduleName;

	@ApiModelProperty(value = "菜单名称")
	private String menuName;

	@ApiModelProperty(value = "菜单排序")
	private Integer menuOrder;

	@ApiModelProperty(value = "菜单图标")
	private String menuIcon;

	@ApiModelProperty(value = "父菜单ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long menuParentId;

	@ApiModelProperty(value = "开放基础版（1：开放；0：不开放）")
	private Integer basicEditionOn;

	@ApiModelProperty(value = "开放高级版（1：开放；0：不开放）")
	private Integer advanceEditionOn;

	@ApiModelProperty(value = "开放旗舰版（1：开放；0：不开放）")
	private Integer ultimateEditionOn;

	@ApiModelProperty(value = "基础版链接地址")
	private String basicUrl;

	@ApiModelProperty(value = "高级版链接地址")
	private String advanceUrl;

	@ApiModelProperty(value = "旗舰版链接地址")
	private String ultimateUrl;

	@ApiModelProperty(value = "子级菜单列表")
	private List<ModuleMenuVo> children;
	
	@ApiModelProperty(value = "是否包含子级菜单")
	private boolean hasChildren;
}
