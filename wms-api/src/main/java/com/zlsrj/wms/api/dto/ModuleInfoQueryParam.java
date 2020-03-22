package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ModuleInfo查询参数", description = "模块信息")
public class ModuleInfoQueryParam implements Serializable {

	private static final long serialVersionUID = 8134426111269101561L;

	@ApiModelProperty(value = "服务模块ID")
	private String id;

	@ApiModelProperty(value = "服务模块名称")
	private String moduleName;

	@ApiModelProperty(value = "开放对象（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）")
	private Integer openTarget;

	@ApiModelProperty(value = "运行环境（1：PC端；2：移动端；3：微信端；4：支付宝端；5：API接口；6：自助终端）")
	private Integer runEnv;

	@ApiModelProperty(value = "依赖模块ID")
	private String relyModuleId;

	@ApiModelProperty(value = "计费模式（1：默认开通；2：免费；3：按量付费；4：固定价格；5：阶梯价格）")
	private Integer billingMode;

	@ApiModelProperty(value = "计费周期（1：实时；2：按天；3：按月；4：按年）")
	private Integer billingCycle;

	@ApiModelProperty(value = "开放基础版（1：开放；0：不开放）")
	private Integer basicEditionOn;

	@ApiModelProperty(value = "开放高级版（1：开放；0：不开放）")
	private Integer advanceEditionOn;

	@ApiModelProperty(value = "开放旗舰版（1：开放；0：不开放）")
	private Integer ultimateEditionOn;

	@ApiModelProperty(value = "服务发布状态（1：发布 ；0：未发布）")
	private Integer moduleOn;

}

