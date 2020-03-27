package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "SystemDictionaries对象", description = "基础字典表")
public class SystemDictionariesVo implements Serializable {

	private static final long serialVersionUID = 7761559138091710106L;

	@ApiModelProperty(value = "字典ID")
	private String id;

	@ApiModelProperty(value = "字典编码")
	private String dictCode;

	@ApiModelProperty(value = "字典类型")
	private String dictType;

	@ApiModelProperty(value = "名称")
	private String dictName;

	@ApiModelProperty(value = "结构化数据")
	private String dictData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}
