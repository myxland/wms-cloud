package com.zlsrj.wms.api.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Vue Element Tree组件配置选项", description = "Tree组件配置选项")
public class VueElementTreeProps implements Serializable {
	
	private static final long serialVersionUID = 8701115220601778463L;
	
//	label	指定节点标签为节点对象的某个属性值	string, function(data, node)	—	—
//	children	指定子树为节点对象的某个属性值	string	—	—
//	disabled	指定节点选择框是否禁用为节点对象的某个属性值	boolean, function(data, node)	—	—
//	isLeaf	指定节点是否为叶子节点，仅在指定了 lazy 属性的情况下生效	boolean, function(data, node)	—	—
	private String label;
	private String children;
	private boolean disabled;
	private boolean isLeaf;
}
