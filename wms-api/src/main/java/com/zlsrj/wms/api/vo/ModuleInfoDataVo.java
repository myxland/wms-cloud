package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ModuleInfo对象", description = "模块信息")
public class ModuleInfoDataVo implements Serializable {

	private static final long serialVersionUID = 1980091093707015717L;

	@ApiModelProperty(value = "是否开通（1：开通，0：未开通）")
	private Integer isopen = 0;

	@ApiModelProperty(value = "服务模块ID")
	private String id;

	@ApiModelProperty(value = "服务模块名称")
	private String moduleName;

	@ApiModelProperty(value = "计费模式（1：默认开通；2：免费；3：按量付费；4：固定价格；5：阶梯价格）")
	private Integer billingMode;

	@ApiModelProperty(value = "计费周期（1：实时；2：按天；3：按月；4：按年）")
	private Integer billingCycle;

//	@ApiModelProperty(value = "开放基础版（1：开放；0：不开放）")
//	private Integer basicEditionOn;
//
//	@ApiModelProperty(value = "开放高级版（1：开放；0：不开放）")
//	private Integer advanceEditionOn;
//
//	@ApiModelProperty(value = "开放旗舰版（1：开放；0：不开放）")
//	private Integer ultimateEditionOn;
	
	@ApiModelProperty(value = "开通版本（1基础版/2高级版/3旗舰版）")
	private Integer moduleEdition;
	
	@ApiModelProperty(value = "开通时间")
	private Date moduleOpenTime;

}
