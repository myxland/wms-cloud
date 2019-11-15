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
@ApiModel(value = "SystemDesign对象", description = "系统定义")
public class SystemDesignVo implements Serializable {

	private static final long serialVersionUID = 5771151026369871151L;

	@ApiModelProperty(value = "系统ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "依赖模块编码")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long relyId;

	@ApiModelProperty(value = "模块名称")
	private String moduleName;

	@ApiModelProperty(value = "开放对象（1：使用单位；2：供应单位；3：内部运营）")
	private Integer openTenantType;

	@ApiModelProperty(value = "运行环境（1：PC；2：移动端）")
	private Integer runEnvType;

	@ApiModelProperty(value = "价格政策（0：免费；1：按量付费；2：固定价格）")
	private Integer pricePolicyType;

	@ApiModelProperty(value = "计费周期（1：实时；2：按天；3：按月；4：按年）")
	private Integer billCycleType;

	@ApiModelProperty(value = "开放基础版（1：开放；0：不开放）")
	private Integer basicOn;

	@ApiModelProperty(value = "开放高级版（1：开放；0：不开放）")
	private Integer advanceOn;

	@ApiModelProperty(value = "开放旗舰版（1：开放；0：不开放）")
	private Integer ultimateOn;

	@ApiModelProperty(value = "功能发布（1：已发布；0：未发布）")
	private Integer moduleReleaseOn;

}
