package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "SystemPrice对象", description = "模块各版本价格定义表")
public class SystemPriceVo implements Serializable {

	private static final long serialVersionUID = 6115991505411151321L;

	@ApiModelProperty(value = "系统ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "模块编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long sysId;

	@ApiModelProperty(value = "模块名称")
	private String moduleName;

	@ApiModelProperty(value = "模块版本（1：基础版；2：高级版；3：旗舰版）")
	private Integer sysEdition;

	@ApiModelProperty(value = "起始量")
	private Integer startNum;

	@ApiModelProperty(value = "终止量")
	private Integer endNum;

	@ApiModelProperty(value = "价格")
	private BigDecimal price;

}
